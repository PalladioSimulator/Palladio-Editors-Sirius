package org.palladiosimulator.editors.sirius.assembly.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.qosannotations.qos_performance.SystemSpecifiedExecutionTime;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetSSETSpecification extends SetRandomVariable {

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.ANY;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		SystemSpecifiedExecutionTime sset = (SystemSpecifiedExecutionTime) element;
		return sset.getSpecification_SpecifiedExecutionTime();
	}
	
}
