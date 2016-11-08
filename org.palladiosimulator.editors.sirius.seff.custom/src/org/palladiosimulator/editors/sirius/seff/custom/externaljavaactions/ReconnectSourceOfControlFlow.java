package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.seff.AbstractAction;

public class ReconnectSourceOfControlFlow implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        AbstractAction oldSource = (AbstractAction) parameters.get("source");
        AbstractAction newSource = (AbstractAction) parameters.get("target");

        DDiagramElement otherEnd = (DDiagramElement) parameters.get("otherEnd");

        // Prevent self connecting os edges
        if (!otherEnd.getTarget().equals(newSource)) {
            newSource.setSuccessor_AbstractAction((AbstractAction) otherEnd.getTarget());
            oldSource.setSuccessor_AbstractAction(null);
        }
    }

}
