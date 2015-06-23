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
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getRole <em>Role</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ParameterImpl#getDataType <em>Data Type
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends EntityImpl implements Parameter {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.PARAMETER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Role getRole() {
        return (Role) this.eDynamicGet(TypePackage.PARAMETER__ROLE, TypePackage.Literals.PARAMETER__ROLE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetRole(final Role newRole, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRole, TypePackage.PARAMETER__ROLE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRole(final Role newRole) {
        this.eDynamicSet(TypePackage.PARAMETER__ROLE, TypePackage.Literals.PARAMETER__ROLE, newRole);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getDataType() {
        return (EDataType) this.eDynamicGet(TypePackage.PARAMETER__DATA_TYPE,
                TypePackage.Literals.PARAMETER__DATA_TYPE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EDataType basicGetDataType() {
        return (EDataType) this.eDynamicGet(TypePackage.PARAMETER__DATA_TYPE,
                TypePackage.Literals.PARAMETER__DATA_TYPE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDataType(final EDataType newDataType) {
        this.eDynamicSet(TypePackage.PARAMETER__DATA_TYPE, TypePackage.Literals.PARAMETER__DATA_TYPE, newDataType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRole((Role) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return this.basicSetRole(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case TypePackage.PARAMETER__ROLE:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.ROLE__PARAMETERS, Role.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.PARAMETER__ROLE:
            return this.getRole();
        case TypePackage.PARAMETER__DATA_TYPE:
            if (resolve) {
                return this.getDataType();
            }
            return this.basicGetDataType();
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
        case TypePackage.PARAMETER__ROLE:
            this.setRole((Role) newValue);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            this.setDataType((EDataType) newValue);
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
        case TypePackage.PARAMETER__ROLE:
            this.setRole((Role) null);
            return;
        case TypePackage.PARAMETER__DATA_TYPE:
            this.setDataType((EDataType) null);
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
        case TypePackage.PARAMETER__ROLE:
            return this.getRole() != null;
        case TypePackage.PARAMETER__DATA_TYPE:
            return this.basicGetDataType() != null;
        }
        return super.eIsSet(featureID);
    }

} // ParameterImpl
