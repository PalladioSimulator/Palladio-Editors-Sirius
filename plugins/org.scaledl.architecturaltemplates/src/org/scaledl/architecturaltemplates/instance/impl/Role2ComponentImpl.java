/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.Role2Component;
import org.scaledl.architecturaltemplates.type.Role;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role2 Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl#getATInstance <em>AT Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Role2ComponentImpl extends CDOObjectImpl implements Role2Component {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected Role2ComponentImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.ROLE2_COMPONENT;
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
        return (AssemblyContext) eDynamicGet(InstancePackage.ROLE2_COMPONENT__COMPONENT,
                InstancePackage.Literals.ROLE2_COMPONENT__COMPONENT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public AssemblyContext basicGetComponent() {
        return (AssemblyContext) eDynamicGet(InstancePackage.ROLE2_COMPONENT__COMPONENT,
                InstancePackage.Literals.ROLE2_COMPONENT__COMPONENT, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setComponent(AssemblyContext newComponent) {
        eDynamicSet(InstancePackage.ROLE2_COMPONENT__COMPONENT, InstancePackage.Literals.ROLE2_COMPONENT__COMPONENT,
                newComponent);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Role getRole() {
        return (Role) eDynamicGet(InstancePackage.ROLE2_COMPONENT__ROLE, InstancePackage.Literals.ROLE2_COMPONENT__ROLE,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Role basicGetRole() {
        return (Role) eDynamicGet(InstancePackage.ROLE2_COMPONENT__ROLE, InstancePackage.Literals.ROLE2_COMPONENT__ROLE,
                false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRole(Role newRole) {
        eDynamicSet(InstancePackage.ROLE2_COMPONENT__ROLE, InstancePackage.Literals.ROLE2_COMPONENT__ROLE, newRole);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ATInstance getATInstance() {
        return (ATInstance) eDynamicGet(InstancePackage.ROLE2_COMPONENT__AT_INSTANCE,
                InstancePackage.Literals.ROLE2_COMPONENT__AT_INSTANCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetATInstance(ATInstance newATInstance, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newATInstance, InstancePackage.ROLE2_COMPONENT__AT_INSTANCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setATInstance(ATInstance newATInstance) {
        eDynamicSet(InstancePackage.ROLE2_COMPONENT__AT_INSTANCE, InstancePackage.Literals.ROLE2_COMPONENT__AT_INSTANCE,
                newATInstance);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
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
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
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
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
            return eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__ROLE2COMPONENTS,
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
        case InstancePackage.ROLE2_COMPONENT__COMPONENT:
            if (resolve)
                return getComponent();
            return basicGetComponent();
        case InstancePackage.ROLE2_COMPONENT__ROLE:
            if (resolve)
                return getRole();
            return basicGetRole();
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
            return getATInstance();
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
        case InstancePackage.ROLE2_COMPONENT__COMPONENT:
            setComponent((AssemblyContext) newValue);
            return;
        case InstancePackage.ROLE2_COMPONENT__ROLE:
            setRole((Role) newValue);
            return;
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
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
        case InstancePackage.ROLE2_COMPONENT__COMPONENT:
            setComponent((AssemblyContext) null);
            return;
        case InstancePackage.ROLE2_COMPONENT__ROLE:
            setRole((Role) null);
            return;
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
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
        case InstancePackage.ROLE2_COMPONENT__COMPONENT:
            return basicGetComponent() != null;
        case InstancePackage.ROLE2_COMPONENT__ROLE:
            return basicGetRole() != null;
        case InstancePackage.ROLE2_COMPONENT__AT_INSTANCE:
            return getATInstance() != null;
        }
        return super.eIsSet(featureID);
    }

} // Role2ComponentImpl
