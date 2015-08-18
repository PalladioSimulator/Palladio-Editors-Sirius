/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.InternalEObject;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getDataType <em>Data Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParameterImpl extends EntityImpl implements Parameter {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Role getRole() {
        return (Role) eDynamicGet(TypePackage.PARAMETER__ROLE, TypePackage.Literals.PARAMETER__ROLE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRole(Role newRole, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newRole, TypePackage.PARAMETER__ROLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRole(Role newRole) {
        eDynamicSet(TypePackage.PARAMETER__ROLE, TypePackage.Literals.PARAMETER__ROLE, newRole);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EDataType getDataType() {
        return (EDataType) eDynamicGet(TypePackage.PARAMETER__DATA_TYPE, TypePackage.Literals.PARAMETER__DATA_TYPE,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EDataType basicGetDataType() {
        return (EDataType) eDynamicGet(TypePackage.PARAMETER__DATA_TYPE, TypePackage.Literals.PARAMETER__DATA_TYPE,
                false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDataType(EDataType newDataType) {
        eDynamicSet(TypePackage.PARAMETER__DATA_TYPE, TypePackage.Literals.PARAMETER__DATA_TYPE, newDataType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetRole((Role) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return basicSetRole(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
        case TypePackage.PARAMETER__ROLE:
            return eInternalContainer().eInverseRemove(this, TypePackage.ROLE__PARAMETERS, Role.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return getRole();
        case TypePackage.PARAMETER__DATA_TYPE:
            if (resolve)
                return getDataType();
            return basicGetDataType();
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
        case TypePackage.PARAMETER__ROLE:
            setRole((Role) newValue);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            setDataType((EDataType) newValue);
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
        case TypePackage.PARAMETER__ROLE:
            setRole((Role) null);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            setDataType((EDataType) null);
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
        case TypePackage.PARAMETER__ROLE:
            return getRole() != null;
        case TypePackage.PARAMETER__DATA_TYPE:
            return basicGetDataType() != null;
        }
        return super.eIsSet(featureID);
    }

} // ParameterImpl
