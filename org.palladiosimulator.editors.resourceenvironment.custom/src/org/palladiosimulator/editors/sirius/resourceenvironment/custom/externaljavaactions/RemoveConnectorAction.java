package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

/**
 * This external java action is used in resourceenvironment.odesign in the
 * Network section to delete a connector between a LinkingResource and a
 * ResourceContainer. Note that The elementView is passed as parameter when the
 * delete action is triggered.
 * 
 * @author Amine Kechaou
 *
 */
public class RemoveConnectorAction implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		DEdge edge = (DEdge) parameters.get("elementView");
		LinkingResource linkingResource = (LinkingResource) edge.getTarget();

		EdgeTarget targetNode = edge.getTargetNode();
		ResourceContainer resourceContainer = (ResourceContainer) (((DDiagramElement) targetNode).getTarget());

		linkingResource.getConnectedResourceContainers_LinkingResource().remove(resourceContainer);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
