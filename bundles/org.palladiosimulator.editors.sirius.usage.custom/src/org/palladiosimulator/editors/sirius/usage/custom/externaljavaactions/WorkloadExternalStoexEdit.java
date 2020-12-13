package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class WorkloadExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof Workload))
			throw new IllegalArgumentException("Only Workload supported");
		var resource = (Workload) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "closed":
			return ((ClosedWorkload) resource).getThinkTime_ClosedWorkload();
		case "open":
			return ((OpenWorkload) resource).getInterArrivalTime_OpenWorkload();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
