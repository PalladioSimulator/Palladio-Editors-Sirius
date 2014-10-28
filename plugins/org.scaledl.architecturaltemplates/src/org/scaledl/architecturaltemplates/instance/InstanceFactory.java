/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage
 * @generated
 */
public interface InstanceFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    InstanceFactory eINSTANCE = org.scaledl.architecturaltemplates.instance.impl.InstanceFactoryImpl.init();

    /**
     * Returns a new object of class '<em>AT Instance</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>AT Instance</em>'.
     * @generated
     */
    ATInstance createATInstance();

    /**
     * Returns a new object of class '<em>Component2 Role</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Component2 Role</em>'.
     * @generated
     */
    Component2Role createComponent2Role();

    /**
     * Returns a new object of class '<em>Role2 Component</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Role2 Component</em>'.
     * @generated
     */
    Role2Component createRole2Component();

    /**
     * Returns a new object of class '<em>Enum Parameter</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Enum Parameter</em>'.
     * @generated
     */
    EnumParameter createEnumParameter();

    /**
     * Returns a new object of class '<em>Integer Parameter</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Integer Parameter</em>'.
     * @generated
     */
    IntegerParameter createIntegerParameter();

    /**
     * Returns a new object of class '<em>Float Parameter</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Float Parameter</em>'.
     * @generated
     */
    FloatParameter createFloatParameter();

    /**
     * Returns a new object of class '<em>String Parameter</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>String Parameter</em>'.
     * @generated
     */
    <T> StringParameter<T> createStringParameter();

    /**
     * Returns a new object of class '<em>Role2 Resource</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Role2 Resource</em>'.
     * @generated
     */
    Role2Resource createRole2Resource();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    InstancePackage getInstancePackage();

} // InstanceFactory
