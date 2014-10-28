/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Completion Parameter</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.CompletionParameter#getCompletion <em>
 * Completion</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getCompletionParameter()
 * @model abstract="true"
 * @generated
 */
public interface CompletionParameter extends EObject {
    /**
     * Returns the value of the '<em><b>Completion</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.type.Completion#getParameters <em>Parameters</em>}
     * '. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Completion</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Completion</em>' container reference.
     * @see #setCompletion(Completion)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getCompletionParameter_Completion()
     * @see org.scaledl.architecturaltemplates.type.Completion#getParameters
     * @model opposite="parameters" required="true" transient="false"
     * @generated
     */
    Completion getCompletion();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.CompletionParameter#getCompletion
     * <em>Completion</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Completion</em>' container reference.
     * @see #getCompletion()
     * @generated
     */
    void setCompletion(Completion value);

} // CompletionParameter
