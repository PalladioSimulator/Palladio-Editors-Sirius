/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.common.util.EList;
import org.scaledl.architecturaltemplates.type.AT;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>AT Instance</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2components <em>
 * Role2components</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getComponent2roles <em>
 * Component2roles</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getAT2Components <em>AT2
 * Components</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getType <em>Type</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getParameterValues <em>
 * Parameter Values</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2resources <em>
 * Role2resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance()
 * @model
 * @generated
 */
public interface ATInstance extends Entity {

    /**
     * Returns the value of the '<em><b>Role2components</b></em>' containment reference list. The
     * list contents are of type {@link org.scaledl.architecturaltemplates.instance.Role2Component}.
     * It is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role2components</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Role2components</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_Role2components()
     * @see org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance
     * @model opposite="ATInstance" containment="true"
     * @generated
     */
    EList<Role2Component> getRole2components();

    /**
     * Returns the value of the '<em><b>Component2roles</b></em>' containment reference list. The
     * list contents are of type {@link org.scaledl.architecturaltemplates.instance.Component2Role}.
     * It is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component2roles</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Component2roles</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_Component2roles()
     * @see org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance
     * @model opposite="ATInstance" containment="true"
     * @generated
     */
    EList<Component2Role> getComponent2roles();

    /**
     * Returns the value of the '<em><b>AT2 Components</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT2 Components</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>AT2 Components</em>' reference.
     * @see #setAT2Components(Role2Component)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_AT2Components()
     * @model required="true"
     * @generated
     */
    Role2Component getAT2Components();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getAT2Components
     * <em>AT2 Components</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>AT2 Components</em>' reference.
     * @see #getAT2Components()
     * @generated
     */
    void setAT2Components(Role2Component value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(AT)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_Type()
     * @model required="true"
     * @generated
     */
    AT getType();

    /**
     * Sets the value of the '{@link org.scaledl.architecturaltemplates.instance.ATInstance#getType
     * <em>Type</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(AT value);

    /**
     * Returns the value of the '<em><b>Parameter Values</b></em>' containment reference list. The
     * list contents are of type {@link org.scaledl.architecturaltemplates.instance.ParameterValue}.
     * It is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance
     * <em>Architectural Template Instance</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter Values</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parameter Values</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_ParameterValues()
     * @see org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance
     * @model opposite="architecturalTemplateInstance" containment="true"
     * @generated
     */
    EList<ParameterValue> getParameterValues();

    /**
     * Returns the value of the '<em><b>Role2resources</b></em>' containment reference list. The
     * list contents are of type {@link org.scaledl.architecturaltemplates.instance.Role2Resource}.
     * It is bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role2resources</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Role2resources</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getATInstance_Role2resources()
     * @see org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance
     * @model opposite="ATInstance" containment="true"
     * @generated
     */
    EList<Role2Resource> getRole2resources();

} // ATInstance
