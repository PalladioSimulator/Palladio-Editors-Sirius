package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.palladiosimulator.editors.dialogs.resource.OpenLatencyDialog;

import de.uka.ipd.sdq.pcm.gmf.resource.edit.parts.ResourceContainerEditPart;

/**
 * Configures custom edit policy for linking resources, i.e., it opens appropriate dialogs for
 * setting latencies, throughput, etc.
 * 
 * @see https://www.eclipse.org/sirius/doc/developer/extensions-provide_custom_style.html
 * 
 * @author Sebastian Lehrig
 */
public class LinkingResourceEditPolicyProvider extends AbstractEditPolicyProvider {

    @Override
    public void createEditPolicies(final EditPart editPart) {
        if (editPart instanceof IOperation) { // FIXME using IOperation to disable this path; find
                                              // the correct type
            editPart.installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenLatencyDialog());
        }
    }

    @Override
    public boolean provides(final IOperation operation) {
        if (operation instanceof CreateEditPoliciesOperation) {
            final CreateEditPoliciesOperation castedOperation = (CreateEditPoliciesOperation) operation;
            final EditPart editPart = castedOperation.getEditPart();
            final Object model = editPart.getModel();
            if (model instanceof View) {
                final View view = (View) model;
                // FIXME find right constraints for this filter
                if (view.getDiagram() != null && view.getDiagram().getElement() != null && view.getDiagram()
                        .getElement().eClass().getEPackage().getNsURI().equals(DiagramPackage.eINSTANCE.getNsURI())) {
                    if (("" + ResourceContainerEditPart.VISUAL_ID).equals(view.getType())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
