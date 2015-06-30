package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.system.System;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.ui.RoleStereotypeSelectionDialog;

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

	/**
	 * Message displayed in the selection dialog.
	 */
	private static final String DIALOG_MESSAGE = "Select Role-Stereotype to apply to the AssemblyContext";
	
	/**
	 * The key of the {@link AssemblyContext} parameter.
	 */
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

		LinkedList<Stereotype> unapplyedStereotypes = new LinkedList<>();

		for (Stereotype stereotype : StereotypeAPI.getApplicableStereotypes(assemblyContext))
		{
            if (!StereotypeAPI.isStereotypeApplied(assemblyContext, stereotype.getName())) {
            	unapplyedStereotypes.add(stereotype);
            }
		}

		
		final RoleStereotypeSelectionDialog dialog = new RoleStereotypeSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		dialog.setMessage(DIALOG_MESSAGE);

		dialog.setElements(unapplyedStereotypes.toArray(new Stereotype[0]));

		if (dialog.open() != Dialog.OK) { return; }

		final Stereotype selectedRoleStereotype = dialog.getResultRoleStereotype();

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
