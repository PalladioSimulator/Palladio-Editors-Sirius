package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

public class SetVariableCharacterisationSpecification extends SetRandomVariable {

	private static final String[] VARIABLE_CHARACTERISATION = { "VariableCharacterisation",
			"Variable Characterisation", "VariableCharacterisationInput", "VariableCharacterisationOutput",
			"VariableCharacterisationvariableSetter" };

	@Override
	public String getInitialText() {
		return VARIABLE_CHARACTERISATION[0];
	}

	@Override
	protected boolean checkMapping(String mappingName) {
		for (String expectedName : VARIABLE_CHARACTERISATION) {
			if (mappingName.equalsIgnoreCase(expectedName)) return true;
		}
		return false;
	}
}
