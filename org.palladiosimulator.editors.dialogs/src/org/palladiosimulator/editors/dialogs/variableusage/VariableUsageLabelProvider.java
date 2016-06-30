/**
 * 
 */
package org.palladiosimulator.editors.dialogs.variableusage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.palladiosimulator.editors.dialogs.datatype.DataTypeContainer;
import org.palladiosimulator.editors.dialogs.datatype.InnerDeclarationContainer;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;

// TODO: Auto-generated Javadoc
/**
 * The Class VariableUsageLabelProvider.
 * 
 * @author admin
 */
public class VariableUsageLabelProvider extends ItemProviderDecorator implements IItemLabelProvider {

    /**
     * Instantiates a new variable usage label provider.
     * 
     * @param adapterFactory
     *            the adapter factory
     */
    public VariableUsageLabelProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getImage(java.lang.Object)
     */
    @Override
    public Object getImage(Object object) {

        if (object instanceof DataTypeContainer) {
            DataTypeContainer treeType = (DataTypeContainer) object;
            return super.getImage(treeType.getObject());
        }

        if (object instanceof InnerDeclarationContainer) {
            InnerDeclarationContainer treeDeclaration = (InnerDeclarationContainer) object;
            return super.getImage(treeDeclaration.getObject());
        }

        return super.getImage(object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getText(java.lang.Object)
     */
    @Override
    public String getText(Object object) {

        if (object instanceof Parameter) {
            Parameter p = (Parameter) object;
            String result = p.getParameterName();
            if (p.getDataType__Parameter() != null) {
                result += ": " + super.getText(p.getDataType__Parameter());
            }
            return result;
        }

        if (object instanceof DataTypeContainer) {
            DataType dataType = getTreeTypeObject(object);
            return "INNER: " + getNameDataType(dataType);
        }

        if (object instanceof InnerDeclarationContainer) {
            InnerDeclaration declaration = getTreeDeclarationObject(object);
            DataType dataType = declaration.getDatatype_InnerDeclaration();

            String typeName = "";

            if (dataType instanceof CollectionDataType) {
                CollectionDataType collType = (CollectionDataType) dataType;
                typeName = collType.getEntityName();
            }
            if (dataType instanceof CompositeDataType) {
                CompositeDataType compType = (CompositeDataType) dataType;
                typeName = compType.getEntityName();
            }
            if (dataType instanceof PrimitiveDataType) {
                PrimitiveDataType primType = (PrimitiveDataType) dataType;
                typeName = primType.getType().getName();
            }

            return declaration.getEntityName() + ": " + typeName;
        }
        return super.getText(object);
    }

    /**
     * Gets the tree type object.
     * 
     * @param object
     *            the object
     * @return - return the DdataType from TreeType-Object
     */
    private DataType getTreeTypeObject(Object object) {
        DataTypeContainer treeType = (DataTypeContainer) object;
        return (DataType) treeType.getObject();
    }

    /**
     * Gets the tree declaration object.
     * 
     * @param object
     *            the object
     * @return - return the InnerDeclaration from TreeDeclaration-Object
     */
    private InnerDeclaration getTreeDeclarationObject(Object object) {
        InnerDeclarationContainer treeDeclaration = (InnerDeclarationContainer) object;
        return (InnerDeclaration) treeDeclaration.getObject();
    }

    /**
     * Gets the name data type.
     * 
     * @param dataType
     *            the data type
     * @return the name data type
     */
    private String getNameDataType(DataType dataType) {
        if (dataType != null) {
            return super.getText(dataType);
        }
        return "null";
    }
}
