package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;

public class VerticalScalingLibrary {
	
	public VerticalScalingLibrary(){
	}
	
	public static double getScaleUpThreshold(
			ResourceContainer resourceContainer, String taggedValueName,
			String stereotypeName) {
		return getValueOfTaggedValue(resourceContainer, taggedValueName, stereotypeName);	
	}
	
	public static double getScaleDownThreshold(
			ResourceContainer resourceContainer, String taggedValueName,
			String stereotypeName) {
		return getValueOfTaggedValue(resourceContainer, taggedValueName, stereotypeName);	
	}
	
	public static int getStepSize(
			ResourceContainer resourceContainer, String taggedValueName,
			String stereotypeName) {
		return getValueOfTaggedValue(resourceContainer, taggedValueName, stereotypeName);	
	}
	
	public static int getMaxRate(
			ResourceContainer resourceContainer, String taggedValueName,
			String stereotypeName) {
		return getValueOfTaggedValue(resourceContainer, taggedValueName, stereotypeName);	
	}
	
	public static int getMinRate(
			ResourceContainer resourceContainer, String taggedValueName,
			String stereotypeName) {
		return getValueOfTaggedValue(resourceContainer, taggedValueName, stereotypeName);	
	}
	
	@SuppressWarnings("unchecked")
	private static <DATA_TYPE> DATA_TYPE getValueOfTaggedValue(
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
