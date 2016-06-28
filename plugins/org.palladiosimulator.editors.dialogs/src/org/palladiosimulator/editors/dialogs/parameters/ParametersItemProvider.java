package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.palladiosimulator.editors.dialogs.datatype.ParameterRepresentation;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * This class is a decorator for the generated EMF.Edit item providers. It provides item providers
 * which are used in the operations tab of the tabbed properties sheet when editing interfaces. It
 * implements ITableItemLabelProvider to display the given EObject in a tabular form. Additionally
 * it provided the labels by partcial delegation to the original IItemLabelProvider.
 * 
 * @author Roman Andrej
 */
public class ParametersItemProvider extends ItemProviderDecorator implements ITableItemLabelProvider,
        IItemLabelProvider {

    /**
     * Inherited default constructor.
     * 
     * @param adapterFactory
     *            the adapter factory
     */
    public ParametersItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * Gets the column image.
     * 
     * @param object
     *            the object
     * @param columnIndex
     *            the column index
     * @return the column image
     * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getColumnImage(java.lang.Object,
     *      int) Get the icon by delegation if the first column is displayed
     */
    @Override
    public Object getColumnImage(Object object, int columnIndex) {
        if (columnIndex == CreateEditorContents.ICON_COLUMN_INDEX) {
            return this.getImage(object);
        }
        return null;
    }

    /**
     * Gets the column text.
     * 
     * @param element
     *            the element
     * @param columnIndex
     *            the column index
     * @return the column text
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     *      Format the columns with the given index constant as string text for displaying
     */
    @Override
    public String getColumnText(Object element, int columnIndex) {
        String result = "";
        Parameter parameter = (Parameter) element;

        switch (columnIndex) {
        case CreateEditorContents.ICON_COLUMN_INDEX:
            break;
        case CreateEditorContents.CONTEXT_COLUMN_INDEX:
            result = ((Signature) parameter.eContainer()).getEntityName();
            break;
        case CreateEditorContents.NAME_COLUMN_INDEX:
            if (parameter.getParameterName() != null) {
                result = parameter.getParameterName();
            } else {
                result = "null";
            }
            break;
        case CreateEditorContents.TYPE_COLUMN_INDEX:
            result = ParameterRepresentation.dataTypeToString(parameter.getDataType__Parameter());
            break;
        default:
            break;
        }
        return result;
    }
}
