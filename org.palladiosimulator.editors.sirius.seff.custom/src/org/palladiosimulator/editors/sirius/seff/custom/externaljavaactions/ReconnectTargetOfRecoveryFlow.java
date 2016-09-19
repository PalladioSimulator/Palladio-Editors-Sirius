package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;

public class ReconnectTargetOfRecoveryFlow implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        // RecoveryActionBehaviour source = (RecoveryActionBehaviour) parameters.get("source");
        RecoveryActionBehaviour newTarget = (RecoveryActionBehaviour) parameters.get("target");
        RecoveryActionBehaviour element = (RecoveryActionBehaviour) parameters.get("element");
        
        // Removing the original edge
        DEdge edge = (DEdge) parameters.get("edgeView");

        RecoveryActionBehaviour source = (RecoveryActionBehaviour) edge.getTarget();

        EdgeTarget targetNode = edge.getTargetNode();
        RecoveryActionBehaviour target = (RecoveryActionBehaviour) (((DDiagramElement) targetNode).getTarget());

        source.getFailureHandlingAlternatives__RecoveryActionBehaviour().remove(target);

        // Adding it as new connection
        element.getFailureHandlingAlternatives__RecoveryActionBehaviour().add(newTarget);
        

    }

}
