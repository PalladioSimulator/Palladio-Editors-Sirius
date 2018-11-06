package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

public class SetLinkingResourceLatency extends SetRandomVariable {

	private static final String LATENCY = "LATENCY";

	public String getInitialText() {
		return LATENCY;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return LATENCY.equalsIgnoreCase(mappingName);
	}

}
