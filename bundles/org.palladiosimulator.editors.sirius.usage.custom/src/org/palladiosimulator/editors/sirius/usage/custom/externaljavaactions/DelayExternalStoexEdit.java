package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.usagemodel.Delay;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class DelayExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof Delay))
			throw new IllegalArgumentException("Only Delay supported");
		var resource = (Delay) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "delay":
			return resource.getTimeSpecification_Delay();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
