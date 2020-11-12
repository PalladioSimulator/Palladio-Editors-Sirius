package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.palladiosimulator.architecturaltemplates.AT;
import org.palladiosimulator.architecturaltemplates.Catalog;

/**
 * A label provider for the Architectural templates.
 * 
 * @author Dominik Werle
 *
 */
public class TemplateTreeLabelProvider extends LabelProvider implements IBaseLabelProvider {
	@Override
	public String getText(final Object element) {
		if (element instanceof AT) {
			return ((AT) element).getEntityName();
		}
		if (element instanceof Map.Entry<?, ?>) {
			return ((Map.Entry<Catalog, List<AT>>) element).getKey().getEntityName();
		}
		return super.getText(element);
	}
}
