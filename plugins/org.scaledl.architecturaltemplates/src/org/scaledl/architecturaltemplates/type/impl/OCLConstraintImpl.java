/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.expressions.OCLExpression;
import org.scaledl.architecturaltemplates.type.OCLConstraint;
import org.scaledl.architecturaltemplates.type.TypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>OCL Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.impl.OCLConstraintImpl#getConstraint <em>
 * Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OCLConstraintImpl extends ConstraintImpl implements OCLConstraint {
    /**
     * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConstraint()
     * @generated
     * @ordered
     */
    protected OCLExpression<?> constraint;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected OCLConstraintImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TypePackage.Literals.OCL_CONSTRAINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OCLExpression<?> getConstraint() {
        if (this.constraint != null && this.constraint.eIsProxy()) {
            final InternalEObject oldConstraint = (InternalEObject) this.constraint;
            this.constraint = (OCLExpression<?>) this.eResolveProxy(oldConstraint);
            if (this.constraint != oldConstraint) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            TypePackage.OCL_CONSTRAINT__CONSTRAINT, oldConstraint, this.constraint));
                }
            }
        }
        return this.constraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public OCLExpression<?> basicGetConstraint() {
        return this.constraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setConstraint(final OCLExpression<?> newConstraint) {
        final OCLExpression<?> oldConstraint = this.constraint;
        this.constraint = newConstraint;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, TypePackage.OCL_CONSTRAINT__CONSTRAINT,
                    oldConstraint, this.constraint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case TypePackage.OCL_CONSTRAINT__CONSTRAINT:
            if (resolve) {
                return this.getConstraint();
            }
            return this.basicGetConstraint();
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
        case TypePackage.OCL_CONSTRAINT__CONSTRAINT:
            this.setConstraint((OCLExpression<?>) newValue);
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
        case TypePackage.OCL_CONSTRAINT__CONSTRAINT:
            this.setConstraint((OCLExpression<?>) null);
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
        case TypePackage.OCL_CONSTRAINT__CONSTRAINT:
            return this.constraint != null;
        }
        return super.eIsSet(featureID);
    }

} // OCLConstraintImpl
