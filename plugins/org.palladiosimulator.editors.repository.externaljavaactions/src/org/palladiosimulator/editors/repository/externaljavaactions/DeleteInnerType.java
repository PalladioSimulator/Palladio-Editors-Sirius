package org.palladiosimulator.editors.repository.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.PrimitiveTypeEnum;
import org.palladiosimulator.pcm.repository.impl.PrimitiveDataTypeImpl;

public class DeleteInnerType implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		for (Entry<String, Object> entry : parameters.entrySet()) {
			DNodeList node = (DNodeList) entry.getValue();
			EObject target = node.getTarget();
			CollectionDataType collectionDataType = (CollectionDataType) target;
			collectionDataType.setInnerType_CollectionDataType(null);
			
		}
		for (EObject o : selections) {
			System.out.println(o.getClass());
		}

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
