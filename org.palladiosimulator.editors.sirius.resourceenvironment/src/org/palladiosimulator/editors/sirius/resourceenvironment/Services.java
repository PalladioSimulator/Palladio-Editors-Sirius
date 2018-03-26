package org.palladiosimulator.editors.sirius.resourceenvironment;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Stereotype;

public class Services {

	public Services() {
	}
	
	public Stereotype print(Stereotype eObject) {
		System.out.println(eObject);
		System.out.println("TESSST");
		return eObject;
	}
	
	public Collection<EObject> printCollection(Collection<EObject> eObjects) {
		System.out.println(eObjects.iterator().next());
		return eObjects;
	}

}
