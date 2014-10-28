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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.Stereotype;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getParameters <em>Parameters
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getAT <em>AT</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getConstraints <em>Constraints
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.RoleImpl#getStereotype <em>Stereotype
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends EntityImpl implements Role {
    /**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getParameters()
     * @generated
     * @ordered
     */
    protected EList<Parameter> parameters;

    /**
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    protected EList<Constraint> constraints;

    /**
     * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStereotype()
     * @generated
     * @ordered
     */
    protected Stereotype stereotype;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.ROLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Parameter> getParameters() {
        if (this.parameters == null) {
            this.parameters = new EObjectContainmentWithInverseEList<Parameter>(Parameter.class, this,
                    TypePackage.ROLE__PARAMETERS, TypePackage.PARAMETER__ROLE);
        }
        return this.parameters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AT getAT() {
        if (this.eContainerFeatureID() != TypePackage.ROLE__AT) {
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
        msgs = this.eBasicSetContainer((InternalEObject) newAT, TypePackage.ROLE__AT, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAT(final AT newAT) {
        if (newAT != this.eInternalContainer() || (this.eContainerFeatureID() != TypePackage.ROLE__AT && newAT != null)) {
            if (EcoreUtil.isAncestor(this, newAT)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAT != null) {
                msgs = ((InternalEObject) newAT).eInverseAdd(this, TypePackage.AT__ROLES, AT.class, msgs);
            }
            msgs = this.basicSetAT(newAT, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.ROLE__AT, newAT, newAT));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Constraint> getConstraints() {
        if (this.constraints == null) {
            this.constraints = new EObjectWithInverseResolvingEList.ManyInverse<Constraint>(Constraint.class, this,
                    TypePackage.ROLE__CONSTRAINTS, TypePackage.CONSTRAINT__ROLES);
        }
        return this.constraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Stereotype getStereotype() {
        if (this.stereotype != null && this.stereotype.eIsProxy()) {
            final InternalEObject oldStereotype = (InternalEObject) this.stereotype;
            this.stereotype = (Stereotype) this.eResolveProxy(oldStereotype);
            if (this.stereotype != oldStereotype) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE, TypePackage.ROLE__STEREOTYPE,
                            oldStereotype, this.stereotype));
                }
            }
        }
        return this.stereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Stereotype basicGetStereotype() {
        return this.stereotype;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStereotype(final Stereotype newStereotype) {
        final Stereotype oldStereotype = this.stereotype;
        this.stereotype = newStereotype;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.ROLE__STEREOTYPE, oldStereotype,
                    this.stereotype));
        }
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
        case TypePackage.ROLE__PARAMETERS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getParameters()).basicAdd(otherEnd, msgs);
        case TypePackage.ROLE__AT:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAT((AT) otherEnd, msgs);
        case TypePackage.ROLE__CONSTRAINTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getConstraints()).basicAdd(otherEnd, msgs);
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
        case TypePackage.ROLE__PARAMETERS:
            return ((InternalEList<?>) this.getParameters()).basicRemove(otherEnd, msgs);
        case TypePackage.ROLE__AT:
            return this.basicSetAT(null, msgs);
        case TypePackage.ROLE__CONSTRAINTS:
            return ((InternalEList<?>) this.getConstraints()).basicRemove(otherEnd, msgs);
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
        case TypePackage.ROLE__AT:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.AT__ROLES, AT.class, msgs);
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
        case TypePackage.ROLE__PARAMETERS:
            return this.getParameters();
        case TypePackage.ROLE__AT:
            return this.getAT();
        case TypePackage.ROLE__CONSTRAINTS:
            return this.getConstraints();
        case TypePackage.ROLE__STEREOTYPE:
            if (resolve) {
                return this.getStereotype();
            }
            return this.basicGetStereotype();
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
        case TypePackage.ROLE__PARAMETERS:
            this.getParameters().clear();
            this.getParameters().addAll((Collection<? extends Parameter>) newValue);
            return;
        case TypePackage.ROLE__AT:
            this.setAT((AT) newValue);
            return;
        case TypePackage.ROLE__CONSTRAINTS:
            this.getConstraints().clear();
            this.getConstraints().addAll((Collection<? extends Constraint>) newValue);
            return;
        case TypePackage.ROLE__STEREOTYPE:
            this.setStereotype((Stereotype) newValue);
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
        case TypePackage.ROLE__PARAMETERS:
            this.getParameters().clear();
            return;
        case TypePackage.ROLE__AT:
            this.setAT((AT) null);
            return;
        case TypePackage.ROLE__CONSTRAINTS:
            this.getConstraints().clear();
            return;
        case TypePackage.ROLE__STEREOTYPE:
            this.setStereotype((Stereotype) null);
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
        case TypePackage.ROLE__PARAMETERS:
            return this.parameters != null && !this.parameters.isEmpty();
        case TypePackage.ROLE__AT:
            return this.getAT() != null;
        case TypePackage.ROLE__CONSTRAINTS:
            return this.constraints != null && !this.constraints.isEmpty();
        case TypePackage.ROLE__STEREOTYPE:
            return this.stereotype != null;
        }
        return super.eIsSet(featureID);
    }

} // RoleImpl
