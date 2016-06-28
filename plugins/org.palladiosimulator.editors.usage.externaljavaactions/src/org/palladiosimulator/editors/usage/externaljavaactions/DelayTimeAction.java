package org.palladiosimulator.editors.usage.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.externaljavaactions.OpenStoExDialog;
import org.palladiosimulator.pcm.usagemodel.Delay;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class DelayTimeAction extends OpenStoExDialog {

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
	protected TypeEnum getExpectedType(RandomVariable rv) {
		return TypeEnum.DOUBLE;
	}

}
