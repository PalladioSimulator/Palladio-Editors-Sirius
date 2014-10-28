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
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Completion;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.Repository;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TypePackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>AT</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getRepository <em>Repository</em>}
 * </li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getRoles <em>Roles</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getConstraints <em>Constraints
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getCompletion <em>Completion</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ATImpl extends EntityImpl implements AT {
    /**
     * The cached value of the '{@link #getRoles() <em>Roles</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRoles()
     * @generated
     * @ordered
     */
    protected EList<Role> roles;

    /**
     * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConstraints()
     * @generated
     * @ordered
     */
    protected EList<Constraint> constraints;

    /**
     * The cached value of the '{@link #getCompletion() <em>Completion</em>}' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCompletion()
     * @generated
     * @ordered
     */
    protected Completion completion;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ATImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.AT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Repository getRepository() {
        if (this.eContainerFeatureID() != TypePackage.AT__REPOSITORY) {
            return null;
        }
        return (Repository) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRepository(final Repository newRepository, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRepository, TypePackage.AT__REPOSITORY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepository(final Repository newRepository) {
        if (newRepository != this.eInternalContainer()
                || (this.eContainerFeatureID() != TypePackage.AT__REPOSITORY && newRepository != null)) {
            if (EcoreUtil.isAncestor(this, newRepository)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newRepository != null) {
                msgs = ((InternalEObject) newRepository).eInverseAdd(this, TypePackage.REPOSITORY__ATS,
                        Repository.class, msgs);
            }
            msgs = this.basicSetRepository(newRepository, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.AT__REPOSITORY, newRepository,
                    newRepository));
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
            this.roles = new EObjectContainmentWithInverseEList<Role>(Role.class, this, TypePackage.AT__ROLES,
                    TypePackage.ROLE__AT);
        }
        return this.roles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Constraint> getConstraints() {
        if (this.constraints == null) {
            this.constraints = new EObjectContainmentWithInverseEList<Constraint>(Constraint.class, this,
                    TypePackage.AT__CONSTRAINTS, TypePackage.CONSTRAINT__AT);
        }
        return this.constraints;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Completion getCompletion() {
        return this.completion;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCompletion(final Completion newCompletion, NotificationChain msgs) {
        final Completion oldCompletion = this.completion;
        this.completion = newCompletion;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    TypePackage.AT__COMPLETION, oldCompletion, newCompletion);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCompletion(final Completion newCompletion) {
        if (newCompletion != this.completion) {
            NotificationChain msgs = null;
            if (this.completion != null) {
                msgs = ((InternalEObject) this.completion).eInverseRemove(this, TypePackage.COMPLETION__AT,
                        Completion.class, msgs);
            }
            if (newCompletion != null) {
                msgs = ((InternalEObject) newCompletion).eInverseAdd(this, TypePackage.COMPLETION__AT,
                        Completion.class, msgs);
            }
            msgs = this.basicSetCompletion(newCompletion, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.AT__COMPLETION, newCompletion,
                    newCompletion));
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
        case TypePackage.AT__REPOSITORY:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRepository((Repository) otherEnd, msgs);
        case TypePackage.AT__ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getRoles()).basicAdd(otherEnd, msgs);
        case TypePackage.AT__CONSTRAINTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getConstraints()).basicAdd(otherEnd, msgs);
        case TypePackage.AT__COMPLETION:
            if (this.completion != null) {
                msgs = ((InternalEObject) this.completion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - TypePackage.AT__COMPLETION, null, msgs);
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
        case TypePackage.AT__REPOSITORY:
            return this.basicSetRepository(null, msgs);
        case TypePackage.AT__ROLES:
            return ((InternalEList<?>) this.getRoles()).basicRemove(otherEnd, msgs);
        case TypePackage.AT__CONSTRAINTS:
            return ((InternalEList<?>) this.getConstraints()).basicRemove(otherEnd, msgs);
        case TypePackage.AT__COMPLETION:
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
        case TypePackage.AT__REPOSITORY:
            return this.eInternalContainer().eInverseRemove(this, TypePackage.REPOSITORY__ATS, Repository.class, msgs);
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
        case TypePackage.AT__REPOSITORY:
            return this.getRepository();
        case TypePackage.AT__ROLES:
            return this.getRoles();
        case TypePackage.AT__CONSTRAINTS:
            return this.getConstraints();
        case TypePackage.AT__COMPLETION:
            return this.getCompletion();
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
        case TypePackage.AT__REPOSITORY:
            this.setRepository((Repository) newValue);
            return;
        case TypePackage.AT__ROLES:
            this.getRoles().clear();
            this.getRoles().addAll((Collection<? extends Role>) newValue);
            return;
        case TypePackage.AT__CONSTRAINTS:
            this.getConstraints().clear();
            this.getConstraints().addAll((Collection<? extends Constraint>) newValue);
            return;
        case TypePackage.AT__COMPLETION:
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
        case TypePackage.AT__REPOSITORY:
            this.setRepository((Repository) null);
            return;
        case TypePackage.AT__ROLES:
            this.getRoles().clear();
            return;
        case TypePackage.AT__CONSTRAINTS:
            this.getConstraints().clear();
            return;
        case TypePackage.AT__COMPLETION:
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
        case TypePackage.AT__REPOSITORY:
            return this.getRepository() != null;
        case TypePackage.AT__ROLES:
            return this.roles != null && !this.roles.isEmpty();
        case TypePackage.AT__CONSTRAINTS:
            return this.constraints != null && !this.constraints.isEmpty();
        case TypePackage.AT__COMPLETION:
            return this.completion != null;
        }
        return super.eIsSet(featureID);
    }

} // ATImpl
