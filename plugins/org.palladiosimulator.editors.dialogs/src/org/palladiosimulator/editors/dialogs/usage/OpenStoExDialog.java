package org.palladiosimulator.editors.dialogs.usage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenStoExDialog.
 */
public class OpenStoExDialog extends OpenEditPolicy {

    /**
     * Gets the random variable.
     * 
     * @param parent
     *            the parent
     * @return the random variable
     */
    protected RandomVariable getRandomVariable(EObject parent) {
        // Default Implementation. Override as necessary
        return (RandomVariable) parent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy#getOpenCommand(org.eclipse
     * .gef.Request)
     */
    @Override
    protected Command getOpenCommand(Request request) {
        IGraphicalEditPart host = (IGraphicalEditPart) getHost();
        RandomVariable rv = getRandomVariable(((View) host.getModel()).getElement());
        StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getShell(), getExpectedType(rv));
        dialog.setInitialExpression(rv);
        dialog.open();
        if (dialog.getReturnCode() == Dialog.OK) {
            SetRequest setRequest = new SetRequest(rv, StoexPackage.eINSTANCE.getRandomVariable_Specification(),
                    new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult()));
            SetValueCommand cmd = new SetValueCommand(setRequest);
            return new ICommandProxy(cmd);
        }
        return null;
    }

    /**
     * Gets the expected type.
     * 
     * @param rv
     *            the rv
     * @return the expected type
     */
    protected TypeEnum getExpectedType(RandomVariable rv) {
        TypeEnum expectedType = TypeEnum.ANY;
        if (rv instanceof VariableCharacterisation) {
            expectedType = StochasticExpressionEditDialog
                    .getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
        }
        return expectedType;
    }

    /**
     * Gets the context.
     * 
     * @param rv
     *            the rv
     * @return the context
     */
    private Parameter[] getContext(EObject rv) {
        Parameter[] parameters = new Parameter[] {};

        ResourceDemandingSEFF seff = getSEFF(rv);

        if (seff != null && seff.getDescribedService__SEFF() != null) {
            if (seff.getDescribedService__SEFF() instanceof OperationSignature
                    && ((OperationSignature) seff.getDescribedService__SEFF()).getParameters__OperationSignature() != null) {
                parameters = (Parameter[]) ((OperationSignature) seff.getDescribedService__SEFF())
                        .getParameters__OperationSignature().toArray();
            }
        }
        return parameters;
    }

    /**
     * Gets the seff.
     * 
     * @param a
     *            the a
     * @return the seff
     */
    private ResourceDemandingSEFF getSEFF(EObject a) {
        EObject container = a;
        while (!(container instanceof ResourceDemandingSEFF)) {
            container = container.eContainer();
        }
        if (!(container instanceof ResourceDemandingSEFF)) {
            return null;
        }
        ResourceDemandingSEFF seff = (ResourceDemandingSEFF) container;
        return seff;
    }

}
