package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.ui.ArchitecturalTemplateProfileSelectionDialog;

import org.palladiosimulator.pcm.system.System;

/**
 * A Sirius action that is used to add an ArchitecturalTemplate Role to the
 * {@link System} it is applied to. Executing the action will create an dialog
 * asking the user to select an {@link AT}.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATAction implements IExternalJavaAction {

	/**
	 * Message displayed in the selection dialog.
	 */
	private static final String DIALOG_MESSAGE = "Select an Architectural Template Profile to apply to the System";

	/**
	 * The name of the parameter to be given. This is an arbitrary object from
	 * the editor to get the corresponding {@link Session}.
	 */
	private static final String SYSTEM_PARAMETER_KEY = "system";

	/**
	 * Creates an {@link ArchitecturalTemplateSelectEObjectDialog} that queries
	 * the user for an {@link AT} and applies the corresponding
	 * {@link Stereotype} to the selected {@link System}.
	 */
	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		final Object parameter = parameters.get(SYSTEM_PARAMETER_KEY);
		if (parameter == null || !(parameter instanceof System)) {
			return;
		}
		final System system = (System) parameter;

		ArchitecturalTemplateProfileSelectionDialog profileSelectionDialog = new ArchitecturalTemplateProfileSelectionDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		profileSelectionDialog.setElements(IProfileRegistry.eINSTANCE
				.getRegisteredProfiles().stream()
				.filter(ArchitecturalTemplateAPI.isArchitecturalTemplate).toArray());

		profileSelectionDialog.setMessage(DIALOG_MESSAGE);

		if (profileSelectionDialog.open() != Dialog.OK) {
			return;
		}

		final Profile atProfile = profileSelectionDialog.getResultProfile();

		ProfileAPI.applyProfile(system.eResource(), atProfile);
		EPackage.Registry.INSTANCE.put(atProfile.getNsURI(), atProfile);

		final Stereotype atSystemRoleStereotype = ArchitecturalTemplateAPI.getSystemRoleStereotype(atProfile);

		if (StereotypeAPI
				.isStereotypeApplicable(system, atSystemRoleStereotype)) {
			StereotypeAPI.applyStereotype(system, atSystemRoleStereotype);
		} else {
			ProfileAPI.unapplyProfile(system.eResource(), atProfile);
			throw new RuntimeException("System-role \""
					+ atSystemRoleStereotype.getName()
					+ "\" not applicable to system \"" + system.getEntityName()
					+ "\"");
		}

	}

	/**
	 * Checks whether there is only one selection and if its a {@link System}.
	 */
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
