package org.palladiosimulator.editors.usage.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.Delay;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class DelayTimeAction extends SetRandomVariable {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

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
