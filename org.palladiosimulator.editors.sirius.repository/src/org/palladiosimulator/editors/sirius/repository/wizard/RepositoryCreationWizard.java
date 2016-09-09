package org.palladiosimulator.editors.sirius.repository.wizard;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.repository.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

public class RepositoryCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		viewpointNames = new ArrayList<String>();
		viewpointNames.add(viewpointName);
		representationDescription = Activator.getDefault().getRepresentationDescription();

		Repository obj = RepositoryFactory.eINSTANCE.createRepository();
		obj.setEntityName("New Repository");
		
		modelObject = obj;
	}

}
