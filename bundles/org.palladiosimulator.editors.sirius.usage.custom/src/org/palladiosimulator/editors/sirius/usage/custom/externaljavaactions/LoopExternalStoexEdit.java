package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.usagemodel.Loop;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LoopExternalStoexEdit extends OpenExternalStoexEditor<Loop> {
    private final String editLoop = "loop";

    public LoopExternalStoexEdit() {
        super(Loop.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final Loop element) {
        switch (action) {
        case editLoop:
            return element.getLoopIteration_Loop();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
