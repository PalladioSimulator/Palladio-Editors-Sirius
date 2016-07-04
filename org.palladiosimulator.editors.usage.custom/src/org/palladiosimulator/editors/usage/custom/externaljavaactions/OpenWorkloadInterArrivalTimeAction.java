package org.palladiosimulator.editors.usage.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class OpenWorkloadInterArrivalTimeAction extends SetRandomVariable {

	public OpenWorkloadInterArrivalTimeAction() {

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		OpenWorkload ow = (OpenWorkload) element;
		return ow.getInterArrivalTime_OpenWorkload();
	}

}
