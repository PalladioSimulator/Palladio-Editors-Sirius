/**
 */
package org.scaledl.architecturaltemplates.type;

import org.eclipse.emf.common.util.EList;

import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Repository</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Allows to collect Architectural Templates within a catalogue. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.scaledl.architecturaltemplates.type.Repository#getATs <em>ATs</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.scaledl.architecturaltemplates.type.TypePackage#getRepository()
 * @model
 * @generated
 */
public interface Repository extends Entity {

    /**
     * Returns the value of the '<em><b>ATs</b></em>' containment reference list. The list contents
     * are of type {@link org.scaledl.architecturaltemplates.type.AT}. It is bidirectional and its
     * opposite is '{@link org.scaledl.architecturaltemplates.type.AT#getRepository
     * <em>Repository</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ATs</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>ATs</em>' containment reference list.
     * @see org.scaledl.architecturaltemplates.type.TypePackage#getRepository_ATs()
     * @see org.scaledl.architecturaltemplates.type.AT#getRepository
     * @model opposite="repository" containment="true"
     * @generated
     */
    EList<AT> getATs();

} // Repository
