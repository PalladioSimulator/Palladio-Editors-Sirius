package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.GuardedBranchTransition;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class GuardedTransistionExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof GuardedBranchTransition))
			throw new IllegalArgumentException("Only GuardedBranchTransition supported");
		var resource = (GuardedBranchTransition) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "guard":
			return resource.getBranchCondition_GuardedBranchTransition();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
