package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CollectionDataType;

/**
 * This external java action is used to delete the Inner Type of a
 * CollectionDataType without deleting the corresponding PrimitiveDataType
 * from the Model containing it. 
 * It is used either when directly deleting the inner type or when deleting the CollectionDataType.
 *  
 * @author Amine Kechaou
 *
 */
public class DeleteInnerType implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		CollectionDataType collectionDataType = (CollectionDataType) parameters.get("collection");
		collectionDataType.setInnerType_CollectionDataType(null);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
