package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.ui.dialogs.ProfileSelectionDialog;

/**
 * A sirius action for selecting and unapplying a {@link Profile} from the selected
 * {@link EObject}`s {@link Resource}.
 * 
 * @author Max Schettler
 *
 */
public class UnapplyProfileAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return selections.size() == 1;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final Resource targetResource = ((DDiagramElement) selections.iterator().next()).getTarget().eResource();

		final ProfileSelectionDialog profileSelectionDialog = new ProfileSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		profileSelectionDialog.setElements(ProfileAPI.getAppliedProfiles(targetResource).toArray());
		
		if (profileSelectionDialog.open() == Dialog.OK) {
			final Profile selectedProfile = profileSelectionDialog.getResultProfile();
			ProfileAPI.unapplyProfile(targetResource, selectedProfile);
		}
	}

}
