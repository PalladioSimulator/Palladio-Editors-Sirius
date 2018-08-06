package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class OpenWorkloadInterArrivalTimeAction extends SetRandomVariable {


	@Override
	public RandomVariable getRandomVariable(EObject element) {
		OpenWorkload ow = (OpenWorkload) element;
		return ow.getInterArrivalTime_OpenWorkload();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.ANY;
	}

}
