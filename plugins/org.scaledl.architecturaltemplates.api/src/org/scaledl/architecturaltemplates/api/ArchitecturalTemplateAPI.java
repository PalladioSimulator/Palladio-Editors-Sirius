package org.scaledl.architecturaltemplates.api;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.modelversioning.emfprofile.Profile;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.system.System;

/**
 * An API class providing methods to interact with Architectural Templates
 * 
 * @author Max Schettler
 *
 */
public final class ArchitecturalTemplateAPI {

	/**
	 * The key for the tagged value with which a {@link Stereotype}s`
	 * {@link Role} can be loaded.
	 */
	private static final String ROLE_URI = "roleURI";

	/**
	 * The name-suffix that identifies a {@link Role} or its corresponding
	 * {@link Stereotype} as an system-role.
	 */
	private static final String SYSTEM_ROLE_NAME_SUFFIX = "System";

	/**
	 * A {@link Predicate} to test whether a {@link Stereotype} conforms the role-convention (a tagged-value for the {@link #ROLE_URI} exists).
	 */
	public static final Predicate<Stereotype> isRole = stereotype -> Objects
			.nonNull(stereotype.getTaggedValue(ROLE_URI));

	/**
	 * A {@link Predicate} to test whether a {@link Stereotype} conforms the system-role-convention (name ends with {@link #SYSTEM_ROLE_NAME_SUFFIX}).
	 */
	private static final Predicate<Stereotype> conformsSystemNameConvention = stereotype -> stereotype
			.getName().endsWith(SYSTEM_ROLE_NAME_SUFFIX);

	/**
	 * A {@link Predicate} to test whether a {@link Stereotype} conforms both the role-convention (a tagged-value for the {@link #ROLE_URI} exists )and the system-role-convention (name ends with {@link #SYSTEM_ROLE_NAME_SUFFIX}).
	 */
	public static final Predicate<Stereotype> isSystemRole = isRole
			.and(conformsSystemNameConvention);


	/**
	 * A {@link Predicate} to test whether a {@link Profile} conforms the Architecural-Template-convention (all stereotypes are roles and exactly one is the system role).
	 * {@see #isRole}
	 * {@see #conformsSystemNameConvention}
	 */
	public static final Predicate<Profile> isArchitecturalTemplate = profile -> profile
			.getStereotypes().stream().allMatch(isRole)
			&& profile.getStereotypes().stream()
					.filter(conformsSystemNameConvention).count() == 1;

	/**
	 * Hidden constructor.
	 */
	private ArchitecturalTemplateAPI() {
	}

	/**
	 * Gets the {@link Role} associated with the given {@link Stereotype}.
	 * @param stereotype the {@link Stereotype}
	 * @return the {@link Role}
	 * @throws RuntimeException if the given stereotype does not conform the role-convention.
	 */
	public static Role getRole(final Stereotype stereotype) {
		if (!isRole(stereotype)) {
			throw new RuntimeException("Stereotype \"" + stereotype
					+ "\" is no role");
		}
		final EObject roleURIEObject = EMFLoadHelper
				.loadAndResolveEObject(stereotype.getTaggedValue(ROLE_URI)
						.getDefaultValueLiteral());
		if (!(roleURIEObject instanceof Role)) {
			throw new RuntimeException("RoleURI Stereotype \"" + stereotype
					+ "\" does not refer to a role");
		}
		return (Role) roleURIEObject;
	}

	/**
	 * Gets the {@link AT} associated with the given {@link Profile}.
	 * @param profile the {@link Profile}
	 * @return the {@link AT}
	 * @throws RuntimeException if the given architectural template does not conform the Architectural-Template-convention.
	 */
	public static AT getArchitecturalTemplate(final Profile profile) {
		if (!isArchitecturalTemplate(profile)) {
			throw new RuntimeException("Profile \"" + profile
					+ "\" is no Architectural Template");
		}
		return getRole(profile.getStereotypes().get(0)).getAT();
	}

	/**
	 * Tests whether a {@link Stereotype} conforms the role-convention (a tagged-value for the {@link #ROLE_URI} exists).
	 * {@see #isRole}
	 */
	public static boolean isRole(final Stereotype stereotype) {
		return isRole.test(stereotype);
	}
	
	/**
	 * Tests whether a {@link Stereotype} is a system-role.
	 * {@see #isSystemRole}
	 */
	public static boolean isSystemRole(final Stereotype stereotype) {
		return isSystemRole.test(stereotype);
	}

	/**
	 * Tests whether a {@link Profile} is an Architecural-Template.
	 * {@see #isArchitecturalTemplate}
	 */
	public static boolean isArchitecturalTemplate(final Profile profile) {
		return isArchitecturalTemplate.test(profile);
	}

	/**
	 * Gets the {@link Stereotype} that represents the system-role for the given {@link Profile}.
	 * @param profile the ArchitecturalTemplate-{@link Profile}
	 * @return the SystemRole-{@link Stereotype}
	 * @throws RuntimeException if the given profile is no Architectural Template
	 */
	public static Stereotype getSystemRoleStereotype(final Profile profile) {
		if (!isArchitecturalTemplate(profile)) {
			throw new RuntimeException("Profile \"" + profile
					+ "\" is no Architectural Template");
		}
		Optional<Stereotype> systemRoleStereotype = profile.getStereotypes()
				.stream().filter(isSystemRole).findAny();
		return systemRoleStereotype.get();
	}

	/**
	 * Applies the given {@link AT} to the {@link System}.
	 * @param system the {@link System}
	 * @param architecturalTemplate the {@link AT}
	 * @throws RuntimeException if the Architectural Template does not define any roles.
	 * @see #applyArchitecturalTemplate(System, Profile)
	 */
	public static void applyArchitecturalTemplate(final System system,
			final AT architecturalTemplate) {
		if (architecturalTemplate.getRoles().size() == 0) {
			throw new RuntimeException("Architectural Template \""
					+ architecturalTemplate + "\" does not contain any roles");
		}

		applyArchitecturalTemplate(system, architecturalTemplate.getRoles()
				.get(0).getStereotype().getProfile());
	}

	/**
	 * Applies the given Architectural-Template-{@link Profile} to the {@link System}.
	 * @param system the {@link System}
	 * @param profile the {@link Profile}
	 * @throws RuntimeException if the profile does not define an Architectural Template.
	 */
	public static void applyArchitecturalTemplate(final System system,
			final Profile profile) {
		if (!isArchitecturalTemplate(profile)) {
			throw new RuntimeException("Profile \"" + profile
					+ "\" is no Architectural Template");
		}

		final Stereotype systemRoleStereotype = getSystemRoleStereotype(profile);

		ProfileAPI.applyProfile(system.eResource(), profile);
		StereotypeAPI.applyStereotype(system, systemRoleStereotype);
	}

	/**
	 * Unapplies the given {@link AT} from the {@link System}.
	 * @param system the {@link System}
	 * @param architecturalTemplate the {@link AT}
	 * @throws RuntimeException if the Architectural Template does not define any roles.
	 * @see #unapplyArchitecturalTemplate(System, Profile)
	 */
	public static void unapplyArchitecturalTemplate(final System system,
			final AT architecturalTemplate) {
		if (architecturalTemplate.getRoles().size() == 0) {
			throw new RuntimeException("Architectural Template \""
					+ architecturalTemplate + "\" does not contain any roles");
		}

		unapplyArchitecturalTemplate(system, architecturalTemplate.getRoles()
				.get(0).getStereotype().getProfile());
	}

	/**
	 * Unapplies the given Architectural-Template-{@link Profile} from the {@link System}.
	 * @param system the {@link System}
	 * @param profile the {@link Profile}
	 * @throws RuntimeException if the profile does not define an Architectural Template.
	 */
	public static void unapplyArchitecturalTemplate(System system,
			Profile profile) {
		if (!isArchitecturalTemplate(profile)) {
			throw new RuntimeException("Profile \"" + profile
					+ "\" is no Architectural Template");
		}

		system.getAssemblyContexts__ComposedStructure().forEach(
				assemblyContext -> profile
						.getStereotypes()
						.stream()
						.forEach(
								stereotype -> StereotypeAPI.unapplyStereotype(
										assemblyContext, stereotype)));
		ProfileAPI.unapplyProfile(system.eResource(), profile);
	}

	/**
	 * Applies the given {@link Role} to the {@link AssemblyContext}.
	 * @param assemblyContext the {@link AssemblyContext}
	 * @param role the {@link Role}
	 */
	public static void applyRole(final AssemblyContext assemblyContext,
			final Role role) {
		StereotypeAPI.applyStereotype(assemblyContext, role.getStereotype());
	}

	/**
	 * Unapplies the given {@link Role} from the {@link AssemblyContext}.
	 * @param assemblyContext the {@link AssemblyContext}
	 * @param role the {@link Role}
	 */
	public static void unapplyRole(final AssemblyContext assemblyContext,
			final Role role) {
		StereotypeAPI.unapplyStereotype(assemblyContext, role.getStereotype());
	}

}
