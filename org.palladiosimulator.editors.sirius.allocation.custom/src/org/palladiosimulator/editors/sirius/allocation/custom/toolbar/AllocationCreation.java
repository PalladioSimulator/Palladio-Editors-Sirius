package org.palladiosimulator.editors.sirius.allocation.custom.toolbar;

import org.palladiosimulator.editors.sirius.allocation.custom.wizard.AllocationCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class AllocationCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new AllocationCreationWizard();
	}

}
