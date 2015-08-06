/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
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
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl#getArchitecturalTemplateInstance <em>Architectural Template Instance</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ParameterValueImpl extends CDOObjectImpl implements ParameterValue {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ParameterValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.PARAMETER_VALUE;
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
    public Parameter getType() {
        return (Parameter) eDynamicGet(InstancePackage.PARAMETER_VALUE__TYPE,
                InstancePackage.Literals.PARAMETER_VALUE__TYPE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public Parameter basicGetType() {
        return (Parameter) eDynamicGet(InstancePackage.PARAMETER_VALUE__TYPE,
                InstancePackage.Literals.PARAMETER_VALUE__TYPE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(Parameter newType) {
        eDynamicSet(InstancePackage.PARAMETER_VALUE__TYPE, InstancePackage.Literals.PARAMETER_VALUE__TYPE, newType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ATInstance getArchitecturalTemplateInstance() {
        return (ATInstance) eDynamicGet(InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE,
                InstancePackage.Literals.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetArchitecturalTemplateInstance(ATInstance newArchitecturalTemplateInstance,
            NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newArchitecturalTemplateInstance,
                InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setArchitecturalTemplateInstance(ATInstance newArchitecturalTemplateInstance) {
        eDynamicSet(InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE,
                InstancePackage.Literals.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE,
                newArchitecturalTemplateInstance);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetArchitecturalTemplateInstance((ATInstance) otherEnd, msgs);
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
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return basicSetArchitecturalTemplateInstance(null, msgs);
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
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return eInternalContainer().eInverseRemove(this, InstancePackage.AT_INSTANCE__PARAMETER_VALUES,
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            if (resolve)
                return getType();
            return basicGetType();
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return getArchitecturalTemplateInstance();
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            setType((Parameter) newValue);
            return;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            setArchitecturalTemplateInstance((ATInstance) newValue);
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            setType((Parameter) null);
            return;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            setArchitecturalTemplateInstance((ATInstance) null);
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
        case InstancePackage.PARAMETER_VALUE__TYPE:
            return basicGetType() != null;
        case InstancePackage.PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE:
            return getArchitecturalTemplateInstance() != null;
        }
        return super.eIsSet(featureID);
    }

} // ParameterValueImpl
