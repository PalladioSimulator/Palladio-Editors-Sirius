package org.palladiosimulator.editors.repository.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CollectionDataType;

/**
 * This external java action is used to delete the Inner Type of a CollectionDataType without
 * deleting the corresponding PrimitiveDataType from the Model containing it. It is used either when
 * directly deleting the inner type or when deleting the CollectionDataType.
 * 
 * @author Amine Kechaou
 *
 */
public class DeleteInnerType implements IExternalJavaAction {

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        for (final Entry<String, Object> entry : parameters.entrySet()) {
            final DNodeList node = (DNodeList) entry.getValue();
            final EObject target = node.getTarget();
            final CollectionDataType collectionDataType = (CollectionDataType) target;
            collectionDataType.setInnerType_CollectionDataType(null);

        }

    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        return true;
    }

}
