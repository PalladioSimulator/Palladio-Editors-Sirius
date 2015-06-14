/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EClass;
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
        return (String) this.eDynamicGet(TypePackage.QVTO_COMPLETION__QVTO_FILE_URI,
                TypePackage.Literals.QVTO_COMPLETION__QVTO_FILE_URI, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setQvtoFileURI(final String newQvtoFileURI) {
        this.eDynamicSet(TypePackage.QVTO_COMPLETION__QVTO_FILE_URI,
                TypePackage.Literals.QVTO_COMPLETION__QVTO_FILE_URI, newQvtoFileURI);
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
            return QVTO_FILE_URI_EDEFAULT == null ? this.getQvtoFileURI() != null : !QVTO_FILE_URI_EDEFAULT.equals(this
                    .getQvtoFileURI());
        }
        return super.eIsSet(featureID);
    }

} // QVTOCompletionImpl
