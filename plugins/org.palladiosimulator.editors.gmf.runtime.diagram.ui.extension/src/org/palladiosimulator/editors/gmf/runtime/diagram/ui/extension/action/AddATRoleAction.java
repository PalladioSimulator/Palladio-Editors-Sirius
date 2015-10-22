package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.system.System;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.ui.dialogs.RoleStereotypeSelectionDialog;

/**
 * This class applies an ArchitecturalTemplate to a {@link System}. It will ask the user to select
 * from an list of available ArchitecturalTemplates.
 * 
 * TODO: Should rely on {@link ArchitecturalTemplateAPI#getApplicableRoles(EObject)} with a
 * refactored {@link RoleStereotypeSelectionDialog} that works on {@link Role}s. This however
 * induces a weird Acceleo bug at the moment (1.7.2015) where expressions on the element will only
 * be evaluated after an restart (ArgumentTypeMissmatch).
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATRoleAction implements IExternalJavaAction {

    /**
     * Message displayed in the selection dialog.
     */
    private static final String DIALOG_MESSAGE = "Select Role-Stereotype to apply to the AssemblyContext";

    /**
     * Asks the user to select a {@link Role} and attaches it to the given {@link AssemblyContext}.
     */
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        final EObject selection = selections.iterator().next();

        // final AssemblyContext assemblyContext = (AssemblyContext) selections.iterator().next();

        final LinkedList<Stereotype> unapplyedStereotypes = new LinkedList<>();

        for (final Stereotype stereotype : StereotypeAPI.getApplicableStereotypes(selection)) {
            if (!StereotypeAPI.isStereotypeApplied(selection, stereotype.getName())) {
                unapplyedStereotypes.add(stereotype);
            }
        }

        final RoleStereotypeSelectionDialog dialog = new RoleStereotypeSelectionDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

        dialog.setMessage(DIALOG_MESSAGE);

        dialog.setElements(unapplyedStereotypes.toArray(new Stereotype[0]));

        if (dialog.open() != Dialog.OK) {
            return;
        }

        final Stereotype selectedRoleStereotype = dialog.getResultRoleStereotype();

        StereotypeAPI.applyStereotype(selection, selectedRoleStereotype);
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
            return (object instanceof AssemblyContext) || (object instanceof ResourceContainer);
        }
        return false;
    }

}
