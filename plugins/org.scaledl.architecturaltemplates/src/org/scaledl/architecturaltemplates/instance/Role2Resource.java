/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.cdo.CDOObject;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Role2 Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance <em>AT
 * Instance</em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Resource#getResource <em>Resource
 * </em>}</li>
 * <li>{@link org.scaledl.architecturaltemplates.instance.Role2Resource#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Resource()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Role2Resource extends CDOObject {

    /**
     * Returns the value of the '<em><b>AT Instance</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2resources
     * <em>Role2resources</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>AT Instance</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>AT Instance</em>' container reference.
     * @see #setATInstance(ATInstance)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Resource_ATInstance()
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getRole2resources
     * @model opposite="role2resources" required="true" transient="false"
     * @generated
     */
    ATInstance getATInstance();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance
     * <em>AT Instance</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>AT Instance</em>' container reference.
     * @see #getATInstance()
     * @generated
     */
    void setATInstance(ATInstance value);

    /**
     * Returns the value of the '<em><b>Resource</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resource</em>' reference.
     * @see #setResource(ResourceContainer)
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Resource_Resource()
     * @model required="true"
     * @generated
     */
    ResourceContainer getResource();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getResource
     * <em>Resource</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Resource</em>' reference.
     * @see #getResource()
     * @generated
     */
    void setResource(ResourceContainer value);

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
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#getRole2Resource_Role()
     * @model required="true"
     * @generated
     */
    Role getRole();

    /**
     * Sets the value of the '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getRole <em>Role</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Role</em>' reference.
     * @see #getRole()
     * @generated
     */
    void setRole(Role value);

} // Role2Resource
