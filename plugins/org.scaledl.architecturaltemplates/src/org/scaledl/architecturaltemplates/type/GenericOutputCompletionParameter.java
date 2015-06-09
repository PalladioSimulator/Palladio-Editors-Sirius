/**
 */
package org.scaledl.architecturaltemplates.type;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Generic Output Completion Parameter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The output of a completion is an arbitrary model, characterized by the
 * given file extension. A user can freely specify this extension as String. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.scaledl.architecturaltemplates.type.GenericOutputCompletionParameter#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getGenericOutputCompletionParameter()
 * @model
 * @generated
 */
public interface GenericOutputCompletionParameter extends CompletionParameter {

    /**
     * Returns the value of the '<em><b>File Extension</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Extension</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>File Extension</em>' attribute.
     * @see #setFileExtension(String)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getGenericOutputCompletionParameter_FileExtension()
     * @model required="true"
     * @generated
     */
    String getFileExtension();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.GenericOutputCompletionParameter#getFileExtension
     * <em>File Extension</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>File Extension</em>' attribute.
     * @see #getFileExtension()
     * @generated
     */
    void setFileExtension(String value);

} // GenericOutputCompletionParameter
