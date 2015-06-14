/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.type.Completion;
import org.scaledl.architecturaltemplates.type.CompletionParameter;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Completion Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.CompletionParameterImpl#getCompletion
 * <em>Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CompletionParameterImpl extends CDOObjectImpl implements CompletionParameter {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected CompletionParameterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.COMPLETION_PARAMETER;
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
    public Completion getCompletion() {
        return (Completion) this.eDynamicGet(TypePackage.COMPLETION_PARAMETER__COMPLETION,
                TypePackage.Literals.COMPLETION_PARAMETER__COMPLETION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCompletion(final Completion newCompletion, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newCompletion, TypePackage.COMPLETION_PARAMETER__COMPLETION,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCompletion(final Completion newCompletion) {
        this.eDynamicSet(TypePackage.COMPLETION_PARAMETER__COMPLETION,
                TypePackage.Literals.COMPLETION_PARAMETER__COMPLETION, newCompletion);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetCompletion((Completion) otherEnd, msgs);
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            return this.basicSetCompletion(null, msgs);
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.COMPLETION__PARAMETERS, Completion.class,
                    msgs);
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            return this.getCompletion();
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            this.setCompletion((Completion) newValue);
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            this.setCompletion((Completion) null);
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
        case TypePackage.COMPLETION_PARAMETER__COMPLETION:
            return this.getCompletion() != null;
        }
        return super.eIsSet(featureID);
    }

} // CompletionParameterImpl
