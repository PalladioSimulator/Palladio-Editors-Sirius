package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.editors.util.Activator;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExParser;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.Role;

public class PCMServices {

    private static final String PARSER_ERROR_TITLE = "Error parsing expression";
    private static final String PARSER_ERROR_MESSAGE = "The entered stochastic expression is invalid.";
    private static final String PARAMETER_VALUE_LITERAL_DIALOG_TITLE = "Enter value literal";
    private static final String PARAMETER_VALUE_LITERAL_DIALOG_MESSAGE = "Please enter a literal to set the parameter";

    public PCMServices() {
        super();
    }

    /**
     * @see StereotypeAPI#getStereotypeApplications(EObject)
     */
    public Collection<StereotypeApplication> getStereotypeApplications(final EObject eObject) {
        return StereotypeAPI.getStereotypeApplications(eObject);
    }

    /**
     * @see ArchitecturalTemplateAPI#isRole(Stereotype)
     */
    public boolean isRole(final StereotypeApplication stereotypeApplication) {
        return ArchitecturalTemplateAPI.isRole(stereotypeApplication.getStereotype());
    }

    /**
     * @see ArchitecturalTemplateAPI#isSystemRole(Stereotype)
     */
    public boolean isSystemRole(final StereotypeApplication stereotypeApplication) {
        return ArchitecturalTemplateAPI.isSystemRole(stereotypeApplication.getStereotype());
    }

    /**
     * Queries an {@link String} literal from the user and uses
     * {@link #setParameterValue(EStructuralFeature, EObject, String)} to set the value.
     * 
     * @param parameter
     *            the parameter to set
     * @param owningEObject
     *            the object for which the feature will be set
     */
    public void queryAndSetParameterValue(final EStructuralFeature parameter, final EObject owningEObject) {
        final InputDialog inputDialog = new InputDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                PARAMETER_VALUE_LITERAL_DIALOG_TITLE, PARAMETER_VALUE_LITERAL_DIALOG_MESSAGE, null, null);
        if (inputDialog.open() == Dialog.OK) {
            setParameterValue(parameter, owningEObject, inputDialog.getValue());
        }
    }

    /**
     * Sets the value of the {@link EStructuralFeature} on the given {@link EObject} to the parsed
     * value literal.
     * 
     * @param parameter
     *            the feature to set
     * @param owningEObject
     *            the object for which the feature will be set
     * @param valueLiteral
     *            the literal representing the new value
     */
    public void setParameterValue(final EStructuralFeature parameter, final EObject owningEObject,
            final String valueLiteral) {
        final EClassifier eType = parameter.getEType();
        Object value = null;

        if (valueLiteral == null && eType != null) {
            value = parameter.isMany() ? null : eType.getDefaultValue();
        } else if (eType instanceof EDataType) {
            final EFactory factory = eType.getEPackage().getEFactoryInstance();
            final EDataType eDataType = (EDataType) eType;
            if (eDataType.isSerializable()) {
                try {
                    value = factory.createFromString(eDataType, valueLiteral);
                } catch (final Throwable e) {
                    // At development time, the real factory may not be
                    // available. Just return null.
                    //
                }
            }
        }
        owningEObject.eSet(parameter, value);
    }

    /**
     * Returns the {@link EStructuralFeature}s that define the {@link Stereotype}`s parameters.
     * 
     * @param stereotype
     *            the {@link Stereotype}
     * @return the parameters` features
     */
    public Collection<EStructuralFeature> getParameters(final Stereotype stereotype) {
        return StereotypeAPI.getParameters(stereotype);
    }

    /**
     * Returns the {@link StereotypeApplication}s that define a {@link Role} -Application on the
     * given {@link EObject}.
     * 
     * @param eObject
     *            object to get roles for
     * @return collection of role-StereotypeApplications
     * @see ArchitecturalTemplateAPI#getRoleApplications(EObject)
     */
    public Collection<StereotypeApplication> getRoleApplications(final EObject eObject) {
        return ArchitecturalTemplateAPI.getRoleApplications(eObject);
    }

    /**
     * Returns the {@link Profile}s on the given {@link EObject}.
     * 
     * @param eObject
     *            object to get profiles for
     * @return collection of Profiles
     * @see ArchitecturalTemplateAPI#getProfiles(EObject)
     */
    public Collection<Profile> getProfiles(final EObject eObject) {
        return ArchitecturalTemplateAPI.getProfiles(eObject);
    }

    /**
     * Returns, whether the given {@link StereotypableElement} has roles applied.
     * 
     * @param object
     *            object to test
     * @return indicator for applied roles
     */
    public boolean hasRoles(final EObject object) {
        return ArchitecturalTemplateAPI.hasRoles(object);
    }

    /**
     * Sets the given string as a specification on the {@link PCMRandomVariable} . For this it first
     * parses it to prevent any errors.
     *
     * @param pcmRandomVariable
     *            the random variable
     * @param expression
     *            the expression
     * @return the random variable
     */
    public EObject editPCMRandomVariable(final EObject pcmRandomVariable, final String expressionString) {
        if (!(pcmRandomVariable instanceof PCMRandomVariable)) {
            return null;
        }
        if (!validExpression(expressionString)) {
            final ErrorDialog errorDialog = new ErrorDialog(
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), PARSER_ERROR_TITLE, null,
                    new Status(IStatus.ERROR, Activator.PLUGIN_ID, PARSER_ERROR_MESSAGE), IStatus.ERROR);
            errorDialog.open();
            return null;
        }

        ((PCMRandomVariable) pcmRandomVariable).setSpecification(expressionString);

        return pcmRandomVariable;
    }

    /**
     * Parses an stochastic expression to determine whether it is valid.
     *
     * @param the
     *            expressionString
     * @return the validity
     */
    private boolean validExpression(final String expressionString) {
        final MyPCMStoExLexer lexer = new MyPCMStoExLexer(new ANTLRStringStream(expressionString));
        final MyPCMStoExParser parser = new MyPCMStoExParser(new CommonTokenStream(lexer));
        try {
            parser.expression();
        } catch (final RecognitionException e1) {
            return false;
        }
        if (lexer.hasErrors() || parser.hasErrors()) {
            return false;
        }
        return true;
    }

}