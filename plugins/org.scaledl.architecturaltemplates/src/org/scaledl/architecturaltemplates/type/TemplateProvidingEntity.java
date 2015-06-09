/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.emf.cdo.CDOObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Template Providing Entity</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The input of a completion is based on a template model file. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.TemplateProvidingEntity#getTemplateFileURI
 * <em>Template File URI</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getTemplateProvidingEntity()
 * @model abstract="true"
 * @extends CDOObject
 * @generated
 */
public interface TemplateProvidingEntity extends CDOObject {

    /**
     * Returns the value of the '<em><b>Template File URI</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Template File URI</em>' attribute isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template File URI</em>' attribute.
     * @see #setTemplateFileURI(String)
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getTemplateProvidingEntity_TemplateFileURI()
     * @model required="true"
     * @generated
     */
    String getTemplateFileURI();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.type.TemplateProvidingEntity#getTemplateFileURI
     * <em>Template File URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template File URI</em>' attribute.
     * @see #getTemplateFileURI()
     * @generated
     */
    void setTemplateFileURI(String value);

} // TemplateProvidingEntity
