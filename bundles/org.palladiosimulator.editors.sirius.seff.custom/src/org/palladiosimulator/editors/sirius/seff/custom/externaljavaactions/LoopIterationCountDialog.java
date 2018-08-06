package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.LoopAction;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class LoopIterationCountDialog extends SetRandomVariable {


	@Override
	public RandomVariable getRandomVariable(EObject element) {
		LoopAction l = (LoopAction) element;
		return l.getIterationCount_LoopAction();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.INT;
	}

}
