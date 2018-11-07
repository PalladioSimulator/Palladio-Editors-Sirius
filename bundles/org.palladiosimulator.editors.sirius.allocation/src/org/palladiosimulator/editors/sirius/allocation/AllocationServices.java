package org.palladiosimulator.editors.sirius.allocation;

import java.util.ArrayList;
import java.util.Collection;

import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.subsystem.SubSystem;

public class AllocationServices {

	public AllocationServices() {
		
	}
	
	public Collection<AssemblyContext> getNestedUndeployedAssemblyContexts(AssemblyContext parentAssemblyContext, Allocation allocation) {
		if (! (parentAssemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem))
			return null;
		
		SubSystem encapsulatedSubsystem = (SubSystem) parentAssemblyContext.getEncapsulatedComponent__AssemblyContext();
		Collection<AssemblyContext> result = new ArrayList<AssemblyContext>();
		for (AssemblyContext nestedAssemblyContext : encapsulatedSubsystem.getAssemblyContexts__ComposedStructure()) {
			if (!isAllocated(nestedAssemblyContext, allocation))
				result.add(nestedAssemblyContext);
		}
		return result;
	}
	
	public Collection<AssemblyContext> getNestedDeployedAssemblyContexts(AllocationContext allocationContext) {
		AssemblyContext assemblyContext = allocationContext.getAssemblyContext_AllocationContext();
		return getNestedDeployedAssemblyContexts(assemblyContext);
	}
	
	public Collection<AssemblyContext> getNestedDeployedAssemblyContexts(AssemblyContext assemblyContext) {
		if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem))
			return null;
		else {
			return ((SubSystem) assemblyContext.getEncapsulatedComponent__AssemblyContext()).getAssemblyContexts__ComposedStructure();
		}
	}


	
	public Collection<AllocationContext> getDeployedComponents(SubSystem subSystem, Allocation allocation) {
		Collection<AllocationContext> result = new ArrayList<AllocationContext>();
		for (AssemblyContext assemblyContext : subSystem.getAssemblyContexts__ComposedStructure()) {
			AllocationContext correspondingAllocationContext = getCorrespondingAllocationContext(assemblyContext, allocation);
			if (correspondingAllocationContext != null)
				result.add(correspondingAllocationContext);
		}
		return result;
	}
	
	public boolean assemblyContextIsAllowed(AssemblyContext assemblyContext, Allocation allocation) {
		if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem))
			return true;

		SubSystem subSystem = (SubSystem) assemblyContext.getEncapsulatedComponent__AssemblyContext();
		return !hasDeployedComponents(subSystem, allocation);
	}
	
	public Collection<AssemblyContext> getUndeployedSubAssemblyContexts(AssemblyContext assemblyContext, Allocation allocation, boolean recursively) {
		if (!(assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem))
			throw new IllegalArgumentException("The encapsulated component of " + assemblyContext + " must be a SubSystem");
		
		SubSystem subSystem = (SubSystem) assemblyContext.getEncapsulatedComponent__AssemblyContext();
		Collection<AssemblyContext> result = new ArrayList<AssemblyContext>();
		for (AssemblyContext a : subSystem.getAssemblyContexts__ComposedStructure()) {
			if (!isAllocated(a, allocation)) {
				result.add(a);
				if (recursively && a.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem) {
					result.addAll(getUndeployedSubAssemblyContexts(a, allocation, true));
				}
			}
			
		}
		return result;
		
	}
	public Collection<AssemblyContext> getUndeployedAssemblyContexts(Allocation allocation) {
		Collection<AssemblyContext> assemblyContexts = allocation.getSystem_Allocation().getAssemblyContexts__ComposedStructure();
		Collection<AssemblyContext> result = new ArrayList<AssemblyContext>();
		for (AssemblyContext assemblyContext : assemblyContexts) {
			if (assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem) {
				if (hasDeployedComponents((SubSystem) assemblyContext.getEncapsulatedComponent__AssemblyContext(), allocation)) {
					result.add(assemblyContext);
					result.addAll(getUndeployedSubAssemblyContexts(assemblyContext, allocation, true));
				}
					
			}
		}
		return result;
	}
	
	
	private boolean isAllocated(AssemblyContext assemblyContext, Allocation allocation) {
		return getCorrespondingAllocationContext(assemblyContext, allocation) != null;
	}
	private AllocationContext getCorrespondingAllocationContext(AssemblyContext assemblyContext, Allocation allocation) {
		for (AllocationContext allocationContext : allocation.getAllocationContexts_Allocation()) {
			if (allocationContext.getAssemblyContext_AllocationContext() == assemblyContext)
				return allocationContext;
		}
		return null;
	}
	
	private boolean hasDeployedComponents(SubSystem subSystem, Allocation allocation) {
		for (AssemblyContext assemblyContext : subSystem.getAssemblyContexts__ComposedStructure()) {
			if (isAllocated(assemblyContext, allocation))
				return true;
			if (assemblyContext.getEncapsulatedComponent__AssemblyContext() instanceof SubSystem) {
				if (hasDeployedComponents((SubSystem) assemblyContext.getEncapsulatedComponent__AssemblyContext(), allocation))
					return true;
			}
		}
		return false;
	}
	
	
}
