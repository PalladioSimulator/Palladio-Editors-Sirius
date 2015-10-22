package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.system.System;
import org.scaledl.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.ui.dialogs.ArchitecturalTemplateSelectionDialog;

/**
 * A Sirius action that is used to add an ArchitecturalTemplate Role to the {@link System} it is
 * applied to. Executing the action will create an dialog asking the user to select an {@link AT}.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATAction implements IExternalJavaAction {

    /**
     * Message displayed in the selection dialog.
     */
    private static final String DIALOG_MESSAGE = "Select an Architectural Template Profile to apply to the System";

    /**
     * Creates an {@link ArchitecturalTemplateSelectEObjectDialog} that queries the user for an
     * {@link AT} and applies the corresponding {@link Stereotype} to the selected {@link System}.
     */
    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {

        final ArchitecturalTemplateSelectionDialog profileSelectionDialog = new ArchitecturalTemplateSelectionDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());

        profileSelectionDialog.setElements(ArchitecturalTemplateAPI.getRegisteredArchitecturalTemplates()
                .toArray(new AT[ArchitecturalTemplateAPI.getRegisteredArchitecturalTemplates().size()]));
        profileSelectionDialog.setMessage(DIALOG_MESSAGE);

        if (profileSelectionDialog.open() != Dialog.OK) {
            return;
        }

        final AT at = profileSelectionDialog.getResultArchitecturalTemplate();

        final EObject eObject = selections.iterator().next();
        if (eObject instanceof System) {
            final System system = (System) eObject;
            ArchitecturalTemplateAPI.applyArchitecturalTemplate(system, at);
        } else if (eObject instanceof ResourceEnvironment) {
            final ResourceEnvironment resourceenvironment = (ResourceEnvironment) eObject;
            ArchitecturalTemplateAPI.applyArchitecturalTemplate(resourceenvironment, at);
        } else {
            throw new RuntimeException("Unsupported eObject: " + eObject);
        }

    }

    /**
     * Checks whether there is only one selection and if its a {@link System}.
     */
    @Override
    public boolean canExecute(final Collection<? extends EObject> selections) {
        if (selections.size() != 1) {
            return false;
        }
        for (final EObject object : selections) {
            return (object instanceof System) || (object instanceof ResourceEnvironment);
        }
        return false;
    }

}
