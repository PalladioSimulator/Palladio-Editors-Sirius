package org.palladiosimulator.editors.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;

import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;



public class SetBranchProbability implements IExternalJavaAction {

	private final static Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ProbabilisticBranchTransition element = (ProbabilisticBranchTransition) parameters.get("element");
		StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(SHELL, TypeEnum.ANY);
		dialog.open();
		if (dialog.getReturnCode() == Dialog.OK) {
			element.setBranchProbability(Double.parseDouble(dialog.getResultText()));
		}
		

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
