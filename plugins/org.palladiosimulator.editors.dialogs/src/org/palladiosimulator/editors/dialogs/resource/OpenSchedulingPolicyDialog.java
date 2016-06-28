package org.palladiosimulator.editors.dialogs.resource;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.selection.PalladioSelectEObjectDialog;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.SchedulingPolicy;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenSchedulingPolicyDialog.
 */
public class OpenSchedulingPolicyDialog extends OpenEditPolicy {

    /**
     * Gets the open command.
     *
     * @param request the request
     * @return the open command
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy#getOpenCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getOpenCommand(Request request) {

        ProcessingResourceSpecification specification = (ProcessingResourceSpecification) ((View) ((IGraphicalEditPart) getHost())
                .getParent().getModel()).getElement();
        ResourceSet set = (specification.getResourceContainer_ProcessingResourceSpecification()).eResource()
                .getResourceSet();
        EObject policy = null;
        ArrayList<Object> filterList = new ArrayList<Object>(); // positive filter
        // Set types to show and their super types
        filterList.add(SchedulingPolicy.class);
        filterList.add(ResourceRepository.class);
        ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
        // set EReference that should be set (in this case: SchedulingPolicy)
        additionalReferences.add(ResourceenvironmentPackage.eINSTANCE
                .getProcessingResourceSpecification_SchedulingPolicy());
        PalladioSelectEObjectDialog dialog = new PalladioSelectEObjectDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), filterList, additionalReferences, set);
        dialog.setProvidedService(SchedulingPolicy.class);
        dialog.open();

        if (dialog.getResult() == null) {
            return null;
        }

        if (!(dialog.getResult() instanceof SchedulingPolicy)) {
            return null;
        }
        policy = dialog.getResult();

        ICommand icmd = new SetValueCommand(new SetRequest(specification,
                ResourceenvironmentPackage.eINSTANCE.getProcessingResourceSpecification_SchedulingPolicy(), policy));
        return new ICommandProxy(icmd);
    }

}
