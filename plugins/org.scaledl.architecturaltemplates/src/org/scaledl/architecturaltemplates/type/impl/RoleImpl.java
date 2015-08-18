/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.Stereotype;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getAT <em>AT</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleImpl extends EntityImpl implements Role {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected RoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.ROLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Parameter> getParameters() {
        return (EList<Parameter>) eDynamicGet(TypePackage.ROLE__PARAMETERS, TypePackage.Literals.ROLE__PARAMETERS, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public AT getAT() {
        return (AT) eDynamicGet(TypePackage.ROLE__AT, TypePackage.Literals.ROLE__AT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetAT(AT newAT, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newAT, TypePackage.ROLE__AT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setAT(AT newAT) {
        eDynamicSet(TypePackage.ROLE__AT, TypePackage.Literals.ROLE__AT, newAT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Constraint> getConstraints() {
        return (EList<Constraint>) eDynamicGet(TypePackage.ROLE__CONSTRAINTS, TypePackage.Literals.ROLE__CONSTRAINTS,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Stereotype getStereotype() {
        return (Stereotype) eDynamicGet(TypePackage.ROLE__STEREOTYPE, TypePackage.Literals.ROLE__STEREOTYPE, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Stereotype basicGetStereotype() {
        return (Stereotype) eDynamicGet(TypePackage.ROLE__STEREOTYPE, TypePackage.Literals.ROLE__STEREOTYPE, false,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setStereotype(Stereotype newStereotype) {
        eDynamicSet(TypePackage.ROLE__STEREOTYPE, TypePackage.Literals.ROLE__STEREOTYPE, newStereotype);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.ROLE__PARAMETERS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getParameters()).basicAdd(otherEnd, msgs);
        case TypePackage.ROLE__AT:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetAT((AT) otherEnd, msgs);
        case TypePackage.ROLE__CONSTRAINTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getConstraints()).basicAdd(otherEnd, msgs);
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
        case TypePackage.ROLE__PARAMETERS:
            return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
        case TypePackage.ROLE__AT:
            return basicSetAT(null, msgs);
        case TypePackage.ROLE__CONSTRAINTS:
            return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
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
        case TypePackage.ROLE__AT:
            return eInternalContainer().eInverseRemove(this, TypePackage.AT__ROLES, AT.class, msgs);
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
        case TypePackage.ROLE__PARAMETERS:
            return getParameters();
        case TypePackage.ROLE__AT:
            return getAT();
        case TypePackage.ROLE__CONSTRAINTS:
            return getConstraints();
        case TypePackage.ROLE__STEREOTYPE:
            if (resolve)
                return getStereotype();
            return basicGetStereotype();
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
        case TypePackage.ROLE__PARAMETERS:
            getParameters().clear();
            getParameters().addAll((Collection<? extends Parameter>) newValue);
            return;
        case TypePackage.ROLE__AT:
            setAT((AT) newValue);
            return;
        case TypePackage.ROLE__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends Constraint>) newValue);
            return;
        case TypePackage.ROLE__STEREOTYPE:
            setStereotype((Stereotype) newValue);
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
        case TypePackage.ROLE__PARAMETERS:
            getParameters().clear();
            return;
        case TypePackage.ROLE__AT:
            setAT((AT) null);
            return;
        case TypePackage.ROLE__CONSTRAINTS:
            getConstraints().clear();
            return;
        case TypePackage.ROLE__STEREOTYPE:
            setStereotype((Stereotype) null);
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
        case TypePackage.ROLE__PARAMETERS:
            return !getParameters().isEmpty();
        case TypePackage.ROLE__AT:
            return getAT() != null;
        case TypePackage.ROLE__CONSTRAINTS:
            return !getConstraints().isEmpty();
        case TypePackage.ROLE__STEREOTYPE:
            return basicGetStereotype() != null;
        }
        return super.eIsSet(featureID);
    }

} // RoleImpl
