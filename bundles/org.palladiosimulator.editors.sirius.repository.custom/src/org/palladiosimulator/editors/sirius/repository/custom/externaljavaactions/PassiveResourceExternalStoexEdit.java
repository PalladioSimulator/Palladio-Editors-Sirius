package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.repository.PassiveResource;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class PassiveResourceExternalStoexEdit extends OpenExternalStoexEditor<PassiveResource> {
    private final String editPassiveResource = "passive";

    public PassiveResourceExternalStoexEdit() {
        super(PassiveResource.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final PassiveResource element) {
        switch (action) {
        case editPassiveResource:
            return element.getCapacity_PassiveResource();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
