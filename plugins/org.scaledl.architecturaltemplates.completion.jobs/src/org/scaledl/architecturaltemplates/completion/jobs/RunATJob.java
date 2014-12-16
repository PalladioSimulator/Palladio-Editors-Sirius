package org.scaledl.architecturaltemplates.completion.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Conducts a complete AT Job:
 * 
 * 1) Load source models (uncompleted PCM models; stereotypes may reference AT models) into the
 * blackboard.
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
public class RunATJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {

    /**
     * Default constructor.
     */
    public RunATJob() {
        super();
    }

    /**
     * Cannot add jobs earlier as extension jobs get configured after instantiation.
     */
    @Override
    public void setJobConfiguration(AbstractExtensionJobConfiguration jobConfiguration) {
        super.setJobConfiguration(jobConfiguration);

        final ATExtensionJobConfiguration configuration = (ATExtensionJobConfiguration) jobConfiguration;
        this.addJob(new ValidateModelsJob(configuration));
        this.addJob(new RunATCompletionJob(configuration));
        this.addJob(new StoreCompletedModelsJob(configuration));
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        // TODO Auto-generated method stub
        super.execute(monitor);
    }

}
