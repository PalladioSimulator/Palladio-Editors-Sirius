package org.palladiosimulator.editors.dialogs.datatype;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.Repository;

// TODO: Auto-generated Javadoc
/**
 * The class sets the actions (add, edit, delete), which PalladioSelectEObjectDialog were defined
 * in.
 * 
 * @author Roman Andrej
 */
public class CallDataTypeDialog extends PalladioSelectEObjectDialog {

    /** The selected data type. */
    private DataType selectedDataType = null;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    private TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new call data type dialog.
     *
     * @param parent the parent
     * @param filterList the filter list
     * @param additionalChildReferences the additional child references
     * @param input the input
     */
    public CallDataTypeDialog(Shell parent, Collection<Object> filterList,
            Collection<EReference> additionalChildReferences, Object input) {
        super(parent, filterList, additionalChildReferences, input);
        this.editingDomain = TransactionUtil.getEditingDomain(input);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.dialogs.selection.SelectEObjectDialog#createDialogArea(Composite parent)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);

        /**
         * Activire the ToolBar with items
         * 
         * @See de.uka.ipd.sdq.dialogs.selection.SelectEObjectDialog#enableToolBar(Boolean
         *      addItemEnabled, boolean deleteItemEnabled, boolean editItemEnabled)
         */
        enableToolBar(true, false, false);

        TreeViewer treeViewer = getTreeViewer();
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                EObject selection = (EObject) sel.getFirstElement();

                enableToolBar(true, false, false);
                if ((selection instanceof CollectionDataType) || (selection instanceof CompositeDataType)) {
                    selectedDataType = (DataType) selection;
                    enableToolBar(true, true, true);
                }
            }
        });

        setAddSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.
             * SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                Shell shell = e.widget.getDisplay().getActiveShell();
                PalladioDataTypeDialog dialog = new PalladioDataTypeDialog(shell, editingDomain);
                dialog.open();
            }

        });

        setDeleteSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.
             * SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {

                RecordingCommand recCommand = new RecordingCommand(editingDomain) {
                    @Override
                    protected void doExecute() {
                        Assert.isNotNull(selectedDataType);

                        Repository repository = selectedDataType.getRepository__DataType();

                        EList<DataType> datatypesRepository = repository.getDataTypes__Repository();
                        datatypesRepository.remove(selectedDataType);

                    }
                };

                recCommand.setDescription("Delete the DataType");
                editingDomain.getCommandStack().execute(recCommand);
                enableToolBar(true, false, false);
            }
        });

        setEditeSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.
             * SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {

                Assert.isNotNull(selectedDataType);
                Shell shell = e.widget.getDisplay().getActiveShell();
                PalladioDataTypeDialog dialog = new PalladioDataTypeDialog(shell, selectedDataType);
                dialog.open();

            }

        });
        return area;
    }
}
