package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.impl.DNodeImpl;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.impl.AbstractUserActionImpl;
public class ReconnectSourceOfUsageFlow implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        AbstractUserAction oldSource = (AbstractUserAction) parameters.get("source");
        AbstractUserAction newSource = (AbstractUserAction) parameters.get("target");


        DNodeImpl otherEnd = (DNodeImpl) parameters.get("otherEnd");

        newSource.setSuccessor((AbstractUserActionImpl) otherEnd.getTarget());
        oldSource.setSuccessor(null);
    }

}
