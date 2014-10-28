/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.scaledl.architecturaltemplates.type.QVTOCompletion;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>QVTO Completion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.QVTOCompletionImpl#getQvtoFileURI <em>
 * Qvto File URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QVTOCompletionImpl extends CompletionImpl implements QVTOCompletion {
    /**
     * The default value of the '{@link #getQvtoFileURI() <em>Qvto File URI</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getQvtoFileURI()
     * @generated
     * @ordered
     */
    protected static final String QVTO_FILE_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQvtoFileURI() <em>Qvto File URI</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getQvtoFileURI()
     * @generated
     * @ordered
     */
    protected String qvtoFileURI = QVTO_FILE_URI_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected QVTOCompletionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.QVTO_COMPLETION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getQvtoFileURI() {
        return this.qvtoFileURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setQvtoFileURI(final String newQvtoFileURI) {
        final String oldQvtoFileURI = this.qvtoFileURI;
        this.qvtoFileURI = newQvtoFileURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.QVTO_COMPLETION__QVTO_FILE_URI,
                    oldQvtoFileURI, this.qvtoFileURI));
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
        case TypePackage.QVTO_COMPLETION__QVTO_FILE_URI:
            return this.getQvtoFileURI();
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
        case TypePackage.QVTO_COMPLETION__QVTO_FILE_URI:
            this.setQvtoFileURI((String) newValue);
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
        case TypePackage.QVTO_COMPLETION__QVTO_FILE_URI:
            this.setQvtoFileURI(QVTO_FILE_URI_EDEFAULT);
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
        case TypePackage.QVTO_COMPLETION__QVTO_FILE_URI:
            return QVTO_FILE_URI_EDEFAULT == null ? this.qvtoFileURI != null : !QVTO_FILE_URI_EDEFAULT
                    .equals(this.qvtoFileURI);
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
        result.append(" (qvtoFileURI: ");
        result.append(this.qvtoFileURI);
        result.append(')');
        return result.toString();
    }

} // QVTOCompletionImpl
