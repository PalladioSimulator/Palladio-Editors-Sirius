package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.usagemodel.Delay;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class DelayExternalStoexEdit extends OpenExternalStoexEditor<Delay> {
    private final String editDelay = "delay";

    public DelayExternalStoexEdit() {
        super(Delay.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final Delay element) {
        switch (action) {
        case editDelay:
            return element.getTimeSpecification_Delay();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
