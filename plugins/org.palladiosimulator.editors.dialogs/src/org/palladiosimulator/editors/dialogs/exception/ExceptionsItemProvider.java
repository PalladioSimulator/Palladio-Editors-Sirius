package org.palladiosimulator.editors.dialogs.exception;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

import org.palladiosimulator.pcm.repository.ExceptionType;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionsItemProvider.
 * 
 * @author roman
 */
public class ExceptionsItemProvider extends ItemProviderDecorator implements ITableItemLabelProvider,
        IItemLabelProvider {

    /**
     * Inherited default constructor.
     * 
     * @param adapterFactory
     *            the adapter factory
     */
    public ExceptionsItemProvider(AdapterFactory adapterFactory) {
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
        if (columnIndex == ExceptionsDialog.ICON_COLUMN_INDEX) {
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
        ExceptionType exceptionType = (ExceptionType) element;

        switch (columnIndex) {
        case ExceptionsDialog.ICON_COLUMN_INDEX:
            break;
        case ExceptionsDialog.CONTEXT_COLUMN_INDEX:
            result = ((Signature) exceptionType.eContainer()).getEntityName();
            break;
        case ExceptionsDialog.NAME_COLUMN_INDEX:
            result = getExceptionName(exceptionType);
            break;
        default:
            break;
        }
        return result == null ? "" : result;
    }

    /**
     * Gets the exception name.
     * 
     * @param type
     *            the type
     * @return the exception name
     */
    private String getExceptionName(ExceptionType type) {
        String exceptionName = type.getExceptionName();
        if (exceptionName != null) {
            return exceptionName;
        }
        return "null";
    }
}
