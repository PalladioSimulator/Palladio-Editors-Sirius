package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetNumberOfCalls extends SetRandomVariable  {

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		if (element instanceof InfrastructureCall) {
			InfrastructureCall call = (InfrastructureCall) element;
				return call.getNumberOfCalls__InfrastructureCall();
		} else if (element instanceof ResourceCall) {
			ResourceCall call = (ResourceCall) element;
				return call.getNumberOfCalls__ResourceCall();
		} else {
			return null;
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.INT;
	}

}
