/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.internal.cdo.CDOObjectImpl;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Completion;
import org.scaledl.architecturaltemplates.type.CompletionParameter;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Completion</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.CompletionImpl#getAT <em>AT</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.CompletionImpl#getParameters <em>
 * Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class CompletionImpl extends CDOObjectImpl implements Completion {

    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<CompletionParameter> parameters;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CompletionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.COMPLETION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AT getAT() {
        if (this.eContainerFeatureID() != TypePackage.COMPLETION__AT) {
            return null;
        }
        return (AT) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAT(final AT newAT, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAT, TypePackage.COMPLETION__AT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAT(final AT newAT) {
        if (newAT != this.eInternalContainer()
                || (this.eContainerFeatureID() != TypePackage.COMPLETION__AT && newAT != null)) {
            if (EcoreUtil.isAncestor(this, newAT)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAT != null) {
                msgs = ((InternalEObject) newAT).eInverseAdd(this, TypePackage.AT__COMPLETION, AT.class, msgs);
            }
            msgs = this.basicSetAT(newAT, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.COMPLETION__AT, newAT, newAT));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CompletionParameter> getParameters() {
        if (this.parameters == null) {
            this.parameters = new EObjectContainmentWithInverseEList<CompletionParameter>(CompletionParameter.class,
                    this, TypePackage.COMPLETION__PARAMETERS, TypePackage.COMPLETION_PARAMETER__COMPLETION);
        }
        return this.parameters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.COMPLETION__AT:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAT((AT) otherEnd, msgs);
        case TypePackage.COMPLETION__PARAMETERS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getParameters()).basicAdd(otherEnd, msgs);
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
        case TypePackage.COMPLETION__AT:
            return this.basicSetAT(null, msgs);
        case TypePackage.COMPLETION__PARAMETERS:
            return ((InternalEList<?>) this.getParameters()).basicRemove(otherEnd, msgs);
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
        case TypePackage.COMPLETION__AT:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.AT__COMPLETION, AT.class, msgs);
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
        case TypePackage.COMPLETION__AT:
            return this.getAT();
        case TypePackage.COMPLETION__PARAMETERS:
            return this.getParameters();
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
        case TypePackage.COMPLETION__AT:
            this.setAT((AT) newValue);
            return;
        case TypePackage.COMPLETION__PARAMETERS:
            this.getParameters().clear();
            this.getParameters().addAll((Collection<? extends CompletionParameter>) newValue);
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
        case TypePackage.COMPLETION__AT:
            this.setAT((AT) null);
            return;
        case TypePackage.COMPLETION__PARAMETERS:
            this.getParameters().clear();
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
        case TypePackage.COMPLETION__AT:
            return this.getAT() != null;
        case TypePackage.COMPLETION__PARAMETERS:
            return this.parameters != null && !this.parameters.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // CompletionImpl
