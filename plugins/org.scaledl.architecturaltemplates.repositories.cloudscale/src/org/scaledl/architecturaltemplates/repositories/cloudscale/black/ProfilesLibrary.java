package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.StereotypeApplication;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

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
	
	public static boolean hasAppliedStereotype(Set<Entity> pcmEntitySet,String stereotypeName){
		Iterator<Entity> iterator = pcmEntitySet.iterator();
		while (iterator.hasNext()) {
			Entity stereotypable = iterator.next();
			if (stereotypable.getAppliedStereotype(stereotypeName) != null) {
				return true;
			}
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
		pcmEntity.saveContainingProfileApplication();
		
	}
	
	public static void setDoubleTaggedValue(Entity pcmEntity, double value, String stereotypeName, String taggedValueName){
		List<StereotypeApplication> stereotypeApplications = pcmEntity.getStereotypeApplications(stereotypeName);
		StereotypeApplication stereotypeApplication = stereotypeApplications
				.get(0);
		setValueOfEStructuralFeature(stereotypeApplication,taggedValueName,value);
		pcmEntity.saveContainingProfileApplication();
		
	}
	
	private static void setValueOfEStructuralFeature(final StereotypeApplication stereotypeApplication, final String name, final Object newValue) {

	        final Stereotype stereotype = stereotypeApplication.getStereotype();
	        if(stereotype !=null){
	        	EStructuralFeature taggedValue = stereotype.getTaggedValue(name);
	        	stereotypeApplication.eSet(taggedValue, newValue);
	        }
	    }
	
	public static void delete(List<NamedElement> rootEObjects,Entity eObject) {
          Set<EObject> eObjects = new HashSet<EObject>();
          Set<EObject> crossResourceEObjects = new HashSet<EObject>();
          eObjects.add(eObject);
          for (@SuppressWarnings("unchecked")
          TreeIterator<InternalEObject> j = (TreeIterator<InternalEObject>) (TreeIterator<?>) eObject
                          .eAllContents(); j.hasNext();) {
                  InternalEObject childEObject = j.next();
                  if (childEObject.eDirectResource() != null) {
                          crossResourceEObjects.add(childEObject);
                  } else {
                          eObjects.add(childEObject);
                  }
          }

          Map<EObject, Collection<EStructuralFeature.Setting>> usages;
         usages = UsageCrossReferencer.findAll(eObjects, rootEObjects);

          for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages
                          .entrySet()) {
                  EObject deletedEObject = entry.getKey();
                  Collection<EStructuralFeature.Setting> settings = entry.getValue();
                  for (EStructuralFeature.Setting setting : settings) {
                          if (!eObjects.contains(setting.getEObject())
                                          && setting.getEStructuralFeature().isChangeable()) {
                                  EcoreUtil.remove(setting, deletedEObject);
                          }
                  }
          }

          EcoreUtil.remove(eObject);

          for (EObject crossResourceEObject : crossResourceEObjects) {
                  EcoreUtil.remove(crossResourceEObject.eContainer(),
                                  crossResourceEObject.eContainmentFeature(),
                                  crossResourceEObject);
          }
  }
}
