/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

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
public class Role2ResourceImpl extends MinimalEObjectImpl.Container implements Role2Resource {

    /**
     * The cached value of the '{@link #getResource() <em>Resource</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getResource()
     * @generated
     * @ordered
     */
    protected ResourceContainer resource;

    /**
     * The cached value of the '{@link #getRole() <em>Role</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected Role role;

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
    public ATInstance getATInstance() {
        if (this.eContainerFeatureID() != InstancePackage.ROLE2_RESOURCE__AT_INSTANCE) {
            return null;
        }
        return (ATInstance) this.eInternalContainer();
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
        if (newATInstance != this.eInternalContainer()
                || (this.eContainerFeatureID() != InstancePackage.ROLE2_RESOURCE__AT_INSTANCE && newATInstance != null)) {
            if (EcoreUtil.isAncestor(this, newATInstance)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newATInstance != null) {
                msgs = ((InternalEObject) newATInstance).eInverseAdd(this, InstancePackage.AT_INSTANCE__ROLE2RESOURCES,
                        ATInstance.class, msgs);
            }
            msgs = this.basicSetATInstance(newATInstance, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE2_RESOURCE__AT_INSTANCE,
                    newATInstance, newATInstance));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceContainer getResource() {
        if (this.resource != null && this.resource.eIsProxy()) {
            final InternalEObject oldResource = (InternalEObject) this.resource;
            this.resource = (ResourceContainer) this.eResolveProxy(oldResource);
            if (this.resource != oldResource) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            InstancePackage.ROLE2_RESOURCE__RESOURCE, oldResource, this.resource));
                }
            }
        }
        return this.resource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ResourceContainer basicGetResource() {
        return this.resource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setResource(final ResourceContainer newResource) {
        final ResourceContainer oldResource = this.resource;
        this.resource = newResource;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE2_RESOURCE__RESOURCE,
                    oldResource, this.resource));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Role getRole() {
        if (this.role != null && this.role.eIsProxy()) {
            final InternalEObject oldRole = (InternalEObject) this.role;
            this.role = (Role) this.eResolveProxy(oldRole);
            if (this.role != oldRole) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            InstancePackage.ROLE2_RESOURCE__ROLE, oldRole, this.role));
                }
            }
        }
        return this.role;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Role basicGetRole() {
        return this.role;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRole(final Role newRole) {
        final Role oldRole = this.role;
        this.role = newRole;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE2_RESOURCE__ROLE, oldRole,
                    this.role));
        }
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
            return this.resource != null;
        case InstancePackage.ROLE2_RESOURCE__ROLE:
            return this.role != null;
        }
        return super.eIsSet(featureID);
    }

} // Role2ResourceImpl
