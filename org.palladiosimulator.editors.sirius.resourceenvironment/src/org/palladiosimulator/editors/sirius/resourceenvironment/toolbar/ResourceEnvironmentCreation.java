package org.palladiosimulator.editors.sirius.resourceenvironment.toolbar;

import org.palladiosimulator.editors.sirius.resourceenvironment.wizard.ResourceEnvironmentCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class ResourceEnvironmentCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new ResourceEnvironmentCreationWizard();
	}

}
