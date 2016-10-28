package org.palladiosimulator.editors.sirius.allocation.custom.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.palladiosimulator.editors.sirius.allocation.custom.Activator;
import org.palladiosimulator.editors.sirius.ui.wizard.model.ModelCreationPage;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationFactory;

public class AllocationCreationWizard extends NewModelWizard {

	@Override
	protected void init(IStructuredSelection selection) {
		String viewpointName = Activator.VIEWPOINT_NAME;
		viewpoint = Activator.getDefault().getViewpoint();
		String ext = Activator.getDefault().getViewpoint().getModelFileExtension();
		modelCreationPage = new ModelCreationPage(selection, viewpointName + " Creation Wizard", "new" + viewpointName,  ext);
		representationDescription = Activator.getDefault().getRepresentationDescription();
		

		Allocation obj = AllocationFactory.eINSTANCE.createAllocation();
		obj.setEntityName("New Allocation");
		
		
		modelObject = obj;
	}

}
