package org.palladiosimulator.editors.util.at;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.system.System;

/**
 * Class containing helper methods for interacting with Architectural Templates. FIXME Should maybe
 * be moved to a separate project in the org.scaledl namespace.
 * 
 * @author max
 *
 */
public final class ArchitecturalTemplateHelpers {

    public static final String ROLE_URI = "roleURI";

    /**
     * Hidden constructor
     */
    private ArchitecturalTemplateHelpers() {

    }

    /**
     * Returns all roles applicable to a {@link AssemblyContext}.
     * 
     * @param assemblyContext
     * @return
     */
    public static List<Role> getApplicableRoles(final AssemblyContext assemblyContext) {
        if (assemblyContext == null || !(assemblyContext.getParentStructure__AssemblyContext() instanceof System))
            return null;

        final List<Role> systemRoles = getSystemRoles((System) assemblyContext.getParentStructure__AssemblyContext());

        final Predicate<Role> isntSystemRole = role -> systemRoles.stream().noneMatch(
                systemRole -> systemRole.equals(role));
        final Predicate<Role> isntAlreadyApplied = role -> StereotypeAPI
        		.getStereotypeApplications(assemblyContext)
                .stream()
                .noneMatch(
                        stereotypeApplication -> stereotypeApplication.getExtension().getSource().getName()
                                .equals(role.getStereotype().getName()));

        final List<Role> applicableRoles = systemRoles.stream().map(systemRole -> systemRole.getAT())
                .flatMap(at -> at.getRoles().stream()).filter(isntSystemRole).filter(isntAlreadyApplied)
                .collect(Collectors.toList());

        // FIXME: This assertion is actually just
        // applicableRoles.stream.allMatch(role ->
        // assemblyContext.isApplicableStereotype(role.getStereotype()); but the
        // Stereotpye::equals method is not implemented correctly.
        assert (applicableRoles.stream().allMatch(role -> StereotypeAPI.getApplicableStereotypes(assemblyContext).stream()
                .map(stereotype -> stereotype.getName())
                .anyMatch(applicableName -> applicableName.equals(role.getStereotype().getName()))));

        return applicableRoles;
    }

    public static List<Role> getSystemRoles(final System system) {
        return StereotypeAPI
        		.getStereotypeApplications(system)
                .stream()
                .map(stereotypeApplication -> stereotypeApplication.getExtension().getSource().getTaggedValue(ROLE_URI))
                .filter(Objects::nonNull).map(taggedValue -> taggedValue.getDefaultValueLiteral())
                .map(uriString -> EMFLoadHelper.loadAndResolveEObject(uriString)).filter(elem -> elem instanceof Role)
                .map(elem -> (Role) elem).collect(Collectors.toList());
    }

}
