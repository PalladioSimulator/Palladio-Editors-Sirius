package org.palladiosimulator.editors.tabs.generic;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * The class delegates straight in the active View selected object on the
 * subclass.
 * 
 * @author Roman Andrej
 */
public class SelectionChangedListener implements ISelectionChangedListener {

	private EObject selectedElement = null;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();
		Object selection = (Object) sel.getFirstElement();
		this.selectedElement = (EObject) selection;
	}

	public EObject getSelectedElement() {
		return selectedElement;
	}
}
