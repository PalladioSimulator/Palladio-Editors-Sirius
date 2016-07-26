package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetSpecification extends SetRandomVariable {

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.ANY;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		ParametricResourceDemand rd = (ParametricResourceDemand) element;
		return rd.getSpecification_ParametericResourceDemand();
	}

}
