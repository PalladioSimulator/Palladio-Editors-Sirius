/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.ecore.EObject;
import org.scaledl.architecturaltemplates.type.Parameter;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Parameter Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ParameterValue#getType <em>Type</em>}</li>
 * <li>
 * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance
 * <em>Architectural Template Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getParameterValue()
 * @model abstract="true"
 * @generated
 */
public interface ParameterValue extends EObject {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(Parameter)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getParameterValue_Type()
     * @model required="true"
     * @generated
     */
    Parameter getType();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getType <em>Type</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(Parameter value);

    /**
     * Returns the value of the '<em><b>Architectural Template Instance</b></em>' container
     * reference. It is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getParameterValues
     * <em>Parameter Values</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Architectural Template Instance</em>' container reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Architectural Template Instance</em>' container reference.
     * @see #setArchitecturalTemplateInstance(ATInstance)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getParameterValue_ArchitecturalTemplateInstance()
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getParameterValues
     * @model opposite="parameterValues" required="true" transient="false"
     * @generated
     */
    ATInstance getArchitecturalTemplateInstance();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance
     * <em>Architectural Template Instance</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Architectural Template Instance</em>' container
     *            reference.
     * @see #getArchitecturalTemplateInstance()
     * @generated
     */
    void setArchitecturalTemplateInstance(ATInstance value);

} // ParameterValue
