package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LinkingResourceExternalStoexEdit extends OpenExternalStoexEditor {
	
	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1,
			EObject element) {
		if(!(element instanceof CommunicationLinkResourceSpecification))
			throw new IllegalArgumentException("Only CommunicationLinkResourceSpecification supported");
		var linkingResource = (CommunicationLinkResourceSpecification) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "latency":
			return linkingResource.getLatency_CommunicationLinkResourceSpecification();
		case "throughput":
			return linkingResource.getThroughput_CommunicationLinkResourceSpecification();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}
}
