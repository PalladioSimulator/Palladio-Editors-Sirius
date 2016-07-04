package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetVariableCharacterisationSpecification extends SetRandomVariable {

	public SetVariableCharacterisationSpecification() {
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

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.ANY;
	}

}
