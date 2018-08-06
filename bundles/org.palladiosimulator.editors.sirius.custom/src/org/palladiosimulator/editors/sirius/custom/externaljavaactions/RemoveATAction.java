package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.ProfileImport;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.palladiosimulator.pcm.system.System;

/**
 * A Sirius action that is used to remove an ArchitecturalTemplate Role from a {@link System}.
 * 
 * @author max
 *
 */
public class RemoveATAction implements IExternalJavaAction {

    /**
     * Removes the selected Architectural Template from the {@link System}
     * 
     * @edited by Edith to unapply ResourceEnvironment stereotypesApplications too
     */
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        final EObject selection = selections.iterator().next();
        if (selection instanceof StereotypeApplication) {
            final StereotypeApplication stereotypeApplication = (StereotypeApplication) selection;
            if (stereotypeApplication.getAppliedTo() instanceof System) {
                final System system = (System) stereotypeApplication.getAppliedTo();

                ArchitecturalTemplateAPI.unapplyArchitecturalTemplate(system,
                        stereotypeApplication.getStereotype().getProfile());
            } else
                throw new RuntimeException(
                        "Unsupported stereoApplication removal: " + stereotypeApplication.getAppliedTo());
        } else if (selection instanceof ProfileImport) {

            final ProfileImport profileImport = (ProfileImport) selection;

            ProfileAPI.unapplyProfile(profileImport.eResource(), profileImport.getProfile());

        }
    }

    /**
     * Checks whether there is only one selection and if its a {@link StereotypeApplication}.
     */
    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        if (selections.size() != 1) {
            return false;
        }
        for (final EObject object : selections) {
            return (object instanceof StereotypeApplication) || (object instanceof ProfileImport);

        }
        return false;
    }

}
