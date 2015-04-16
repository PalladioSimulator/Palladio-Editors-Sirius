package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.util.dialogs.ArchitecturalTemplateSelectEObjectDialog;
import org.scaledl.architecturaltemplates.type.AT;

import de.uka.ipd.sdq.pcm.system.System;

/**
 * A Sirius action that is used to add an ArchitecturalTemplate Role to the {@link System} it is applied to.
 * Executing the action will create an dialog asking the user to select an {@link AT}.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATAction implements IExternalJavaAction {

	private static final Object SELF_KEY = "self";

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		final Object parameter = parameters.get(SELF_KEY);
		if (parameter == null || !(parameter instanceof EObject)) {
			return;
		}
		final ResourceSet resourceSet = SessionManager.INSTANCE.getSession(
				(EObject) parameter).getTransactionalEditingDomain().getResourceSet();

		final ArchitecturalTemplateSelectEObjectDialog atSelectionDialog = new ArchitecturalTemplateSelectEObjectDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				resourceSet, ArchitecturalTemplateSelectEObjectDialog .Type.AT);
		if (atSelectionDialog.open() == Dialog.OK) {
			for (EObject object : selections) {
				java.lang.System.out.println(object);
			}
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		if (selections.size() != 1) {
			return false;
		}
		for (EObject object : selections) {
			return object instanceof System;
		}
		return false;
	}

}
