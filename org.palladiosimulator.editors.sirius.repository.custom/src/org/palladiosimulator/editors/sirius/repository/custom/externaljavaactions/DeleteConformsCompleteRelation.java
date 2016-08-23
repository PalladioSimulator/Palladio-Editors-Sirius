package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompleteComponentType;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;

public class DeleteConformsCompleteRelation implements IExternalJavaAction {

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        	DEdge edge = (DEdge) parameters.get("elementView");

            ImplementationComponentType source = (ImplementationComponentType) edge.getTarget();

            EdgeTarget targetNode = edge.getTargetNode();
            CompleteComponentType cc = (CompleteComponentType) (((DDiagramElement) targetNode).getTarget());

            source.getParentCompleteComponentTypes().remove(cc);

    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

}
