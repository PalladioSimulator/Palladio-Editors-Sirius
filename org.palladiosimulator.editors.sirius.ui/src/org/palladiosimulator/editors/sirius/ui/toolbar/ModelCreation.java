package org.palladiosimulator.editors.sirius.ui.toolbar;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.palladiosimulator.editors.sirius.ui.wizard.model.NewModelWizard;

public abstract class ModelCreation extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell activeShell = HandlerUtil.getActiveShell(event);

		NewModelWizard wizard = getModelCreationWizard();
		IWorkbench workbench = PlatformUI.getWorkbench();
		IStructuredSelection selection = (IStructuredSelection) workbench.getActiveWorkbenchWindow().getSelectionService().getSelection();
		wizard.init(workbench, selection);
		WizardDialog dialog = new WizardDialog(activeShell, wizard);

		dialog.open();
		return null;
	}

	protected abstract NewModelWizard getModelCreationWizard();

}
