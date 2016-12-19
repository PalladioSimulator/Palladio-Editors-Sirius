package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetSpecification extends SetRandomVariable {

	

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.ANY;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		if (element instanceof ParametricResourceDemand) {
			ParametricResourceDemand rd = (ParametricResourceDemand) element;
			return rd.getSpecification_ParametericResourceDemand();
		} else if (element instanceof ResourceCall) {
			ResourceCall rc = (ResourceCall) element;
			return rc.getNumberOfCalls__ResourceCall();
		} else {
			return null;
		}
	}

}
