package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.ui.PlatformUI;
import org.palladiosimulator.editors.commons.dialogs.stoex.StochasticExpressionEditDialog;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

/**
 * This External Java Action opens a StochasticExpressionEditDialog and sets the
 * RandomVariable of the object passed to getRandomVariable.
 * 
 * The RandomVariable instance should already be created.
 * 
 * This class is abstract and therefore must be extended. (See for example
 * {@link SetVariableCharacterisationSpecification})
 * 
 * The RandomVariable's container (e.g. VariableCharacterisation in
 * {@link SetVariableCharacterisationSpecification}) must be passed as parameter
 * to the External Java Action with the name "element". (See for example
 * repository.odesign > Section Internal Elements > Node Creation Variable
 * Characterisation and Double Click Edit Variable Characterisation)
 * 
 * @author Amine Kechaou
 *
 */
public abstract class SetRandomVariable implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		RandomVariable randomVariable = getRandomVariable((EObject) parameters.get("element"));
		StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getExpectedType());

		dialog.setInitialExpression(randomVariable);
		dialog.open();
		if (dialog.getReturnCode() == Dialog.OK) {
			randomVariable.setSpecification(dialog.getResultText());
			randomVariable.setSpecification(new PCMStoExPrettyPrintVisitor().prettyPrint(dialog.getResult()));
		}
	}

	/**
	 * Gets the expected type of the RandomVariable to be set
	 * @return the expected type of the RandomVariable to be set
	 */
	public abstract TypeEnum getExpectedType();

	/**
	 * Gets the RandomVariable of the element 
	 * @param element Element containing the RandomVariable to be set
	 * @return The RandomVariable of the given element
	 */
	public abstract RandomVariable getRandomVariable(EObject element);
	
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}
}
