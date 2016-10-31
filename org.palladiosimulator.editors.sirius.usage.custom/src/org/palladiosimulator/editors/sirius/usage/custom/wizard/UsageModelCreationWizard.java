package org.palladiosimulator.editors.sirius.usage.custom.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.usage.custom.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsagemodelFactory;

public class UsageModelCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		viewpoint = Activator.getDefault().getViewpoint();
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		representationDescription = Activator.getDefault().getRepresentationDescription();
		

		UsageModel obj = UsagemodelFactory.eINSTANCE.createUsageModel();
		
		modelObject = obj;
	}

}
