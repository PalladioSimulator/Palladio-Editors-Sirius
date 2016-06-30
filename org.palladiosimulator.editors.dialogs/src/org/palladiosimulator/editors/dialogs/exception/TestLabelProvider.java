/**
 * 
 */
package org.palladiosimulator.editors.dialogs.exception;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import org.palladiosimulator.pcm.repository.ExceptionType;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * The Class TestLabelProvider.
 * 
 * @author admin
 */
public class TestLabelProvider implements ITableLabelProvider {

    /**
     * Gets the column image.
     * 
     * @param element
     *            the element
     * @param columnIndex
     *            the column index
     * @return the column image
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public Image getColumnImage(Object element, int columnIndex) {
        // TODO Auto-generated method stub
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
     */
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

    /**
     * Adds the listener.
     * 
     * @param listener
     *            the listener
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.
     *      ILabelProviderListener)
     */
    public void addListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

    /**
     * Dispose.
     * 
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        // TODO Auto-generated method stub

    }

    /**
     * Checks if is label property.
     * 
     * @param element
     *            the element
     * @param property
     *            the property
     * @return true, if is label property
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
     *      java.lang.String)
     */
    public boolean isLabelProperty(Object element, String property) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Removes the listener.
     * 
     * @param listener
     *            the listener
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.
     *      ILabelProviderListener)
     */
    public void removeListener(ILabelProviderListener listener) {
        // TODO Auto-generated method stub

    }

}
