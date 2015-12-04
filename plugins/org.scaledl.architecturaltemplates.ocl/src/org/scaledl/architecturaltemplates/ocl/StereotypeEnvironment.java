package org.scaledl.architecturaltemplates.ocl;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;

public class StereotypeEnvironment extends EcoreEnvironment {
    EOperation hasAppliedStereotype;
    EOperation getDoubleTaggedValue;
    EOperation parseDouble;
    EOperation getAllocation;
    EOperation getResourceEnvironment;

    StereotypeEnvironment(final EcoreEnvironmentFactory fac, final Resource resource) {
        super(fac, resource);
        defineCustomOperations();
    }

    StereotypeEnvironment(final StereotypeEnvironment parent) {
        super(parent);
        // get the parent's custom operations
        hasAppliedStereotype = parent.hasAppliedStereotype;
        getDoubleTaggedValue = parent.getDoubleTaggedValue;
        getAllocation = parent.getAllocation;
        getResourceEnvironment = parent.getResourceEnvironment;
        parseDouble = parent.parseDouble;
    }

    // use the AbstractEnvironment's mechanism for defining
    // "additional operations" to add our custom operation to
    // OCL's String primitive type
    private void defineCustomOperations() {

        // hasAppliedStereotype operation
        hasAppliedStereotype = createOperation("hasAppliedStereotype", getOCLStandardLibrary().getBoolean());
        final EParameter stereotypeParameter = createParameter("stereotype", getOCLStandardLibrary().getString());
        hasAppliedStereotype.getEParameters().add(stereotypeParameter);

        // getDoubleTaggedValue operation
        getDoubleTaggedValue = createOperation("getDoubleTaggedValue", getOCLStandardLibrary().getReal());
        final EParameter doubleTaggedValueParameter = createParameter("doubleTaggedValue",
                getOCLStandardLibrary().getString());
        final EParameter stereotypeNameParameter = createParameter("stereotypeName",
                getOCLStandardLibrary().getString());
        getDoubleTaggedValue.getEParameters().add(doubleTaggedValueParameter);
        getDoubleTaggedValue.getEParameters().add(stereotypeNameParameter);

        // parseDouble operation
        parseDouble = createOperation("parseDouble", getOCLStandardLibrary().getReal());

        // getAllocation operation
        getAllocation = createOperation("getAllocation", AllocationPackage.Literals.ALLOCATION);

        // getResourceEnvironment operation
        getResourceEnvironment = createOperation("getResourceEnvironment",
                ResourceenvironmentPackage.Literals.RESOURCE_ENVIRONMENT);

        // define it as an additional operation on OCL String
        addHelperOperation(EntityPackage.Literals.ENTITY, getDoubleTaggedValue);
        addHelperOperation(getOCLStandardLibrary().getString(), parseDouble);
        addHelperOperation(EntityPackage.Literals.ENTITY, hasAppliedStereotype);
        addHelperOperation(EntityPackage.Literals.ENTITY, getAllocation);
        addHelperOperation(EntityPackage.Literals.ENTITY, getResourceEnvironment);
    }

    private EOperation createOperation(final String operationName, final EClassifier returnType) {
        final EOperation operation = EcoreFactory.eINSTANCE.createEOperation();
        operation.setName(operationName);
        operation.setEType(returnType);
        final EAnnotation operationAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        operationAnnotation.setSource("StereotypeEnvironment");
        operation.getEAnnotations().add(operationAnnotation);
        return operation;
    }

    private EParameter createParameter(final String parameterName, final EClassifier parameterType) {
        final EParameter parameter = EcoreFactory.eINSTANCE.createEParameter();
        parameter.setName(parameterName);
        parameter.setEType(parameterType);
        return parameter;
    }
}
