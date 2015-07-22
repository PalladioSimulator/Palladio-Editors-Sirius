package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.ui.dialogs.ProfileSelectionDialog;

/**
 * A sirius action for selecting and applying a {@link Profile} to the
 * {@link Resource} of the selected {@link EObject}. selected object.
 * 
 * @author Max Schettler
 *
 */
public class ApplyProfileAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return selections.size() == 1;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final Resource targetResource = ((EObject) selections.iterator().next()).eResource();

		final ProfileSelectionDialog profileSelectionDialog = new ProfileSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		
		profileSelectionDialog.setElements(ProfileAPI.getApplicableProfiles(targetResource).toArray());
		
		if (profileSelectionDialog.open() == Dialog.OK) {
			final Profile selectedProfile = profileSelectionDialog.getResultProfile();
			ProfileAPI.applyProfile(targetResource, selectedProfile);
		}
	}

}
