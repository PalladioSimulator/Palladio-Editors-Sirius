package org.palladiosimulator.editors.sirius.custom.wizards;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class CreateModelCommand extends RecordingCommand {

	private final URI modelURI;
	private final ResourceSet resourceSet;
	private EObject modelObject;
	

	public CreateModelCommand(final TransactionalEditingDomain domain, final URI modelURI, EObject modelObject) {
		super(domain);
		this.modelURI = modelURI;
		this.resourceSet = domain.getResourceSet();
		this.modelObject = modelObject;
	}

	@Override
	protected void doExecute() {
		/*final Allocation allocation = AllocationFactory.eINSTANCE.createAllocation();
		
		allocation.setEntityName(modelURI.segment(modelURI.segmentCount() -1).split("\\.")[0]);*/
		
		final Resource resource = resourceSet.createResource(modelURI);
		resource.getContents().add(modelObject);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}



}
