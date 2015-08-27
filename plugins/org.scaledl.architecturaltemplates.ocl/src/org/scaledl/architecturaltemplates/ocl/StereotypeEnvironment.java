package org.scaledl.architecturaltemplates.ocl;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;

public class StereotypeEnvironment extends EcoreEnvironment {
	EOperation hasAppliedStereotype;
	EOperation getIntTaggedValue;
	EOperation getAllocation;

	StereotypeEnvironment(EcoreEnvironmentFactory fac, Resource resource) {
		super(fac, resource);
		defineCustomOperations();
	}

	StereotypeEnvironment(StereotypeEnvironment parent) {
		super(parent);
		// get the parent's custom operations
		hasAppliedStereotype = parent.hasAppliedStereotype;
		getIntTaggedValue = parent.getIntTaggedValue;
		getAllocation = parent.getAllocation;
	}

	// use the AbstractEnvironment's mechanism for defining
	// "additional operations" to add our custom operation to
	// OCL's String primitive type
	private void defineCustomOperations() {
		// hasAppliedStereotype operation
		hasAppliedStereotype = EcoreFactory.eINSTANCE.createEOperation();
		hasAppliedStereotype.setName("hasAppliedStereotype");
		hasAppliedStereotype.setEType(getOCLStandardLibrary().getBoolean());
		EParameter stereotypeParameter = EcoreFactory.eINSTANCE.createEParameter();
		stereotypeParameter.setName("stereotype");
		stereotypeParameter.setEType(getOCLStandardLibrary().getString());
		hasAppliedStereotype.getEParameters().add(stereotypeParameter);

		// annotate it so that we will recognize it
		// in the evaluation environment
		EAnnotation stereotypeAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		stereotypeAnnotation.setSource("StereotypeEnvironment");
		hasAppliedStereotype.getEAnnotations().add(stereotypeAnnotation);

		// getIntTaggedValue operation
		getIntTaggedValue = EcoreFactory.eINSTANCE.createEOperation();
		getIntTaggedValue.setName("getIntTaggedValue");
		getIntTaggedValue.setEType(getOCLStandardLibrary().getInteger());
		EParameter intTaggedValueParameter = EcoreFactory.eINSTANCE.createEParameter();
		EParameter stereotypeNameParameter = EcoreFactory.eINSTANCE.createEParameter();
		intTaggedValueParameter.setName("taggedValue");
		intTaggedValueParameter.setEType(getOCLStandardLibrary().getString());
		stereotypeNameParameter.setName("stereotype");
		stereotypeNameParameter.setEType(getOCLStandardLibrary().getString());
		getIntTaggedValue.getEParameters().add(intTaggedValueParameter);
		getIntTaggedValue.getEParameters().add(stereotypeNameParameter);

		// annotate it so that we will recognize it
		// in the evaluation environment
		EAnnotation intTaggedValueAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		intTaggedValueAnnotation.setSource("StereotypeEnvironment");
		getIntTaggedValue.getEAnnotations().add(intTaggedValueAnnotation);

		// getAllocation operation
		getAllocation = EcoreFactory.eINSTANCE.createEOperation();
		getAllocation.setName("getAllocation");
		getAllocation.setEType(AllocationPackage.Literals.ALLOCATION);

		// annotate it so that we will recognize it
		// in the evaluation environment
		EAnnotation allocationAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		allocationAnnotation.setSource("StereotypeEnvironment");
		getAllocation.getEAnnotations().add(allocationAnnotation);

		// define it as an additional operation on OCL String
		addHelperOperation(EntityPackage.Literals.ENTITY, getIntTaggedValue);
		addHelperOperation(EntityPackage.Literals.ENTITY, hasAppliedStereotype);
		addHelperOperation(EntityPackage.Literals.ENTITY, getAllocation);
	}
}
