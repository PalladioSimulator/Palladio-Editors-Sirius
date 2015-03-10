package org.palladiosimulator.editors.util.api.services;

import java.awt.Component;
import java.util.Collection;
import java.util.HashSet;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.repository.ImplementationComponentType;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.MyPCMStoExLexer;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.MyPCMStoExParser;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.PCMStoExLexer;
import de.uka.ipd.sdq.pcm.stochasticexpressions.parser.PCMStoExParser;
import de.uka.ipd.sdq.stoex.Expression;

public class ComposedProvidingRequiringEntityServices {

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
	public Collection<EObject> getVariableUsages(EObject object) {
		if (!(object instanceof AssemblyContext))
			return null; // return null to indicate invalid input

		AssemblyContext assemblyContext = (AssemblyContext) object;
		Collection<EObject> usages = new HashSet<>();

		// only ImplementationComponentTypes can have VariableUsages
		if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof ImplementationComponentType))
			return usages;

		Collection<VariableUsage> componentVariableUsages = ((ImplementationComponentType) assemblyContext
				.getEncapsulatedComponent__AssemblyContext()).getComponentParameterUsage_ImplementationComponentType();

		// combine the sets
		usages.addAll(assemblyContext.getConfigParameterUsages__AssemblyContext());
		componentVariableUsages.stream()
				.filter(componentVariableUsage -> !isOverridden(componentVariableUsage, assemblyContext))
				.forEach(componentVariableUsage -> usages.add(componentVariableUsage));
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
	public boolean isOverridden(EObject variableUsageParam, EObject assemblyContextParam) {
		if (!(variableUsageParam instanceof VariableUsage && assemblyContextParam instanceof AssemblyContext)) {
			return false; // FIXME: proper error handling
		}

		String variableUsageReferenceName = ((VariableUsage) variableUsageParam).getNamedReference__VariableUsage()
				.getReferenceName();
		AssemblyContext assemblyContext = (AssemblyContext) assemblyContextParam;

		return assemblyContext
				.getConfigParameterUsages__AssemblyContext()
				.stream()
				.anyMatch(
						assemblyContextVariableUsage -> assemblyContextVariableUsage.getNamedReference__VariableUsage()
								.getReferenceName().equals(variableUsageReferenceName));
	}
	
	/**
	 * Sets the given string as a specification on the {@link PCMRandomVariable}.
	 * For this it first parses it to prevent any errors.
	 *
	 * @param pcmRandomVariable the random variable
	 * @param expression the expression
	 * @return the random variable
	 */
	public EObject editPCMRandomVariable(EObject pcmRandomVariable, String expressionString) {
		if (!(pcmRandomVariable instanceof PCMRandomVariable)) {
			return null;
		}
		if (!validExpression(expressionString)) {
			// TODO: display error dialog
            System.out.printf("Expression \"%s\" invalid", expressionString);
			return null;
		}
		System.out.printf("Expression \"%s\" valid", expressionString);

		// TODO: check whether the VariableUsage is defined on an Component. If so, duplicate it on the AssemblyContext

		((PCMRandomVariable) pcmRandomVariable).setSpecification(expressionString);

		return pcmRandomVariable;
	}

	/**
	 * Parses an stochastic expression to determine whether it is valid.
	 *
	 * @param the expressionString
	 * @return the validity
	 */
	private boolean validExpression(String expressionString) {
        final MyPCMStoExLexer lexer = new MyPCMStoExLexer(new ANTLRStringStream(expressionString));
        final MyPCMStoExParser parser = new MyPCMStoExParser(new CommonTokenStream(lexer));
        try {
            parser.expression();
        } catch (final RecognitionException e1) {
            return false; // TODO: return exception to be displayed in the error message
        }
        if (lexer.hasErrors() || parser.hasErrors()) {
            return false;
        }
		return true;
	}
}
