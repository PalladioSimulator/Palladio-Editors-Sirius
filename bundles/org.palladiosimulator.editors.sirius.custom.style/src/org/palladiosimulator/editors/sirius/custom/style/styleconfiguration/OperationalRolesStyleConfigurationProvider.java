package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;

public class OperationalRolesStyleConfigurationProvider implements IStyleConfigurationProvider {

	public OperationalRolesStyleConfigurationProvider() {
	}

	@Override
	public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {
		return new OperationalRolesStyleConfiguration();
	}

	@Override
	public boolean provides(DiagramElementMapping mapping, Style style) {
		List<String> supportedMappings = Arrays.asList("ExternalOperationProvidedRole Node");
		if (supportedMappings.contains(mapping.getName())) {
			return true;
		}
		return false;
	}

}
