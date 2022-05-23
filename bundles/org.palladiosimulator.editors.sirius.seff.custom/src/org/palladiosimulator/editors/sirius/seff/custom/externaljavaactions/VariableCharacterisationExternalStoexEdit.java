package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class VariableCharacterisationExternalStoexEdit extends OpenExternalStoexEditor<VariableCharacterisation> {
    private final String editVariableCharacterisation = "variable";

    public VariableCharacterisationExternalStoexEdit() {
        super(VariableCharacterisation.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final VariableCharacterisation element) {
        switch (action) {
        case editVariableCharacterisation:
            return element.getSpecification_VariableCharacterisation();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
