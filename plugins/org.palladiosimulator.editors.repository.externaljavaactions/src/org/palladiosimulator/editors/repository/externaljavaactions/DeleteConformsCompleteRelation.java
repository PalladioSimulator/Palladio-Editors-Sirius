package org.palladiosimulator.editors.repository.externaljavaactions;

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
import org.palladiosimulator.pcm.repository.ProvidesComponentType;


public class DeleteConformsCompleteRelation implements IExternalJavaAction {

	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		
		for (Entry<String, Object> entry : parameters.entrySet()) {
			DEdge value = (DEdge) entry.getValue();

			EObject source = value.getTarget();
			BasicComponent bc = (BasicComponent) source;
			
			EdgeTarget targetNode = value.getTargetNode(); 
			CompleteComponentType cc = (CompleteComponentType) (((DDiagramElement) targetNode).getTarget());
			
			
			bc.getParentCompleteComponentTypes().remove(cc);
			
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
