package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.seff.LoopAction;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LoopActionExternalStoexEdit extends OpenExternalStoexEditor<LoopAction> {
    private final String editLoopAction = "loop";

    public LoopActionExternalStoexEdit() {
        super(LoopAction.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final LoopAction element) {
        switch (action) {
        case editLoopAction:
            return element.getIterationCount_LoopAction();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
