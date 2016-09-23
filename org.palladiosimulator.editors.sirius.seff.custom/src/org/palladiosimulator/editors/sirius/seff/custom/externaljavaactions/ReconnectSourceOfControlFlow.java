package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.sirius.diagram.impl.DNodeContainerImpl;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.palladiosimulator.pcm.seff.AbstractInternalControlFlowAction;

public class ReconnectSourceOfControlFlow implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

        AbstractInternalControlFlowAction oldSource = (AbstractInternalControlFlowAction) parameters.get("source");
        AbstractInternalControlFlowAction newSource = (AbstractInternalControlFlowAction) parameters.get("target");

        DRepresentationElement otherEnd = (DRepresentationElement) parameters.get("otherEnd");

        newSource.setSuccessor_AbstractAction((AbstractInternalControlFlowAction) otherEnd.getTarget());
        oldSource.setSuccessor_AbstractAction(null);
    }

}
