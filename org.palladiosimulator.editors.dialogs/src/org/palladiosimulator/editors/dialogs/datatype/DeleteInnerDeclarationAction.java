package org.palladiosimulator.editors.dialogs.datatype;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.palladiosimulator.editors.dialogs.parameters.EditorContentsSelectionAction;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;

// TODO: Auto-generated Javadoc
/**
 * The class define an action, which a InnerDeclaration for the CompositeDataType delete.
 * 
 * @author Roman Andrej
 */
public class DeleteInnerDeclarationAction extends EditorContentsSelectionAction implements SelectionListener {

    /** The dialog. */
    private PalladioDataTypeDialog dialog;

    /** The editing domain. */
    private TransactionalEditingDomain editingDomain;

    /**
     * Instantiates a new delete inner declaration action.
     * 
     * @param dialog
     *            the dialog
     * @param editingDomain
     *            the editing domain
     */
    public DeleteInnerDeclarationAction(PalladioDataTypeDialog dialog, TransactionalEditingDomain editingDomain) {
        this.dialog = dialog;
        this.editingDomain = editingDomain;
    }

    /**
     * Widget selected.
     * 
     * @param e
     *            the e
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent
     *      )
     */
    public void widgetSelected(SelectionEvent e) {

        final InnerDeclaration selectedDeclaration = (InnerDeclaration) getSelectedDeclaration();
        CompositeDataType parentDataType = (CompositeDataType) selectedDeclaration.eContainer();
        final EList<InnerDeclaration> declarations = parentDataType.getInnerDeclaration_CompositeDataType();

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                declarations.remove(selectedDeclaration);
            }
        };

        recCommand.setDescription("Delete ...");
        editingDomain.getCommandStack().execute(recCommand);
        // validate the innerdeclaration
        dialog.validateInput();
    }

    /**
     * Widget default selected.
     * 
     * @param e
     *            the e
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

    }
}
