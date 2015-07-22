package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;

/**
 * A sirius action for unapplying a {@link Stereotype} from the selected
 * {@link StereotypeApplication}.
 * 
 * @author Max Schettler
 *
 */
public class UnapplyStereotypeAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return selections.size() == 1 && selections.iterator().next() instanceof StereotypeApplication;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		final StereotypeApplication stereotypeApplication = (StereotypeApplication) selections.iterator().next();

		StereotypeAPI.unapplyStereotype(stereotypeApplication.getAppliedTo(), stereotypeApplication.getStereotype());
	}

}
