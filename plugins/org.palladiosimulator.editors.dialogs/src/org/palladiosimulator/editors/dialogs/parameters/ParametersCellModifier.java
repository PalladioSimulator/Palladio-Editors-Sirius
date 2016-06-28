package org.palladiosimulator.editors.dialogs.parameters;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.Parameter;

// TODO: Auto-generated Javadoc
/**
 * The class define the CellModifire for the ParametersDialog.
 * 
 * @author Roman Andrej
 */
public class ParametersCellModifier implements ICellModifier {

    /** The viewer. */
    private TableViewer viewer;

    /** The column names. */
    private List<String> columnNames;

    /** The parameter. */
    private Parameter parameter;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    private TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new parameters cell modifier.
     * 
     * @param viewer
     *            the viewer
     * @param editingDomain
     *            the editing domain
     */
    public ParametersCellModifier(TableViewer viewer, TransactionalEditingDomain editingDomain) {
        this.viewer = viewer;
        this.columnNames = Arrays.asList(CreateEditorContents.getColumnNames());
        this.editingDomain = editingDomain;
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
        return (new ParametersItemProvider(null)).getColumnText(element, columnNames.indexOf(property));
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
        parameter = (Parameter) item.getData();

        switch (columnIndex) {
        case CreateEditorContents.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
            break;
        case CreateEditorContents.CONTEXT_COLUMN_INDEX: // RETURNTYPE_COLUMN
            break;
        case CreateEditorContents.NAME_COLUMN_INDEX: // SERVICENAME_COLUMN
            String valueString = ((String) value).trim();
            setParameterName(valueString);
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
     * set parameter DataType.
     * 
     * @param dataType
     *            the new data type
     */
    private void setDataType(final DataType dataType) {

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                parameter.setDataType__Parameter(dataType);
            }
        };

        if (!dataType.equals(parameter.getDataType__Parameter())) {
            recCommand.setDescription("Edit Parameter Property");
            recCommand.setLabel("Set parameter DataType");
            editingDomain.getCommandStack().execute(recCommand);
        }

        reloadParametersViewer();
    }

    /**
     * set the parameter name.
     * 
     * @param valueString
     *            the new parameter name
     */
    private void setParameterName(String valueString) {
        final String value = valueString;

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                parameter.setParameterName(value);
            }
        };

        if (!value.equals(parameter.getParameterName())) {
            recCommand.setLabel("Set ParameterName");
            editingDomain.getCommandStack().execute(recCommand);
        }
        reloadParametersViewer();
    }

    /**
     * Reload parameters viewer.
     */
    private void reloadParametersViewer() {
        viewer.refresh();
    }
}
