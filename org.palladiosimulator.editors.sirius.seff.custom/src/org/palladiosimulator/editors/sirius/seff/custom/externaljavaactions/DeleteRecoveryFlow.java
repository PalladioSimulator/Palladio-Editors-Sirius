package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;

public class DeleteRecoveryFlow implements IExternalJavaAction {


	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
			DEdge edge = (DEdge) parameters.get("elementView");

			RecoveryActionBehaviour source = (RecoveryActionBehaviour) edge.getTarget();
			
			EdgeTarget targetNode = edge.getTargetNode(); 
			RecoveryActionBehaviour target = (RecoveryActionBehaviour) (((DDiagramElement) targetNode).getTarget());

			source.getFailureHandlingAlternatives__RecoveryActionBehaviour().remove(target);
			

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
