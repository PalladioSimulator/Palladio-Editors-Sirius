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
import org.palladiosimulator.pcm.repository.OperationSignature;

/**
 * @author Roman Andrej
 */
public class OperationDeleteCellValueListener extends SelectionChangedListener implements SelectionListener {


	private TableViewer viewer;
	
	/** Constructor */
	public OperationDeleteCellValueListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		
		final OperationSignature signature = (OperationSignature) getSelectedElement();
		
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(signature);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				signature.setReturnType__OperationSignature(null);
			}
		};

		recCommand.setDescription("Set void return type signature");
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
