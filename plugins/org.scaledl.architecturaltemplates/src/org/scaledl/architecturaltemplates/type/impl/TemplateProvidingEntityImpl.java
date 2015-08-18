/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.type.TemplateProvidingEntity;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Template Providing Entity</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.TemplateProvidingEntityImpl#getTemplateFileURI <em>Template File URI</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TemplateProvidingEntityImpl extends CDOObjectImpl implements TemplateProvidingEntity {

    /**
     * The default value of the '{@link #getTemplateFileURI() <em>Template File URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getTemplateFileURI()
     * @generated
     * @ordered
     */
    protected static final String TEMPLATE_FILE_URI_EDEFAULT = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TemplateProvidingEntityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.TEMPLATE_PROVIDING_ENTITY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected int eStaticFeatureCount() {
        return 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTemplateFileURI() {
        return (String) eDynamicGet(TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI,
                TypePackage.Literals.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTemplateFileURI(String newTemplateFileURI) {
        eDynamicSet(TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI,
                TypePackage.Literals.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI, newTemplateFileURI);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            return getTemplateFileURI();
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            setTemplateFileURI((String) newValue);
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            setTemplateFileURI(TEMPLATE_FILE_URI_EDEFAULT);
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
        case TypePackage.TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI:
            return TEMPLATE_FILE_URI_EDEFAULT == null ? getTemplateFileURI() != null
                    : !TEMPLATE_FILE_URI_EDEFAULT.equals(getTemplateFileURI());
        }
        return super.eIsSet(featureID);
    }

} // TemplateProvidingEntityImpl
