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
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.PCMBlackboardCompletionParameterImpl#getFileExtension <em>File Extension</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PCMBlackboardCompletionParameterImpl extends CompletionParameterImpl
        implements PCMBlackboardCompletionParameter {

    /**
     * The default value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getFileExtension()
     * @generated
     * @ordered
     */
    protected static final PCMFileExtensions FILE_EXTENSION_EDEFAULT = PCMFileExtensions.SYSTEM;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected PCMBlackboardCompletionParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PCMFileExtensions getFileExtension() {
        return (PCMFileExtensions) eDynamicGet(TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFileExtension(PCMFileExtensions newFileExtension) {
        eDynamicSet(TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION,
                TypePackage.Literals.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION, newFileExtension);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            return getFileExtension();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            setFileExtension((PCMFileExtensions) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            setFileExtension(FILE_EXTENSION_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION:
            return getFileExtension() != FILE_EXTENSION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

} // PCMBlackboardCompletionParameterImpl
