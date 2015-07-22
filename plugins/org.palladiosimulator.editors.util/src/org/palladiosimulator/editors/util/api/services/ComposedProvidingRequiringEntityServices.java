package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

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
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.editors.util.Activator;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExParser;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.Role;

public class ComposedProvidingRequiringEntityServices {

	private static final String PARSER_ERROR_TITLE = "Error parsing expression";
	private static final String PARSER_ERROR_MESSAGE = "The entered stochastic expression is invalid.";
	private static final String PARAMETER_VALUE_LITERAL_DIALOG_TITLE = "Enter value literal";
	private static final String PARAMETER_VALUE_LITERAL_DIALOG_MESSAGE = "Please enter a literal to set the parameter";
	
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
	 * {@link #setParameterValue(EStructuralFeature, EObject, String)} to set
	 * the value.
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
	 * Sets the value of the {@link EStructuralFeature} on the given
	 * {@link EObject} to the parsed value literal.
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
		EClassifier eType = parameter.getEType();
		Object value = null;

		if (valueLiteral == null && eType != null) {
			value = parameter.isMany() ? null : eType.getDefaultValue();
		} else if (eType instanceof EDataType) {
			EFactory factory = eType.getEPackage().getEFactoryInstance();
			EDataType eDataType = (EDataType) eType;
			if (eDataType.isSerializable()) {
				try {
					value = factory.createFromString(eDataType, valueLiteral);
				} catch (Throwable e) {
					// At development time, the real factory may not be
					// available. Just return null.
					//
				}
			}
		}
		owningEObject.eSet(parameter, value);
	}

	/**
	 * Returns the {@link EStructuralFeature}s that define the
	 * {@link Stereotype}`s parameters.
	 * 
	 * @param stereotype
	 *            the {@link Stereotype}
	 * @return the parameters` features
	 */
	public Collection<EStructuralFeature> getParameters(final Stereotype stereotype) {
		return StereotypeAPI.getParameters(stereotype);
	}

	/**
	 * Returns the {@link StereotypeApplication}s that define a {@link Role}
	 * -Application on the given {@link EObject}.
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
	 * Returns, whether the given {@link StereotypableElement} has roles
	 * applied.
	 * 
	 * @param object
	 *            object to test
	 * @return indicator for applied roles
	 */
	public boolean hasRoles(final EObject object) {
		return ArchitecturalTemplateAPI.hasRoles(object);
	}

	/**
	 * Returns a list containing all {@link VariableUsage}s associated with the
	 * {@link AssemblyContext}. This means all usages that are defined on the
	 * context itself and those of its encapsulated component which are not
	 * overwritten.
	 * 
	 * @param object
	 *            AssemblyContext
	 * @return associated VariableUsages
	 * @see #isOverridden(EObject, EObject)
	 */
	public Collection<EObject> getVariableUsages(final EObject object) {
		if (!(object instanceof AssemblyContext))
			return null; // return null to indicate invalid input

		final AssemblyContext assemblyContext = (AssemblyContext) object;
		final Collection<EObject> usages = new HashSet<>();

		// only ImplementationComponentTypes can have VariableUsages
		if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof ImplementationComponentType))
			return usages;

		final Collection<VariableUsage> componentVariableUsages = ((ImplementationComponentType) assemblyContext
				.getEncapsulatedComponent__AssemblyContext()).getComponentParameterUsage_ImplementationComponentType();

		// combine the sets
		usages.addAll(assemblyContext.getConfigParameterUsages__AssemblyContext());

		for (VariableUsage vu : componentVariableUsages) {
			if (!isOverridden(vu, assemblyContext)) {
				usages.add(vu);
			}
		}

		return usages;
	}

	/**
	 * Computes whether or not the given {@link AssemblyContext} has a
	 * {@link VariableUsage} associated that overrides, i.e. hides the given
	 * {@link VariableUsage}
	 * 
	 * If either the parameter is not instance of VariableUsage resp.
	 * AssemblyContext, the method will return false.
	 * 
	 * @param variableUsageParam
	 *            VariableUsage
	 * @param assemblyContextParam
	 *            AssemblyContext
	 * @return
	 * 
	 */
	public boolean isOverridden(final EObject variableUsageParam, final EObject assemblyContextParam) {
		if (!(variableUsageParam instanceof VariableUsage && assemblyContextParam instanceof AssemblyContext)) {
			return false; // FIXME: proper error handling
		}

		final String variableUsageReferenceName = ((VariableUsage) variableUsageParam)
				.getNamedReference__VariableUsage().getReferenceName();
		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextParam;

		for (VariableUsage vu : assemblyContext.getConfigParameterUsages__AssemblyContext()) {
			if (vu.getNamedReference__VariableUsage().getReferenceName().equals(variableUsageReferenceName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Sets the given string as a specification on the {@link PCMRandomVariable}
	 * . For this it first parses it to prevent any errors.
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

	/**
	 * Copies the {@link VariableUsage} to the {@link AssemblyContext}, i.e.
	 * 'instantiates' it. This method will return the VariableUsage or null if
	 * the parameters do not have the correct types.
	 *
	 * @param variableUsage
	 *            the VariableUsage to be copied
	 * @param assemblyContext
	 *            the target AssemblyContext
	 * @return the original VariableUsage
	 */
	public EObject copyToAssemblyContext(final EObject variableUsageObject, final EObject assemblyContextObject) {
		if (!(variableUsageObject instanceof VariableUsage) || !(assemblyContextObject instanceof AssemblyContext))
			return null;

		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextObject;

		final List<EObject> copiedVariableUsage = EMFCopyHelper
				.deepCopyEObjectList(Collections.singletonList(variableUsageObject));
		if (copiedVariableUsage.size() != 1 || !(copiedVariableUsage.get(0) instanceof VariableUsage))
			return null;

		final VariableUsage newVariableUsage = (VariableUsage) copiedVariableUsage.get(0);
		newVariableUsage.setAssemblyContext__VariableUsage(assemblyContext);
		assemblyContext.getConfigParameterUsages__AssemblyContext().add(newVariableUsage);

		return variableUsageObject;
	}
}
