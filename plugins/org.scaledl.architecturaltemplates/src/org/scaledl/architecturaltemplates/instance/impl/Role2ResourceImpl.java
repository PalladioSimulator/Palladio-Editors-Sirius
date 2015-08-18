/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.type.Role;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role2 Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getATInstance <em>AT Instance</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getRole <em>Role</em>}</li>
 * </ul>
 *
 * @generated
 */
public class Role2ResourceImpl extends CDOObjectImpl implements Role2Resource {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected Role2ResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.ROLE2_RESOURCE;
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
    public ATInstance getATInstance() {
        return (ATInstance) eDynamicGet(InstancePackage.ROLE2_RESOURCE__AT_INSTANCE,
                InstancePackage.Literals.ROLE2_RESOURCE__AT_INSTANCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetATInstance(ATInstance newATInstance, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newATInstance, InstancePackage.ROLE2_RESOURCE__AT_INSTANCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setATInstance(ATInstance newATInstance) {
        eDynamicSet(InstancePackage.ROLE2_RESOURCE__AT_INSTANCE, InstancePackage.Literals.ROLE2_RESOURCE__AT_INSTANCE,
                newATInstance);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ResourceContainer getResource() {
        return (ResourceContainer) eDynamicGet(InstancePackage.ROLE2_RESOURCE__RESOURCE,
                InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public ResourceContainer basicGetResource() {
        return (ResourceContainer) eDynamicGet(InstancePackage.ROLE2_RESOURCE__RESOURCE,
                InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setResource(ResourceContainer newResource) {
        eDynamicSet(InstancePackage.ROLE2_RESOURCE__RESOURCE, InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE,
                newResource);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Role getRole() {
        return (Role) eDynamicGet(InstancePackage.ROLE2_RESOURCE__ROLE, InstancePackage.Literals.ROLE2_RESOURCE__ROLE,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Role basicGetRole() {
        return (Role) eDynamicGet(InstancePackage.ROLE2_RESOURCE__ROLE, InstancePackage.Literals.ROLE2_RESOURCE__ROLE,
                false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRole(Role newRole) {
        eDynamicSet(InstancePackage.ROLE2_RESOURCE__ROLE, InstancePackage.Literals.ROLE2_RESOURCE__ROLE, newRole);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__ROLE2RESOURCES,
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return getATInstance();
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            if (resolve)
                return getResource();
            return basicGetResource();
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            if (resolve)
                return getRole();
            return basicGetRole();
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            setATInstance((ATInstance) newValue);
            return;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            setResource((ResourceContainer) newValue);
            return;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            setRole((Role) newValue);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            setATInstance((ATInstance) null);
            return;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            setResource((ResourceContainer) null);
            return;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            setRole((Role) null);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return getATInstance() != null;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            return basicGetResource() != null;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            return basicGetRole() != null;
        }
        return super.eIsSet(featureID);
    }

} // Role2ResourceImpl
