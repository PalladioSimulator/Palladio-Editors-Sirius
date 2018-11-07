package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetSpecification extends SetRandomVariable {

	private static final String RESOURCE_DEMAND = "ResourceDemand";

	@Override
	protected String getInitialText() {
		return RESOURCE_DEMAND;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return RESOURCE_DEMAND.equalsIgnoreCase(mappingName);
	}

}
