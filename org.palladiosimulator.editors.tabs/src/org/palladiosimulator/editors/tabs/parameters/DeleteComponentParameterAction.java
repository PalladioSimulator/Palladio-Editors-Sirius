package org.palladiosimulator.editors.tabs.parameters;

import java.util.Observable;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableUsage;

public class DeleteComponentParameterAction extends Observable implements
		SelectionListener, ISelectionChangedListener {

	private EObject selectedElement = null;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();
		Object selection = (Object) sel.getFirstElement();
		this.selectedElement = (EObject) selection;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		final VariableUsageWrapper wrapper = (VariableUsageWrapper) selectedElement;
		final VariableUsage variableUsage = wrapper.getVariableUsage();
		final AssemblyContext context = (AssemblyContext) variableUsage
				.eContainer();

		Assert.isNotNull(context);

		TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(context);

		RecordingCommand recCommand = new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				context.getConfigParameterUsages__AssemblyContext().remove(
						variableUsage);
			}
		};

		recCommand.setDescription("Delete ...");
		editingDomain.getCommandStack().execute(recCommand);
		
		// set TabelItem not edited
		wrapper.setEdited(false);
		// update observer
		notifyObservers(wrapper);

	}
	
	/* (non-Javadoc)
	 * @see java.util.Observable#notifyObservers(java.lang.Object)
	 */
	@Override
	public void notifyObservers(Object arg) {
		setChanged();
		super.notifyObservers(arg);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		// The implementation is not necessary.
	}
}
