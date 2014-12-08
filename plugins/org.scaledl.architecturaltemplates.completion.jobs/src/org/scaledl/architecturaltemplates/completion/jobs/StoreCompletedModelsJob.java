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

        /**
         * For debugging, generated files might be saved...
         * 
         * try { outResource.save(Collections.emptyMap()); } catch (IOException e) {
         * logger.error("Unable to save output resource for QVTo transformation"); }
         */

        /*
         * // save the modified model SavePartitionToDiskJob savePartitionJob = new
         * SavePartitionToDiskJob( LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
         * savePartitionJob.setBlackboard(this.getBlackboard()); savePartitionJob.execute(monitor);
         * 
         * // save the modified model SavePartitionToDiskJob savePartitionJob2 = new
         * SavePartitionToDiskJob( LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID);
         * savePartitionJob2.setBlackboard(this.getBlackboard());
         * savePartitionJob2.execute(monitor);
         */
    }

}
