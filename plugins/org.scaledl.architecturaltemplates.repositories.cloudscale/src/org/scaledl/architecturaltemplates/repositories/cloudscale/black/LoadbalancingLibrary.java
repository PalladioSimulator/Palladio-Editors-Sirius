package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.entity.Entity;

public class LoadbalancingLibrary {
	
	public LoadbalancingLibrary(){
		super();
	}
	
	public static int getNumberOfReplicas(
			AssemblyContext assemblyContext, String taggedValueName,
			String stereotypeName) {
		return getValueOfIntegerTaggedValue(assemblyContext, taggedValueName, stereotypeName);	
	}
	
	public static double getThreshold(
			AssemblyContext assemblyContext, String taggedValueName,
			String stereotypeName) {
		return getValueOfIntegerTaggedValue(assemblyContext, taggedValueName, stereotypeName);	
	}

	@SuppressWarnings("unchecked")
	private static <DATA_TYPE> DATA_TYPE getValueOfIntegerTaggedValue(
			Entity pcmEntity, String taggedValueName,
			String stereotypeName) {
		EList<StereotypeApplication> pcmEntityStereotypeApplications = pcmEntity
				.getStereotypeApplications(stereotypeName);
		StereotypeApplication stereotypeApplication = pcmEntityStereotypeApplications
				.get(0);

		Stereotype stereotype = stereotypeApplication.getStereotype();

		EStructuralFeature taggedValue = stereotype
				.getTaggedValue(taggedValueName);

		return (DATA_TYPE) stereotypeApplication.eGet(taggedValue);

	}
}
