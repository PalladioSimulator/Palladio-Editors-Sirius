package org.palladiosimulator.editors.util.api.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.repository.ImplementationComponentType;

public class ComposedProvidingRequiringEntityServices {

	/**
	 * Returns a list containing all {@link VariableUsage} associated with the {@link AssemblyContext}.
	 * This means all usages that are defined on the context itself and those of its encapsulated component which are not overwritten.
	 * 
	 * @param object AssemblyContext
	 * @return associated VariableUsages
	 */
	public Collection<VariableUsage> getVariableUsages(AssemblyContext assemblyContext) {
//	public Collection<VariableUsage> getVariableUsages(EObject object) {
//		if (!(object instanceof AssemblyContext))
//			return null;
//
//		AssemblyContext assemblyContext = (AssemblyContext) object;
		Set<VariableUsage> usages = new TreeSet<>(new Comparator<VariableUsage>() {

			@Override
			public int compare(VariableUsage o1, VariableUsage o2) {
				return o1.getNamedReference__VariableUsage().getReferenceName().compareTo(
								o2.getNamedReference__VariableUsage().getReferenceName());
			}
		});
		// Check if the encapsulated component can have VariableUsages associated
		if (assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof ImplementationComponentType) {
			ImplementationComponentType encapsulatedComponent = (ImplementationComponentType) assemblyContext.getEncapsulatedComponent__AssemblyContext();
			usages.addAll(assemblyContext.getConfigParameterUsages__AssemblyContext());
			usages.addAll(encapsulatedComponent.getComponentParameterUsage_ImplementationComponentType());
		}
		return usages;
	}

}
