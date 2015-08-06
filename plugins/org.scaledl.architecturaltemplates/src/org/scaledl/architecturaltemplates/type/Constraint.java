/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.emf.common.util.EList;

import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Constraint</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.scaledl.architecturaltemplates.type.Constraint#getAT <em>AT</em>}</li>
 *   <li>{@link org.scaledl.architecturaltemplates.type.Constraint#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getConstraint()
 * @model abstract="true"
 * @generated
 */
public interface Constraint extends Entity {

    /**
     * Returns the value of the '<em><b>AT</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.scaledl.architecturaltemplates.type.AT#getConstraints <em>Constraints</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT</em>' container reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>AT</em>' container reference.
     * @see #setAT(AT)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getConstraint_AT()
     * @see org.scaledl.architecturaltemplates.type.AT#getConstraints
     * @model opposite="constraints" required="true" transient="false"
     * @generated
     */
    AT getAT();

    /**
     * Sets the value of the '{@link org.scaledl.architecturaltemplates.type.Constraint#getAT <em>AT</em>}' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>AT</em>' container reference.
     * @see #getAT()
     * @generated
     */
    void setAT(AT value);

    /**
     * Returns the value of the '<em><b>Roles</b></em>' reference list.
     * The list contents are of type {@link org.scaledl.architecturaltemplates.type.Role}.
     * It is bidirectional and its opposite is '{@link org.scaledl.architecturaltemplates.type.Role#getConstraints <em>Constraints</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Roles</em>' reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Roles</em>' reference list.
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getConstraint_Roles()
     * @see org.scaledl.architecturaltemplates.type.Role#getConstraints
     * @model opposite="constraints"
     * @generated
     */
    EList<Role> getRoles();

} // Constraint
