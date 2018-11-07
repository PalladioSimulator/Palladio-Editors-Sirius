package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetProcessingResourceProcessingRate extends SetRandomVariable {

	private static final String PROCESSING_RATE = "Processing Rate";
	
	@Override
	protected String getInitialText() {
		return PROCESSING_RATE;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return PROCESSING_RATE.equalsIgnoreCase(mappingName);
	}

}
