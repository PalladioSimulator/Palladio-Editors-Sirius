package org.palladiosimulator.editors.sirius.usage.custom.toolbar;

import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;
import org.palladiosimulator.editors.sirius.usage.custom.wizard.UsageModelCreationWizard;

public class UsageModelCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new UsageModelCreationWizard();
	}

}
