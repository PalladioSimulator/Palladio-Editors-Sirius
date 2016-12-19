package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class LoopIterationsAction extends SetRandomVariable {

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		Loop l = (Loop) element;
		return l.getLoopIteration_Loop();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.INT;
	}

}
