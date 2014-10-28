/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.scaledl.architecturaltemplates.type.TemplateProvidingEntity;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Template Providing Entity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.impl.TemplateProvidingEntityImpl#getTemplateFileURI
 * <em>Template File URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TemplateProvidingEntityImpl extends MinimalEObjectImpl.Container implements
TemplateProvidingEntity {
    /**
     * The default value of the '{@link #getTemplateFileURI() <em>Template File URI</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTemplateFileURI()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_FILE_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTemplateFileURI() <em>Template File URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTemplateFileURI()
     * @generated
     * @ordered
     */
    protected String templateFileURI = TEMPLATE_FILE_URI_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TemplateProvidingEntityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.TEMPLATE_PROVIDING_ENTITY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTemplateFileURI() {
        return this.templateFileURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTemplateFileURI(final String newTemplateFileURI) {
        final String oldTemplateFileURI = this.templateFileURI;
        this.templateFileURI = newTemplateFileURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI, oldTemplateFileURI, this.templateFileURI));
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            return this.getTemplateFileURI();
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            this.setTemplateFileURI((String) newValue);
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            this.setTemplateFileURI(TEMPLATE_FILE_URI_EDEFAULT);
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            return TEMPLATE_FILE_URI_EDEFAULT == null ? this.templateFileURI != null : !TEMPLATE_FILE_URI_EDEFAULT
            .equals(this.templateFileURI);
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
        result.append(" (templateFileURI: ");
        result.append(this.templateFileURI);
        result.append(')');
        return result.toString();
    }

} // TemplateProvidingEntityImpl
