package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ResourceCallExternalStoexEdit extends OpenExternalStoexEditor<ResourceCall> {
    private final String editResourceCall = "resource";

    public ResourceCallExternalStoexEdit() {
        super(ResourceCall.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final ResourceCall element) {
        switch (action) {
        case editResourceCall:
            return element.getNumberOfCalls__ResourceCall();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
