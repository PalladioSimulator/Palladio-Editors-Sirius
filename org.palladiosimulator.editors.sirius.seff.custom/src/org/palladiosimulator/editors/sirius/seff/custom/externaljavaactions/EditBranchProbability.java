package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.EditProbability;
import org.palladiosimulator.pcm.seff.ProbabilisticBranchTransition;



public class EditBranchProbability extends EditProbability {


	@Override
	protected void setProbability(EObject eObject, double probability) {
		ProbabilisticBranchTransition element = (ProbabilisticBranchTransition) eObject;
		element.setBranchProbability(probability);
		
		
	}

}
