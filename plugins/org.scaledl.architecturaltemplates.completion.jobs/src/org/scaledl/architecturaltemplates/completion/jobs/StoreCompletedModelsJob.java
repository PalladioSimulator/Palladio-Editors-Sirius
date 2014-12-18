package org.scaledl.architecturaltemplates.completion.jobs;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.simulizar.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.SavePartitionToDiskJob;
import de.uka.ipd.sdq.workflow.pcm.configurations.AbstractPCMWorkflowRunConfiguration;
import de.uka.ipd.sdq.workflow.pcm.jobs.CreatePluginProjectJob;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * Copies all resources of the partitions defined in the PARTITION_IDS constant to a given model
 * storage location. The latter is a property of this jobs' configuration.
 * 
 * @author Sebastian Lehrig
 */
public class StoreCompletedModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    private final static String MODEL_GEN_FOLDER_NAME = "model-gen";
    private final static String AT_COPY_PARTITION = "AT_COPY";
    private final static String[] PARTITION_IDS = new String[] {
            LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID,
            LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID };
    private final static AbstractPCMWorkflowRunConfiguration PLUGIN_CONFIGURATION = new SimuComWorkflowConfiguration(
            Collections.<String, Object> emptyMap());

    static {
        PLUGIN_CONFIGURATION.setDeleteTemporaryDataAfterAnalysis(false);
        PLUGIN_CONFIGURATION.setOverwriteWithoutAsking(true);
    }

    private final ATExtensionJobConfiguration configuration;

    /**
     * Default constructor.
     * 
     * @param configuration
     *            the configuration object, including the storage location for copied resources
     */
    public StoreCompletedModelsJob(final ATExtensionJobConfiguration configuration) {
        this.configuration = configuration;

        PLUGIN_CONFIGURATION.setStoragePluginID(configuration.getModelStorageLocation());
        this.add(new CreatePluginProjectJob(PLUGIN_CONFIGURATION));
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        final URI storageURI = URI.createPlatformResourceURI(configuration.getModelStorageLocation(), false);
        for (final String partitionID : PARTITION_IDS) {
            final ResourceSetPartition copy = copyPartition(this.getBlackboard().getPartition(partitionID), storageURI);
            this.getBlackboard().addPartition(AT_COPY_PARTITION, copy);
            storePartition(monitor, AT_COPY_PARTITION);
        }
    }

    /**
     * Copies all resources of a given partition to the given storage URI. Ensures that all model
     * links point to corresponding copies.
     * 
     * The copy-traversal sadly removes the resource information of EObjects, most important the
     * resource URI. However, we want to use the last segment of the resource URI to determine the
     * filename of the resource to be stored. The current implementation therefore re-traverses the
     * original resources to assign the copied ones this filename.
     * 
     * TODO The above described solution is not nice. We may provide an improved copy method.
     * 
     * @param partition
     *            the partition from with resources shall be copied
     * @param storageURI
     *            the URI where the copied resources shall be stored
     * @return a copy of the given partition
     */
    private ResourceSetPartition copyPartition(final ResourceSetPartition partition, final URI storageURI) {
        final ResourceSetPartition partitionCopy = new ResourceSetPartition();
        final List<EObject> elementsCopy = EMFCopyHelper.deepCopyToEObjectList(partition.getResourceSet());

        // re-traverse
        int elementCounter = 0;
        for (final Resource r : partition.getResourceSet().getResources()) {
            final URI targetURI = storageURI.appendSegment(MODEL_GEN_FOLDER_NAME).appendSegment(
                    r.getURI().lastSegment());
            final Resource resourceCopy = partitionCopy.getResourceSet().createResource(targetURI);

            for (int i = 0; i < r.getContents().size(); i++) {
                resourceCopy.getContents().add(elementsCopy.get(elementCounter));
                elementCounter++;
            }
        }

        return partitionCopy;
    }

    private void storePartition(final IProgressMonitor monitor, final String partitionID) throws JobFailedException,
            UserCanceledException {
        final SavePartitionToDiskJob savePartitionJob = new SavePartitionToDiskJob(partitionID);
        savePartitionJob.setBlackboard(this.getBlackboard());
        savePartitionJob.execute(monitor);
    }

}
