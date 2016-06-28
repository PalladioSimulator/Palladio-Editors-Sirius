package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.editors.dialogs.Messages;
import org.palladiosimulator.editors.dialogs.datatype.PalladioDataTypeDialog;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * The Class place the validate methods for CompositeDataType and Parameters(Signature) editor area.
 * The methods are used for validating of InnerDeclaration of CompositeDataType and signature
 * parameters. Validating passes each Action (Delete, Up, Down) with call.
 * 
 * @author Roman Andrej
 */
public final class UpDownButtonsValidator {

    /** The singelton. */
    private static UpDownButtonsValidator singelton = null;

    /** The contents. */
    private CreateEditorContents contents;

    /**
     * Instantiates a new up down buttons validator.
     */
    private UpDownButtonsValidator() {
    }

    /**
     * Validate.
     * 
     * @param elementIndex
     *            the element index
     * @param maxIndex
     *            the max index
     */
    public void validate(int elementIndex, int maxIndex) {
        Assert.isNotNull(contents);

        contents.setDownItemsEnabled(true);
        contents.setUpItemsEnabled(true);

        if (elementIndex == 0) {
            contents.setUpItemsEnabled(false);
        }
        if (elementIndex == maxIndex - 1) {
            contents.setDownItemsEnabled(false);
        }
    }

    /**
     * Validate selection from table viewer.
     * 
     * @param selection
     *            the selection
     */
    public void validateSelection(Object selection) {
        if (selection == null) {
            contents.setDeleteItemsEnabled(false);
            contents.setDownItemsEnabled(false);
            contents.setUpItemsEnabled(false);
        } else if (selection instanceof Parameter) {
            Parameter parameter = (Parameter) selection;
            UpDownButtonsValidator.getSingelton().validateParameter(parameter);
            contents.setDeleteItemsEnabled(true);
        } else if (selection instanceof InnerDeclaration) {
            InnerDeclaration declaration = (InnerDeclaration) selection;
            UpDownButtonsValidator.getSingelton().validateInnerDeclaration(declaration);
            contents.setDeleteItemsEnabled(true);
        }
    }

    /**
     * Validate (Enabled/Unenabled) up-, down-button in the ParameterDialog. Call if selection
     * instanceof Parameter.
     * 
     * @param parameter
     *            the parameter
     */
    public void validateParameter(Parameter parameter) {
        EList<Parameter> parameters = ParametersUtil.getParametersOfSignature((Signature) parameter.eContainer());
        validate(parameters.indexOf(parameter), parameters.size());
    }

    /**
     * Validate (Enabled/Unenabled) up-, down-button in the DataTypeDialog. Call if selection
     * instanceof InnerDeclaration.
     * 
     * @param declaration
     *            the declaration
     */
    public void validateInnerDeclaration(InnerDeclaration declaration) {
        if (declaration.eContainer() instanceof CompositeDataType) {
            CompositeDataType dataType = (CompositeDataType) declaration.eContainer();
            EList<InnerDeclaration> declarations = dataType.getInnerDeclaration_CompositeDataType();
            validate(declarations.indexOf(declaration), declarations.size());
        }
    }

    /**
     * Validdate declaration inner data type.
     * 
     * @param declaration
     *            the declaration
     * @param dialog
     *            the dialog
     * @return true, if successful
     */
    public boolean validdateDeclarationInnerDataType(InnerDeclaration declaration, PalladioDataTypeDialog dialog) {
        if (declaration.getDatatype_InnerDeclaration() == null) {
            dialog.setErrorMessage(Messages.DataTypeDialog_ErrorMsgInnerName);
            return false;
        }
        if (declaration.getEntityName().equals("")) {
            dialog.setErrorMessage(Messages.DataTypeDialog_ErrorMsgInnerName);
            return false;
        }
        return true;
    }

    /**
     * Gets the singelton.
     * 
     * @return the singelton
     */
    public static UpDownButtonsValidator getSingelton() {
        if (singelton == null) {
            singelton = new UpDownButtonsValidator();
        }
        return singelton;
    }

    /**
     * Sets the contents.
     * 
     * @param contents
     *            the new contents
     */
    public void setContents(CreateEditorContents contents) {
        this.contents = contents;
    }
}
