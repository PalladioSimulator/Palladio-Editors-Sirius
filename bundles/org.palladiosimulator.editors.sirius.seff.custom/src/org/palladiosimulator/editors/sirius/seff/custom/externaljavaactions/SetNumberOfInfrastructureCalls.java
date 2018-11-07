package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetNumberOfInfrastructureCalls extends SetRandomVariable {
	
	private static final String INFRASTRUCTURE_CALL = "InfrastructureCall";

	@Override
	protected String getInitialText() {
		return INFRASTRUCTURE_CALL;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return INFRASTRUCTURE_CALL.equalsIgnoreCase(mappingName);
	}

}
