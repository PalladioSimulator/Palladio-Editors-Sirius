package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompleteComponentType;
import org.palladiosimulator.pcm.repository.ProvidesComponentType;


public class DeleteConformsProvidesRelation implements IExternalJavaAction {

	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		
			DEdge edge = (DEdge) parameters.get("elementView");

			EObject source = edge.getTarget();
			CompleteComponentType cc = (CompleteComponentType) source;
			
			EdgeTarget targetNode = edge.getTargetNode(); 
			ProvidesComponentType pc = (ProvidesComponentType) (((DDiagramElement) targetNode).getTarget());
			
			
			cc.getParentProvidesComponentTypes().remove(pc);
			
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
