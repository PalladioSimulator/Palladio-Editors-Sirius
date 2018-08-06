package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.Delay;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class DelayTimeAction extends SetRandomVariable {


	@Override
	public RandomVariable getRandomVariable(EObject element) {
		Delay d = (Delay) element;
		return d.getTimeSpecification_Delay();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.DOUBLE;
	}

}
