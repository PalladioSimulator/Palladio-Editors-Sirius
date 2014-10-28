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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Constraint</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ConstraintImpl#getAT <em>AT</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ConstraintImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ConstraintImpl extends EntityImpl implements Constraint {
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
    protected ConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AT getAT() {
        if (this.eContainerFeatureID() != TypePackage.CONSTRAINT__AT) {
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
        msgs = this.eBasicSetContainer((InternalEObject) newAT, TypePackage.CONSTRAINT__AT, msgs);
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
                || (this.eContainerFeatureID() != TypePackage.CONSTRAINT__AT && newAT != null)) {
            if (EcoreUtil.isAncestor(this, newAT)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAT != null) {
                msgs = ((InternalEObject) newAT).eInverseAdd(this, TypePackage.AT__CONSTRAINTS, AT.class, msgs);
            }
            msgs = this.basicSetAT(newAT, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.CONSTRAINT__AT, newAT, newAT));
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
            this.roles = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this,
                    TypePackage.CONSTRAINT__ROLES, TypePackage.ROLE__CONSTRAINTS);
        }
        return this.roles;
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
        case TypePackage.CONSTRAINT__AT:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAT((AT) otherEnd, msgs);
        case TypePackage.CONSTRAINT__ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getRoles()).basicAdd(otherEnd, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return this.basicSetAT(null, msgs);
        case TypePackage.CONSTRAINT__ROLES:
            return ((InternalEList<?>) this.getRoles()).basicRemove(otherEnd, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.AT__CONSTRAINTS, AT.class, msgs);
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
        case TypePackage.CONSTRAINT__AT:
            return this.getAT();
        case TypePackage.CONSTRAINT__ROLES:
            return this.getRoles();
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
        case TypePackage.CONSTRAINT__AT:
            this.setAT((AT) newValue);
            return;
        case TypePackage.CONSTRAINT__ROLES:
            this.getRoles().clear();
            this.getRoles().addAll((Collection<? extends Role>) newValue);
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
        case TypePackage.CONSTRAINT__AT:
            this.setAT((AT) null);
            return;
        case TypePackage.CONSTRAINT__ROLES:
            this.getRoles().clear();
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
        case TypePackage.CONSTRAINT__AT:
            return this.getAT() != null;
        case TypePackage.CONSTRAINT__ROLES:
            return this.roles != null && !this.roles.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ConstraintImpl
