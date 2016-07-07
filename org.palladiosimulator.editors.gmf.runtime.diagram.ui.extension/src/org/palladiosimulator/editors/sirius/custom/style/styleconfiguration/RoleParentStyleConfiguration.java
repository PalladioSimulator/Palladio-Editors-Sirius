package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration;

import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.BorderItemLocatorProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.SimpleStyleConfiguration;
import org.palladiosimulator.editors.sirius.custom.style.borderItemLocator.provider.RoleBorderItemLocatorProvider;

public class RoleParentStyleConfiguration extends SimpleStyleConfiguration {

	@Override
	public BorderItemLocatorProvider getBorderItemLocatorProvider() {
		return RoleBorderItemLocatorProvider.getInstance();
	}

}
