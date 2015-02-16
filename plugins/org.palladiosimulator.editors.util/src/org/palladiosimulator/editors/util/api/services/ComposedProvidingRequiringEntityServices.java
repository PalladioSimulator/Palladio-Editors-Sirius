package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.repository.ImplementationComponentType;

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
}
