package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.repository.PassiveResource;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetCapacity extends SetRandomVariable {

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		PassiveResource pr = (PassiveResource) element;
		return pr.getCapacity_PassiveResource();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.DOUBLE;
	}

}
