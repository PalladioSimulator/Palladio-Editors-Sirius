package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for the template selection list.
 * @author Benjamin Klatt
 *
 */
public class TemplateContentProvider implements IStructuredContentProvider {
	private Set<PalladioTemplate> availableTemplates;
	
	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.availableTemplates = (Set<PalladioTemplate>) newInput;
	}
	
	@Override
	public void dispose() {}
	
	@Override
	public Object[] getElements(Object inputElement) {
		return availableTemplates.toArray();
	}

}
