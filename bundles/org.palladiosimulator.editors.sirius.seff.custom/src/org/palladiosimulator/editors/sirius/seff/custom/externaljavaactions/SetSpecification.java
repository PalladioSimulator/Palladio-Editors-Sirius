package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

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
