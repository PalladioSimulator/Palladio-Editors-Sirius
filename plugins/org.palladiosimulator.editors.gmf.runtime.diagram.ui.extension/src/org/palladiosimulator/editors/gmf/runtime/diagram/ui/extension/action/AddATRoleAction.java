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
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

/**
 * This class applies an ArchitecturalTemplate to a {@link System}. It will ask
 * the user to select from an list of available ArchitecturalTemplates.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATRoleAction implements IExternalJavaAction {

	private static final Object SELF_KEY = "self";

	/**
	 * Asks the user to select a {@link Role} and attaches it to the given
	 * {@link AssemblyContext}.
	 */
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
				resourceSet, ArchitecturalTemplateSelectEObjectDialog .Type.ROLE);
		if (atSelectionDialog.open() == Dialog.OK) {
			for (EObject object : selections) {
				System.out.println(object);
			}
		}
	}

	/**
	 * Tests whether the object this tool is applied to is a {@link System}.
	 */
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		if (selections.size() != 1) {
			return false;
		}
		for (EObject object : selections) {
			return object instanceof AssemblyContext;
		}
		return false;
	}

}
