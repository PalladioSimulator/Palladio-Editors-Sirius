package org.palladiosimulator.editors.sirius.resourceenvironment.custom.toolbar;

import org.palladiosimulator.editors.sirius.resourceenvironment.custom.wizard.ResourceEnvironmentCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class ResourceEnvironmentCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new ResourceEnvironmentCreationWizard();
	}

}
