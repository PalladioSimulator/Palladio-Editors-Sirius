package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
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

        DRepresentationElement otherEnd = (DRepresentationElement) parameters.get("otherEnd");

        newSource.setSuccessor_AbstractAction((AbstractAction) otherEnd.getTarget());
        oldSource.setSuccessor_AbstractAction(null);
    }

}
