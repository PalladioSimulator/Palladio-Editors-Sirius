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
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

/**
 * This class implements an ICellModifier for the modification of event types. 
 * An ICellModifier is called when the user modifies a cell in the tableViewer
 * 
 * @author Benjamin Klatt
 */

public class EventTypesCellModifier extends ObservableCellModifier {

	private List<String> columnNames;
	private EventType eventType;

	/**
	 * The transactional editing domain which is used to get the commands and
	 * alter the model
	 */
	protected TransactionalEditingDomain editingDomain = null;

	public EventTypesCellModifier() {
		this.columnNames = Arrays.asList(EventTypesEditorSection.columnNames);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(Object element,
	 *      String property)
	 */
	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		return (new EventTypesTabItemProvider(null)).getColumnText(element,
				columnNames.indexOf(property));
	}

	public void modify(Object element, String property, Object value) {

		// Find the index of the column
		int columnIndex = columnNames.indexOf(property);

		Assert.isNotNull(element);
		TableItem item = (TableItem) element;
		eventType = (EventType) item.getData();
		editingDomain = TransactionUtil.getEditingDomain(eventType);

		switch (columnIndex) {
		case EventTypesEditorSection.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
			break;
		case EventTypesEditorSection.EVENTTYPENAME_COLUMN_INDEX: // EVENT TYPE NAME_COLUMN
			String valueString = ((String) value).trim();
			setEventTypeName(valueString);
			break;
		case EventTypesEditorSection.PARAMETER_NAME_COLUMN_INDEX: // OWNEDPARAMETER_COLUMN
			String parameterNameString = ((String) value).trim();
			setEventTypeParameterName(parameterNameString);
			break;
		case EventTypesEditorSection.PARAMETER_TYPE_COLUMN_INDEX: // OWNEDPARAMETER TYPE COLUMN
			if (value instanceof DataType)
				setParameterType((DataType) value);
			break;
		default:
		}
	}

	/**
	 * Set the name of the event type.
	 * @param valueString The name to be set.
	 */
	private void setEventTypeName(String valueString) {
		final String value = valueString;

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				eventType.setEntityName(value);
			}
		};

		if (!value.equals(eventType.getEntityName())) {
			recCommand.setDescription("Edit EventType Property");
			recCommand.setLabel("Set name");
			editingDomain.getCommandStack().execute(recCommand);
		}
	}

	/**
	 * Set the name of the event type parameter.
	 * @param valueString The name to be set.
	 */
	private void setEventTypeParameterName(String valueString) {
		final String value = valueString;
		
		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// create the parameter object if it did not exist before
				if(eventType.getParameter__EventType() == null){
					Parameter parameter = RepositoryFactory.eINSTANCE.createParameter();
					eventType.setParameter__EventType(parameter);
				}

				// set the parameter name
				eventType.getParameter__EventType().setParameterName(value);
			}
		};

		if (eventType.getParameter__EventType() == null || 
				!value.equals(eventType.getParameter__EventType().getParameterName())) {
			recCommand.setDescription("Edit EventType Parameter Property");
			recCommand.setLabel("Set name");
			editingDomain.getCommandStack().execute(recCommand);
			notifyObservers();
		}
	}

	/**
	 * Set the data type of the parameter
	 * @param type The type to be set
	 */
	private void setParameterType(DataType type) {
		final DataType dataType = type;

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// create the parameter object if it did not exist before
				if(eventType.getParameter__EventType() == null){
					Parameter parameter = RepositoryFactory.eINSTANCE.createParameter();
					eventType.setParameter__EventType(parameter);
				}
				
				// set the parameter data type
				eventType.getParameter__EventType().setDataType__Parameter(dataType);
			}
		};

		if (eventType.getParameter__EventType() == null || 
				!dataType.equals(eventType.getParameter__EventType().getDataType__Parameter())) {
			recCommand.setDescription("Edit EventType Parameter Property");
			recCommand.setLabel("Set parameter type");
			editingDomain.getCommandStack().execute(recCommand);
			// sent message to observer
			notifyObservers();
		}
		
	}
}
