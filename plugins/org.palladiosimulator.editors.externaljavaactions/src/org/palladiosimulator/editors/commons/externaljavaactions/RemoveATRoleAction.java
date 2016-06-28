package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.palladiosimulator.architecturaltemplates.Role;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.system.System;

/**
 * This class applies an ArchitecturalTemplate to a {@link System}. It will ask the user to select
 * from an list of available ArchitecturalTemplates.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class RemoveATRoleAction implements IExternalJavaAction {

    /**
     * Asks the user to select a {@link Role} and attaches it to the given {@link AssemblyContext}.
     * 
     */
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final StereotypeApplication stereotypeApplication = (StereotypeApplication) (selections.isEmpty() ? null
                : selections.iterator().next());

        ArchitecturalTemplateAPI.unapplyRole(stereotypeApplication.getAppliedTo(),
                stereotypeApplication.getStereotype());
    }

    /**
     * Tests whether the object this tool is applied to is a {@link System}.
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
