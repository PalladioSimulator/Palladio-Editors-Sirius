package org.scaledl.architecturaltemplates.completion.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.scaledl.architecturaltemplates.completion.config.ATConfiguration;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class StoreCompletedModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    public StoreCompletedModelsJob(final ATConfiguration configuration) {
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        // save the modified model
        /*
         * final SavePartitionToDiskJob savePartitionJob = new SavePartitionToDiskJob(
         * LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
         * savePartitionJob.setBlackboard(getBlackboard()); savePartitionJob.execute(monitor);
         */
    }

}
