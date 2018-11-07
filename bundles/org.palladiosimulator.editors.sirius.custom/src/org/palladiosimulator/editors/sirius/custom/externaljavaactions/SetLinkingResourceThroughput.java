package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

public class SetLinkingResourceThroughput extends SetRandomVariable {
	
	private static final String THROUGHPUT = "Throughput";

	@Override
	public String getInitialText() {
		return THROUGHPUT;
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		return THROUGHPUT.equalsIgnoreCase(mappingName);
	}

}
