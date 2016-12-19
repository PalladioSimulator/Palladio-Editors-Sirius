package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class ClosedWorkloadThinkTimeAction extends SetRandomVariable {

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		ClosedWorkload workload = (ClosedWorkload) element;
		PCMRandomVariable rv = workload.getThinkTime_ClosedWorkload();
		return rv;
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.DOUBLE;
	}

}
