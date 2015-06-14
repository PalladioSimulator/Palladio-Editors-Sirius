/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
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
        return (Repository) this.eDynamicGet(TypePackage.AT__REPOSITORY, TypePackage.Literals.AT__REPOSITORY, true,
                true);
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
        this.eDynamicSet(TypePackage.AT__REPOSITORY, TypePackage.Literals.AT__REPOSITORY, newRepository);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Role> getRoles() {
        return (EList<Role>) this.eDynamicGet(TypePackage.AT__ROLES, TypePackage.Literals.AT__ROLES, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Constraint> getConstraints() {
        return (EList<Constraint>) this.eDynamicGet(TypePackage.AT__CONSTRAINTS, TypePackage.Literals.AT__CONSTRAINTS,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Completion getCompletion() {
        return (Completion) this.eDynamicGet(TypePackage.AT__COMPLETION, TypePackage.Literals.AT__COMPLETION, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetCompletion(final Completion newCompletion, NotificationChain msgs) {
        msgs = this.eDynamicInverseAdd((InternalEObject) newCompletion, TypePackage.AT__COMPLETION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCompletion(final Completion newCompletion) {
        this.eDynamicSet(TypePackage.AT__COMPLETION, TypePackage.Literals.AT__COMPLETION, newCompletion);
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
            final Completion completion = this.getCompletion();
            if (completion != null) {
                msgs = ((InternalEObject) completion).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
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
            return !this.getRoles().isEmpty();
        case TypePackage.AT__CONSTRAINTS:
            return !this.getConstraints().isEmpty();
        case TypePackage.AT__COMPLETION:
            return this.getCompletion() != null;
        }
        return super.eIsSet(featureID);
    }

} // ATImpl
