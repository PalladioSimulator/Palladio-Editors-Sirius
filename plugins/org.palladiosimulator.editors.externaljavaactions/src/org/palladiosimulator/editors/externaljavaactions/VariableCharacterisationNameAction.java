package org.palladiosimulator.editors.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class VariableCharacterisationNameAction extends OpenStoExDialog {

	public VariableCharacterisationNameAction() {
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		VariableCharacterisation vc = (VariableCharacterisation) element;
		return vc.getSpecification_VariableCharacterisation();
	}

}
