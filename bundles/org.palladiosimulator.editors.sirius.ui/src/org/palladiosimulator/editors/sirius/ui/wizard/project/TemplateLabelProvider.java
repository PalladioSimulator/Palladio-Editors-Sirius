package org.palladiosimulator.editors.sirius.ui.wizard.project;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.palladiosimulator.architecturaltemplates.AT;

/**
 * A label provider for the Architectural templates.
 * 
 * @author Sebastian Lehrig
 *
 */
public class TemplateLabelProvider extends LabelProvider implements IBaseLabelProvider {

    @Override
    public String getText(final Object element) {
        if (element instanceof AT) {
            return ((AT) element).getEntityName();
        }
        return super.getText(element);
    }
}
