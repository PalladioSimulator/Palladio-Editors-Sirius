/**
 */
package org.scaledl.architecturaltemplates.type;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PCM Output Completion Parameter</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The output of a completion is a PCM model, characterized by its typical
 * file extension. A user can select from available PCM extensions. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter#getFileExtension
 * <em>File Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getPCMOutputCompletionParameter()
 * @model
 * @generated
 */
public interface PCMOutputCompletionParameter extends CompletionParameter {
    /**
     * Returns the value of the '<em><b>File Extension</b></em>' attribute. The literals are from
     * the enumeration {@link org.scaledl.architecturaltemplates.type.PCMFileExtensions}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>File Extension</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>File Extension</em>' attribute.
     * @see org.scaledl.architecturaltemplates.type.PCMFileExtensions
     * @see #setFileExtension(PCMFileExtensions)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getPCMOutputCompletionParameter_FileExtension()
     * @model required="true"
     * @generated
     */
    PCMFileExtensions getFileExtension();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter#getFileExtension
     * <em>File Extension</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>File Extension</em>' attribute.
     * @see org.scaledl.architecturaltemplates.type.PCMFileExtensions
     * @see #getFileExtension()
     * @generated
     */
    void setFileExtension(PCMFileExtensions value);

} // PCMOutputCompletionParameter
