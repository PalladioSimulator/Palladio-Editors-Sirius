package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompleteComponentType;
import org.palladiosimulator.pcm.repository.ProvidesComponentType;


public class DeleteConformsProvidedRelation implements IExternalJavaAction {

	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		
		for (Entry<String, Object> entry : parameters.entrySet()) {
			DEdge value = (DEdge) entry.getValue();

			EObject source = value.getTarget();
			CompleteComponentType cc = (CompleteComponentType) source;
			
			EdgeTarget targetNode = value.getTargetNode(); 
			ProvidesComponentType pc = (ProvidesComponentType) (((DDiagramElement) targetNode).getTarget());
			
			
			cc.getParentProvidesComponentTypes().remove(pc);
			
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
