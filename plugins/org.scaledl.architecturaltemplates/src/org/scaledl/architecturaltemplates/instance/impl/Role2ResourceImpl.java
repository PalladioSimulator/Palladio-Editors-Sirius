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
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getATInstance <em>
 * AT Instance</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getResource <em>
 * Resource</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl#getRole <em>Role
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Role2ResourceImpl extends CDOObjectImpl implements Role2Resource {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected Role2ResourceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.ROLE2_RESOURCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected int eStaticFeatureCount() {
        return 0;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ATInstance getATInstance() {
        return (ATInstance) this.eDynamicGet(InstancePackage.ROLE2_RESOURCE__AT_INSTANCE,
                InstancePackage.Literals.ROLE2_RESOURCE__AT_INSTANCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetATInstance(final ATInstance newATInstance, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newATInstance, InstancePackage.ROLE2_RESOURCE__AT_INSTANCE,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setATInstance(final ATInstance newATInstance) {
        this.eDynamicSet(InstancePackage.ROLE2_RESOURCE__AT_INSTANCE,
                InstancePackage.Literals.ROLE2_RESOURCE__AT_INSTANCE, newATInstance);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceContainer getResource() {
        return (ResourceContainer) this.eDynamicGet(InstancePackage.ROLE2_RESOURCE__RESOURCE,
                InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ResourceContainer basicGetResource() {
        return (ResourceContainer) this.eDynamicGet(InstancePackage.ROLE2_RESOURCE__RESOURCE,
                InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setResource(final ResourceContainer newResource) {
        this.eDynamicSet(InstancePackage.ROLE2_RESOURCE__RESOURCE, InstancePackage.Literals.ROLE2_RESOURCE__RESOURCE,
                newResource);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Role getRole() {
        return (Role) this.eDynamicGet(InstancePackage.ROLE2_RESOURCE__ROLE,
                InstancePackage.Literals.ROLE2_RESOURCE__ROLE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Role basicGetRole() {
        return (Role) this.eDynamicGet(InstancePackage.ROLE2_RESOURCE__ROLE,
                InstancePackage.Literals.ROLE2_RESOURCE__ROLE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRole(final Role newRole) {
        this.eDynamicSet(InstancePackage.ROLE2_RESOURCE__ROLE, InstancePackage.Literals.ROLE2_RESOURCE__ROLE, newRole);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetATInstance((ATInstance) otherEnd, msgs);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return this.basicSetATInstance(null, msgs);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return this.eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__ROLE2RESOURCES,
                    ATInstance.class, msgs);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return this.getATInstance();
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            if (resolve) {
                return this.getResource();
            }
            return this.basicGetResource();
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            if (resolve) {
                return this.getRole();
            }
            return this.basicGetRole();
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            this.setATInstance((ATInstance) newValue);
            return;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            this.setResource((ResourceContainer) newValue);
            return;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            this.setRole((Role) newValue);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            this.setATInstance((ATInstance) null);
            return;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            this.setResource((ResourceContainer) null);
            return;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            this.setRole((Role) null);
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
        case InstancePackage.ROLE2_RESOURCE__AT_INSTANCE:
            return this.getATInstance() != null;
        case InstancePackage.ROLE2_RESOURCE__RESOURCE:
            return this.basicGetResource() != null;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            return this.basicGetRole() != null;
        }
        return super.eIsSet(featureID);
    }

} // Role2ResourceImpl
