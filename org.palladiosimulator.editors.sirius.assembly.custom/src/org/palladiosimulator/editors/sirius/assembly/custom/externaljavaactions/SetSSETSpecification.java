package org.palladiosimulator.editors.sirius.assembly.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetSSETSpecification extends SetRandomVariable {

	private static final String SSET = "SSET";
	
	@Override
	protected String getInitialText() {
		return SSET;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return SSET.equalsIgnoreCase(mappingName);
	}


}
