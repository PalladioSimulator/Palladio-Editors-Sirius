/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component2 Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getComponent <em>
 * Component</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getRoles <em>Roles
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl#getATInstance <em>
 * AT Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Component2RoleImpl extends MinimalEObjectImpl.Container implements Component2Role {

    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected AssemblyContext component;

    /**
     * The cached value of the '{@link #getRoles() <em>Roles</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRoles()
     * @generated
     * @ordered
     */
    protected EList<Role> roles;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected Component2RoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.COMPONENT2_ROLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AssemblyContext getComponent() {
        if (this.component != null && this.component.eIsProxy()) {
            final InternalEObject oldComponent = (InternalEObject) this.component;
            this.component = (AssemblyContext) this.eResolveProxy(oldComponent);
            if (this.component != oldComponent) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            InstancePackage.COMPONENT2_ROLE__COMPONENT, oldComponent, this.component));
                }
            }
        }
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AssemblyContext basicGetComponent() {
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setComponent(final AssemblyContext newComponent) {
        final AssemblyContext oldComponent = this.component;
        this.component = newComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.COMPONENT2_ROLE__COMPONENT,
                    oldComponent, this.component));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Role> getRoles() {
        if (this.roles == null) {
            this.roles = new EObjectResolvingEList<Role>(Role.class, this, InstancePackage.COMPONENT2_ROLE__ROLES);
        }
        return this.roles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ATInstance getATInstance() {
        if (this.eContainerFeatureID() != InstancePackage.COMPONENT2_ROLE__AT_INSTANCE) {
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
        msgs = this.eBasicSetContainer((InternalEObject) newATInstance, InstancePackage.COMPONENT2_ROLE__AT_INSTANCE,
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
                || (this.eContainerFeatureID() != InstancePackage.COMPONENT2_ROLE__AT_INSTANCE && newATInstance != null)) {
            if (EcoreUtil.isAncestor(this, newATInstance)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newATInstance != null) {
                msgs = ((InternalEObject) newATInstance).eInverseAdd(this,
                        InstancePackage.AT_INSTANCE__COMPONENT2ROLES, ATInstance.class, msgs);
            }
            msgs = this.basicSetATInstance(newATInstance, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.COMPONENT2_ROLE__AT_INSTANCE,
                    newATInstance, newATInstance));
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
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
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
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
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
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return this.eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__COMPONENT2ROLES,
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            if (resolve) {
                return this.getComponent();
            }
            return this.basicGetComponent();
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            return this.getRoles();
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return this.getATInstance();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            this.setComponent((AssemblyContext) newValue);
            return;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            this.getRoles().clear();
            this.getRoles().addAll((Collection<? extends Role>) newValue);
            return;
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            this.setATInstance((ATInstance) newValue);
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            this.setComponent((AssemblyContext) null);
            return;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            this.getRoles().clear();
            return;
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            this.setATInstance((ATInstance) null);
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
        case InstancePackage.COMPONENT2_ROLE__COMPONENT:
            return this.component != null;
        case InstancePackage.COMPONENT2_ROLE__ROLES:
            return this.roles != null && !this.roles.isEmpty();
        case InstancePackage.COMPONENT2_ROLE__AT_INSTANCE:
            return this.getATInstance() != null;
        }
        return super.eIsSet(featureID);
    }

} // Component2RoleImpl
