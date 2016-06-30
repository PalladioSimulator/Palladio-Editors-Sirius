package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

/**
 * Event Listener for the creation of a new event type.
 * 
 * This event listener initialises the new event type. 
 * I.e. it sets the default event type name and adds a default parameter.
 * 
 * @author Benjamin Klatt
 *
 */
public class EventTypeAddActionListener extends SelectionAdapter {

	/**
	 * Define the selected interface. The variable is set not during the class
	 * production, separates later.
	 */
	private EventGroup selectedEventGroup;

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		Assert.isNotNull(selectedEventGroup);

		/**
		 * The transactional editing domain which is used to get the commands
		 * and alter the model
		 */
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(selectedEventGroup);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				
				// create the new event type
				EventType eventType = RepositoryFactory.eINSTANCE
						.createEventType();
				eventType
						.setEntityName("EventTypeName"
								+ (selectedEventGroup.getEventTypes__EventGroup()
										.size() + 1));
				
				// create the event content parameter with a default name and String data type
				Parameter parameter = RepositoryFactory.eINSTANCE.createParameter();
				parameter.setParameterName("eventConent");
				PrimitiveDataType dataType = RepositoryFactory.eINSTANCE.createPrimitiveDataType();
				dataType.setType(PrimitiveTypeEnum.STRING);
				parameter.setDataType__Parameter(dataType);
				eventType.setParameter__EventType(parameter);
				
				// add the prepared event type
				selectedEventGroup.getEventTypes__EventGroup().add(eventType);
			}
		};

		recCommand.setDescription("Add new event type");
		editingDomain.getCommandStack().execute(recCommand);
	}

	/**
	 * @return the selected event group
	 */
	public EventGroup getSelectedEventGroup() {
		return selectedEventGroup;
	}

	/**
	 * @param selectedEventGroup The event group to set
	 */
	public void setSelectedInterface(EventGroup selectedEventGroup) {
		this.selectedEventGroup = selectedEventGroup;
	}
}
