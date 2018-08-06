package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.palladiosimulator.architecturaltemplates.AT;

/**
 * Content provider for the template selection list.
 * 
 * @author Benjamin Klatt
 *
 */
public class TemplateContentProvider implements IStructuredContentProvider {

    private Set<AT> initiatorTemplates;

    @SuppressWarnings("unchecked")
    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        this.initiatorTemplates = (Set<AT>) newInput;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        return this.initiatorTemplates.toArray();
    }

}
