/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EClass;
import org.scaledl.architecturaltemplates.type.GenericBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Generic Blackboard Completion Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.impl.GenericBlackboardCompletionParameterImpl#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenericBlackboardCompletionParameterImpl extends CompletionParameterImpl implements
GenericBlackboardCompletionParameter {

    /**
     * The default value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFileExtension()
     * @generated
     * @ordered
     */
    protected static final String FILE_EXTENSION_EDEFAULT = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GenericBlackboardCompletionParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.GENERIC_BLACKBOARD_COMPLETION_PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFileExtension() {
        return (String) this.eDynamicGet(TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFileExtension(final String newFileExtension) {
        this.eDynamicSet(TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, newFileExtension);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            return this.getFileExtension();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            this.setFileExtension((String) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            this.setFileExtension(FILE_EXTENSION_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            return FILE_EXTENSION_EDEFAULT == null ? this.getFileExtension() != null : !FILE_EXTENSION_EDEFAULT
            .equals(this.getFileExtension());
        }
        return super.eIsSet(featureID);
    }

} // GenericBlackboardCompletionParameterImpl
