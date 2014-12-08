package profilesblackboxlibrary;

import java.util.Iterator;
import java.util.Set;

import org.modelversioning.emfprofile.Stereotype;

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
		Stereotype stereotype = pcmEntity
				.getApplicableStereotype(stereotypeName);
		pcmEntity.applyStereotype(stereotype);
		pcmEntity.saveContainingProfileApplication();
	}
	
	public static void removeStereotypeApplications(Entity pcmEntity, String stereotypeName) {
		final Stereotype stereotype = pcmEntity.getAppliedStereotype(stereotypeName);
		if(stereotype != null) {
			pcmEntity.removeAllStereotypeApplications(stereotype);
			//pcmEntity.saveContainingProfileApplication();
		}
	}
	
}
