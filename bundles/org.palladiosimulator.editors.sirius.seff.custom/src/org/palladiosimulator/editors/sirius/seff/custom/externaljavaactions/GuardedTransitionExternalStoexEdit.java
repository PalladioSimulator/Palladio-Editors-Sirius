package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.GuardedBranchTransition;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class GuardedTransitionExternalStoexEdit extends OpenExternalStoexEditor<GuardedBranchTransition> {
    private final String editGuardedBranchTransition = "guard";

    public GuardedTransitionExternalStoexEdit() {
        super(GuardedBranchTransition.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final GuardedBranchTransition element) {
        switch (action) {
        case editGuardedBranchTransition:
            return element.getBranchCondition_GuardedBranchTransition();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
