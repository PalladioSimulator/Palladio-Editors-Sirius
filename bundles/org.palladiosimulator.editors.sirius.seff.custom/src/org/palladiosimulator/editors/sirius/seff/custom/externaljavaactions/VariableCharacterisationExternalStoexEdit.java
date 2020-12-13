package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.seff.LoopAction;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class VariableCharacterisationExternalStoexEdit extends OpenExternalStoexEditor {

	@Override
	protected RandomVariable extractRandomVariable(Map<String, Object> arg1, EObject element) {
		if(!(element instanceof VariableCharacterisation))
			throw new IllegalArgumentException("Only VariableCharacterisation supported");
		var resource = (VariableCharacterisation) element;
		
		var type = (String) arg1.get("type");
		
		switch (type) {
		case "variable":
			return resource.getSpecification_VariableCharacterisation();

		default:
			throw new IllegalArgumentException("RandomVar Type missing");
		}
	}

}
