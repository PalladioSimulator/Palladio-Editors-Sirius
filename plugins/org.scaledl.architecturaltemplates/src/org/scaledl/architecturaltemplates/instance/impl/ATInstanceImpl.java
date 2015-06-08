/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.ParameterValue;
import org.scaledl.architecturaltemplates.instance.Role2Component;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.type.AT;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>AT Instance</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getRole2components
 * <em>Role2components</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getComponent2roles
 * <em>Component2roles</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getAT2Components <em>
 * AT2 Components</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getType <em>Type</em>}
 * </li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getParameterValues
 * <em>Parameter Values</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl#getRole2resources <em>
 * Role2resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ATInstanceImpl extends EntityImpl implements ATInstance {

    /**
     * The cached value of the '{@link #getRole2components() <em>Role2components</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRole2components()
     * @generated
     * @ordered
     */
    protected EList<Role2Component> role2components;

    /**
     * The cached value of the '{@link #getComponent2roles() <em>Component2roles</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponent2roles()
     * @generated
     * @ordered
     */
    protected EList<Component2Role> component2roles;

    /**
     * The cached value of the '{@link #getAT2Components() <em>AT2 Components</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAT2Components()
     * @generated
     * @ordered
     */
    protected Role2Component at2Components;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected AT type;

    /**
     * The cached value of the '{@link #getParameterValues() <em>Parameter Values</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParameterValues()
     * @generated
     * @ordered
     */
    protected EList<ParameterValue> parameterValues;

    /**
     * The cached value of the '{@link #getRole2resources() <em>Role2resources</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRole2resources()
     * @generated
     * @ordered
     */
    protected EList<Role2Resource> role2resources;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ATInstanceImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.AT_INSTANCE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Role2Component> getRole2components() {
        if (this.role2components == null) {
            this.role2components = new EObjectContainmentWithInverseEList<Role2Component>(Role2Component.class, this,
                    InstancePackage.AT_INSTANCE__ROLE2COMPONENTS, InstancePackage.ROLE2_COMPONENT__AT_INSTANCE);
        }
        return this.role2components;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Component2Role> getComponent2roles() {
        if (this.component2roles == null) {
            this.component2roles = new EObjectContainmentWithInverseEList<Component2Role>(Component2Role.class, this,
                    InstancePackage.AT_INSTANCE__COMPONENT2ROLES, InstancePackage.COMPONENT2_ROLE__AT_INSTANCE);
        }
        return this.component2roles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Role2Component getAT2Components() {
        if (this.at2Components != null && ((EObject) this.at2Components).eIsProxy()) {
            final InternalEObject oldAT2Components = (InternalEObject) this.at2Components;
            this.at2Components = (Role2Component) this.eResolveProxy(oldAT2Components);
            if (this.at2Components != oldAT2Components) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            InstancePackage.AT_INSTANCE__AT2_COMPONENTS, oldAT2Components, this.at2Components));
                }
            }
        }
        return this.at2Components;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Role2Component basicGetAT2Components() {
        return this.at2Components;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAT2Components(final Role2Component newAT2Components) {
        final Role2Component oldAT2Components = this.at2Components;
        this.at2Components = newAT2Components;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.AT_INSTANCE__AT2_COMPONENTS,
                    oldAT2Components, this.at2Components));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AT getType() {
        if (this.type != null && ((EObject) this.type).eIsProxy()) {
            final InternalEObject oldType = (InternalEObject) this.type;
            this.type = (AT) this.eResolveProxy(oldType);
            if (this.type != oldType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstancePackage.AT_INSTANCE__TYPE,
                            oldType, this.type));
                }
            }
        }
        return this.type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AT basicGetType() {
        return this.type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(final AT newType) {
        final AT oldType = this.type;
        this.type = newType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.AT_INSTANCE__TYPE, oldType,
                    this.type));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ParameterValue> getParameterValues() {
        if (this.parameterValues == null) {
            this.parameterValues = new EObjectContainmentWithInverseEList<ParameterValue>(ParameterValue.class, this,
                    InstancePackage.AT_INSTANCE__PARAMETER_VALUES,
                    InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE);
        }
        return this.parameterValues;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Role2Resource> getRole2resources() {
        if (this.role2resources == null) {
            this.role2resources = new EObjectContainmentWithInverseEList<Role2Resource>(Role2Resource.class, this,
                    InstancePackage.AT_INSTANCE__ROLE2RESOURCES, InstancePackage.ROLE2_RESOURCE__AT_INSTANCE);
        }
        return this.role2resources;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getRole2components()).basicAdd(otherEnd,
                    msgs);
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getComponent2roles()).basicAdd(otherEnd,
                    msgs);
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getParameterValues()).basicAdd(otherEnd,
                    msgs);
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getRole2resources()).basicAdd(otherEnd,
                    msgs);
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
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            return ((InternalEList<?>) this.getRole2components()).basicRemove(otherEnd, msgs);
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            return ((InternalEList<?>) this.getComponent2roles()).basicRemove(otherEnd, msgs);
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            return ((InternalEList<?>) this.getParameterValues()).basicRemove(otherEnd, msgs);
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            return ((InternalEList<?>) this.getRole2resources()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            return this.getRole2components();
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            return this.getComponent2roles();
        case InstancePackage.AT_INSTANCE__AT2_COMPONENTS:
            if (resolve) {
                return this.getAT2Components();
            }
            return this.basicGetAT2Components();
        case InstancePackage.AT_INSTANCE__TYPE:
            if (resolve) {
                return this.getType();
            }
            return this.basicGetType();
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            return this.getParameterValues();
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            return this.getRole2resources();
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
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            this.getRole2components().clear();
            this.getRole2components().addAll((Collection<? extends Role2Component>) newValue);
            return;
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            this.getComponent2roles().clear();
            this.getComponent2roles().addAll((Collection<? extends Component2Role>) newValue);
            return;
        case InstancePackage.AT_INSTANCE__AT2_COMPONENTS:
            this.setAT2Components((Role2Component) newValue);
            return;
        case InstancePackage.AT_INSTANCE__TYPE:
            this.setType((AT) newValue);
            return;
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            this.getParameterValues().clear();
            this.getParameterValues().addAll((Collection<? extends ParameterValue>) newValue);
            return;
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            this.getRole2resources().clear();
            this.getRole2resources().addAll((Collection<? extends Role2Resource>) newValue);
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
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            this.getRole2components().clear();
            return;
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            this.getComponent2roles().clear();
            return;
        case InstancePackage.AT_INSTANCE__AT2_COMPONENTS:
            this.setAT2Components((Role2Component) null);
            return;
        case InstancePackage.AT_INSTANCE__TYPE:
            this.setType((AT) null);
            return;
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            this.getParameterValues().clear();
            return;
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            this.getRole2resources().clear();
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
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
            return this.role2components != null && !this.role2components.isEmpty();
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
            return this.component2roles != null && !this.component2roles.isEmpty();
        case InstancePackage.AT_INSTANCE__AT2_COMPONENTS:
            return this.at2Components != null;
        case InstancePackage.AT_INSTANCE__TYPE:
            return this.type != null;
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
            return this.parameterValues != null && !this.parameterValues.isEmpty();
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
            return this.role2resources != null && !this.role2resources.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ATInstanceImpl
