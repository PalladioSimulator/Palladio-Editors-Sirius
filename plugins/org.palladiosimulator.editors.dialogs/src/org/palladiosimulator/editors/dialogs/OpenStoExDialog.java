package org.palladiosimulator.editors.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
/**
 * Manually written open policy to open the StoEx Dialog. It's called via a CustomBehaviour in the
 * genmap
 */
public class OpenStoExDialog extends OpenEditPolicy {

    /** The random variable feature. */
    protected EStructuralFeature randomVariableFeature;

    /**
     * Instantiates a new open sto ex dialog.
     * 
     * @param randomVariableFeature
     *            the random variable feature
     */
    public OpenStoExDialog(EStructuralFeature randomVariableFeature) {
        super();
        this.randomVariableFeature = randomVariableFeature;
    }

    /**
     * Instantiates a new open sto ex dialog.
     */
    public OpenStoExDialog() {
        super();
        this.randomVariableFeature = null;
    }

    /**
     * Gets the random variable.
     * 
     * @param parent
     *            the parent
     * @return the random variable
     */
    protected RandomVariable getRandomVariable(EObject parent) {
        // Default Implementation. Override as necessary
        if (randomVariableFeature == null && parent instanceof RandomVariable) {
            return (RandomVariable) parent;
        } else if (randomVariableFeature == null && parent instanceof VariableCharacterisation) {
            return ((VariableCharacterisation) parent).getSpecification_VariableCharacterisation();
        } else {
            return (RandomVariable) parent.eGet(randomVariableFeature);
        }
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
                .getActiveWorkbenchWindow().getShell(), getExpectedType(rv), rv);
        if (rv != null) {
            dialog.setInitialExpression(rv);
        }
        if (getDialogMessage() != null) {
            dialog.setDisplayTitle(getDialogMessage());
        }

        dialog.open();
        if (dialog.getReturnCode() == Dialog.OK) {
            Object obj = request.getType();
            SetRequest setRequest = new SetRequest(rv, StoexPackage.eINSTANCE.getRandomVariable_Specification(),
                    dialog.getResultText());
            SetValueCommand cmd = new SetValueCommand(setRequest);
            return new ICommandProxy(cmd);
        }
        return null;
    }

    /**
     * Gets the dialog message.
     * 
     * @return the dialog message
     */
    protected String getDialogMessage() {
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
        VariableCharacterisation vc = null;

        if (rv instanceof VariableCharacterisation) {
            vc = (VariableCharacterisation) rv;
        }
        if (rv instanceof PCMRandomVariable && rv.eContainer() instanceof VariableCharacterisation) {
            vc = (VariableCharacterisation) rv.eContainer();
        }
        if (vc != null) {
            expectedType = StochasticExpressionEditDialog.getTypeFromVariableCharacterisation(vc);
        }
        return expectedType;
    }

}
