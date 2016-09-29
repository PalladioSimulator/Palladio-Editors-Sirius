package org.palladiosimulator.editors.sirius.repository.toolbar;
import org.palladiosimulator.editors.sirius.repository.wizard.RepositoryCreationWizard;
import org.palladiosimulator.editors.sirius.ui.toolbar.ModelCreation;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public class RepositoryCreation extends ModelCreation {

	@Override
	protected NewModelWizard getModelCreationWizard() {
		return new RepositoryCreationWizard();
	}
}
