package org.palladiosimulator.editors.sirius.custom.style.styleconfiguration;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.palladiosimulator.editors.sirius.custom.style.ComposedProvidingRequiringEntityEditorConstants;

public class ComposedProvidingRequringEntityStyleConfiguration implements
		IStyleConfigurationProvider {

	public ComposedProvidingRequringEntityStyleConfiguration() {}

	@Override
	public boolean provides(DiagramElementMapping mapping, Style style) {
		
		return isRoleParentNode(mapping);
	}

	@Override
	public StyleConfiguration createStyleConfiguration(
			DiagramElementMapping mapping, Style style) {

		if (isRoleParentNode(mapping)) {
			return new RoleParentStyleConfiguration();
		}
		throw new RuntimeException("Called with invalid parameters");
	}
	
	private boolean isRoleParentNode (DiagramElementMapping mapping)
	{
		return mapping.getName().equals(ComposedProvidingRequiringEntityEditorConstants.ASSEMBLY_CONTEXT_MAPPING) ||
				mapping.getName().equals(ComposedProvidingRequiringEntityEditorConstants.COMPOSED_PROVIDING_REQUIRING_ENTITY_MAPPING);
		
	}
}
