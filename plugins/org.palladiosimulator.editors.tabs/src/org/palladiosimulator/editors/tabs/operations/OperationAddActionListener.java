package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

public class OperationAddActionListener extends SelectionAdapter {

	/**
	 * Define the selected interface. The variable is set not during the class
	 * production, separates later.
	 */
	private OperationInterface selectedInterface;

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {
		Assert.isNotNull(selectedInterface);

		/**
		 * The transactional editing domain which is used to get the commands
		 * and alter the model
		 */
		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(selectedInterface);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				OperationSignature signature = RepositoryFactory.eINSTANCE
						.createOperationSignature();
				signature
						.setEntityName("ServiceName"
								+ (selectedInterface.getSignatures__OperationInterface()
										.size() + 1));
				selectedInterface.getSignatures__OperationInterface().add(signature);
			}
		};

		recCommand.setDescription("Add new signature");
		editingDomain.getCommandStack().execute(recCommand);
	}

	/**
	 * @return the selectedInterface
	 */
	public OperationInterface getSelectedInterface() {
		return selectedInterface;
	}

	/**
	 * @param selectedInterface the selectedInterface to set
	 */
	public void setSelectedInterface(OperationInterface selectedInterface) {
		this.selectedInterface = selectedInterface;
	}
}
