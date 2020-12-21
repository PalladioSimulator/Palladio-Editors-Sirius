package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class InfrastructureCallExternalStoexEdit extends OpenExternalStoexEditor<InfrastructureCall> {
    private final String editInfrastructureCall = "infrastructure";

    public InfrastructureCallExternalStoexEdit() {
        super(InfrastructureCall.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final InfrastructureCall element) {
        switch (action) {
        case editInfrastructureCall:
            return element.getNumberOfCalls__InfrastructureCall();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
