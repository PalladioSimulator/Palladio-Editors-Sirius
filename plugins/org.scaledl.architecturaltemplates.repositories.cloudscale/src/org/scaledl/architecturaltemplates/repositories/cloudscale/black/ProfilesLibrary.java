package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import java.util.List;
import java.util.Set;

import org.palladiosimulator.pcm.profiles.util.helper.ProfileHelper;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

public class ProfilesLibrary {

    public ProfilesLibrary() {
        super();
    }

    public static boolean hasAppliedStereotype(Entity pcmEntity, String stereotypeName) {
        return pcmEntity.isStereotypeApplied(stereotypeName);
    }

    public static boolean hasAppliedStereotype(Set<Entity> pcmEntitySet, String stereotypeName) {
        return hasAppliedStereotype(pcmEntitySet, stereotypeName);
    }

    public static boolean appliedStereotypesEqualsOne(Set<Entity> pcmEntitySet, String stereotypeName) {
        return ProfileHelper.appliedStereotypesEqualsOne(pcmEntitySet, stereotypeName);
    }

    public static void applyStereotype(Entity pcmEntity, String stereotypeName) {
        pcmEntity.applyStereotype(stereotypeName);
    }

    public static void removeStereotypeApplications(Entity pcmEntity, String stereotypeName) {
        pcmEntity.unapplyStereotype(stereotypeName);
    }

    public static void setTaggedValue(Entity pcmEntity, int value, String stereotypeName, String taggedValueName) {
        ProfileHelper.setTaggedValue(pcmEntity, value, stereotypeName, taggedValueName);
    }

    public static int getIntTaggedValue(Entity pcmEntity, String taggedValueName, String stereotypeName) {
        return ProfileHelper.getIntTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static double getDoubleTaggedValue(Entity pcmEntity, String taggedValueName, String stereotypeName) {
        return ProfileHelper.getDoubleTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static void delete(List<NamedElement> rootEObjects, Entity eObject) {
        ProfileHelper.delete(rootEObjects, eObject);
    }

}
