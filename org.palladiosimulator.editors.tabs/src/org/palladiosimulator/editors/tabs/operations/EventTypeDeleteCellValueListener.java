/**
 * 
 */
package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.palladiosimulator.editors.tabs.generic.SelectionChangedListener;
import org.palladiosimulator.pcm.repository.EventType;

/**
 * Cell value delete listener for event types.
 * 
 * @author Benjamin Klatt
 */
public class EventTypeDeleteCellValueListener extends SelectionChangedListener implements SelectionListener {


	private TableViewer viewer;
	
	/** Constructor */
	public EventTypeDeleteCellValueListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		
		final EventType eventType = (EventType) getSelectedElement();
		
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(eventType);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				eventType.getParameter__EventType().setDataType__Parameter(null);
			}
		};

		recCommand.setDescription("Set event type parameter type to null");
		editingDomain.getCommandStack().execute(recCommand);
		
		viewer.refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		// The implementation is not necessary.
	}
}
