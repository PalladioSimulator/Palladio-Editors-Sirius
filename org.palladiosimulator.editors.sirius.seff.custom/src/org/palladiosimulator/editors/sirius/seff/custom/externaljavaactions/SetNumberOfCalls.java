package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetNumberOfCalls extends SetRandomVariable  {

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		InfrastructureCall call = (InfrastructureCall) element;
		return call.getNumberOfCalls__InfrastructureCall();
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
