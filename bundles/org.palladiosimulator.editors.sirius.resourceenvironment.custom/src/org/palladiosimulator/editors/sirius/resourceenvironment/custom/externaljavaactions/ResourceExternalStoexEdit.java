package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class ResourceExternalStoexEdit extends OpenExternalStoexEditor<ProcessingResourceSpecification> {
    private final String editProcessingRate = "rate";

    public ResourceExternalStoexEdit() {
        super(ProcessingResourceSpecification.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final ProcessingResourceSpecification element) {
        switch (action) {
        case editProcessingRate:
            return element.getProcessingRate_ProcessingResourceSpecification();
        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }
}
