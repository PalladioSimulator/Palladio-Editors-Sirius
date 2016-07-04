package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.repository.PassiveResource;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class SetCapacity extends SetRandomVariable {



	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		PassiveResource pr = (PassiveResource) element;
		return pr.getCapacity_PassiveResource();
	}

}
