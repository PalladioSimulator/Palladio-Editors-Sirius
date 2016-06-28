package org.palladiosimulator.editors.dialogs.stoex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExParser;
import org.palladiosimulator.pcm.stochasticexpressions.parser.PCMStoExLexer;

import de.uka.ipd.sdq.errorhandling.IIssue;
import de.uka.ipd.sdq.stoex.Expression;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.exceptions.ExpectedTypeMismatchIssue;
import de.uka.ipd.sdq.stoex.analyser.visitors.ExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.NonProbabilisticExpressionInferTypeVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeCheckVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
/**
 * The Class StochasticExpressionEditDialog.
 */
public class StochasticExpressionEditDialog extends AbstractGrammerBasedEditDialog {

    /** The expected type. */
    private final TypeEnum expectedType;

    /**
     * Instantiates a new stochastic expression edit dialog.
     * 
     * @param parent
     *            the parent
     * @param expectedType
     *            the expected type
     */
    public StochasticExpressionEditDialog(final Shell parent, final TypeEnum expectedType) {
        super(parent);
        this.expectedType = expectedType;
    }

    /**
     * Instantiates a new stochastic expression edit dialog.
     * 
     * @param parent
     *            the parent
     * @param expectedType
     *            the expected type
     * @param context
     *            the context
     */
    public StochasticExpressionEditDialog(final Shell parent, final TypeEnum expectedType, final Parameter[] context) {
        super(parent, context);
        this.expectedType = expectedType;
    }

    /**
     * Instantiates a new stochastic expression edit dialog.
     * 
     * @param parent
     *            The parent shell
     * @param expectedType
     *            The type of the expression to be entered
     * @param contextObject
     *            An EObject from which a parameter context is derived by searching for its parent
     *            SEFF and taking this SEFFs signature into account
     */
    public StochasticExpressionEditDialog(final Shell parent, final TypeEnum expectedType,
            final EObject contextObject) {
        super(parent);
        this.expectedType = expectedType;
        this.context = getContext(contextObject);
    }

    /**
     * Sets the initial expression.
     * 
     * @param ex
     *            the new initial expression
     */
    public void setInitialExpression(final RandomVariable ex) {
        this.newText = ex.getSpecification();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getResult()
     */
    @Override
    public Expression getResult() {
        return (Expression) super.getResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getResultText()
     */
    @Override
    public String getResultText() {
        return super.getResultText();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getInitialText()
     */
    @Override
    protected String getInitialText() {
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getLexer(java.lang.
     * String)
     */
    @Override
    protected Lexer getLexer(final String text) {
        return new MyPCMStoExLexer(new ANTLRStringStream(text));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getTitle()
     */
    @Override
    protected String getTitle() {
        return "Edit stochastic expression";
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#parse(org.antlr.
     * runtime.Lexer )
     */
    @Override
    protected EObject parse(final Lexer lexer) throws RecognitionException, StoExParserException {
        final MyPCMStoExParser parser = new MyPCMStoExParser(new CommonTokenStream(lexer));
        final EObject result = parser.expression();
        final ArrayList<IIssue> errorList = new ArrayList<IIssue>();
        errorList.addAll(((MyPCMStoExLexer) lexer).getErrors());
        errorList.addAll(parser.getErrors());

        if (errorList.size() == 0) {
            final NonProbabilisticExpressionInferTypeVisitor typeVisitor = new NonProbabilisticExpressionInferTypeVisitor();
            typeVisitor.doSwitch(result);
            errorList.addAll(checkTypes(result, typeVisitor));
            errorList.addAll(assertType(result, typeVisitor, this.expectedType));
        }
        if (errorList.size() > 0) {
            throw new StoExParserException(errorList);
        }
        return result;
    }

    /**
     * Assert type.
     * 
     * @param result
     *            the result
     * @param typeVisitor
     *            the type visitor
     * @param expectedType
     *            the expected type
     * @return the collection<? extends i issue>
     */
    private Collection<? extends IIssue> assertType(final EObject result, final ExpressionInferTypeVisitor typeVisitor,
            final TypeEnum expectedType) {
        if (!TypeCheckVisitor.typesCompatible(expectedType, typeVisitor.getType((Expression) result))) {
            return Collections.singletonList(
                    new ExpectedTypeMismatchIssue(expectedType, typeVisitor.getType((Expression) result)));
        }
        return Collections.emptyList();
    }

    /**
     * Check types.
     * 
     * @param result
     *            the result
     * @param typeVisitor
     *            the type visitor
     * @return the collection
     */
    private Collection<IIssue> checkTypes(final EObject result,
            final NonProbabilisticExpressionInferTypeVisitor typeVisitor) {
        final TypeCheckVisitor typeChecker = new TypeCheckVisitor(typeVisitor);
        typeChecker.doSwitch(result);
        final TreeIterator<EObject> iterator = result.eAllContents();
        for (; iterator.hasNext();) {
            final EObject treeNode = iterator.next();
            typeChecker.doSwitch(treeNode);
        }
        return typeChecker.getIssues();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getLexerClass()
     */
    @Override
    protected Class<PCMStoExLexer> getLexerClass() {
        return PCMStoExLexer.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.dialogs.stoex.AbstractGrammerBasedEditDialog#getTokenMapper()
     */
    @Override
    protected ITokenMapper getTokenMapper() {
        return new StoExTokenMapper();
    }

    /**
     * Get the type of an variable characterisation.
     * 
     * @param ch
     *            The characterisation to check.
     * @return INT for ByteSize and number of elements, ANY for all others.
     */
    public static TypeEnum getTypeFromVariableCharacterisation(final VariableCharacterisation ch) {
        switch (ch.getType()) {
        case BYTESIZE:
        case NUMBER_OF_ELEMENTS:
            return TypeEnum.INT;
        default:
            // unspecified
        }
        return TypeEnum.ANY;
    }

    /**
     * Get the context the expression edit dialog is working on. This provides access to the
     * surrounding seff and the described signature. It is used to access provided variables etc.
     * 
     * @param rv
     *            the rv
     * @return The parameters found in the context.
     */
    private Parameter[] getContext(final EObject rv) {
        Parameter[] parameters = new Parameter[] {};

        final ResourceDemandingSEFF seff = getSEFF(rv);
        if (seff != null && seff.getDescribedService__SEFF() != null) {

            // if the seff is about an operation signature
            if (seff.getDescribedService__SEFF() instanceof OperationSignature) {
                final OperationSignature signature = (OperationSignature) seff.getDescribedService__SEFF();
                parameters = signature.getParameters__OperationSignature().toArray(parameters);

                // if the seff is an handler for an EventType
            } else if (seff.getDescribedService__SEFF() instanceof EventType) {
                final EventType eventType = (EventType) seff.getDescribedService__SEFF();
                if (eventType.getParameter__EventType() != null) {
                    parameters = new Parameter[] { eventType.getParameter__EventType() };
                }
            }

            // if the actual context is an ExternalCallAction, that has a return value other than
            // void
            // also provide the return parameter in the code complition
            final ExternalCallAction eca = getParentCallAction(rv);
            if (eca != null && isOutputCharacterisation(eca, rv) && eca.getCalledService_ExternalService() != null
                    && eca.getCalledService_ExternalService().getReturnType__OperationSignature() != null) {
                final Parameter[] parametersWithReturn = new Parameter[parameters.length + 1];
                System.arraycopy(parameters, 0, parametersWithReturn, 0, parameters.length);
                parametersWithReturn[parameters.length] = RepositoryFactory.eINSTANCE.createParameter();
                parametersWithReturn[parameters.length].setDataType__Parameter(
                        eca.getCalledService_ExternalService().getReturnType__OperationSignature());
                parametersWithReturn[parameters.length].setParameterName("RETURN");
                parameters = parametersWithReturn;
            }
        }

        return parameters;
    }

    /**
     * Checks if is output characterisation.
     * 
     * @param eca
     *            the eca
     * @param rv
     *            the rv
     * @return true, if is output characterisation
     */
    private boolean isOutputCharacterisation(final ExternalCallAction eca, final EObject rv) {
        for (final VariableUsage vu : eca.getReturnVariableUsage__CallReturnAction()) {
            if (vu.getVariableCharacterisation_VariableUsage().contains(rv)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get the RessourceDemandingSeff an object is included in.
     * 
     * @param a
     *            The object to get the RDSEFF for.
     * @return The found ressource demanding seff or null if there is none.
     */
    private ResourceDemandingSEFF getSEFF(final EObject a) {
        EObject container = a;
        while (container != null && !(container instanceof ResourceDemandingSEFF)) {
            container = container.eContainer();
        }
        if (container == null || !(container instanceof ResourceDemandingSEFF)) {
            return null;
        }
        final ResourceDemandingSEFF seff = (ResourceDemandingSEFF) container;
        return seff;
    }

    /**
     * Check if the actual container of an object is an external call action. If this is the case,
     * return the action, otherwise null.
     * 
     * @param a
     *            The object to check the container for.
     * @return The ExternalCallAction container or null if there is none.
     */
    private ExternalCallAction getParentCallAction(final EObject a) {
        EObject container = a;
        while (!(container instanceof ResourceDemandingSEFF) && !(container instanceof ExternalCallAction)) {
            container = container.eContainer();
        }
        if (!(container instanceof ExternalCallAction)) {
            return null;
        }
        final ExternalCallAction call = (ExternalCallAction) container;
        return call;
    }

}
