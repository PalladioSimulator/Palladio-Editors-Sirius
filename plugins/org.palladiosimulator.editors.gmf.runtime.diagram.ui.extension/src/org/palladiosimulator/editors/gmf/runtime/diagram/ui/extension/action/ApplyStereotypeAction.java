package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.mdsdprofiles.ui.dialogs.StereotypeSelectionDialog;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;

/**
 * A sirius action for selecting and applying a {@link Stereotype} to the
 * selected object.
 * 
 * @author Max Schettler
 *
 */
public class ApplyStereotypeAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		if (selections.size() != 1)
			return false;
		final EObject selection = selections.iterator().next();
		return selection instanceof AssemblyContext || selection instanceof ComposedProvidingRequiringEntity;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final EObject targetEObject = selections.iterator().next();
		
		if (!ProfileAPI.hasProfileApplication(targetEObject.eResource())) {
			// TODO alternative: create profileapplication and present empty list
			return;
		}

		final StereotypeSelectionDialog stereotypeSelectionDialog = new StereotypeSelectionDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

		stereotypeSelectionDialog.setElements(StereotypeAPI.getApplicableStereotypes(targetEObject).toArray());
		
		if (stereotypeSelectionDialog.open() == Dialog.OK) {
			final Stereotype selectedStereotype = stereotypeSelectionDialog.getResultStereotype();
			
			StereotypeAPI.applyStereotype(targetEObject, selectedStereotype);
		}
		
	}

}
