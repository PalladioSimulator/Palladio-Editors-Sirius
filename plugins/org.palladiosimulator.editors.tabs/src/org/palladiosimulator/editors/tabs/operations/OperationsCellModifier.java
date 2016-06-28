package org.palladiosimulator.editors.tabs.operations;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.widgets.TableItem;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.OperationSignature;

/**
 * @author Roman Andrej
 * 
 * This class implements an ICellModifier. An ICellModifier is called when the
 * user modifes a cell in the tableViewer
 */

public class OperationsCellModifier extends ObservableCellModifier {

	private List<String> columnNames;
	private OperationSignature signature;

	/**
	 * The transactional editing domain which is used to get the commands and
	 * alter the model
	 */
	protected TransactionalEditingDomain editingDomain = null;

	public OperationsCellModifier() {
		this.columnNames = Arrays.asList(OperationsEditorSection.columnNames);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(Object element,
	 *      String property)
	 */
	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		return (new OperationsTabItemProvider(null)).getColumnText(element,
				columnNames.indexOf(property));
	}

	public void modify(Object element, String property, Object value) {

		// Find the index of the column
		int columnIndex = columnNames.indexOf(property);

		Assert.isNotNull(element);
		TableItem item = (TableItem) element;
		signature = (OperationSignature) item.getData();
		editingDomain = TransactionUtil.getEditingDomain(signature);

		switch (columnIndex) {
		case OperationsEditorSection.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
			break;
		case OperationsEditorSection.RETURNTYPE_COLUMN_INDEX: // RETURNTYPE_COLUMN
			if (value instanceof DataType)
				setReturnType((DataType) value);
			break;
		case OperationsEditorSection.SIGNATURENAME_COLUMN_INDEX: // SERVICENAME_COLUMN
			String valueString = ((String) value).trim();
			setServiceName(valueString);
			break;
		case OperationsEditorSection.PARAMETER_COLUMN_INDEX: // OWNEDPARAMETER_COLUMN
			break;
		case OperationsEditorSection.EXCEPTIONS_COLUMN_INDEX: // EXEPTIONTYPE_COLUM
			break;
		default:
		}
	}

	private void setServiceName(String valueString) {
		final String value = valueString;

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				signature.setEntityName(value);
			}
		};

		if (!value.equals(signature.getEntityName())) {
			recCommand.setDescription("Edit Signature Property");
			recCommand.setLabel("Set name");
			editingDomain.getCommandStack().execute(recCommand);
		}
	}

	private void setReturnType(DataType type) {
		final DataType dataType = type;

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				signature.setReturnType__OperationSignature(dataType);
			}
		};

		if (!dataType.equals(signature.getReturnType__OperationSignature())) {
			recCommand.setDescription("Edit Signature Property");
			recCommand.setLabel("Set return type");
			editingDomain.getCommandStack().execute(recCommand);
			// sent message to observer
			notifyObservers();
		}
		
	}
}
