package org.scaledl.architecturaltemplates.completion.jobs;

import org.scaledl.architecturaltemplates.completion.config.ATConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.debug.IDebugListener;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Conducts a complete AT Job:
 * 
 * 1) Load source models (uncompleted PCM models; stereotypes may reference AT models) into the blackboard.
 * 
 * 2) Validate these models according to AT constraints.
 * 
 * 3) Run the AT completion that transforms the source models into models enriched with information
 * derivable from the given AT.
 * 
 * 4a) For debug: Store the resulting models into a new Palladio project.
 * 
 * 4b) Store the resulting models in the blackboard partition. This may already happened in step 3)?
 * 
 * FIXME Daria: All of these jobs have to be implemented; start with that [Lehrig]
 * 
 * @author Sebastian Lehrig
 */
public class RunATJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * Convenience constructor.
     * 
     * @param configuration
     *            Configuration for AT runs.
     */
    public RunATJob(ATConfiguration configuration) {
        this(configuration, null);
    }

    /**
     * Default constructor.
     * 
     * @param configuration
     *            Configuration for AT runs.
     * @param listener
     *            DebugListener for simulations.
     */
    public RunATJob(ATConfiguration configuration, IDebugListener listener) {
        super(false);

        if (listener == null && configuration.isDebug()) {
            throw new IllegalArgumentException("Debug listener has to be non-null for debug runs");
        }

        this.addJob(new ValidateModelsJob(configuration));
        this.addJob(new RunATCompletionJob(configuration));
        this.addJob(new StoreCompletedModelsJob(configuration));
    }

}
