package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractInternalControlFlowAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingBehaviour;
import org.palladiosimulator.pcm.seff.impl.ResourceDemandingBehaviourImpl;

public class DragDropAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		AbstractAction element = (AbstractAction)parameters.get("element");
		ResourceDemandingBehaviour oldSemanticContainer = (ResourceDemandingBehaviour) parameters.get("oldSemanticContainer");
		ResourceDemandingBehaviour newSemanticContainer = (ResourceDemandingBehaviour)parameters.get("newSemanticContainer");

		if(newSemanticContainer.getSteps_Behaviour().add(element)) {
			oldSemanticContainer.getSteps_Behaviour().remove(element);
		}
	}
}
