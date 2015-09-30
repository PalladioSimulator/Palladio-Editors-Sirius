package org.palladiosimulator.editors.resourceenvironment.design.commands;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;

/**
 * Command for creating a System at the given {@link URI}.
 * @author Edith Kegel
 *
 */
public class CreateResourceenvironmentCommand extends RecordingCommand {
	
	private final URI resourceURI;
	private final ResourceSet resourceSet;
	private ResourceEnvironment createdResourceEnvironment;

	public CreateResourceenvironmentCommand(final TransactionalEditingDomain domain, final URI resourceURI) {
		super(domain);
		this.resourceURI = resourceURI;
		this.resourceSet = domain.getResourceSet();
	}

	@Override
	protected void doExecute() {
		final ResourceEnvironment resourceEnvironment = ResourceenvironmentFactory.eINSTANCE.createResourceEnvironment();
		
		resourceEnvironment.setEntityName(resourceURI.segment(resourceURI.segmentCount() -1).split("\\.")[0]);
		
		final Resource resource = resourceSet.createResource(resourceURI);
		resource.getContents().add(resourceEnvironment);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		createdResourceEnvironment = resourceEnvironment;
	}

	/**
	 * Retuns the {@link System} that has been created by this dialog.
	 * @return the {@link System}
	 */
	public ResourceEnvironment getcreatedResourceEnvironment() {
		return createdResourceEnvironment;
	}


}
