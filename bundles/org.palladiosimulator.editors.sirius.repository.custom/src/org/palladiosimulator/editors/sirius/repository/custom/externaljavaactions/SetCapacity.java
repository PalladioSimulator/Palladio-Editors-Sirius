package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class SetCapacity extends SetRandomVariable {
	
	private static final String CAPACITY = "Capacity";

	@Override
	public String getInitialText() {
		return CAPACITY;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return CAPACITY.equalsIgnoreCase(mappingName);
	}

}
