/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Component2 Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Component2Role#getComponent <em>Component
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Component2Role#getRoles <em>Roles</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance <em>AT
 * Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getComponent2Role()
 * @model
 * @generated
 */
public interface Component2Role extends EObject {

    /**
     * Returns the value of the '<em><b>Component</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Component</em>' reference.
     * @see #setComponent(AssemblyContext)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getComponent2Role_Component()
     * @model required="true"
     * @generated
     */
    AssemblyContext getComponent();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getComponent
     * <em>Component</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Component</em>' reference.
     * @see #getComponent()
     * @generated
     */
    void setComponent(AssemblyContext value);

    /**
     * Returns the value of the '<em><b>Roles</b></em>' reference list. The list contents are of
     * type {@link org.scaledl.architecturaltemplates.type.Role}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Roles</em>' reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Roles</em>' reference list.
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getComponent2Role_Roles()
     * @model
     * @generated
     */
    EList<Role> getRoles();

    /**
     * Returns the value of the '<em><b>AT Instance</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getComponent2roles
     * <em>Component2roles</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT Instance</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>AT Instance</em>' container reference.
     * @see #setATInstance(ATInstance)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getComponent2Role_ATInstance()
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getComponent2roles
     * @model opposite="component2roles" required="true" transient="false"
     * @generated
     */
    ATInstance getATInstance();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance
     * <em>AT Instance</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>AT Instance</em>' container reference.
     * @see #getATInstance()
     * @generated
     */
    void setATInstance(ATInstance value);

} // Component2Role
