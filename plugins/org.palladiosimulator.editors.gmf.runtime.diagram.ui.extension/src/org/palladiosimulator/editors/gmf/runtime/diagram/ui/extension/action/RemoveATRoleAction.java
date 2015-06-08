package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.system.System;

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
     */
    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        final StereotypeApplication stereotypeApplication = (StereotypeApplication) selections.stream().findFirst()
                .get();
        if (!(stereotypeApplication.getAppliedTo() instanceof AssemblyContext)) {
            return;
        }
        final AssemblyContext assemblyContext = (AssemblyContext) stereotypeApplication.getAppliedTo();

        StereotypeAPI.unapplyStereotype(assemblyContext, stereotypeApplication.getStereotype());
    }

    /**
     * Tests whether the object this tool is applied to is a {@link System}.
     */
    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        if (selections.size() != 1) {
            return false;
        }
        for (EObject object : selections) {
            return object instanceof StereotypeApplication;
        }
        return false;
    }

}
