package org.palladiosimulator.editors.composedprovidingrequiringentity.commands;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;

/**
 * Command for creating a System at the given {@link URI}.
 * @author Max Schettler
 *
 */
public class CreateSystemModelCommand extends RecordingCommand {
	
	private final URI systemURI;
	private final ResourceSet resourceSet;
	private System createdSystem;

	public CreateSystemModelCommand(final TransactionalEditingDomain domain, final URI systemURI) {
		super(domain);
		this.systemURI = systemURI;
		this.resourceSet = domain.getResourceSet();
	}

	@Override
	protected void doExecute() {
		final System system = SystemFactory.eINSTANCE.createSystem();
		system.setEntityName(systemURI.segment(systemURI.segmentCount() -1).split("\\.")[0]);
		
		final Resource resource = resourceSet.createResource(systemURI);
		resource.getContents().add(system);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		createdSystem = system;
	}

	/**
	 * Retuns the {@link System} that has been created by this dialog.
	 * @return the {@link System}
	 */
	public System getCreatedSystem() {
		return createdSystem;
	}


}
