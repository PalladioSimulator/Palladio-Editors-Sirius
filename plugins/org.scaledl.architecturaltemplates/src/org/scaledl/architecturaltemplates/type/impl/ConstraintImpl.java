/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Constraint</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ConstraintImpl#getAT <em>AT</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ConstraintImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ConstraintImpl extends EntityImpl implements Constraint {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AT getAT() {
        return (AT) eDynamicGet(TypePackage.CONSTRAINT__AT, TypePackage.Literals.CONSTRAINT__AT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAT(AT newAT, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newAT, TypePackage.CONSTRAINT__AT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAT(AT newAT) {
        eDynamicSet(TypePackage.CONSTRAINT__AT, TypePackage.Literals.CONSTRAINT__AT, newAT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Role> getRoles() {
        return (EList<Role>) eDynamicGet(TypePackage.CONSTRAINT__ROLES, TypePackage.Literals.CONSTRAINT__ROLES, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.CONSTRAINT__AT:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetAT((AT) otherEnd, msgs);
        case TypePackage.CONSTRAINT__ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getRoles()).basicAdd(otherEnd, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return basicSetAT(null, msgs);
        case TypePackage.CONSTRAINT__ROLES:
            return ((InternalEList<?>) getRoles()).basicRemove(otherEnd, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return eInternalContainer().eInverseRemove(this, TypePackage.AT__CONSTRAINTS, AT.class, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return getAT();
        case TypePackage.CONSTRAINT__ROLES:
            return getRoles();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TypePackage.CONSTRAINT__AT:
            setAT((AT) newValue);
            return;
        case TypePackage.CONSTRAINT__ROLES:
            getRoles().clear();
            getRoles().addAll((Collection<? extends Role>) newValue);
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
        case TypePackage.CONSTRAINT__AT:
            setAT((AT) null);
            return;
        case TypePackage.CONSTRAINT__ROLES:
            getRoles().clear();
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
        case TypePackage.CONSTRAINT__AT:
            return getAT() != null;
        case TypePackage.CONSTRAINT__ROLES:
            return !getRoles().isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ConstraintImpl
