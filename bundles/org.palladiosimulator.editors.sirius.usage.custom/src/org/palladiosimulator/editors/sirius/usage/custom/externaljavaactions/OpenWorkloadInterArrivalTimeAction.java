package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class OpenWorkloadInterArrivalTimeAction extends SetRandomVariable {

	private static final String INTER_ARRIVAL_TIME = "interArrivalTime";

	@Override
	public String getInitialText() {
		return INTER_ARRIVAL_TIME;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return INTER_ARRIVAL_TIME.equalsIgnoreCase(mappingName);
	}

}
