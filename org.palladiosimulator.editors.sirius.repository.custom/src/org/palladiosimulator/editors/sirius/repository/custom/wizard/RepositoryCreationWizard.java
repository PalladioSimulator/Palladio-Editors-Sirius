package org.palladiosimulator.editors.sirius.repository.custom.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.repository.custom.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

public class RepositoryCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		viewpoint = Activator.getDefault().getViewpoint();
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		representationDescription = Activator.getDefault().getRepresentationDescription();

		Repository obj = RepositoryFactory.eINSTANCE.createRepository();
		obj.setEntityName("New Repository");
		
		modelObject = obj;
	}

}
