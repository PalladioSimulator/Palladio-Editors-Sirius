/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.architecturaltemplates.type.PCMFileExtensions;
import org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PCM Output Completion Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.impl.PCMOutputCompletionParameterImpl#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PCMOutputCompletionParameterImpl extends CompletionParameterImpl implements PCMOutputCompletionParameter {
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
     * The cached value of the '{@link #getFileExtension() <em>File Extension</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFileExtension()
     * @generated
     * @ordered
     */
    protected PCMFileExtensions fileExtension = FILE_EXTENSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PCMOutputCompletionParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PCM_OUTPUT_COMPLETION_PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PCMFileExtensions getFileExtension() {
        return this.fileExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFileExtension(final PCMFileExtensions newFileExtension) {
        final PCMFileExtensions oldFileExtension = this.fileExtension;
        this.fileExtension = newFileExtension == null ? FILE_EXTENSION_EDEFAULT : newFileExtension;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION, oldFileExtension, this.fileExtension));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION:
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
        case TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION:
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
        case TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION:
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
        case TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION:
            return this.fileExtension != FILE_EXTENSION_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (fileExtension: ");
        result.append(this.fileExtension);
        result.append(')');
        return result.toString();
    }

} // PCMOutputCompletionParameterImpl
