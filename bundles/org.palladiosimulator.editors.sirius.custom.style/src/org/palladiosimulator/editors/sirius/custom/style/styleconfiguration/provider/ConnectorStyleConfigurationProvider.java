package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.provider;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.ConnectorStyleConfiguration;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider.DiamondShapedSlidableAnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider.OrientedFixpointAnchorProvider;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorProvider.OrientedHalfAlphaBasedAnchorProvider;

public class ConnectorStyleConfigurationProvider implements IStyleConfigurationProvider {

	public ConnectorStyleConfigurationProvider() {
	}

	@Override
	public StyleConfiguration createStyleConfiguration(DiagramElementMapping mapping, Style style) {

		StyleConfiguration sc;

		switch (mapping.getName()) {
			// EventChannel
			case "EventChannel Node":
				sc = new ConnectorStyleConfiguration(new DiamondShapedSlidableAnchorProvider());
				break;
			// InfrastructureReq
			case "InfrastructureRequiredRole Node":
				sc = new ConnectorStyleConfiguration(new OrientedFixpointAnchorProvider(1.0 / 3));
				break;
			// All Provided Types
			case "OperationProvidedRole Node":
			case "InfrastructureProvidedRole Node":
			case "SinkRole Node":
				sc = new ConnectorStyleConfiguration(new OrientedHalfAlphaBasedAnchorProvider());
				break;
			// All ExternalRoles
			case "ExternalOperationRequiredRole Node":
			case "ExternalOperationProvidedRole Node":
			case "ExternalSourceRole Node":
			case "ExternalSinkRole Node":
			case "ExternalInfrastructureRequiredRole Node":
			case "ExternalInfrastructureProvidedRole Node":
				sc = new ConnectorStyleConfiguration(new OrientedFixpointAnchorProvider(1.0));
				break;
			// OperationReq
			case "OperationRequiredRole Node":
				sc = new ConnectorStyleConfiguration(new OrientedFixpointAnchorProvider(0.25));
				break;
			// SourceRole
			case "SourceRole Node":
			default:
				sc = new ConnectorStyleConfiguration(new OrientedFixpointAnchorProvider(0.4));
		}

		return sc;
	}

	@Override
	public boolean provides(DiagramElementMapping mapping, Style style) {

		List<String> supportedMappings = Arrays.asList("InfrastructureRequiredRole Node", "OperationProvidedRole Node",
				"InfrastructureProvidedRole Node", "SinkRole Node", "EventChannel Node",
				"ExternalOperationRequiredRole Node", "ExternalOperationProvidedRole Node", "ExternalSourceRole Node",
				"ExternalSinkRole Node", "ExternalInfrastructureRequiredRole Node",
				"ExternalInfrastructureProvidedRole Node", "OperationRequiredRole Node", "SourceRole Node");

		if (supportedMappings.contains(mapping.getName())) {
			return true;
		}
		return false;
	}

}
