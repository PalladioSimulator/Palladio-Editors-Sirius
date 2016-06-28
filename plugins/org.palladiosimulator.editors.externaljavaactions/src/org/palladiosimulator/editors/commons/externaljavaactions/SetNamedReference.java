package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.variablenames.SetOutputVariableNameDialog;

import de.uka.ipd.sdq.stoex.VariableReference;

public class SetNamedReference implements IExternalJavaAction {

    public static final Shell SHELL = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final VariableReference variableReference = (VariableReference) parameters.get("VariableReference");
        final SetOutputVariableNameDialog dialog = new SetOutputVariableNameDialog(SHELL);
        dialog.open();
        variableReference.setReferenceName(dialog.getResult());

    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

}
