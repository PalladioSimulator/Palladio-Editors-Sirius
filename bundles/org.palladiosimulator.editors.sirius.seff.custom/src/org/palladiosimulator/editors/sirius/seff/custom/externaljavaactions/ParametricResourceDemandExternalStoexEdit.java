package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ParametricResourceDemandExternalStoexEdit extends OpenExternalStoexEditor<ParametricResourceDemand> {
    private final String editParametericResourceDemand = "demand";

    public ParametricResourceDemandExternalStoexEdit() {
        super(ParametricResourceDemand.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final ParametricResourceDemand element) {
        switch (action) {
        case editParametericResourceDemand:
            return element.getSpecification_ParametericResourceDemand();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
