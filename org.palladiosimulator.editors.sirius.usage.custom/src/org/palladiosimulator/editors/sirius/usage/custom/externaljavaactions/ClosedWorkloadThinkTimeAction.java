package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.SetRandomVariable;

public class ClosedWorkloadThinkTimeAction extends SetRandomVariable {

	private static final String THINK_TIME = "thinkTime";

	@Override
	public String getInitialText() {
		return THINK_TIME;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return THINK_TIME.equalsIgnoreCase(mappingName);
	}

}
