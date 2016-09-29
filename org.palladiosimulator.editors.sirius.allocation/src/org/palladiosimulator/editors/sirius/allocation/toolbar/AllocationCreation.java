package org.palladiosimulator.editors.sirius.allocation.toolbar;

import org.palladiosimulator.editors.sirius.allocation.wizard.AllocationCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class AllocationCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new AllocationCreationWizard();
	}

}
