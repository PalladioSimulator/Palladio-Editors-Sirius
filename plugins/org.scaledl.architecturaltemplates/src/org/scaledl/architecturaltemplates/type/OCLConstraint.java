/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.ocl.expressions.OCLExpression;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>OCL Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.OCLConstraint#getConstraint <em>Constraint
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getOCLConstraint()
 * @model
 * @generated
 */
public interface OCLConstraint extends Constraint {

    /**
     * Returns the value of the '<em><b>Constraint</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Constraint</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Constraint</em>' reference.
     * @see #setConstraint(OCLExpression)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getOCLConstraint_Constraint()
     * @model required="true"
     * @generated
     */
    OCLExpression<?> getConstraint();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.OCLConstraint#getConstraint
     * <em>Constraint</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Constraint</em>' reference.
     * @see #getConstraint()
     * @generated
     */
    void setConstraint(OCLExpression<?> value);

} // OCLConstraint
