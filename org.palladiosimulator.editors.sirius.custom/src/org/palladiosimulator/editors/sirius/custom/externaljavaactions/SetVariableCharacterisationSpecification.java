package org.palladiosimulator.editors.sirius.custom.externaljavaactions;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetVariableCharacterisationSpecification extends SetRandomVariable {

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
