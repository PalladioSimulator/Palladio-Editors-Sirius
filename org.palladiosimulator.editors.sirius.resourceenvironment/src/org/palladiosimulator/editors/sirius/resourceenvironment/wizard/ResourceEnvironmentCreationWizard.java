package org.palladiosimulator.editors.sirius.resourceenvironment.wizard;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.resourceenvironment.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;

public class ResourceEnvironmentCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		viewpointNames = new ArrayList<String>();
		viewpointNames.add(viewpointName);
		representation = Activator.getDefault().getRepresentation();
		

		ResourceEnvironment obj = ResourceenvironmentFactory.eINSTANCE.createResourceEnvironment();
		obj.setEntityName("New ResourceEnvironment");
		
		
		modelObject = obj;
	}

}
