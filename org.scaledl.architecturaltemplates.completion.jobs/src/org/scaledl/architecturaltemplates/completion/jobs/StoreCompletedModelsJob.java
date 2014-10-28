package org.scaledl.architecturaltemplates.completion.jobs;

import org.scaledl.architecturaltemplates.completion.config.ATConfiguration;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class StoreCompletedModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {
    
    public StoreCompletedModelsJob(final ATConfiguration configuration) {
    }
    
}
