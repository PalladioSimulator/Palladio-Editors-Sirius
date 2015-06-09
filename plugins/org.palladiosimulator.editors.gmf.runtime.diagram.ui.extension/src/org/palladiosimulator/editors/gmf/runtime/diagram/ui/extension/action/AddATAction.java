package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofile.registry.IProfileRegistry;
import org.palladiosimulator.editors.util.at.ArchitecturalTemplateHelpers;
import org.palladiosimulator.editors.util.dialogs.ArchitecturalTemplateSelectEObjectDialog;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.type.AT;

import de.uka.ipd.sdq.pcm.system.System;

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
	 * The name of the parameter to be given. This is an arbitrary object from
	 * the editor to get the corresponding {@link Session}.
	 */
	private static final String SYSTEM_PARAMETER_KEY = "system";
	private static final String PROFILE_SELECTION_DIALOG_TITLE = "Select Architectural Template";
	private static final String PROFILE_SELECTION_DIALOG_EMPTY_LIST_MESSAGE = "No Architectural Templates are registered";
	private static final String PROFILE_SELECTION_DIALOG_EMPTY_SELECTION_MESSAGE = "You need to select an Architectural Template";
	/**
	 * TODO extract to ArchitecturalTemplateAPI
	 */
	private static final String SYSTEM_ROLE_NAME_SUFFIX = "System";

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

		// TODO Extract to API
		final Predicate<Stereotype> isRole = stereotype -> Objects
				.nonNull(stereotype
						.getTaggedValue(ArchitecturalTemplateHelpers.ROLE_URI));
		final Predicate<Stereotype> isSystemRole = isRole.and(stereotype -> stereotype.getName().endsWith(SYSTEM_ROLE_NAME_SUFFIX));
		final Predicate<Profile> isArchitecturalTemplate = profile -> profile
				.getStereotypes()
				.stream()
				.filter(isSystemRole).findAny()
				.isPresent();

		// TODO create subclass for selecting a AT-profiles
		ElementListSelectionDialog profileSelectionDialog = new ElementListSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				new LabelProvider() {
					@Override
					public String getText(Object element) {
						return ((Profile) element).getName();
					}
				});


		profileSelectionDialog.setElements(IProfileRegistry.eINSTANCE
				.getRegisteredProfiles().stream()
				.filter(isArchitecturalTemplate).toArray());

		profileSelectionDialog.setTitle(PROFILE_SELECTION_DIALOG_TITLE);
		profileSelectionDialog
				.setEmptyListMessage(PROFILE_SELECTION_DIALOG_EMPTY_LIST_MESSAGE);
		profileSelectionDialog
				.setEmptySelectionMessage(PROFILE_SELECTION_DIALOG_EMPTY_SELECTION_MESSAGE);

		if (profileSelectionDialog.open() != Dialog.OK
				|| profileSelectionDialog.getResult().length != 1) {
			return;
		}

		final Profile atProfile = (Profile) profileSelectionDialog.getResult()[0];

		// TODO extract this to an API
		if (!isArchitecturalTemplate.test(atProfile)) {
			throw new RuntimeException("Selected Profile \"" + atProfile
					+ "\" does not only define roles");
		}

		ProfileAPI.applyProfile(system.eResource(), atProfile);
		EPackage.Registry.INSTANCE.put(atProfile.getNsURI(), atProfile);

		// Role getSystemRole(final AT at)
		final List<Stereotype> atSystemRoleStereotypes = atProfile
				.getStereotypes().stream()
				.filter(isSystemRole)
				.collect(Collectors.toList());

		if (atSystemRoleStereotypes.size() == 0) {
			throw new RuntimeException(
					"No system-role found in Architectural Template \""
							+ atProfile.getName() + "\"");
		}
		if (atSystemRoleStereotypes.size() > 1) {
			throw new RuntimeException("Architectural Template \""
					+ atProfile.getName() + "\" defines multiple system-roles");
		}

		final Stereotype atSystemRoleStereotype = atSystemRoleStereotypes
				.get(0);

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
