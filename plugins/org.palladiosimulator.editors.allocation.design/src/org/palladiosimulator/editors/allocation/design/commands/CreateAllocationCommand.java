package org.palladiosimulator.editors.allocation.design.commands;

import java.io.IOException;
import java.util.Collections;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;

/**
 * Command for creating a System at the given {@link URI}.
 * @author Edith Kegel
 *
 */
public class CreateAllocationCommand extends RecordingCommand {
	
	private final URI allocationURI;
	private final ResourceSet resourceSet;
	private Allocation createdAllocation;
	

	public CreateAllocationCommand(final TransactionalEditingDomain domain, final URI allocationURI) {
		super(domain);
		this.allocationURI = allocationURI;
		this.resourceSet = domain.getResourceSet();
	}

	@Override
	protected void doExecute() {
		final Allocation allocation = AllocationFactory.eINSTANCE.createAllocation();
		
		allocation.setEntityName(allocationURI.segment(allocationURI.segmentCount() -1).split("\\.")[0]);
		
		final Resource resource = resourceSet.createResource(allocationURI);
		resource.getContents().add(allocation);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		createdAllocation = allocation;
	}

	/**
	 * Retuns the {@link System} that has been created by this dialog.
	 * @return the {@link System}
	 */
	public Allocation getcreatedAllocation() {
		return createdAllocation;
	}


}
