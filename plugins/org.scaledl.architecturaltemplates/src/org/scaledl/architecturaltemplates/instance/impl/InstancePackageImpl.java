/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.types.TypesPackage;
import org.eclipse.ocl.utilities.UtilitiesPackage;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.EnumParameter;
import org.scaledl.architecturaltemplates.instance.FloatParameter;
import org.scaledl.architecturaltemplates.instance.InstanceFactory;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.IntegerParameter;
import org.scaledl.architecturaltemplates.instance.ParameterValue;
import org.scaledl.architecturaltemplates.instance.Role2Component;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.instance.StringParameter;
import org.scaledl.architecturaltemplates.type.TypePackage;
import org.scaledl.architecturaltemplates.type.impl.TypePackageImpl;

import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class InstancePackageImpl extends EPackageImpl implements InstancePackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass atInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass component2RoleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass role2ComponentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass parameterValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass enumParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass integerParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass floatParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stringParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass role2ResourceEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.scaledl.architecturaltemplates.instance.InstancePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private InstancePackageImpl() {
        super(eNS_URI, InstanceFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link InstancePackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static InstancePackage init() {
        if (isInited) {
            return (InstancePackage) EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI);
        }

        // Obtain or create and register package
        final InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof InstancePackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new InstancePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EMFProfilePackage.eINSTANCE.eClass();
        TypesPackage.eINSTANCE.eClass();
        UtilitiesPackage.eINSTANCE.eClass();
        ExpressionsPackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        final TypePackageImpl theTypePackage = (TypePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(TypePackage.eNS_URI) instanceof TypePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TypePackage.eNS_URI) : TypePackage.eINSTANCE);

        // Create package meta-data objects
        theInstancePackage.createPackageContents();
        theTypePackage.createPackageContents();

        // Initialize created meta-data
        theInstancePackage.initializePackageContents();
        theTypePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theInstancePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(InstancePackage.eNS_URI, theInstancePackage);
        return theInstancePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getATInstance() {
        return this.atInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_Role2components() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_Component2roles() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_AT2Components() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_Type() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_ParameterValues() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getATInstance_Role2resources() {
        return (EReference) this.atInstanceEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getComponent2Role() {
        return this.component2RoleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getComponent2Role_Component() {
        return (EReference) this.component2RoleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getComponent2Role_Roles() {
        return (EReference) this.component2RoleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getComponent2Role_ATInstance() {
        return (EReference) this.component2RoleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRole2Component() {
        return this.role2ComponentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Component_Component() {
        return (EReference) this.role2ComponentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Component_Role() {
        return (EReference) this.role2ComponentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Component_ATInstance() {
        return (EReference) this.role2ComponentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getParameterValue() {
        return this.parameterValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getParameterValue_Type() {
        return (EReference) this.parameterValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getParameterValue_ArchitecturalTemplateInstance() {
        return (EReference) this.parameterValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEnumParameter() {
        return this.enumParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIntegerParameter() {
        return this.integerParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFloatParameter() {
        return this.floatParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStringParameter() {
        return this.stringParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRole2Resource() {
        return this.role2ResourceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Resource_ATInstance() {
        return (EReference) this.role2ResourceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Resource_Resource() {
        return (EReference) this.role2ResourceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRole2Resource_Role() {
        return (EReference) this.role2ResourceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public InstanceFactory getInstanceFactory() {
        return (InstanceFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;

        // Create classes and their features
        this.atInstanceEClass = this.createEClass(AT_INSTANCE);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__ROLE2COMPONENTS);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__COMPONENT2ROLES);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__AT2_COMPONENTS);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__TYPE);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__PARAMETER_VALUES);
        this.createEReference(this.atInstanceEClass, AT_INSTANCE__ROLE2RESOURCES);

        this.component2RoleEClass = this.createEClass(COMPONENT2_ROLE);
        this.createEReference(this.component2RoleEClass, COMPONENT2_ROLE__COMPONENT);
        this.createEReference(this.component2RoleEClass, COMPONENT2_ROLE__ROLES);
        this.createEReference(this.component2RoleEClass, COMPONENT2_ROLE__AT_INSTANCE);

        this.role2ComponentEClass = this.createEClass(ROLE2_COMPONENT);
        this.createEReference(this.role2ComponentEClass, ROLE2_COMPONENT__COMPONENT);
        this.createEReference(this.role2ComponentEClass, ROLE2_COMPONENT__ROLE);
        this.createEReference(this.role2ComponentEClass, ROLE2_COMPONENT__AT_INSTANCE);

        this.parameterValueEClass = this.createEClass(PARAMETER_VALUE);
        this.createEReference(this.parameterValueEClass, PARAMETER_VALUE__TYPE);
        this.createEReference(this.parameterValueEClass, PARAMETER_VALUE__ARCHITECTURAL_TEMPLATE_INSTANCE);

        this.enumParameterEClass = this.createEClass(ENUM_PARAMETER);

        this.integerParameterEClass = this.createEClass(INTEGER_PARAMETER);

        this.floatParameterEClass = this.createEClass(FLOAT_PARAMETER);

        this.stringParameterEClass = this.createEClass(STRING_PARAMETER);

        this.role2ResourceEClass = this.createEClass(ROLE2_RESOURCE);
        this.createEReference(this.role2ResourceEClass, ROLE2_RESOURCE__AT_INSTANCE);
        this.createEReference(this.role2ResourceEClass, ROLE2_RESOURCE__RESOURCE);
        this.createEReference(this.role2ResourceEClass, ROLE2_RESOURCE__ROLE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Obtain other dependent packages
        final EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE
                .getEPackage(EntityPackage.eNS_URI);
        final TypePackage theTypePackage = (TypePackage) EPackage.Registry.INSTANCE.getEPackage(TypePackage.eNS_URI);
        final CompositionPackage theCompositionPackage = (CompositionPackage) EPackage.Registry.INSTANCE
                .getEPackage(CompositionPackage.eNS_URI);
        final ResourceenvironmentPackage theResourceenvironmentPackage = (ResourceenvironmentPackage) EPackage.Registry.INSTANCE
                .getEPackage(ResourceenvironmentPackage.eNS_URI);

        // Create type parameters
        this.addETypeParameter(this.stringParameterEClass, "T");

        // Set bounds for type parameters

        // Add supertypes to classes
        this.atInstanceEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.enumParameterEClass.getESuperTypes().add(this.getParameterValue());
        this.integerParameterEClass.getESuperTypes().add(this.getParameterValue());
        this.floatParameterEClass.getESuperTypes().add(this.getParameterValue());
        this.stringParameterEClass.getESuperTypes().add(this.getParameterValue());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.atInstanceEClass, ATInstance.class, "ATInstance", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getATInstance_Role2components(), this.getRole2Component(),
                this.getRole2Component_ATInstance(), "role2components", null, 0, -1, ATInstance.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        this.initEReference(this.getATInstance_Component2roles(), this.getComponent2Role(),
                this.getComponent2Role_ATInstance(), "component2roles", null, 0, -1, ATInstance.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        this.initEReference(this.getATInstance_AT2Components(), this.getRole2Component(), null, "AT2Components", null,
                1, 1, ATInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getATInstance_Type(), theTypePackage.getAT(), null, "type", null, 1, 1,
                ATInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getATInstance_ParameterValues(), this.getParameterValue(),
                this.getParameterValue_ArchitecturalTemplateInstance(), "parameterValues", null, 0, -1,
                ATInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getATInstance_Role2resources(), this.getRole2Resource(),
                this.getRole2Resource_ATInstance(), "role2resources", null, 0, -1, ATInstance.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        this.initEClass(this.component2RoleEClass, Component2Role.class, "Component2Role", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getComponent2Role_Component(), theCompositionPackage.getAssemblyContext(), null,
                "component", null, 1, 1, Component2Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getComponent2Role_Roles(), theTypePackage.getRole(), null, "roles", null, 0, -1,
                Component2Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getComponent2Role_ATInstance(), this.getATInstance(),
                this.getATInstance_Component2roles(), "ATInstance", null, 1, 1, Component2Role.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.role2ComponentEClass, Role2Component.class, "Role2Component", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRole2Component_Component(), theCompositionPackage.getAssemblyContext(), null,
                "component", null, 1, 1, Role2Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRole2Component_Role(), theTypePackage.getRole(), null, "role", null, 1, 1,
                Role2Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRole2Component_ATInstance(), this.getATInstance(),
                this.getATInstance_Role2components(), "ATInstance", null, 1, 1, Role2Component.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.parameterValueEClass, ParameterValue.class, "ParameterValue", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getParameterValue_Type(), theTypePackage.getParameter(), null, "type", null, 1, 1,
                ParameterValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getParameterValue_ArchitecturalTemplateInstance(), this.getATInstance(),
                this.getATInstance_ParameterValues(), "architecturalTemplateInstance", null, 1, 1,
                ParameterValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.enumParameterEClass, EnumParameter.class, "EnumParameter", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.integerParameterEClass, IntegerParameter.class, "IntegerParameter", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.floatParameterEClass, FloatParameter.class, "FloatParameter", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.stringParameterEClass, StringParameter.class, "StringParameter", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.role2ResourceEClass, Role2Resource.class, "Role2Resource", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRole2Resource_ATInstance(), this.getATInstance(),
                this.getATInstance_Role2resources(), "ATInstance", null, 1, 1, Role2Resource.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRole2Resource_Resource(), theResourceenvironmentPackage.getResourceContainer(),
                null, "resource", null, 1, 1, Role2Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRole2Resource_Role(), theTypePackage.getRole(), null, "role", null, 1, 1,
                Role2Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // InstancePackageImpl
