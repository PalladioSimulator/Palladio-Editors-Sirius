/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EClass;
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
        return (OCLExpression<?>) this.eDynamicGet(TypePackage.OCL_CONSTRAINT__CONSTRAINT,
                TypePackage.Literals.OCL_CONSTRAINT__CONSTRAINT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OCLExpression<?> basicGetConstraint() {
        return (OCLExpression<?>) this.eDynamicGet(TypePackage.OCL_CONSTRAINT__CONSTRAINT,
                TypePackage.Literals.OCL_CONSTRAINT__CONSTRAINT, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setConstraint(final OCLExpression<?> newConstraint) {
        this.eDynamicSet(TypePackage.OCL_CONSTRAINT__CONSTRAINT, TypePackage.Literals.OCL_CONSTRAINT__CONSTRAINT,
                newConstraint);
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
            return this.basicGetConstraint() != null;
        }
        return super.eIsSet(featureID);
    }

} // OCLConstraintImpl
