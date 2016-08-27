package org.palladiosimulator.editors.sirius.repository.wizards;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.wizards.ModelCreationPage;
import org.palladiosimulator.editors.sirius.custom.wizards.NewModelWizard;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;


public class NewRepositoryModel extends NewModelWizard {

	@Override
	protected String initViewpointName() {
		return "repository";
	}

	@Override
	protected EObject initModelObject() {
		Repository repository = RepositoryFactory.eINSTANCE.createRepository();
		//TODO: set entityName, initialize
		return repository;
	}

	@Override
	protected ModelCreationPage initModelCreationPage() {
		// TODO Auto-generated method stub
		return null;
	}


}
