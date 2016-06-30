/**
 * 
 */
package org.palladiosimulator.editors.dialogs.stoex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.Parameter;

// TODO: Auto-generated Javadoc
/**
 * The Class StoExCompletionProcessor.
 * 
 * @author Snowball
 */
public class StoExCompletionProcessor implements IContentAssistProcessor {

    /** The default characterisations. */
    private HashMap<String, String> defaultCharacterisations = new HashMap<String, String>();

    /** The parameter names. */
    private HashMap<String, String> parameterNames = new HashMap<String, String>();

    /** The template prefixes. */
    private String templatePrefixes = "+-*/%(?:";

    /** The template processor. */
    private StoExTemplateCompletionProcessor templateProcessor;

    /**
     * Instantiates a new sto ex completion processor.
     * 
     * @param context
     *            the context
     */
    public StoExCompletionProcessor(Parameter[] context) {
        templateProcessor = new StoExTemplateCompletionProcessor();

        defaultCharacterisations.put("BYTESIZE", "Characterise the memory footprint in bytes");
        defaultCharacterisations.put("NUMBER_OF_ELEMENTS",
                "Characterise the number of elements of a collection datatype");
        defaultCharacterisations.put("STRUCTURE", "Characterise the structure of a datastructure");
        defaultCharacterisations.put("VALUE", "Characterise the actual value of a variable");
        defaultCharacterisations.put("TYPE", "Characterise the type of a variable");

        for (int i = 0; i < context.length; i++) {
            String[] parameterPrefixes = getPrefixesFor(context[i]);
            for (String parameterPrefix : parameterPrefixes) {
                if (parameterPrefix.startsWith("RETURN")) {
                    parameterNames.put(parameterPrefix, "Call Result " + context[i].getParameterName());
                } else {
                    parameterNames.put(parameterPrefix, "Signature Parameter " + context[i].getParameterName());
                }
            }
        }

    }

    /**
     * Gets the prefixes for.
     * 
     * @param parameter
     *            the parameter
     * @return the prefixes for
     */
    private String[] getPrefixesFor(Parameter parameter) {
        ArrayList<String> prefixes = new ArrayList<String>();
        prefixes.add(parameter.getParameterName());
        appendDatatypePrefixes(prefixes, parameter.getParameterName(), parameter.getDataType__Parameter());
        return prefixes.toArray(new String[] {});
    }

    /**
     * Append datatype prefixes.
     * 
     * @param prefixes
     *            the prefixes
     * @param parameterName
     *            the parameter name
     * @param datatype__Parameter
     *            the datatype__ parameter
     */
    private void appendDatatypePrefixes(ArrayList<String> prefixes, String parameterName, DataType datatype__Parameter) {
        if (datatype__Parameter instanceof CollectionDataType) {
            prefixes.add(parameterName + ".INNER");
            appendDatatypePrefixes(prefixes, parameterName + ".INNER",
                    ((CollectionDataType) datatype__Parameter).getInnerType_CollectionDataType());
        } else if (datatype__Parameter instanceof CompositeDataType) {
            CompositeDataType cdt = (CompositeDataType) datatype__Parameter;
            for (InnerDeclaration inner : cdt.getInnerDeclaration_CompositeDataType()) {
                prefixes.add(parameterName + "." + inner.getEntityName());
                appendDatatypePrefixes(prefixes, parameterName + "." + inner.getEntityName(),
                        inner.getDatatype_InnerDeclaration());
            }
        }
    }

    /**
     * Compute completion proposals.
     * 
     * @param viewer
     *            the viewer
     * @param offset
     *            the offset
     * @return the i completion proposal[]
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org
     *      .eclipse.jface.text.ITextViewer, int)
     */
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        ArrayList<ICompletionProposal> resultList = new ArrayList<ICompletionProposal>();

        String currentText = viewer.getDocument().get();

        // compute variable characterisation completions (i.e., VALUE, TYPE, etc.)
        int lastDotIndex = currentText.substring(0, offset).lastIndexOf(".");
        if (isCharactersationCompletionApplicable(lastDotIndex, currentText)) {
            String typedFragment = currentText.substring(lastDotIndex + 1, offset);
            addCompletionProposalsString(resultList, lastDotIndex, typedFragment, defaultCharacterisations);
        }

        // compute parameter from context completions (i.e., current input parameters)
        int lastIndex = getLastIndexOfTemplatePrefix(offset, currentText);
        if (isStartOfAtom(lastIndex, currentText)) {
            String typedFragment = currentText.substring(lastIndex + 1, offset).trim();
            addCompletionProposalsString(resultList, lastIndex, typedFragment, parameterNames);
        }
        // compute template completions (i.e., IntPMF, DoublePDF, etc.)
        if (isStartOfAtom(lastIndex, currentText)) {
            String typedFragment = currentText.substring(lastIndex + 1, offset).trim();
            for (ICompletionProposal p : templateProcessor.computeCompletionProposals(viewer, offset)) {
                if (p.getDisplayString().toUpperCase().startsWith(typedFragment.toUpperCase())) {
                    resultList.add(p);
                }
            }
        }

        return resultList.toArray(new ICompletionProposal[] {});
    }

    /**
     * Checks, whether the user is characterising a variable (i.e., typed a dot).
     * 
     * @param offset
     *            the offset
     * @param currentText
     *            the current text
     * @return true, if is charactersation completion applicable
     */
    private boolean isCharactersationCompletionApplicable(int offset, String currentText) {
        // Is letter or underscore
        if (offset - 1 >= 0 && offset - 1 < currentText.length() && isIDChar(currentText.charAt(offset - 1), 0)) {
            return true;
        } else if (offset - 1 >= 0 && offset - 1 < currentText.length() && isIDChar(currentText.charAt(offset - 1), 1)) {
            // Backtrace till we find an IDChar that is no DIGIT
            int i = 2;
            while (offset - i >= 0 && !isIDChar(currentText.charAt(offset - i), 0)
                    && isIDChar(currentText.charAt(offset - i), 1)) {
                i++;
            }
            return offset - i >= 0 && isIDChar(currentText.charAt(offset - i), 0);
        }

        return false;
    }

    /**
     * Checks, whether user started to type a atom.
     * 
     * @param offset
     *            the offset
     * @param currentText
     *            the current text
     * @return true, if is start of atom
     */
    private boolean isStartOfAtom(int offset, String currentText) {
        if (offset + 1 < currentText.length() // cursor is not at last character
                && offset + 1 >= 0) { // cursor is not at first character
            currentText = currentText.substring(offset + 1); // cut of everything before cursor
        }

        String trimText = currentText.trim();

        if (trimText.equals("")) {
            // only whitespace after offset
            return true;
        }

        char lastChar = trimText.charAt(trimText.length() - 1);
        if (templatePrefixes.indexOf(lastChar) >= 0) {
            // last character before current offset is one of the template prefixes
            return true;
        }

        boolean hasIDChars = true;
        for (int i = 0; i < trimText.length(); i++) {
            char c = trimText.charAt(i);
            if (!isIDChar(c, i)) {
                hasIDChars = false;
            }
        }
        return hasIDChars;

    }

    // As defined in the ANTLR grammar
    /**
     * Checks if is iD char.
     * 
     * @param c
     *            the c
     * @param index
     *            the index
     * @return true, if is iD char
     */
    private static boolean isIDChar(char c, int index) {
        return Character.isLetter(c) || c == '.' || c == '_' || (index > 0 && Character.isDigit(c));
    }

    /**
     * Gets the last index of template prefix.
     * 
     * @param offset
     *            the offset
     * @param currentText
     *            the current text
     * @return the last index of template prefix
     */
    private int getLastIndexOfTemplatePrefix(int offset, String currentText) {
        int lastIndex = -1;
        String templatePrefixesAndWS = templatePrefixes + " ";
        for (int i = 0; i < templatePrefixesAndWS.length(); i++) {
            int newLastIndex = currentText.substring(0, offset).lastIndexOf(templatePrefixesAndWS.charAt(i));
            if (newLastIndex > lastIndex) {
                lastIndex = newLastIndex;
            }
        }
        return lastIndex;
    }

    /**
     * Adds the completion proposals string.
     * 
     * @param resultList
     *            the result list
     * @param lastIndex
     *            the last index
     * @param typedFragment
     *            the typed fragment
     * @param completions
     *            the completions
     */
    private void addCompletionProposalsString(ArrayList<ICompletionProposal> resultList, int lastIndex,
            String typedFragment, HashMap<String, String> completions) {
        for (Entry<String, String> entry : completions.entrySet()) {
            String completion = entry.getKey();
            String description = entry.getValue();

            if (completion.toUpperCase().startsWith(typedFragment.toUpperCase())) {
                IContextInformation info = new ContextInformation(completion, description); //$NON-NLS-1$
                resultList.add(new CompletionProposal(completion, lastIndex + 1, typedFragment.length(), completion
                        .length(), null, completion + " - " + description, info, description)); //$NON-NLS-1$
            }
        }
    }

    /**
     * Gets the completion proposal auto activation characters.
     * 
     * @return the completion proposal auto activation characters
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
     *      getCompletionProposalAutoActivationCharacters()
     */
    public char[] getCompletionProposalAutoActivationCharacters() {
        ArrayList<Character> result = new ArrayList<Character>();
        for (String parameterName : parameterNames.keySet()) {
            result.add(parameterName.charAt(0));
        }
        for (int i = 0; i < templatePrefixes.length(); i++) {
            result.add(templatePrefixes.charAt(i));
        }
        result.add('.');
        char[] realResult = new char[result.size()];
        for (int i = 0; i < result.size(); i++) {
            realResult[i] = result.get(i);
        }
        return realResult;
    }

    /**
     * Compute context information.
     * 
     * @param viewer
     *            the viewer
     * @param offset
     *            the offset
     * @return the i context information[]
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org
     *      .eclipse.jface.text.ITextViewer, int)
     */
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return null;
    }

    /**
     * Gets the context information auto activation characters.
     * 
     * @return the context information auto activation characters
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#
     *      getContextInformationAutoActivationCharacters()
     */
    public char[] getContextInformationAutoActivationCharacters() {
        return null;
    }

    /**
     * Gets the context information validator.
     * 
     * @return the context information validator
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    public IContextInformationValidator getContextInformationValidator() {
        return null;
    }

    /**
     * Gets the error message.
     * 
     * @return the error message
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    public String getErrorMessage() {
        return "No proposals available";
    }
}
