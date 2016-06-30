package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.palladiosimulator.editors.tabs.generic.SelectionChangedListener;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;

public class OperationDeleteActionListener extends SelectionChangedListener implements SelectionListener {

	/**
	 * The transactional editing domain which is used to get the commands and
	 * alter the model
	 */
	private TransactionalEditingDomain editingDomain = null;


	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {

		final OperationSignature selectedSignature = (OperationSignature) getSelectedElement();
		Assert.isNotNull(selectedSignature);
		final OperationInterface selectedInterface = (OperationInterface) selectedSignature
				.getInterface__OperationSignature();
		Assert.isNotNull(selectedInterface);
		editingDomain = TransactionUtil.getEditingDomain(selectedSignature);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				selectedInterface.getSignatures__OperationInterface().remove(
						selectedSignature);
			}
		};

		recCommand.setDescription("Delete ...");
		editingDomain.getCommandStack().execute(recCommand);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		// The implementation is not necessary.
	}
}
