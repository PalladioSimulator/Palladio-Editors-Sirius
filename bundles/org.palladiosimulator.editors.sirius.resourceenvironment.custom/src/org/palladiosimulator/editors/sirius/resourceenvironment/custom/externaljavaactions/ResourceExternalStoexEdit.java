package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ResourceExternalStoexEdit extends OpenExternalStoexEditor {
	
	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1,
			EObject element) {
		if(!(element instanceof ProcessingResourceSpecification))
			throw new IllegalArgumentException("Only ProcessingResourceSpecification supported");
		var resource = (ProcessingResourceSpecification) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "rate":
			return resource.getProcessingRate_ProcessingResourceSpecification();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}
}
