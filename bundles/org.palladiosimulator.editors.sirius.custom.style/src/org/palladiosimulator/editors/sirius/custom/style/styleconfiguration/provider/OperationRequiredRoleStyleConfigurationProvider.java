package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.provider;

import java.util.Arrays;
import java.util.List;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.ConnectorStyleConfiguration;
import org.palladiosimulator.editors.sirius.custom.style.styleconfiguration.anchorprovider.OrientedFixpointAnchorProvider;

public class OperationRequiredRoleStyleConfigurationProvider implements IStyleConfigurationProvider {

    public OperationRequiredRoleStyleConfigurationProvider() {
    }

    @Override
    public StyleConfiguration createStyleConfiguration(final DiagramElementMapping mapping, final Style style) {
        return new ConnectorStyleConfiguration(new OrientedFixpointAnchorProvider(0.25));
    }

    @Override
    public boolean provides(final DiagramElementMapping mapping, final Style style) {

        final List<String> supportedMappings = Arrays.asList("OperationRequiredRole Node");

        return supportedMappings.contains(mapping.getName());
    }

}
