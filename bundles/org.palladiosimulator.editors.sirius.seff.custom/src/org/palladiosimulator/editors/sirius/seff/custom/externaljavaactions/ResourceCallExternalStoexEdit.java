package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ResourceCallExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof ResourceCall))
			throw new IllegalArgumentException("Only ResourceCall supported");
		var resource = (ResourceCall) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "resource":
			return resource.getNumberOfCalls__ResourceCall();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
