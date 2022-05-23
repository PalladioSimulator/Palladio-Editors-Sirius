package org.palladiosimulator.editors.sirius.resourceenvironment.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class LinkingResourceExternalStoexEdit extends OpenExternalStoexEditor<CommunicationLinkResourceSpecification> {
    private final String editLatency = "latency";
    private final String editThroughput = "throughput";

    public LinkingResourceExternalStoexEdit() {
        super(CommunicationLinkResourceSpecification.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action,
            final CommunicationLinkResourceSpecification element) {
        switch (action) {
        case editLatency:
            return element.getLatency_CommunicationLinkResourceSpecification();
        case editThroughput:
            return element.getThroughput_CommunicationLinkResourceSpecification();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }
}
