package org.palladiosimulator.editors.sirius.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.qosannotations.QoSAnnotations;
import org.palladiosimulator.pcm.qosannotations.SpecifiedQoSAnnotation;
import org.palladiosimulator.pcm.qosannotations.qos_performance.SystemSpecifiedExecutionTime;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;
import org.palladiosimulator.pcm.repository.Role;

import de.uka.ipd.sdq.stoex.AbstractNamedReference;

public class ComposedProvidingRequiringEntityServices extends PCMServices {

    public ComposedProvidingRequiringEntityServices() {
        super();
    }

    /**
     * Returns a list containing all {@link VariableUsage}s associated with the
     * {@link AssemblyContext}. This means all usages that are defined on the context itself and
     * those of its encapsulated component which are not overwritten.
     * 
     * @param assemblyContext
     *            AssemblyContext
     * @return associated VariableUsages
     * @see #isOverridden(VariableUsage, AssemblyContext)
     */
    public Collection<EObject> getVariableUsages(final AssemblyContext assemblyContext) {
        final Collection<EObject> usages = new HashSet<>();

        // only ImplementationComponentTypes can have VariableUsages
        if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof ImplementationComponentType))
            return usages;

        final Collection<VariableUsage> componentVariableUsages = ((ImplementationComponentType) assemblyContext
                .getEncapsulatedComponent__AssemblyContext()).getComponentParameterUsage_ImplementationComponentType();

        // combine the sets
        usages.addAll(assemblyContext.getConfigParameterUsages__AssemblyContext());

        for (final VariableUsage vu : componentVariableUsages) {
            if (!isOverridden(vu, assemblyContext)) {
                usages.add(vu);
            }
        }

        return usages;
    }

    /**
     * Computes whether or not the given {@link AssemblyContext} has a {@link VariableUsage}
     * associated that overrides, i.e. hides the given {@link VariableUsage}
     * 
     * 
     * @param variableUsage
     *            VariableUsage
     * @param assemblyContext
     *            AssemblyContext
     * @return
     * 
     */
    public boolean isOverridden(final VariableUsage variableUsage, final AssemblyContext assemblyContext) {
        final String variableUsageReferenceName = variableUsage.getNamedReference__VariableUsage().getReferenceName();

        for (final VariableUsage vu : assemblyContext.getConfigParameterUsages__AssemblyContext()) {
        	AbstractNamedReference reference = vu.getNamedReference__VariableUsage();
        	
            if (reference != null && variableUsageReferenceName.equals(reference.getReferenceName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Copies the {@link VariableUsage} to the {@link AssemblyContext}, i.e. 'instantiates' it. This
     * method will return the original VariableUsage.
     *
     * @param variableUsage
     *            the VariableUsage to be copied
     * @param assemblyContext
     *            the target AssemblyContext
     * @return the original VariableUsage
     */
    public EObject copyToAssemblyContext(final VariableUsage variableUsage, final AssemblyContext assemblyContext) {
        final List<EObject> copiedVariableUsage = EMFCopyHelper.deepCopyEObjectList(Collections.singletonList(variableUsage));
        if (copiedVariableUsage.size() != 1 || !(copiedVariableUsage.get(0) instanceof VariableUsage))
            return null;

        final VariableUsage newVariableUsage = (VariableUsage) copiedVariableUsage.get(0);
        newVariableUsage.setAssemblyContext__VariableUsage(assemblyContext);
        assemblyContext.getConfigParameterUsages__AssemblyContext().add(newVariableUsage);

        return variableUsage;
    }
    
    /**
	 * filters the given {@link SystemSpecifiedExecutionTime}s to produce a collection with the first occurrences of SystemSpecifiedExecutionTime having distinct {@link Role}s
	 * @param ssets 
	 * @return
	 */
	public Collection<SystemSpecifiedExecutionTime> getSystemSpecifiedExecutionTimesWithDistinctRoles(org.palladiosimulator.pcm.system.System system) {
		Collection<SystemSpecifiedExecutionTime> result = new ArrayList<SystemSpecifiedExecutionTime>();
		Collection<SystemSpecifiedExecutionTime> ssets = new ArrayList<SystemSpecifiedExecutionTime>();
		EList<QoSAnnotations> annotations = system.getQosAnnotations_System();
		for (QoSAnnotations annotation : annotations) {
			for (SpecifiedQoSAnnotation specifiedAnnotation : annotation.getSpecifiedQoSAnnotations_QoSAnnotations()) {
				if (specifiedAnnotation instanceof SystemSpecifiedExecutionTime) {
					ssets.add((SystemSpecifiedExecutionTime) specifiedAnnotation);
				}
			}
		}
		for (SystemSpecifiedExecutionTime sset : ssets) {
			
			if (sset.getRole_SpecifiedQoSAnnotation() != null && result.stream().noneMatch(x -> x.getRole_SpecifiedQoSAnnotation().equals(sset.getRole_SpecifiedQoSAnnotation()))) 
				result.add(sset);
		}
		
		return result;
		
	}
}
