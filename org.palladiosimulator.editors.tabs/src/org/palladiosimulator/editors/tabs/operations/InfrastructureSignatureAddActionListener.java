package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.palladiosimulator.pcm.repository.InfrastructureInterface;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

public class InfrastructureSignatureAddActionListener extends SelectionAdapter {

	/**
	 * Define the selected interface. The variable is set not during the class
	 * production, separates later.
	 */
	private InfrastructureInterface selectedInterface;

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
				InfrastructureSignature signature = RepositoryFactory.eINSTANCE
						.createInfrastructureSignature();
				signature
						.setEntityName("Signature"
								+ (selectedInterface.getInfrastructureSignatures__InfrastructureInterface()
										.size() + 1));
				selectedInterface.getInfrastructureSignatures__InfrastructureInterface().add(signature);
			}
		};

		recCommand.setDescription("Add new infrastructure signature");
		editingDomain.getCommandStack().execute(recCommand);
	}

	/**
	 * @return the selected infrastructure interface
	 */
	public InfrastructureInterface getSelectedInterface() {
		return selectedInterface;
	}

	/**
	 * @param selectedInterface the selected infrastructure interface to set
	 */
	public void setSelectedInterface(InfrastructureInterface selectedInterface) {
		this.selectedInterface = selectedInterface;
	}
}
