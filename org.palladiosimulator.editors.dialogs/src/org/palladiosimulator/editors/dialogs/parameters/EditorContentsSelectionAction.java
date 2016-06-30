package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

// TODO: Auto-generated Javadoc
/**
 * The class delegates straight in the active View selected object on the subclass.
 * 
 * @author Roman Andrej
 */
public class EditorContentsSelectionAction implements ISelectionChangedListener {

    /** The selected declaration. */
    private EObject selectedDeclaration = null;

    /**
     * Selection changed.
     * 
     * @param event
     *            the event
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers
     *      .SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection sel = (IStructuredSelection) event.getSelection();
        Object selection = sel.getFirstElement();
        this.selectedDeclaration = (EObject) selection;

        /** make validation(Enabled/Unenabled) for delete-,up-,downToolItem */
        UpDownButtonsValidator.getSingelton().validateSelection(selection);
    }

    /**
     * Gets the selected declaration.
     * 
     * @return the selected declaration
     */
    public EObject getSelectedDeclaration() {
        return selectedDeclaration;
    }
}
