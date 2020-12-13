package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.LoopAction;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class InfrastructureCallExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof InfrastructureCall))
			throw new IllegalArgumentException("Only LoopAction supported");
		var resource = (InfrastructureCall) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "infrastructure":
			return resource.getNumberOfCalls__InfrastructureCall();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
