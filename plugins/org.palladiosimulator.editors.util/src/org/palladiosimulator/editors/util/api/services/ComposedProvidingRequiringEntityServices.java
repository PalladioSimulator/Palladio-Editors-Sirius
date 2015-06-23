package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.editors.util.Activator;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;

import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import org.palladiosimulator.pcm.stochasticexpressions.parser.MyPCMStoExParser;

public class ComposedProvidingRequiringEntityServices {

	private static final String PARSER_ERROR_TITLE = "Error parsing expression";
	private static final String PARSER_ERROR_MESSAGE = "The entered stochastic expression is invalid.";

	public EObject removeATRole(final EObject assemblyContextObject,
			final EObject stereotypeObject) {
		if (assemblyContextObject == null
				|| !(assemblyContextObject instanceof AssemblyContext)
				|| stereotypeObject == null
				|| !(stereotypeObject instanceof Stereotype)) {
			System.out.println("False parameters");
			return assemblyContextObject;
		}
		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextObject;
		final Stereotype stereotype = (Stereotype) stereotypeObject;

		System.out.println("Called with " + assemblyContext + " and "
				+ stereotype);

		return assemblyContext;
	}

	/**
	 * Returns a list of {@link Stereotype}s that are associated with the given
	 * object, that comply with the Architectural Template`s convention, i.e.
	 * have a tagged value "roleURI" pointing to the correct repository element.
	 * 
	 * @param object
	 *            object to get roles for
	 * @return list of roles
	 */
	public List<StereotypeApplication> getRoles(final EObject object) {
		return StereotypeAPI
				.getStereotypeApplications(object)
				.stream()
				.filter(stereotypeApplication -> ArchitecturalTemplateAPI.isRole
						.test(stereotypeApplication.getStereotype()))
				.collect(Collectors.toList());
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
		return StereotypeAPI
				.getStereotypeApplications(object)
				.stream()
				.map(stereotypeApplication -> stereotypeApplication.getStereotype())
				.anyMatch(ArchitecturalTemplateAPI.isRole);
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
				.getEncapsulatedComponent__AssemblyContext())
				.getComponentParameterUsage_ImplementationComponentType();

		// combine the sets
		usages.addAll(assemblyContext
				.getConfigParameterUsages__AssemblyContext());
		componentVariableUsages
				.stream()
				.filter(componentVariableUsage -> !isOverridden(
						componentVariableUsage, assemblyContext))
				.forEach(
						componentVariableUsage -> usages
								.add(componentVariableUsage));

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
	public boolean isOverridden(final EObject variableUsageParam,
			final EObject assemblyContextParam) {
		if (!(variableUsageParam instanceof VariableUsage && assemblyContextParam instanceof AssemblyContext)) {
			return false; // FIXME: proper error handling
		}

		final String variableUsageReferenceName = ((VariableUsage) variableUsageParam)
				.getNamedReference__VariableUsage().getReferenceName();
		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextParam;

		return assemblyContext
				.getConfigParameterUsages__AssemblyContext()
				.stream()
				.anyMatch(
						assemblyContextVariableUsage -> assemblyContextVariableUsage
								.getNamedReference__VariableUsage()
								.getReferenceName()
								.equals(variableUsageReferenceName));
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
	public EObject editPCMRandomVariable(final EObject pcmRandomVariable,
			final String expressionString) {
		if (!(pcmRandomVariable instanceof PCMRandomVariable)) {
			return null;
		}
		if (!validExpression(expressionString)) {
			final ErrorDialog errorDialog = new ErrorDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					PARSER_ERROR_TITLE, null, new Status(IStatus.ERROR,
							Activator.PLUGIN_ID, PARSER_ERROR_MESSAGE),
					IStatus.ERROR);
			errorDialog.open();
			return null;
		}

		((PCMRandomVariable) pcmRandomVariable)
				.setSpecification(expressionString);

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
		final MyPCMStoExLexer lexer = new MyPCMStoExLexer(
				new ANTLRStringStream(expressionString));
		final MyPCMStoExParser parser = new MyPCMStoExParser(
				new CommonTokenStream(lexer));
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
	public EObject copyToAssemblyContext(final EObject variableUsageObject,
			final EObject assemblyContextObject) {
		if (!(variableUsageObject instanceof VariableUsage)
				|| !(assemblyContextObject instanceof AssemblyContext))
			return null;

		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextObject;

		final List<EObject> copiedVariableUsage = EMFCopyHelper
				.deepCopyEObjectList(Collections
						.singletonList(variableUsageObject));
		if (copiedVariableUsage.size() != 1
				|| !(copiedVariableUsage.get(0) instanceof VariableUsage))
			return null;

		final VariableUsage newVariableUsage = (VariableUsage) copiedVariableUsage
				.get(0);
		newVariableUsage.setAssemblyContext__VariableUsage(assemblyContext);
		assemblyContext.getConfigParameterUsages__AssemblyContext().add(
				newVariableUsage);

		return variableUsageObject;
	}
}
