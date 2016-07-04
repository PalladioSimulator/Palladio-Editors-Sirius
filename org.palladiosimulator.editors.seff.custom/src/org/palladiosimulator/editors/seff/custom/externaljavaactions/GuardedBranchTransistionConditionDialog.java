package org.palladiosimulator.editors.seff.custom.externaljavaactions;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.commons.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.GuardedBranchTransition;

import de.uka.ipd.sdq.stoex.RandomVariable;

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

}
