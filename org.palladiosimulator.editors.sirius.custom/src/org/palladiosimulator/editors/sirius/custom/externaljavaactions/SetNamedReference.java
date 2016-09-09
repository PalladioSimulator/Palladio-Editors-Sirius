package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.variablenames.SetOutputVariableNameDialog;
import de.uka.ipd.sdq.stoex.VariableReference;

public class SetNamedReference implements IExternalJavaAction {

	public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	
	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		VariableReference variableReference = (VariableReference) parameters.get("VariableReference");
		SetOutputVariableNameDialog dialog = new SetOutputVariableNameDialog(SHELL);
		dialog.open();
		variableReference.setReferenceName(dialog.getResult());
	

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
