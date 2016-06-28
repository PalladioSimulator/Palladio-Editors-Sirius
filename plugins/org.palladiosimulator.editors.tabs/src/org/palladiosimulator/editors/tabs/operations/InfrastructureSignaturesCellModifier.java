package org.palladiosimulator.editors.tabs.operations;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.widgets.TableItem;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;

/**Modifier for cells containing infrastructure signature descriptions.
 * @author Roman Andrej
 * @author groenda
 */

public class InfrastructureSignaturesCellModifier extends ObservableCellModifier {

	/** Name of the columns. */
	private List<String> columnNames;
	/** Signature. */
	private InfrastructureSignature signature;

	/** Editing domain for model modifications. */
	protected TransactionalEditingDomain editingDomain = null;

	/**Initializes a new instance.
	 */
	public InfrastructureSignaturesCellModifier() {
		this.columnNames = Arrays.asList(InfrastructureSignaturesEditorSection.columnNames);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(Object element,
	 *      String property)
	 */
	public boolean canModify(Object element, String property) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
	 */
	public Object getValue(Object element, String property) {
		return (new InfrastructureSignaturesTabItemProvider(null)).getColumnText(element,
				columnNames.indexOf(property));
	}

	public void modify(Object element, String property, Object value) {

		// Find the index of the column
		int columnIndex = columnNames.indexOf(property);

		Assert.isNotNull(element);
		TableItem item = (TableItem) element;
		signature = (InfrastructureSignature) item.getData();
		editingDomain = TransactionUtil.getEditingDomain(signature);

		switch (columnIndex) {
		case InfrastructureSignaturesEditorSection.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
			break;
		case InfrastructureSignaturesEditorSection.SIGNATURENAME_COLUMN_INDEX: // SERVICENAME_COLUMN
			String valueString = ((String) value).trim();
			setSignatureName(valueString);
			break;
		case InfrastructureSignaturesEditorSection.PARAMETER_COLUMN_INDEX: // OWNEDPARAMETER_COLUMN
			break;
		case InfrastructureSignaturesEditorSection.EXCEPTIONS_COLUMN_INDEX: // EXEPTIONTYPE_COLUM
			break;
		default:
		}
	}

	/**Sets the name of the infrastructure signature.
	 * @param valueString New name.
	 */
	private void setSignatureName(String valueString) {
		final String value = valueString;

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				signature.setEntityName(value);
			}
		};

		if (!value.equals(signature.getEntityName())) {
			recCommand.setDescription("Edit Infrastructure Signature Property");
			recCommand.setLabel("Set signature name");
			editingDomain.getCommandStack().execute(recCommand);
		}
	}
}
