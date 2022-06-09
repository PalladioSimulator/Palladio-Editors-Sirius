package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.provider;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.ConnectorStyleConfiguration;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider.DiamondShapedSlidableAnchorProvider;

public class EventChannelStyleConfigurationProvider implements IStyleConfigurationProvider {

	public EventChannelStyleConfigurationProvider() {
	}

	@Override
	public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {
		return new ConnectorStyleConfiguration(new DiamondShapedSlidableAnchorProvider());
	}

	@Override
	public boolean provides(DiagramElementMapping mapping, Style style) {

		List<String> supportedMappings = Arrays.asList("EventChannel Node");

		if (supportedMappings.contains(mapping.getName())) {
			return true;
		}
		return false;
	}

}
