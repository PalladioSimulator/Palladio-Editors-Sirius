package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.system.System;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;

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
        final StereotypeApplication stereotypeApplication = (StereotypeApplication) (selections.isEmpty() ? null
                : selections.iterator().next());

        if (stereotypeApplication.getAppliedTo() instanceof System) {
            final System system = (System) stereotypeApplication.getAppliedTo();

            ArchitecturalTemplateAPI.unapplyArchitecturalTemplate(system,
                    stereotypeApplication.getStereotype().getProfile());
        } else if (stereotypeApplication.getAppliedTo() instanceof ResourceEnvironment) {
            final ResourceEnvironment resourceEnvironment = (ResourceEnvironment) stereotypeApplication.getAppliedTo();

            ArchitecturalTemplateAPI.unapplyArchitecturalTemplate(resourceEnvironment,
                    stereotypeApplication.getStereotype().getProfile());
        }

        else
            throw new RuntimeException("Unsupported stereoApplication removal: " + stereotypeApplication.getAppliedTo());
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
            return object instanceof StereotypeApplication;
        }
        return false;
    }

}
