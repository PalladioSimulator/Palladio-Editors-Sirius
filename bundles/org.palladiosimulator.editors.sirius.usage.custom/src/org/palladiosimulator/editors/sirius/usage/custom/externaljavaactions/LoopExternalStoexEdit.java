package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LoopExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof Loop))
			throw new IllegalArgumentException("Only Loop supported");
		var resource = (Loop) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "loop":
			return resource.getLoopIteration_Loop();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
