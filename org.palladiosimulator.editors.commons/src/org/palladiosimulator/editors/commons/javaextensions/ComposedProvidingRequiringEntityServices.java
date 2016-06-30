package org.palladiosimulator.editors.commons.javaextensions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;

public class ComposedProvidingRequiringEntityServices extends PCMServices {

    public ComposedProvidingRequiringEntityServices() {
        super();
    }

    /**
     * Returns a list containing all {@link VariableUsage}s associated with the
     * {@link AssemblyContext}. This means all usages that are defined on the context itself and
     * those of its encapsulated component which are not overwritten.
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
     * If either the parameter is not instance of VariableUsage resp. AssemblyContext, the method
     * will return false.
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

        for (final VariableUsage vu : assemblyContext.getConfigParameterUsages__AssemblyContext()) {
            if (vu.getNamedReference__VariableUsage().getReferenceName().equals(variableUsageReferenceName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Copies the {@link VariableUsage} to the {@link AssemblyContext}, i.e. 'instantiates' it. This
     * method will return the VariableUsage or null if the parameters do not have the correct types.
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
