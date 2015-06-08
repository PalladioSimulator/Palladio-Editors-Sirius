/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.cdo.CDOObject;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Role2 Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Component#getComponent <em>Component
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Component#getRole <em>Role</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance <em>AT
 * Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Component()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Role2Component extends CDOObject {

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
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Component_Component()
     * @model required="true"
     * @generated
     */
    AssemblyContext getComponent();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getComponent
     * <em>Component</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Component</em>' reference.
     * @see #getComponent()
     * @generated
     */
    void setComponent(AssemblyContext value);

    /**
     * Returns the value of the '<em><b>Role</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Role</em>' reference.
     * @see #setRole(Role)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Component_Role()
     * @model required="true"
     * @generated
     */
    Role getRole();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getRole <em>Role</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Role</em>' reference.
     * @see #getRole()
     * @generated
     */
    void setRole(Role value);

    /**
     * Returns the value of the '<em><b>AT Instance</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2components
     * <em>Role2components</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT Instance</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>AT Instance</em>' container reference.
     * @see #setATInstance(ATInstance)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Component_ATInstance()
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getRole2components
     * @model opposite="role2components" required="true" transient="false"
     * @generated
     */
    ATInstance getATInstance();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance
     * <em>AT Instance</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>AT Instance</em>' container reference.
     * @see #getATInstance()
     * @generated
     */
    void setATInstance(ATInstance value);

} // Role2Component
