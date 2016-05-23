package org.palladiosimulator.editors.repository.externaljavaactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;


public class DeleteInnerDeclaration implements IExternalJavaAction {

	
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		
		for (Entry<String, Object> entry : parameters.entrySet()) {
			DEdge value = (DEdge) entry.getValue();

			EObject source = value.getTarget();
			CompositeDataType composite = (CompositeDataType) source;
			
			
			EdgeTarget targetNode = value.getTargetNode();
			DataType targetType = null;
			EObject target = ((DDiagramElement) targetNode).getTarget();
			if (target instanceof CollectionDataType) {
				targetType = (CollectionDataType) target;
			}
			else if (target instanceof CompositeDataType) {
				targetType = (CompositeDataType) target;
			}
			
			EList<InnerDeclaration> innerDeclarations = composite.getInnerDeclaration_CompositeDataType();
			Iterator<InnerDeclaration> it = innerDeclarations.iterator();
			while (it.hasNext()) {
				InnerDeclaration innerDeclaration = (InnerDeclaration) it.next();
				if (innerDeclaration.getDatatype_InnerDeclaration().equals(targetType))
					it.remove();
			}
			
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
