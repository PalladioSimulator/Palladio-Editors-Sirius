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

public class StoreCompletedModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    private final static String MODEL_GEN_FOLDER_NAME = "model-gen";
    private final static String AT_COPY_PARTITION = "AT_COPY";
    private final static String[] PARTITION_IDs = new String[] {
            LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID,
            LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID };
    private final static AbstractPCMWorkflowRunConfiguration PLUGIN_CONFIGURATION = new SimuComWorkflowConfiguration(
            Collections.<String, Object> emptyMap());
    static {
        PLUGIN_CONFIGURATION.setDeleteTemporaryDataAfterAnalysis(false);
        PLUGIN_CONFIGURATION.setOverwriteWithoutAsking(true);
    }

    final ATExtensionJobConfiguration configuration;

    public StoreCompletedModelsJob(final ATExtensionJobConfiguration configuration) {
        this.configuration = configuration;

        PLUGIN_CONFIGURATION.setStoragePluginID(configuration.getModelStorageLocation());
        this.add(new CreatePluginProjectJob(PLUGIN_CONFIGURATION));
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        final URI storageURI = URI.createPlatformResourceURI(configuration.getModelStorageLocation(), false);
        for (final String partitionID : PARTITION_IDs) {
            final ResourceSetPartition copy = copyPartition(this.getBlackboard().getPartition(partitionID), storageURI);
            this.getBlackboard().addPartition(AT_COPY_PARTITION, copy);
            storePartition(monitor, AT_COPY_PARTITION);
        }
    }

    /**
     * @param storageURI
     * @return a copy of the given partition
     */
    private ResourceSetPartition copyPartition(final ResourceSetPartition partition, final URI storageURI) {
        final ResourceSetPartition partitionCopy = new ResourceSetPartition();
        final List<EObject> elementsCopy = EMFCopyHelper.deepCopyToEObjectList(partition.getResourceSet());

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
