package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetWriteProcessingRate extends SetRandomVariable {

	private static final String WRITE_PROCESSING_RATE = "Write Processing Rate";
	
	@Override
	protected String getInitialText() {
		return WRITE_PROCESSING_RATE;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return WRITE_PROCESSING_RATE.equalsIgnoreCase(mappingName);
	}

}
