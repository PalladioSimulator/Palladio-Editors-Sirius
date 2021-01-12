package org.palladiosimulator.editors.sirius.usage.custom.externaljavaactions;

import org.palladiosimulator.editors.sirius.custom.externaljavaactions.OpenExternalStoexEditor;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.stoex.RandomVariable;

public class WorkloadExternalStoexEdit extends OpenExternalStoexEditor<Workload> {
    private final String editThinkTime = "closed";
    private final String editInterArrivalTime = "open";

    public WorkloadExternalStoexEdit() {
        super(Workload.class);
    }

    @Override
    protected RandomVariable editStoexAction(final String action, final Workload element) {
        switch (action) {
        case editThinkTime:
            return ((ClosedWorkload) element).getThinkTime_ClosedWorkload();
        case editInterArrivalTime:
            return ((OpenWorkload) element).getInterArrivalTime_OpenWorkload();

        default:
            throw new IllegalArgumentException(action + "missing");
        }
    }

}
