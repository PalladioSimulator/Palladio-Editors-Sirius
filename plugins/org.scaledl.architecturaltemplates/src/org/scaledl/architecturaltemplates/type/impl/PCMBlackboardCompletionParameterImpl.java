/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EClass;
import org.scaledl.architecturaltemplates.type.PCMBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMFileExtensions;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PCM Blackboard Completion Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.impl.PCMBlackboardCompletionParameterImpl#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PCMBlackboardCompletionParameterImpl extends CompletionParameterImpl implements
PCMBlackboardCompletionParameter {

    /**
     * The default value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFileExtension()
     * @generated
     * @ordered
     */
    protected static final PCMFileExtensions FILE_EXTENSION_EDEFAULT = PCMFileExtensions.SYSTEM;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PCMBlackboardCompletionParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PCMFileExtensions getFileExtension() {
        return (PCMFileExtensions) this.eDynamicGet(TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFileExtension(final PCMFileExtensions newFileExtension) {
        this.eDynamicSet(TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, newFileExtension);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
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
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            this.setFileExtension((PCMFileExtensions) newValue);
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
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
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
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            return this.getFileExtension() != FILE_EXTENSION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

} // PCMBlackboardCompletionParameterImpl
