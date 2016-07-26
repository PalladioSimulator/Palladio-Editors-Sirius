package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.GuardedBranchTransition;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class GuardedBranchTransistionConditionDialog extends SetRandomVariable {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		return true;
	}

	@Override
	public RandomVariable getRandomVariable(EObject element) {
		GuardedBranchTransition gbt = (GuardedBranchTransition) element;
		return gbt.getBranchCondition_GuardedBranchTransition();
	}

	@Override
	public TypeEnum getExpectedType() {
		return TypeEnum.BOOL;
	}

}
