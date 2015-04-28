package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import de.uka.ipd.sdq.pcm.system.System;

/**
 * A Sirius action that is used to remove an ArchitecturalTemplate Role from a
 * {@link System}.
 * 
 * @author max
 *
 */
public class RemoveATAction implements IExternalJavaAction {

	/**
	 * Removes the selected Architectural Template from the {@link System}
	 */
	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		final StereotypeApplication stereotypeApplication = (StereotypeApplication) selections.stream().findFirst().get();
		if (!(stereotypeApplication.getAppliedTo() instanceof System)) {
			return;
		}
		final System system = (System) stereotypeApplication.getAppliedTo();
		system.removeStereotypeApplication(stereotypeApplication);
	}

	/**
	 * Checks whether there is only one selection and if its a {@link StereotypeApplication}.
	 */
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		if (selections.size() != 1) {
			return false;
		}
		for (EObject object : selections) {
			return object instanceof StereotypeApplication;
		}
		return false;
	}

}
