/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Completion</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.Completion#getAT <em>AT</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.type.Completion#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getCompletion()
 * @model abstract="true"
 * @generated
 */
public interface Completion extends EObject {
    /**
     * Returns the value of the '<em><b>AT</b></em>' container reference. It is bidirectional and
     * its opposite is '{@link org.scaledl.architecturaltemplates.type.AT#getCompletion
     * <em>Completion</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT</em>' container reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>AT</em>' container reference.
     * @see #setAT(AT)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getCompletion_AT()
     * @see org.scaledl.architecturaltemplates.type.AT#getCompletion
     * @model opposite="completion" required="true" transient="false"
     * @generated
     */
    AT getAT();

    /**
     * Sets the value of the '{@link org.scaledl.architecturaltemplates.type.Completion#getAT
     * <em>AT</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>AT</em>' container reference.
     * @see #getAT()
     * @generated
     */
    void setAT(AT value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list. The list
     * contents are of type {@link org.scaledl.architecturaltemplates.type.CompletionParameter}. It
     * is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.type.CompletionParameter#getCompletion
     * <em>Completion</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getCompletion_Parameters()
     * @see org.scaledl.architecturaltemplates.type.CompletionParameter#getCompletion
     * @model opposite="completion" containment="true" required="true"
     * @generated
     */
    EList<CompletionParameter> getParameters();

} // Completion
