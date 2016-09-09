package org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.wizard;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.system.SystemFactory;

public class SystemCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		viewpointNames = new ArrayList<String>();
		viewpointNames.add(viewpointName);
		representationDescription = Activator.getDefault().getRepresentationDescription();
		

		org.palladiosimulator.pcm.system.System obj = SystemFactory.eINSTANCE.createSystem();
		obj.setEntityName("New System");
		
		
		modelObject = obj;
	}

}
