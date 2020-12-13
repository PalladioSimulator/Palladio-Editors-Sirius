package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.LoopAction;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LoopActionExternalStoexEdit extends OpenExternalStoexEditor{

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof LoopAction))
			throw new IllegalArgumentException("Only LoopAction supported");
		var resource = (LoopAction) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "loop":
			return resource.getIterationCount_LoopAction();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
