package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.EditProbability;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

public class EditLinkingResourceFailureProbability extends EditProbability {

	@Override
	protected void setProbability(EObject eObject, double probability) {
		CommunicationLinkResourceSpecification c = (CommunicationLinkResourceSpecification) eObject;
		c.setFailureProbability(probability);

	}

}
