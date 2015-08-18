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

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>AT</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.impl.ATImpl#getCompletion <em>Completion</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ATImpl extends EntityImpl implements AT {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ATImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.AT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Repository getRepository() {
        return (Repository) eDynamicGet(TypePackage.AT__REPOSITORY, TypePackage.Literals.AT__REPOSITORY, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRepository(Repository newRepository, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject) newRepository, TypePackage.AT__REPOSITORY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRepository(Repository newRepository) {
        eDynamicSet(TypePackage.AT__REPOSITORY, TypePackage.Literals.AT__REPOSITORY, newRepository);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Role> getRoles() {
        return (EList<Role>) eDynamicGet(TypePackage.AT__ROLES, TypePackage.Literals.AT__ROLES, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<Constraint> getConstraints() {
        return (EList<Constraint>) eDynamicGet(TypePackage.AT__CONSTRAINTS, TypePackage.Literals.AT__CONSTRAINTS, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Completion getCompletion() {
        return (Completion) eDynamicGet(TypePackage.AT__COMPLETION, TypePackage.Literals.AT__COMPLETION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetCompletion(Completion newCompletion, NotificationChain msgs) {
        msgs = eDynamicInverseAdd((InternalEObject) newCompletion, TypePackage.AT__COMPLETION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setCompletion(Completion newCompletion) {
        eDynamicSet(TypePackage.AT__COMPLETION, TypePackage.Literals.AT__COMPLETION, newCompletion);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case TypePackage.AT__REPOSITORY:
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            return basicSetRepository((Repository) otherEnd, msgs);
        case TypePackage.AT__ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getRoles()).basicAdd(otherEnd, msgs);
        case TypePackage.AT__CONSTRAINTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getConstraints()).basicAdd(otherEnd, msgs);
        case TypePackage.AT__COMPLETION:
            Completion completion = getCompletion();
            if (completion != null)
                msgs = ((InternalEObject) completion).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - TypePackage.AT__COMPLETION, null, msgs);
            return basicSetCompletion((Completion) otherEnd, msgs);
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
        case TypePackage.AT__REPOSITORY:
            return basicSetRepository(null, msgs);
        case TypePackage.AT__ROLES:
            return ((InternalEList<?>) getRoles()).basicRemove(otherEnd, msgs);
        case TypePackage.AT__CONSTRAINTS:
            return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
        case TypePackage.AT__COMPLETION:
            return basicSetCompletion(null, msgs);
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
        case TypePackage.AT__REPOSITORY:
            return eInternalContainer().eInverseRemove(this, TypePackage.REPOSITORY__ATS, Repository.class, msgs);
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
        case TypePackage.AT__REPOSITORY:
            return getRepository();
        case TypePackage.AT__ROLES:
            return getRoles();
        case TypePackage.AT__CONSTRAINTS:
            return getConstraints();
        case TypePackage.AT__COMPLETION:
            return getCompletion();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TypePackage.AT__REPOSITORY:
            setRepository((Repository) newValue);
            return;
        case TypePackage.AT__ROLES:
            getRoles().clear();
            getRoles().addAll((Collection<? extends Role>) newValue);
            return;
        case TypePackage.AT__CONSTRAINTS:
            getConstraints().clear();
            getConstraints().addAll((Collection<? extends Constraint>) newValue);
            return;
        case TypePackage.AT__COMPLETION:
            setCompletion((Completion) newValue);
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
        case TypePackage.AT__REPOSITORY:
            setRepository((Repository) null);
            return;
        case TypePackage.AT__ROLES:
            getRoles().clear();
            return;
        case TypePackage.AT__CONSTRAINTS:
            getConstraints().clear();
            return;
        case TypePackage.AT__COMPLETION:
            setCompletion((Completion) null);
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
        case TypePackage.AT__REPOSITORY:
            return getRepository() != null;
        case TypePackage.AT__ROLES:
            return !getRoles().isEmpty();
        case TypePackage.AT__CONSTRAINTS:
            return !getConstraints().isEmpty();
        case TypePackage.AT__COMPLETION:
            return getCompletion() != null;
        }
        return super.eIsSet(featureID);
    }

} // ATImpl
