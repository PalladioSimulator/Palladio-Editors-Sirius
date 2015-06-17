package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.styleconfiguration;

import java.util.function.Predicate;

import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationProvider;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.viewpoint.Style;
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.ComposedProvidingRequiringEntityEditorConstants;

public class ComposedProvidingRequringEntityStyleConfiguration implements
		IStyleConfigurationProvider {

	private static final Predicate<DiagramElementMapping> isRoleParentNode = mapping -> mapping
			.getName()
			.equals(ComposedProvidingRequiringEntityEditorConstants.ASSEMBLY_CONTEXT_MAPPING)
			|| mapping
					.getName()
					.equals(ComposedProvidingRequiringEntityEditorConstants.COMPOSED_PROVIDING_REQUIRING_ENTITY_MAPPING);

	public ComposedProvidingRequringEntityStyleConfiguration() {}

	@Override
	public boolean provides(DiagramElementMapping mapping, Style style) {
		return isRoleParentNode.test(mapping);
	}

	@Override
	public StyleConfiguration createStyleConfiguration(
			DiagramElementMapping mapping, Style style) {
		if (isRoleParentNode.test(mapping)) {
			return new RoleParentStyleConfiguration();
		}
		throw new RuntimeException("Called with invalid parameters");
	}
}
