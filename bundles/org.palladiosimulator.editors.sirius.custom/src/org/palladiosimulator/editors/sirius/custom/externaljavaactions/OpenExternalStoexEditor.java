package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public abstract class OpenExternalStoexEditor implements IExternalJavaAction {

	public OpenExternalStoexEditor() {
		super();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		if (arg0.size() != 1)
			throw new IllegalArgumentException("Multiselection not supported");
		var element = arg0.iterator().next();

		RandomVariable randVar = extractRandomVariable(arg1, element);

		if (randVar.getSpecification() == null) {
			dialogEmptyRandomVariable(randVar);
		} else {
			dialogNotEmptyRandomVariable(randVar);
		}
		

	}

	protected abstract RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element);

	private void dialogEmptyRandomVariable(RandomVariable randVar) {
		var copyRand = EcoreUtil.copy(randVar);
		copyRand.setSpecification("0");
		StochasticExpressionEditDialog dialog = createDialog(copyRand);
		if (dialog.getReturnCode() == Dialog.OK) {
			String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
			randVar.setSpecification(result);
		}
	}

	private void dialogNotEmptyRandomVariable(RandomVariable randVar) {
		StochasticExpressionEditDialog dialog = createDialog(randVar);
		if (dialog.getReturnCode() == Dialog.OK) {
			String result = new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult());
			randVar.setSpecification(result);
		}
	}

	private StochasticExpressionEditDialog createDialog(RandomVariable randVar) {
		StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getExpectedType(randVar), randVar);
		dialog.setInitialExpression(randVar);
		dialog.open();
		return dialog;
	}

	private TypeEnum getExpectedType(RandomVariable rv) {
		TypeEnum expectedType = TypeEnum.ANY;
		if (rv instanceof VariableCharacterisation) {
			expectedType = StochasticExpressionEditDialog
					.getTypeFromVariableCharacterisation((VariableCharacterisation) rv);
		}
		return expectedType;
	}

}
