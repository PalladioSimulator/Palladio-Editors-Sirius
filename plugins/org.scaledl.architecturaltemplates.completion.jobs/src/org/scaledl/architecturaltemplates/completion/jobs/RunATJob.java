package org.scaledl.architecturaltemplates.completion.jobs;

import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Conducts a complete AT Job:
 * 
 * 1) Validate blackboard models according to AT constraints.
 * 
 * 2) Run the AT completion that transforms the source models into models enriched with information
 * derivable from the given AT. Results are stored on the blackboard.
 * 
 * 3) For debug and if enabled in the ATExtensionJobConfiguration: Store the resulting models into a
 * new, temporary Palladio project.
 * 
 * @author Sebastian Lehrig
 */
public class RunATJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {

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

}
