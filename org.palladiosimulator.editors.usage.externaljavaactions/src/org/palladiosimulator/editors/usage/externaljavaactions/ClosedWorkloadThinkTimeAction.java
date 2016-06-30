package org.palladiosimulator.editors.usage.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.OpenStoExDialog;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class ClosedWorkloadThinkTimeAction extends OpenStoExDialog {

	public ClosedWorkloadThinkTimeAction() {

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		ClosedWorkload workload = (ClosedWorkload) element;
		PCMRandomVariable rv = workload.getThinkTime_ClosedWorkload();
		return rv;
	}

	@Override
	protected TypeEnum getExpectedType(RandomVariable rv) {
		return TypeEnum.DOUBLE;
	}

}
