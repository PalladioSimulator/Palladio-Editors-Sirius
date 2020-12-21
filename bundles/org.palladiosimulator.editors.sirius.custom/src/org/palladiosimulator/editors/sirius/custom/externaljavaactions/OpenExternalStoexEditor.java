package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public abstract class OpenExternalStoexEditor<T> implements IExternalJavaAction {
    private final String actionParameterType = "type";
    private Class<T> type;

    public OpenExternalStoexEditor(final Class<T> type) {
        this.type = type;
    }

    @Override
    public boolean canExecute(final Collection<? extends EObject> arg0) {
        return true;
    }

    @Override
    public void execute(final Collection<? extends EObject> arg0, final Map<String, Object> arg1) {
        if (arg0.size() != 1) {
            throw new IllegalArgumentException("Multiselection not supported");
        }
        final var element = arg0.iterator()
            .next();

        final var typedElement = this.getTypedElement(element);
        final var action = (String) arg1.get(this.actionParameterType);
        final RandomVariable randVar = this.editStoexAction(action, typedElement);

        if (randVar.getSpecification() == null) {
            this.dialogEmptyRandomVariable(randVar);
        } else {
            this.dialogNotEmptyRandomVariable(randVar);
        }

    }

    protected abstract RandomVariable editStoexAction(String action, T element);

    private void dialogEmptyRandomVariable(final RandomVariable randVar) {
        final var copyRand = EcoreUtil.copy(randVar);
        copyRand.setSpecification("0");
        final StochasticExpressionEditDialog dialog = this.createDialog(copyRand);
        if (dialog.getReturnCode() == Window.OK) {
            final String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
            randVar.setSpecification(result);
        }
    }

    private void dialogNotEmptyRandomVariable(final RandomVariable randVar) {
        final StochasticExpressionEditDialog dialog = this.createDialog(randVar);
        if (dialog.getReturnCode() == Window.OK) {
            final String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
            randVar.setSpecification(result);
        }
    }

    private StochasticExpressionEditDialog createDialog(final RandomVariable randVar) {
        final StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(PlatformUI.getWorkbench()
            .getActiveWorkbenchWindow()
            .getShell(), this.getExpectedType(randVar), randVar);
        dialog.setInitialExpression(randVar);
        dialog.open();
        return dialog;
    }

    private TypeEnum getExpectedType(final RandomVariable rv) {
        TypeEnum expectedType = TypeEnum.ANY;
        if (rv instanceof VariableCharacterisation) {
            expectedType = StochasticExpressionEditDialog
                .getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
        }
        return expectedType;
    }

    @SuppressWarnings("unchecked") // type is checked by class
    private T getTypedElement(final EObject o) {
        if (this.type.isInstance(o)) {
            return (T) o;
        } else {
            throw new IllegalArgumentException("Only " + this.type + " supported");
        }
    }

}
