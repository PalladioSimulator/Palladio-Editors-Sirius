package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.system.System;

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

	private static final String SELECT_ROLE_DIALOG_MESSAGE = "Select Role to apply to the AssemblyContext";
	private static final String SELECT_ROLE_DIALOG_TITLE = "Select Role";
	private static final String ASSEMBLY_CONTEXT_PARAMETER_KEY = "assemblyContext";

	/**
	 * Asks the user to select a {@link Role} and attaches it to the given
	 * {@link AssemblyContext}.
	 */
	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		final Object parameter = parameters.get(ASSEMBLY_CONTEXT_PARAMETER_KEY);
		if (parameter == null || !(parameter instanceof AssemblyContext)) {
			return;
		}

		final AssemblyContext assemblyContext = (AssemblyContext) parameter;
		final Predicate<Stereotype> notAlreadyApplied = stereotype -> StereotypeAPI
				.getAppliedStereotypes(assemblyContext)
				.stream()
				.noneMatch(
						otherSteretype -> otherSteretype.getName().equals(
								stereotype));

		final ElementListSelectionDialog dialog = new ElementListSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				new LabelProvider() {

					@Override
					public String getText(Object element) {
						return ((Stereotype) element).getName();
					}

				});

		dialog.setTitle(SELECT_ROLE_DIALOG_TITLE);
		dialog.setMessage(SELECT_ROLE_DIALOG_MESSAGE);
		dialog.setElements(StereotypeAPI
				.getApplicableStereotypes(assemblyContext).stream()
				.filter(notAlreadyApplied).toArray());

		if (dialog.open() != Dialog.OK || dialog.getFirstResult() == null) { return; }

		final Stereotype selectedRoleStereotype = (Stereotype) dialog.getFirstResult();

		StereotypeAPI.applyStereotype(assemblyContext,
				selectedRoleStereotype);
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
