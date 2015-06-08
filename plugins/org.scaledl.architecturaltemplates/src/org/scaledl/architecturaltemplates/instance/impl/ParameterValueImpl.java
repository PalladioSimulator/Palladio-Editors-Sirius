/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.ParameterValue;
import org.scaledl.architecturaltemplates.type.Parameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl#getType <em>Type
 * </em>}</li>
 * <li>
 * {@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl#getArchitecturalTemplateInstance
 * <em>Architectural Template Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ParameterValueImpl extends CDOObjectImpl implements ParameterValue {

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected Parameter type;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ParameterValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.PARAMETER_VALUE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Parameter getType() {
        if (this.type != null && ((EObject) this.type).eIsProxy()) {
            final InternalEObject oldType = (InternalEObject) this.type;
            this.type = (Parameter) this.eResolveProxy(oldType);
            if (this.type != oldType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            InstancePackage.PARAMETER_VALUE__TYPE, oldType, this.type));
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
    public Parameter basicGetType() {
        return this.type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(final Parameter newType) {
        final Parameter oldType = this.type;
        this.type = newType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.PARAMETER_VALUE__TYPE, oldType,
                    this.type));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ATInstance getArchitecturalTemplateInstance() {
        if (this.eContainerFeatureID() != InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE) {
            return null;
        }
        return (ATInstance) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetArchitecturalTemplateInstance(final ATInstance newArchitecturalTemplateInstance,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newArchitecturalTemplateInstance,
                InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setArchitecturalTemplateInstance(final ATInstance newArchitecturalTemplateInstance) {
        if (newArchitecturalTemplateInstance != this.eInternalContainer()
                || (this.eContainerFeatureID() != InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE && newArchitecturalTemplateInstance != null)) {
            if (EcoreUtil.isAncestor(this, newArchitecturalTemplateInstance)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newArchitecturalTemplateInstance != null) {
                msgs = ((InternalEObject) newArchitecturalTemplateInstance).eInverseAdd(this,
                        InstancePackage.AT_INSTANCE__PARAMETER_VALUES, ATInstance.class, msgs);
            }
            msgs = this.basicSetArchitecturalTemplateInstance(newArchitecturalTemplateInstance, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE, newArchitecturalTemplateInstance,
                    newArchitecturalTemplateInstance));
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
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetArchitecturalTemplateInstance((ATInstance) otherEnd, msgs);
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
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return this.basicSetArchitecturalTemplateInstance(null, msgs);
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
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return this.eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__PARAMETER_VALUES,
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            if (resolve) {
                return this.getType();
            }
            return this.basicGetType();
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return this.getArchitecturalTemplateInstance();
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            this.setType((Parameter) newValue);
            return;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            this.setArchitecturalTemplateInstance((ATInstance) newValue);
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            this.setType((Parameter) null);
            return;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            this.setArchitecturalTemplateInstance((ATInstance) null);
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            return this.type != null;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return this.getArchitecturalTemplateInstance() != null;
        }
        return super.eIsSet(featureID);
    }

} // ParameterValueImpl
