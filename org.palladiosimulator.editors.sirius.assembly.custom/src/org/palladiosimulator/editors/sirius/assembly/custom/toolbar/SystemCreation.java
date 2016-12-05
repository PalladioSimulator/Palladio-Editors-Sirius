package org.palladiosimulator.editors.sirius.assembly.custom.toolbar;

import org.palladiosimulator.editors.sirius.assembly.custom.wizard.SystemCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class SystemCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new SystemCreationWizard();
	}

}
