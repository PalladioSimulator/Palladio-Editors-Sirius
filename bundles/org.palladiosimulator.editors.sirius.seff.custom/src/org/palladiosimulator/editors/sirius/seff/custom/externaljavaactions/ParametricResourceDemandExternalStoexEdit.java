package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ParametricResourceDemandExternalStoexEdit extends OpenExternalStoexEditor{

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof ParametricResourceDemand))
			throw new IllegalArgumentException("Only ParametricResourceDemand supported");
		var resource = (ParametricResourceDemand) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "demand":
			return resource.getSpecification_ParametericResourceDemand();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
