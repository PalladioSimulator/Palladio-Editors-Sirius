package org.scaledl.architecturaltemplates.completion.jobs;

import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;

import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ValidateModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {
    
    public ValidateModelsJob(final ATExtensionJobConfiguration configuration) {
    }
    
}
