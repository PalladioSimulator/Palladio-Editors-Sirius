package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;

public class ReconnectSourceOfContainsRelation implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		CompositeDataType oldSource = (CompositeDataType) parameters.get("source");
		CompositeDataType newSource = (CompositeDataType) parameters.get("target");
		
		
		
		DNodeList otherEnd = (DNodeList) parameters.get("otherEnd");
		DataType target = (DataType) otherEnd.getTarget();
		
		InnerDeclaration innerDeclaration = (InnerDeclaration) parameters.get("instance");
		
		EList<InnerDeclaration> newInnerDeclarations = newSource.getInnerDeclaration_CompositeDataType();
		
		boolean found = false;
		
		Iterator<InnerDeclaration> it = newInnerDeclarations.iterator();
		while (it.hasNext()) {
			InnerDeclaration current = (InnerDeclaration) it.next();
			if (current.getDatatype_InnerDeclaration() != null && current.getDatatype_InnerDeclaration().equals(target))
				found = true;
		}
		
		if(found){
			newInnerDeclarations.remove(innerDeclaration);
		}
		else {
			innerDeclaration.setDatatype_InnerDeclaration(target);
		}
		
		
		EList<InnerDeclaration> oldInnerDeclarations = oldSource.getInnerDeclaration_CompositeDataType();
		it = oldInnerDeclarations.iterator();
		while (it.hasNext()) {
			InnerDeclaration current = (InnerDeclaration) it.next();
			if (current.getDatatype_InnerDeclaration().equals(target))
				it.remove();
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
