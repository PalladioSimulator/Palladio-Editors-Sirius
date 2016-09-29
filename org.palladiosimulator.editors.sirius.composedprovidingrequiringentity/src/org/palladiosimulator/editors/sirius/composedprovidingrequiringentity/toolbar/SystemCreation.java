package org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.toolbar;

import org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.wizard.SystemCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class SystemCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new SystemCreationWizard();
	}

}
