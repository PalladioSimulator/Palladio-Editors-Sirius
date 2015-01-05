package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

public class ProfilesLibrary {

	public ProfilesLibrary() {
		super();
	}

	public static boolean hasAppliedStereotype(Entity pcmEntity,
			String stereotypeName) {
		if (pcmEntity.getAppliedStereotype(stereotypeName) != null) {
			return true;
		}
		return false;
	}

	public static boolean appliedStereotypesEqualsOne(Set<Entity> pcmEntitySet,
			String stereotypeName) {
		int appliedStereotypes = 0;
		Iterator<Entity> iterator = pcmEntitySet.iterator();

		while (iterator.hasNext()) {
			Entity stereotypable = iterator.next();
			if (stereotypable.getAppliedStereotype(stereotypeName) != null) {
				appliedStereotypes++;
			}
		}

		if (appliedStereotypes > 1) {
			return false;
		}
		return true;
	}

	public static void applyStereotype(Entity pcmEntity, String stereotypeName) {
		final Stereotype stereotype = pcmEntity
				.getApplicableStereotype(stereotypeName);
		if(stereotype != null) {
			pcmEntity.applyStereotype(stereotype);
			pcmEntity.saveContainingProfileApplication();
		}
	}
	
	public static void removeStereotypeApplications(Entity pcmEntity, String stereotypeName) {
		final Stereotype stereotype = pcmEntity.getAppliedStereotype(stereotypeName);
		if(stereotype != null) {
			pcmEntity.removeAllStereotypeApplications(stereotype);
			//pcmEntity.saveContainingProfileApplication();
		}
	}
	
	public static void setIntTaggedValue(Entity pcmEntity, int value, String stereotypeName, String taggedValueName){
		List<StereotypeApplication> stereotypeApplications = pcmEntity.getStereotypeApplications(stereotypeName);
		StereotypeApplication stereotypeApplication = stereotypeApplications
				.get(0);
		setValueOfEStructuralFeature(stereotypeApplication,taggedValueName,value);
		
	}
 private static void setValueOfEStructuralFeature(final StereotypeApplication stereotypeApplication, final String name, final Object newValue) {

	        final Stereotype stereotype = stereotypeApplication.getStereotype();
	        if(stereotype !=null){
	        	EStructuralFeature taggedValue = stereotype.getTaggedValue(name);
	        	stereotypeApplication.eSet(taggedValue, newValue);
	        }
	    }
}
