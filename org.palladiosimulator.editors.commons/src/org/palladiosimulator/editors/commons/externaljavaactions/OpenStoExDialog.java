package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public abstract class OpenStoExDialog implements IExternalJavaAction {

    @Override
    public void execute(final Collection<? extends EObject> selections, final Map<String, Object> parameters) {
        // A parameter "element" containing the element is needed !
        final RandomVariable randomVariable = getRandomVariable((EObject) parameters.get("element"));
        final StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
                PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getExpectedType(randomVariable));

        dialog.setInitialExpression(randomVariable);
        dialog.open();
        if (dialog.getReturnCode() == Dialog.OK) {
            randomVariable.setSpecification(dialog.getResultText());
            randomVariable.setSpecification(new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult()));
        }
    }
    /*
     * public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
     * RandomVariable rv = null; EObject check = arg0.iterator().next(); if (check instanceof
     * DNodeListElement) { rv = getRandomVariable(((DNodeListElement) check).getTarget()); } else if
     * (check instanceof DNodeContainer) { rv = getRandomVariable(((DNodeContainer)
     * check).getTarget()); } else if (check instanceof DNode) { rv = getRandomVariable(((DNode)
     * check).getTarget()); } StochasticExpressionEditDialog dialog = new
     * StochasticExpressionEditDialog(
     * PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getExpectedType(rv));
     * dialog.setInitialExpression(rv); dialog.open(); if (dialog.getReturnCode() == Dialog.OK) {
     * rv.setSpecification(dialog.getResultText()); rv.setSpecification(new
     * PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult())); } }
     */

    protected TypeEnum getExpectedType(final RandomVariable rv) {
        TypeEnum expectedType = TypeEnum.ANY;
        if (rv instanceof VariableCharacterisation) {
            expectedType = StochasticExpressionEditDialog
                    .getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
        }
        return expectedType;
    }

    public abstract RandomVariable getRandomVariable(EObject element);

}
