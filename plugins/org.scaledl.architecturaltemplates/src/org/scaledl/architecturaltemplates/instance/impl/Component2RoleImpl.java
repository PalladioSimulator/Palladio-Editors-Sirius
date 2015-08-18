/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.type.Role;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component2 Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getATInstance <em>AT Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Component2RoleImpl extends CDOObjectImpl implements Component2Role {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected Component2RoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.COMPONENT2_ROLE;
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
    public AssemblyContext getComponent() {
        return (AssemblyContext) eDynamicGet(InstancePackage.COMPONENT2_ROLE__COMPONENT,
                InstancePackage.Literals.COMPONENT2_ROLE__COMPONENT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public AssemblyContext basicGetComponent() {
        return (AssemblyContext) eDynamicGet(InstancePackage.COMPONENT2_ROLE__COMPONENT,
                InstancePackage.Literals.COMPONENT2_ROLE__COMPONENT, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setComponent(AssemblyContext newComponent) {
        eDynamicSet(InstancePackage.COMPONENT2_ROLE__COMPONENT, InstancePackage.Literals.COMPONENT2_ROLE__COMPONENT,
                newComponent);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Role> getRoles() {
        return (EList<Role>) eDynamicGet(InstancePackage.COMPONENT2_ROLE__ROLES,
                InstancePackage.Literals.COMPONENT2_ROLE__ROLES, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ATInstance getATInstance() {
        return (ATInstance) eDynamicGet(InstancePackage.COMPONENT2_ROLE__AT_INSTANCE,
                InstancePackage.Literals.COMPONENT2_ROLE__AT_INSTANCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetATInstance(ATInstance newATInstance, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newATInstance, InstancePackage.COMPONENT2_ROLE__AT_INSTANCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setATInstance(ATInstance newATInstance) {
        eDynamicSet(InstancePackage.COMPONENT2_ROLE__AT_INSTANCE, InstancePackage.Literals.COMPONENT2_ROLE__AT_INSTANCE,
                newATInstance);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetATInstance((ATInstance) otherEnd, msgs);
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
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return basicSetATInstance(null, msgs);
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
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__COMPONENT2ROLES,
                    ATInstance.class, msgs);
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            if (resolve)
                return getComponent();
            return basicGetComponent();
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            return getRoles();
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return getATInstance();
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            setComponent((AssemblyContext) newValue);
            return;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            getRoles().clear();
            getRoles().addAll((Collection<? extends Role>) newValue);
            return;
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            setATInstance((ATInstance) newValue);
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            setComponent((AssemblyContext) null);
            return;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            getRoles().clear();
            return;
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            setATInstance((ATInstance) null);
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            return basicGetComponent() != null;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            return !getRoles().isEmpty();
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return getATInstance() != null;
        }
        return super.eIsSet(featureID);
    }

} // Component2RoleImpl
