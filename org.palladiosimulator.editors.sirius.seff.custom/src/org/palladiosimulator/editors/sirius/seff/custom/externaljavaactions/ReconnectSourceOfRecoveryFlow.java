package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;

public class ReconnectSourceOfRecoveryFlow implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        RecoveryActionBehaviour oldSource = (RecoveryActionBehaviour) parameters.get("source");
        RecoveryActionBehaviour newSource = (RecoveryActionBehaviour) parameters.get("target");
        
        // Finding the target that the edge is going to
        DEdge edge = (DEdge) parameters.get("edgeView");
        RecoveryActionBehaviour target = (RecoveryActionBehaviour) (((DDiagramElement) edge.getTargetNode())
                .getTarget());

        oldSource.getFailureHandlingAlternatives__RecoveryActionBehaviour().remove(target);
        newSource.getFailureHandlingAlternatives__RecoveryActionBehaviour().add(target);
        

    }

}
