package org.scaledl.architecturaltemplates.repositories.cloudscale.black;

import java.util.List;
import java.util.Set;

import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.profiles.util.helper.ProfileHelper;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

public class ProfilesLibrary {

    public ProfilesLibrary() {
        super();
    }

    public static boolean isProfileApplied(final Entity pcmEntity, final String profileName) {
        return ProfileAPI.isProfileApplied(pcmEntity.eResource(), profileName);
    }

    public static void applyProfile(final Entity pcmEntity, final String profileName) {
        ProfileAPI.applyProfile(pcmEntity.eResource(), profileName);
    }

    public static boolean hasAppliedStereotype(final Entity pcmEntity, final String stereotypeName) {
        return StereotypeAPI.isStereotypeApplied(pcmEntity, stereotypeName);
    }

    public static boolean hasAppliedStereotype(final Set<Entity> pcmEntitySet, final String stereotypeName) {
        return ProfileHelper.hasAppliedStereotype(pcmEntitySet, stereotypeName);
    }

    public static boolean appliedStereotypesEqualsOne(final Set<Entity> pcmEntitySet, final String stereotypeName) {
        return ProfileHelper.appliedStereotypesEqualsOne(pcmEntitySet, stereotypeName);
    }

    public static void applyStereotype(final Entity pcmEntity, final String stereotypeName) {
        StereotypeAPI.applyStereotype(pcmEntity, stereotypeName);
    }

    public static void removeStereotypeApplications(final Entity pcmEntity, final String stereotypeName) {
        StereotypeAPI.unapplyStereotype(pcmEntity, stereotypeName);
    }

    public static void setTaggedValue(final Entity pcmEntity, final int value, final String stereotypeName,
            final String taggedValueName) {
        ProfileHelper.setTaggedValue(pcmEntity, value, stereotypeName, taggedValueName);
    }

    public static int getIntTaggedValue(final Entity pcmEntity, final String taggedValueName,
            final String stereotypeName) {
        return ProfileHelper.getIntTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static double getDoubleTaggedValue(final Entity pcmEntity, final String taggedValueName,
            final String stereotypeName) {
        return ProfileHelper.getDoubleTaggedValue(pcmEntity, taggedValueName, stereotypeName);
    }

    public static void delete(final List<NamedElement> rootEObjects, final Entity eObject) {
        ProfileHelper.delete(rootEObjects, eObject);
    }

}
