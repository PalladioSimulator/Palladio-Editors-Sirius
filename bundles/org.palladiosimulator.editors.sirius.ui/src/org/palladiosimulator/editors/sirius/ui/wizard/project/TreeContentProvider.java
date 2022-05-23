package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.palladiosimulator.architecturaltemplates.AT;
import org.palladiosimulator.architecturaltemplates.Catalog;

/**
 * Provides content from a {@code Map<Catalog, List<AT>} or for some collection that is accepted by
 * {@link ArrayContentProvider#getElements(Object)} to a {@link TreeViewer}.
 * <p>
 * This class will not work correctly if elements other than those are provided.
 * 
 * @author Dominik Werle
 *
 */
public class TreeContentProvider implements ITreeContentProvider {
    @Override
    public boolean hasChildren(Object element) {
        return (element instanceof Map.Entry<?, ?>);
    }

    @Override
    public Object getParent(Object element) {
        return null;
    }

    @Override
    public Object[] getElements(Object inputElement) {
        if ((inputElement instanceof Map)) {
            var typedInputElement = (Map<?, ?>) inputElement;
            return ArrayContentProvider.getInstance()
                .getElements(typedInputElement.entrySet());
        }

        return ArrayContentProvider.getInstance()
            .getElements(inputElement);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object[] getChildren(Object parentElement) {
        if (!(parentElement instanceof Map.Entry<?, ?>))
            return new Object[0];

        var typedParentElement = (Map.Entry<Catalog, List<AT>>) parentElement;
        return ArrayContentProvider.getInstance()
            .getElements(typedParentElement.getValue());
    }
}