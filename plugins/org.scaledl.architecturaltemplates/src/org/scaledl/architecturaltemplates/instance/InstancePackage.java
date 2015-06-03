/**
 */
package org.scaledl.architecturaltemplates.instance;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.scaledl.architecturaltemplates.instance.InstanceFactory
 * @model kind="package"
 * @generated
 */
public interface InstancePackage extends EPackage {

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "instance";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://scaledl.org/ArchitecturalTemplates/Instance/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "org.scaledl.architecturaltemplates";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    InstancePackage eINSTANCE = org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl <em>AT Instance</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getATInstance()
     * @generated
     */
    int AT_INSTANCE = 0;

    /**
     * The feature id for the '<em><b>Profileable Element</b></em>' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__PROFILEABLE_ELEMENT = EntityPackage.ENTITY__PROFILEABLE_ELEMENT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Role2components</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__ROLE2COMPONENTS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Component2roles</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__COMPONENT2ROLES = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>AT2 Components</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__AT2_COMPONENTS = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__TYPE = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Parameter Values</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__PARAMETER_VALUES = EntityPackage.ENTITY_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Role2resources</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE__ROLE2RESOURCES = EntityPackage.ENTITY_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>AT Instance</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int AT_INSTANCE_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl
     * <em>Component2 Role</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getComponent2Role()
     * @generated
     */
    int COMPONENT2_ROLE = 1;

    /**
     * The feature id for the '<em><b>Component</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT2_ROLE__COMPONENT = 0;

    /**
     * The feature id for the '<em><b>Roles</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT2_ROLE__ROLES = 1;

    /**
     * The feature id for the '<em><b>AT Instance</b></em>' container reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT2_ROLE__AT_INSTANCE = 2;

    /**
     * The number of structural features of the '<em>Component2 Role</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPONENT2_ROLE_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl
     * <em>Role2 Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getRole2Component()
     * @generated
     */
    int ROLE2_COMPONENT = 2;

    /**
     * The feature id for the '<em><b>Component</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_COMPONENT__COMPONENT = 0;

    /**
     * The feature id for the '<em><b>Role</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_COMPONENT__ROLE = 1;

    /**
     * The feature id for the '<em><b>AT Instance</b></em>' container reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_COMPONENT__AT_INSTANCE = 2;

    /**
     * The number of structural features of the '<em>Role2 Component</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_COMPONENT_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl
     * <em>Parameter Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getParameterValue()
     * @generated
     */
    int PARAMETER_VALUE = 3;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE__TYPE = 0;

    /**
     * The feature id for the '<em><b>Architectural Template Instance</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE = 1;

    /**
     * The number of structural features of the '<em>Parameter Value</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PARAMETER_VALUE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.EnumParameterImpl
     * <em>Enum Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.EnumParameterImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getEnumParameter()
     * @generated
     */
    int ENUM_PARAMETER = 4;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_PARAMETER__TYPE = PARAMETER_VALUE__TYPE;

    /**
     * The feature id for the '<em><b>Architectural Template Instance</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_PARAMETER__ARCHITECTURAL_TEMPLATE_INSTANCE = PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE;

    /**
     * The number of structural features of the '<em>Enum Parameter</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_PARAMETER_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.IntegerParameterImpl
     * <em>Integer Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.IntegerParameterImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getIntegerParameter()
     * @generated
     */
    int INTEGER_PARAMETER = 5;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_PARAMETER__TYPE = PARAMETER_VALUE__TYPE;

    /**
     * The feature id for the '<em><b>Architectural Template Instance</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_PARAMETER__ARCHITECTURAL_TEMPLATE_INSTANCE = PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE;

    /**
     * The number of structural features of the '<em>Integer Parameter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_PARAMETER_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.FloatParameterImpl
     * <em>Float Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.FloatParameterImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getFloatParameter()
     * @generated
     */
    int FLOAT_PARAMETER = 6;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLOAT_PARAMETER__TYPE = PARAMETER_VALUE__TYPE;

    /**
     * The feature id for the '<em><b>Architectural Template Instance</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLOAT_PARAMETER__ARCHITECTURAL_TEMPLATE_INSTANCE = PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE;

    /**
     * The number of structural features of the '<em>Float Parameter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLOAT_PARAMETER_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.StringParameterImpl
     * <em>String Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.StringParameterImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getStringParameter()
     * @generated
     */
    int STRING_PARAMETER = 7;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_PARAMETER__TYPE = PARAMETER_VALUE__TYPE;

    /**
     * The feature id for the '<em><b>Architectural Template Instance</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_PARAMETER__ARCHITECTURAL_TEMPLATE_INSTANCE = PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE;

    /**
     * The number of structural features of the '<em>String Parameter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_PARAMETER_FEATURE_COUNT = PARAMETER_VALUE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl
     * <em>Role2 Resource</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl
     * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getRole2Resource()
     * @generated
     */
    int ROLE2_RESOURCE = 8;

    /**
     * The feature id for the '<em><b>AT Instance</b></em>' container reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_RESOURCE__AT_INSTANCE = 0;

    /**
     * The feature id for the '<em><b>Resource</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_RESOURCE__RESOURCE = 1;

    /**
     * The feature id for the '<em><b>Role</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_RESOURCE__ROLE = 2;

    /**
     * The number of structural features of the '<em>Role2 Resource</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ROLE2_RESOURCE_FEATURE_COUNT = 3;

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance <em>AT Instance</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>AT Instance</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance
     * @generated
     */
    EClass getATInstance();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2components
     * <em>Role2components</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Role2components</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getRole2components()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_Role2components();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getComponent2roles
     * <em>Component2roles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Component2roles</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getComponent2roles()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_Component2roles();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getAT2Components
     * <em>AT2 Components</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>AT2 Components</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getAT2Components()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_AT2Components();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getType()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_Type();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getParameterValues
     * <em>Parameter Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Parameter Values</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getParameterValues()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_ParameterValues();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.scaledl.architecturaltemplates.instance.ATInstance#getRole2resources
     * <em>Role2resources</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Role2resources</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ATInstance#getRole2resources()
     * @see #getATInstance()
     * @generated
     */
    EReference getATInstance_Role2resources();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role <em>Component2 Role</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Component2 Role</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Component2Role
     * @generated
     */
    EClass getComponent2Role();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getComponent
     * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Component2Role#getComponent()
     * @see #getComponent2Role()
     * @generated
     */
    EReference getComponent2Role_Component();

    /**
     * Returns the meta object for the reference list '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getRoles <em>Roles</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Roles</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Component2Role#getRoles()
     * @see #getComponent2Role()
     * @generated
     */
    EReference getComponent2Role_Roles();

    /**
     * Returns the meta object for the container reference '
     * {@link org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>AT Instance</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Component2Role#getATInstance()
     * @see #getComponent2Role()
     * @generated
     */
    EReference getComponent2Role_ATInstance();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component <em>Role2 Component</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Role2 Component</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Component
     * @generated
     */
    EClass getRole2Component();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getComponent
     * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Component#getComponent()
     * @see #getRole2Component()
     * @generated
     */
    EReference getRole2Component_Component();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getRole <em>Role</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Role</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Component#getRole()
     * @see #getRole2Component()
     * @generated
     */
    EReference getRole2Component_Role();

    /**
     * Returns the meta object for the container reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>AT Instance</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Component#getATInstance()
     * @see #getRole2Component()
     * @generated
     */
    EReference getRole2Component_ATInstance();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue <em>Parameter Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Parameter Value</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ParameterValue
     * @generated
     */
    EClass getParameterValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getType <em>Type</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.scaledl.architecturaltemplates.instance.ParameterValue#getType()
     * @see #getParameterValue()
     * @generated
     */
    EReference getParameterValue_Type();

    /**
     * Returns the meta object for the container reference '
     * {@link org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance
     * <em>Architectural Template Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Architectural Template Instance</em>
     *         '.
     * @see org.scaledl.architecturaltemplates.instance.ParameterValue#getArchitecturalTemplateInstance()
     * @see #getParameterValue()
     * @generated
     */
    EReference getParameterValue_ArchitecturalTemplateInstance();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.EnumParameter <em>Enum Parameter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Enum Parameter</em>'.
     * @see org.scaledl.architecturaltemplates.instance.EnumParameter
     * @generated
     */
    EClass getEnumParameter();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.IntegerParameter
     * <em>Integer Parameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Integer Parameter</em>'.
     * @see org.scaledl.architecturaltemplates.instance.IntegerParameter
     * @generated
     */
    EClass getIntegerParameter();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.FloatParameter <em>Float Parameter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Float Parameter</em>'.
     * @see org.scaledl.architecturaltemplates.instance.FloatParameter
     * @generated
     */
    EClass getFloatParameter();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.StringParameter <em>String Parameter</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>String Parameter</em>'.
     * @see org.scaledl.architecturaltemplates.instance.StringParameter
     * @generated
     */
    EClass getStringParameter();

    /**
     * Returns the meta object for class '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource <em>Role2 Resource</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Role2 Resource</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Resource
     * @generated
     */
    EClass getRole2Resource();

    /**
     * Returns the meta object for the container reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance
     * <em>AT Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>AT Instance</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Resource#getATInstance()
     * @see #getRole2Resource()
     * @generated
     */
    EReference getRole2Resource_ATInstance();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getResource
     * <em>Resource</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Resource</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Resource#getResource()
     * @see #getRole2Resource()
     * @generated
     */
    EReference getRole2Resource_Resource();

    /**
     * Returns the meta object for the reference '
     * {@link org.scaledl.architecturaltemplates.instance.Role2Resource#getRole <em>Role</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Role</em>'.
     * @see org.scaledl.architecturaltemplates.instance.Role2Resource#getRole()
     * @see #getRole2Resource()
     * @generated
     */
    EReference getRole2Resource_Role();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    InstanceFactory getInstanceFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl
         * <em>AT Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.ATInstanceImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getATInstance()
         * @generated
         */
        EClass AT_INSTANCE = eINSTANCE.getATInstance();

        /**
         * The meta object literal for the '<em><b>Role2components</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__ROLE2COMPONENTS = eINSTANCE.getATInstance_Role2components();

        /**
         * The meta object literal for the '<em><b>Component2roles</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__COMPONENT2ROLES = eINSTANCE.getATInstance_Component2roles();

        /**
         * The meta object literal for the '<em><b>AT2 Components</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__AT2_COMPONENTS = eINSTANCE.getATInstance_AT2Components();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__TYPE = eINSTANCE.getATInstance_Type();

        /**
         * The meta object literal for the '<em><b>Parameter Values</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__PARAMETER_VALUES = eINSTANCE.getATInstance_ParameterValues();

        /**
         * The meta object literal for the '<em><b>Role2resources</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference AT_INSTANCE__ROLE2RESOURCES = eINSTANCE.getATInstance_Role2resources();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl
         * <em>Component2 Role</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.Component2RoleImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getComponent2Role()
         * @generated
         */
        EClass COMPONENT2_ROLE = eINSTANCE.getComponent2Role();

        /**
         * The meta object literal for the '<em><b>Component</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT2_ROLE__COMPONENT = eINSTANCE.getComponent2Role_Component();

        /**
         * The meta object literal for the '<em><b>Roles</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT2_ROLE__ROLES = eINSTANCE.getComponent2Role_Roles();

        /**
         * The meta object literal for the '<em><b>AT Instance</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPONENT2_ROLE__AT_INSTANCE = eINSTANCE.getComponent2Role_ATInstance();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl
         * <em>Role2 Component</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.Role2ComponentImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getRole2Component()
         * @generated
         */
        EClass ROLE2_COMPONENT = eINSTANCE.getRole2Component();

        /**
         * The meta object literal for the '<em><b>Component</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_COMPONENT__COMPONENT = eINSTANCE.getRole2Component_Component();

        /**
         * The meta object literal for the '<em><b>Role</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_COMPONENT__ROLE = eINSTANCE.getRole2Component_Role();

        /**
         * The meta object literal for the '<em><b>AT Instance</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_COMPONENT__AT_INSTANCE = eINSTANCE.getRole2Component_ATInstance();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl
         * <em>Parameter Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.ParameterValueImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getParameterValue()
         * @generated
         */
        EClass PARAMETER_VALUE = eINSTANCE.getParameterValue();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PARAMETER_VALUE__TYPE = eINSTANCE.getParameterValue_Type();

        /**
         * The meta object literal for the '<em><b>Architectural Template Instance</b></em>'
         * container reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE = eINSTANCE
                .getParameterValue_ArchitecturalTemplateInstance();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.EnumParameterImpl
         * <em>Enum Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.EnumParameterImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getEnumParameter()
         * @generated
         */
        EClass ENUM_PARAMETER = eINSTANCE.getEnumParameter();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.IntegerParameterImpl
         * <em>Integer Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.IntegerParameterImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getIntegerParameter()
         * @generated
         */
        EClass INTEGER_PARAMETER = eINSTANCE.getIntegerParameter();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.FloatParameterImpl
         * <em>Float Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.FloatParameterImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getFloatParameter()
         * @generated
         */
        EClass FLOAT_PARAMETER = eINSTANCE.getFloatParameter();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.StringParameterImpl
         * <em>String Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.StringParameterImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getStringParameter()
         * @generated
         */
        EClass STRING_PARAMETER = eINSTANCE.getStringParameter();

        /**
         * The meta object literal for the '
         * {@link org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl
         * <em>Role2 Resource</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.scaledl.architecturaltemplates.instance.impl.Role2ResourceImpl
         * @see org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl#getRole2Resource()
         * @generated
         */
        EClass ROLE2_RESOURCE = eINSTANCE.getRole2Resource();

        /**
         * The meta object literal for the '<em><b>AT Instance</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_RESOURCE__AT_INSTANCE = eINSTANCE.getRole2Resource_ATInstance();

        /**
         * The meta object literal for the '<em><b>Resource</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_RESOURCE__RESOURCE = eINSTANCE.getRole2Resource_Resource();

        /**
         * The meta object literal for the '<em><b>Role</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE2_RESOURCE__ROLE = eINSTANCE.getRole2Resource_Role();

    }

} // InstancePackage
