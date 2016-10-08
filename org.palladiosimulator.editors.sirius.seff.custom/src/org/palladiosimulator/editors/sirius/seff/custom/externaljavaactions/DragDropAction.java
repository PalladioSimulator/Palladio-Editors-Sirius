package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;

public class DragDropAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        // BranchTransition
        if (parameters.get("element") instanceof AbstractBranchTransition
                && parameters.get("oldSemanticContainer") instanceof BranchAction
                && parameters.get("newSemanticContainer") instanceof BranchAction) {
            AbstractBranchTransition element = (AbstractBranchTransition) parameters.get("element");
            BranchAction oldSemanticContainer = (BranchAction) parameters.get("oldSemanticContainer");
            BranchAction newSemanticContainer = (BranchAction) parameters.get("newSemanticContainer");
            if (newSemanticContainer.getBranches_Branch().add(element)) {
                oldSemanticContainer.getBranches_Branch().remove(element);
            }
        }
        // All Abstract Actions
        else if (parameters.get("element") instanceof AbstractAction
                && parameters.get("oldSemanticContainer") instanceof ResourceDemandingBehaviour
                && parameters.get("newSemanticContainer") instanceof ResourceDemandingBehaviour) {
            AbstractAction element = (AbstractAction) parameters.get("element");
            ResourceDemandingBehaviour oldSemanticContainer = (ResourceDemandingBehaviour) parameters
                    .get("oldSemanticContainer");
            ResourceDemandingBehaviour newSemanticContainer = (ResourceDemandingBehaviour) parameters
                    .get("newSemanticContainer");
            if (newSemanticContainer.getSteps_Behaviour().add(element)) {
                oldSemanticContainer.getSteps_Behaviour().remove(element);
            }
        }
	}
}
