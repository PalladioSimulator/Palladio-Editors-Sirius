package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetReadProcessingRate extends SetRandomVariable {
	
	private static final String READ_PROCESSING_RATE = "Read Processing Rate";

	@Override
	protected String getInitialText() {
		return READ_PROCESSING_RATE;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return READ_PROCESSING_RATE.equalsIgnoreCase(mappingName);
	}

}
