/**
 */
package org.scaledl.architecturaltemplates.type.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.types.TypesPackage;
import org.eclipse.ocl.utilities.UtilitiesPackage;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.impl.InstancePackageImpl;
import org.scaledl.architecturaltemplates.type.Completion;
import org.scaledl.architecturaltemplates.type.CompletionParameter;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.GenericBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.GenericOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.GenericTemplateCompletionParameter;
import org.scaledl.architecturaltemplates.type.OCLConstraint;
import org.scaledl.architecturaltemplates.type.PCMBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMFileExtensions;
import org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMTemplateCompletionParameter;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.QVTOCompletion;
import org.scaledl.architecturaltemplates.type.Repository;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TemplateProvidingEntity;
import org.scaledl.architecturaltemplates.type.TypeFactory;
import org.scaledl.architecturaltemplates.type.TypePackage;

import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class TypePackageImpl extends EPackageImpl implements TypePackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass atEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass constraintEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass parameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass repositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass roleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass oclConstraintEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass qvtoCompletionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass completionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass completionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass genericTemplateCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass genericBlackboardCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass pcmBlackboardCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass pcmTemplateCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass templateProvidingEntityEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass pcmOutputCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass genericOutputCompletionParameterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum pcmFileExtensionsEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.scaledl.architecturaltemplates.type.TypePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TypePackageImpl() {
        super(eNS_URI, TypeFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link TypePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TypePackage init() {
        if (isInited)
            return (TypePackage) EPackage.Registry.INSTANCE.getEPackage(TypePackage.eNS_URI);

        // Obtain or create and register package
        TypePackageImpl theTypePackage = (TypePackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof TypePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new TypePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EMFProfilePackage.eINSTANCE.eClass();
        TypesPackage.eINSTANCE.eClass();
        UtilitiesPackage.eINSTANCE.eClass();
        ExpressionsPackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) : InstancePackage.eINSTANCE);

        // Create package meta-data objects
        theTypePackage.createPackageContents();
        theInstancePackage.createPackageContents();

        // Initialize created meta-data
        theTypePackage.initializePackageContents();
        theInstancePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTypePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TypePackage.eNS_URI, theTypePackage);
        return theTypePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getAT() {
        return atEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAT_Repository() {
        return (EReference) atEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAT_Roles() {
        return (EReference) atEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAT_Constraints() {
        return (EReference) atEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getAT_Completion() {
        return (EReference) atEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getConstraint() {
        return constraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConstraint_AT() {
        return (EReference) constraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getConstraint_Roles() {
        return (EReference) constraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getParameter() {
        return parameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getParameter_Role() {
        return (EReference) parameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getParameter_DataType() {
        return (EReference) parameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRepository() {
        return repositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRepository_ATs() {
        return (EReference) repositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getRole() {
        return roleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRole_Parameters() {
        return (EReference) roleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRole_AT() {
        return (EReference) roleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRole_Constraints() {
        return (EReference) roleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getRole_Stereotype() {
        return (EReference) roleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getOCLConstraint() {
        return oclConstraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getOCLConstraint_Constraint() {
        return (EAttribute) oclConstraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getQVTOCompletion() {
        return qvtoCompletionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getQVTOCompletion_QvtoFileURI() {
        return (EAttribute) qvtoCompletionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCompletion() {
        return completionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCompletion_AT() {
        return (EReference) completionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCompletion_Parameters() {
        return (EReference) completionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getCompletionParameter() {
        return completionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getCompletionParameter_Completion() {
        return (EReference) completionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getGenericTemplateCompletionParameter() {
        return genericTemplateCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getGenericBlackboardCompletionParameter() {
        return genericBlackboardCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGenericBlackboardCompletionParameter_FileExtension() {
        return (EAttribute) genericBlackboardCompletionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPCMBlackboardCompletionParameter() {
        return pcmBlackboardCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPCMBlackboardCompletionParameter_FileExtension() {
        return (EAttribute) pcmBlackboardCompletionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPCMTemplateCompletionParameter() {
        return pcmTemplateCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTemplateProvidingEntity() {
        return templateProvidingEntityEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTemplateProvidingEntity_TemplateFileURI() {
        return (EAttribute) templateProvidingEntityEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getPCMOutputCompletionParameter() {
        return pcmOutputCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getPCMOutputCompletionParameter_FileExtension() {
        return (EAttribute) pcmOutputCompletionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getGenericOutputCompletionParameter() {
        return genericOutputCompletionParameterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getGenericOutputCompletionParameter_FileExtension() {
        return (EAttribute) genericOutputCompletionParameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getPCMFileExtensions() {
        return pcmFileExtensionsEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TypeFactory getTypeFactory() {
        return (TypeFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        atEClass = createEClass(AT);
        createEReference(atEClass, AT__REPOSITORY);
        createEReference(atEClass, AT__ROLES);
        createEReference(atEClass, AT__CONSTRAINTS);
        createEReference(atEClass, AT__COMPLETION);

        constraintEClass = createEClass(CONSTRAINT);
        createEReference(constraintEClass, CONSTRAINT__AT);
        createEReference(constraintEClass, CONSTRAINT__ROLES);

        parameterEClass = createEClass(PARAMETER);
        createEReference(parameterEClass, PARAMETER__ROLE);
        createEReference(parameterEClass, PARAMETER__DATA_TYPE);

        repositoryEClass = createEClass(REPOSITORY);
        createEReference(repositoryEClass, REPOSITORY__ATS);

        roleEClass = createEClass(ROLE);
        createEReference(roleEClass, ROLE__PARAMETERS);
        createEReference(roleEClass, ROLE__AT);
        createEReference(roleEClass, ROLE__CONSTRAINTS);
        createEReference(roleEClass, ROLE__STEREOTYPE);

        oclConstraintEClass = createEClass(OCL_CONSTRAINT);
        createEAttribute(oclConstraintEClass, OCL_CONSTRAINT__CONSTRAINT);

        qvtoCompletionEClass = createEClass(QVTO_COMPLETION);
        createEAttribute(qvtoCompletionEClass, QVTO_COMPLETION__QVTO_FILE_URI);

        completionEClass = createEClass(COMPLETION);
        createEReference(completionEClass, COMPLETION__AT);
        createEReference(completionEClass, COMPLETION__PARAMETERS);

        completionParameterEClass = createEClass(COMPLETION_PARAMETER);
        createEReference(completionParameterEClass, COMPLETION_PARAMETER__COMPLETION);

        genericTemplateCompletionParameterEClass = createEClass(GENERIC_TEMPLATE_COMPLETION_PARAMETER);

        genericBlackboardCompletionParameterEClass = createEClass(GENERIC_BLACKBOARD_COMPLETION_PARAMETER);
        createEAttribute(genericBlackboardCompletionParameterEClass,
                GENERIC_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION);

        pcmBlackboardCompletionParameterEClass = createEClass(PCM_BLACKBOARD_COMPLETION_PARAMETER);
        createEAttribute(pcmBlackboardCompletionParameterEClass, PCM_BLACKBOARD_COMPLETION_PARAMETER__FILE_EXTENSION);

        pcmTemplateCompletionParameterEClass = createEClass(PCM_TEMPLATE_COMPLETION_PARAMETER);

        templateProvidingEntityEClass = createEClass(TEMPLATE_PROVIDING_ENTITY);
        createEAttribute(templateProvidingEntityEClass, TEMPLATE_PROVIDING_ENTITY__TEMPLATE_FILE_URI);

        pcmOutputCompletionParameterEClass = createEClass(PCM_OUTPUT_COMPLETION_PARAMETER);
        createEAttribute(pcmOutputCompletionParameterEClass, PCM_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION);

        genericOutputCompletionParameterEClass = createEClass(GENERIC_OUTPUT_COMPLETION_PARAMETER);
        createEAttribute(genericOutputCompletionParameterEClass, GENERIC_OUTPUT_COMPLETION_PARAMETER__FILE_EXTENSION);

        // Create enums
        pcmFileExtensionsEEnum = createEEnum(PCM_FILE_EXTENSIONS);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage) EPackage.Registry.INSTANCE
                .getEPackage(EMFProfilePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        atEClass.getESuperTypes().add(theEntityPackage.getEntity());
        constraintEClass.getESuperTypes().add(theEntityPackage.getEntity());
        parameterEClass.getESuperTypes().add(theEntityPackage.getEntity());
        repositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
        roleEClass.getESuperTypes().add(theEntityPackage.getEntity());
        oclConstraintEClass.getESuperTypes().add(this.getConstraint());
        qvtoCompletionEClass.getESuperTypes().add(this.getCompletion());
        genericTemplateCompletionParameterEClass.getESuperTypes().add(this.getGenericBlackboardCompletionParameter());
        genericTemplateCompletionParameterEClass.getESuperTypes().add(this.getTemplateProvidingEntity());
        genericBlackboardCompletionParameterEClass.getESuperTypes().add(this.getCompletionParameter());
        pcmBlackboardCompletionParameterEClass.getESuperTypes().add(this.getCompletionParameter());
        pcmTemplateCompletionParameterEClass.getESuperTypes().add(this.getPCMBlackboardCompletionParameter());
        pcmTemplateCompletionParameterEClass.getESuperTypes().add(this.getTemplateProvidingEntity());
        pcmOutputCompletionParameterEClass.getESuperTypes().add(this.getCompletionParameter());
        genericOutputCompletionParameterEClass.getESuperTypes().add(this.getCompletionParameter());

        // Initialize classes and features; add operations and parameters
        initEClass(atEClass, org.scaledl.architecturaltemplates.type.AT.class, "AT", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAT_Repository(), this.getRepository(), this.getRepository_ATs(), "repository", null, 1, 1,
                org.scaledl.architecturaltemplates.type.AT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAT_Roles(), this.getRole(), this.getRole_AT(), "roles", null, 0, -1,
                org.scaledl.architecturaltemplates.type.AT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAT_Constraints(), this.getConstraint(), this.getConstraint_AT(), "constraints", null, 0, -1,
                org.scaledl.architecturaltemplates.type.AT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAT_Completion(), this.getCompletion(), this.getCompletion_AT(), "completion", null, 1, 1,
                org.scaledl.architecturaltemplates.type.AT.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(constraintEClass, Constraint.class, "Constraint", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConstraint_AT(), this.getAT(), this.getAT_Constraints(), "AT", null, 1, 1, Constraint.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConstraint_Roles(), this.getRole(), this.getRole_Constraints(), "roles", null, 0, -1,
                Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getParameter_Role(), this.getRole(), this.getRole_Parameters(), "role", null, 1, 1,
                Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getParameter_DataType(), theEcorePackage.getEDataType(), null, "dataType", null, 1, 1,
                Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRepository_ATs(), this.getAT(), this.getAT_Repository(), "ATs", null, 0, -1, Repository.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(roleEClass, Role.class, "Role", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRole_Parameters(), this.getParameter(), this.getParameter_Role(), "parameters", null, 0, -1,
                Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRole_AT(), this.getAT(), this.getAT_Roles(), "AT", null, 1, 1, Role.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getRole_Constraints(), this.getConstraint(), this.getConstraint_Roles(), "constraints", null, 0,
                -1, Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRole_Stereotype(), theEMFProfilePackage.getStereotype(), null, "stereotype", null, 1, 1,
                Role.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(oclConstraintEClass, OCLConstraint.class, "OCLConstraint", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOCLConstraint_Constraint(), theEcorePackage.getEString(), "constraint", null, 1, 1,
                OCLConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(qvtoCompletionEClass, QVTOCompletion.class, "QVTOCompletion", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQVTOCompletion_QvtoFileURI(), theEcorePackage.getEString(), "qvtoFileURI", null, 1, 1,
                QVTOCompletion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(completionEClass, Completion.class, "Completion", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompletion_AT(), this.getAT(), this.getAT_Completion(), "AT", null, 1, 1, Completion.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCompletion_Parameters(), this.getCompletionParameter(),
                this.getCompletionParameter_Completion(), "parameters", null, 1, -1, Completion.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(completionParameterEClass, CompletionParameter.class, "CompletionParameter", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompletionParameter_Completion(), this.getCompletion(), this.getCompletion_Parameters(),
                "completion", null, 1, 1, CompletionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(genericTemplateCompletionParameterEClass, GenericTemplateCompletionParameter.class,
                "GenericTemplateCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(genericBlackboardCompletionParameterEClass, GenericBlackboardCompletionParameter.class,
                "GenericBlackboardCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGenericBlackboardCompletionParameter_FileExtension(), theEcorePackage.getEString(),
                "fileExtension", null, 1, 1, GenericBlackboardCompletionParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pcmBlackboardCompletionParameterEClass, PCMBlackboardCompletionParameter.class,
                "PCMBlackboardCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPCMBlackboardCompletionParameter_FileExtension(), this.getPCMFileExtensions(),
                "fileExtension", null, 1, 1, PCMBlackboardCompletionParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pcmTemplateCompletionParameterEClass, PCMTemplateCompletionParameter.class,
                "PCMTemplateCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(templateProvidingEntityEClass, TemplateProvidingEntity.class, "TemplateProvidingEntity", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplateProvidingEntity_TemplateFileURI(), theEcorePackage.getEString(), "templateFileURI",
                null, 1, 1, TemplateProvidingEntity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pcmOutputCompletionParameterEClass, PCMOutputCompletionParameter.class,
                "PCMOutputCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPCMOutputCompletionParameter_FileExtension(), this.getPCMFileExtensions(), "fileExtension",
                null, 1, 1, PCMOutputCompletionParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(genericOutputCompletionParameterEClass, GenericOutputCompletionParameter.class,
                "GenericOutputCompletionParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGenericOutputCompletionParameter_FileExtension(), theEcorePackage.getEString(),
                "fileExtension", null, 1, 1, GenericOutputCompletionParameter.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(pcmFileExtensionsEEnum, PCMFileExtensions.class, "PCMFileExtensions");
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.SYSTEM);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.ALLOCATION);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.RESOURCEENVIRONMENT);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.REPOSITORY);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.USAGEMODEL);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.RESOURCETYPE);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.MONITORREPOSITORY);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.PCMMEASURINGPOINT);
        addEEnumLiteral(pcmFileExtensionsEEnum, PCMFileExtensions.SERVICELEVELOBJECTIVE);

        // Create resource
        createResource(eNS_URI);
    }

} // TypePackageImpl
