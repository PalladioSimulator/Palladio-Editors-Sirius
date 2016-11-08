package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

public class ReconnectLinkingResourceOfConnector implements IExternalJavaAction {

    @Override
    public boolean canExecute(Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(Collection<? extends EObject> selection, Map<String, Object> parameters) {
        DNodeContainer otherEnd = (DNodeContainer) parameters.get("otherEnd");
        ResourceContainer element = (ResourceContainer) otherEnd.getTarget();

        LinkingResource source = (LinkingResource) parameters.get("source");
        LinkingResource target = (LinkingResource) parameters.get("target");

        source.getConnectedResourceContainers_LinkingResource().remove(element);
        target.getConnectedResourceContainers_LinkingResource().add(element);
    }

}
