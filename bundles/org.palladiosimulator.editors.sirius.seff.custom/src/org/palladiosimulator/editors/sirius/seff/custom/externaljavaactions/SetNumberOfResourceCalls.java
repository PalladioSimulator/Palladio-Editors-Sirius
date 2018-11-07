package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetNumberOfResourceCalls extends SetRandomVariable  {
	
	private static final String RESOURCE_CALL = "ResourceCall";

	@Override
	protected String getInitialText() {
		return RESOURCE_CALL;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return RESOURCE_CALL.equalsIgnoreCase(mappingName);
	}


}
