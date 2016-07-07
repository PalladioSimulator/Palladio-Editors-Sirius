package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.CompleteComponentType;

public class DeleteConformsCompleteRelation implements IExternalJavaAction {

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        for (final Entry<String, Object> entry : parameters.entrySet()) {
            final DEdge value = (DEdge) entry.getValue();

            final EObject source = value.getTarget();
            final BasicComponent bc = (BasicComponent) source;

            final EdgeTarget targetNode = value.getTargetNode();
            final CompleteComponentType cc = (CompleteComponentType) (((DDiagramElement) targetNode).getTarget());

            bc.getParentCompleteComponentTypes().remove(cc);

        }
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

}
