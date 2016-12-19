package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.EditProbability;
import org.palladiosimulator.pcm.reliability.InternalFailureOccurrenceDescription;

public class EditInternalFailureOccurrenceDescriptionProbability extends EditProbability {

	@Override
	protected void setProbability(EObject eObject, double probability) {
		InternalFailureOccurrenceDescription element = (InternalFailureOccurrenceDescription) eObject;
		element.setFailureProbability(probability);
	}

}
