package org.palladiosimulator.editors.dialogs.datatype;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.swt.widgets.TableItem;
import org.palladiosimulator.editors.dialogs.parameters.CreateEditorContents;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;

// TODO: Auto-generated Javadoc
/**
 * The Class InnerDeclarationCellModifier.
 */
public class InnerDeclarationCellModifier implements ICellModifier {

    /** The dialog. */
    private PalladioDataTypeDialog dialog;

    /** The column names. */
    private List<String> columnNames;

    /** The declaration. */
    private InnerDeclaration declaration;

    /** The editing domain. */
    private TransactionalEditingDomain editingDomain;

    /**
     * Instantiates a new inner declaration cell modifier.
     * 
     * @param dialog
     *            the dialog
     * @param editingDomain
     *            the editing domain
     */
    public InnerDeclarationCellModifier(PalladioDataTypeDialog dialog, TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
        this.dialog = dialog;
        this.columnNames = Arrays.asList(CreateEditorContents.getColumnNames());
    }

    /**
     * Can modify.
     * 
     * @param element
     *            the element
     * @param property
     *            the property
     * @return true, if successful
     * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
     */
    public boolean canModify(Object element, String property) {
        return true;
    }

    /**
     * Gets the value.
     * 
     * @param element
     *            the element
     * @param property
     *            the property
     * @return the value
     * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
     */
    public Object getValue(Object element, String property) {
        return (new InnerDeclarationItemProvider(null)).getColumnText(element, columnNames.indexOf(property));
    }

    /**
     * Modify.
     * 
     * @param element
     *            the element
     * @param property
     *            the property
     * @param value
     *            the value
     * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String,
     *      java.lang.Object)
     */
    public void modify(Object element, String property, Object value) {
        // Find the index of the column
        int columnIndex = columnNames.indexOf(property);

        Assert.isNotNull(element);
        TableItem item = (TableItem) element;
        declaration = (InnerDeclaration) item.getData();

        switch (columnIndex) {
        case CreateEditorContents.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
            break;
        case CreateEditorContents.CONTEXT_COLUMN_INDEX: // RETURNTYPE_COLUMN
            break;
        case CreateEditorContents.NAME_COLUMN_INDEX: // SERVICENAME_COLUMN
            String valueString = ((String) value).trim();
            setDeclarationName(valueString);
            break;
        case CreateEditorContents.TYPE_COLUMN_INDEX: // OWNEDPARAMETER_COLUMN
            if (value instanceof DataType) {
                DataType valueDataType = (DataType) value;
                setDataType(valueDataType);
            }
            break;
        default:
        }
    }

    /**
     * Sets the data type.
     * 
     * @param dataType
     *            the new data type
     */
    private void setDataType(final DataType dataType) {

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                declaration.setDatatype_InnerDeclaration(dataType);
            }
        };

        if (!dataType.equals(declaration.getDatatype_InnerDeclaration())) {
            recCommand.setLabel("Set InnerDeclaration DataType");
            editingDomain.getCommandStack().execute(recCommand);
        }
        reloadDeclarationViewer();
    }

    /**
     * set the name of innerdeclaration.
     * 
     * @param valueString
     *            the new declaration name
     */
    private void setDeclarationName(final String valueString) {

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                declaration.setEntityName(valueString);
            }
        };

        if (!valueString.equals(declaration.getEntityName())) {
            recCommand.setLabel("Set InnerDeclaration name");
            editingDomain.getCommandStack().execute(recCommand);
        }

        reloadDeclarationViewer();
    }

    /**
     * Reload declaration viewer.
     */
    private void reloadDeclarationViewer() {
        dialog.refresh();
    }
}
