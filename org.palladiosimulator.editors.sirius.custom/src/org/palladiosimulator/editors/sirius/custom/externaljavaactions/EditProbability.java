package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * Abstract class used to edit a probability using the Direct Edit Label.
 * The input value must be passed as an argument called "var"
 * 
 * @author Amine Kechaou
 * 
 */
public abstract class EditProbability implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject element = selections.iterator().next();
		String input = (String) parameters.get("var");
		
		try {
			double p = Double.parseDouble(input);
			if (p > 1 || p < 0) {
				System.out.println("Invalid label. Enter a probability between 0 and 1");
			}
			else {
				setProbability(element, p);
			}
			
		}  catch (NumberFormatException e) {
			System.out.println("Invalid label. Enter a probability between 0 and 1");
		}

	}

	protected abstract void setProbability(EObject eObject, double probability);
	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
