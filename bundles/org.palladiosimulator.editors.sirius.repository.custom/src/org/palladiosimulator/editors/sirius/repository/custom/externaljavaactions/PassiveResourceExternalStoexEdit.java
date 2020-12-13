package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class PassiveResourceExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof PassiveResource))
			throw new IllegalArgumentException("Only PassiveResource supported");
		var resource = (PassiveResource) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "passive":
			return resource.getCapacity_PassiveResource();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
