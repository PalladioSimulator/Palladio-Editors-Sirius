package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;
import java.util.HashSet;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.util.Activator;

import de.uka.ipd.sdq.pcm.core.CoreFactory;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.impl.CoreFactoryImpl;
import de.uka.ipd.sdq.pcm.parameter.ParameterFactory;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.parameter.impl.ParameterFactoryImpl;
import de.uka.ipd.sdq.pcm.repository.ImplementationComponentType;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.MyPCMStoExParser;
import de.uka.ipd.sdq.stoex.StoexFactory;
import de.uka.ipd.sdq.stoex.impl.StoexFactoryImpl;

public class ComposedProvidingRequiringEntityServices {

	private static final String PARSER_ERROR_TITLE = "Error parsing expression";
	private static final String PARSER_ERROR_MESSAGE = "The entered stochastic expression is invalid.";

	/**
	 * Returns a list containing all {@link VariableUsage} associated with the
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
		;

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
	public EObject editPCMRandomVariable(final EObject pcmRandomVariable, final String expressionString) {
		if (!(pcmRandomVariable instanceof PCMRandomVariable)) {
			return null;
		}
		if (!validExpression(expressionString)) {
			final ErrorDialog errorDialog = new ErrorDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
					PARSER_ERROR_TITLE, 
					null, 
					new Status(IStatus.ERROR, Activator.PLUGIN_ID, PARSER_ERROR_MESSAGE), 
					IStatus.ERROR);
			errorDialog.open();
			return null;
		}

		// TODO: check whether the VariableUsage is defined on an Component. If so, duplicate it on the AssemblyContext

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
		final MyPCMStoExLexer lexer = new MyPCMStoExLexer(
				new ANTLRStringStream(expressionString));
		final MyPCMStoExParser parser = new MyPCMStoExParser(
				new CommonTokenStream(lexer));
		try {
			parser.expression();
		} catch (final RecognitionException e1) {
			return false; // TODO: return exception to be displayed in the error
							// message
		}
		if (lexer.hasErrors() || parser.hasErrors()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Copies the {@link VariableUsage} to the {@link AssemblyContext}, i.e. 'instantiates' it.
	 * It performs a deep copy so that the copy does not references to the old object.
	 * This method will return the VariableUsage or null if the parameters do not have the correct types.
	 * TODO: For now this only copies the reference name as well as the variable characterisations.
	 * TODO: There should be a copy-constructor to take over this task.
	 *
	 * @param variableUsageObject the VariableUsage to be copied
	 * @param assemblyContextObject the target AssemblyContext
	 * @return the original VariableUsage
	 */
	public EObject copyToAssemblyContext(final EObject variableUsageObject, final EObject assemblyContextObject) {
		if (!(variableUsageObject instanceof VariableUsage) || !(assemblyContextObject instanceof AssemblyContext))
			return null;

		final ParameterFactory parameterFactory = new ParameterFactoryImpl();
		final StoexFactory stoexFactory = new StoexFactoryImpl();
		final CoreFactory coreFactory = new CoreFactoryImpl();
		
		final VariableUsage variableUsage = (VariableUsage) variableUsageObject;
		final AssemblyContext assemblyContext = (AssemblyContext) assemblyContextObject;
		final VariableUsage newVariableUsage = parameterFactory.createVariableUsage();
		
		// Create and set the new NamedReference
		newVariableUsage.setNamedReference__VariableUsage(stoexFactory.createVariableReference());
		newVariableUsage.getNamedReference__VariableUsage().setReferenceName(variableUsage.getNamedReference__VariableUsage().getReferenceName());
		
		// Copy the VariableCharacterisations
		for (VariableCharacterisation variableCharacterisation : variableUsage.getVariableCharacterisation_VariableUsage()) {
			VariableCharacterisation newVariableCharacterisation = parameterFactory.createVariableCharacterisation();

			PCMRandomVariable randomVariable = coreFactory.createPCMRandomVariable();
			randomVariable.setSpecification(variableCharacterisation.getSpecification_VariableCharacterisation().getSpecification());
			newVariableCharacterisation.setSpecification_VariableCharacterisation(randomVariable);

			newVariableUsage.getVariableCharacterisation_VariableUsage().add(newVariableCharacterisation);
		}
		
		newVariableUsage.setAssemblyContext__VariableUsage(assemblyContext);
		assemblyContext.getConfigParameterUsages__AssemblyContext().add(newVariableUsage);
		
		return variableUsage;
	}
}
