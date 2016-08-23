package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;

public class DeleteContainsRelation implements IExternalJavaAction {

	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		DEdge edge = (DEdge) parameters.get("elementView");

		EObject source = edge.getTarget();
		CompositeDataType composite = (CompositeDataType) source;

		EdgeTarget targetNode = edge.getTargetNode();
		DataType target = (DataType) ((DDiagramElement) targetNode).getTarget();
		EList<InnerDeclaration> innerDeclarations = composite.getInnerDeclaration_CompositeDataType();
		Iterator<InnerDeclaration> it = innerDeclarations.iterator();
		while (it.hasNext()) {
			InnerDeclaration innerDeclaration = (InnerDeclaration) it.next();
			if (innerDeclaration.getDatatype_InnerDeclaration().equals(target))
				it.remove();
		}

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
