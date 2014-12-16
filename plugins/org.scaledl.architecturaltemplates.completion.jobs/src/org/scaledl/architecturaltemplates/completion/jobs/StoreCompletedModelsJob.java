package org.scaledl.architecturaltemplates.completion.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.simulizar.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import org.scaledl.architecturaltemplates.completion.config.ATConfiguration;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.SavePartitionToDiskJob;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

public class StoreCompletedModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    private static final String MODEL_GEN_FOLDER_NAME = "model-gen";
    private static final String AT_COPY_PARTITION = "AT_COPY";
    final static String[] PARTITION_IDs = new String[] { LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID,
            LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID };

    public StoreCompletedModelsJob(final ATConfiguration configuration) {
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.myBlackboard
                .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);

        de.uka.ipd.sdq.pcm.system.System system = null;
        try {
            system = pcmRepositoryPartition.getSystem();
        } catch (final IndexOutOfBoundsException e) {
        }
        final URI systemRoot = system.eResource().getURI().trimFragment().trimSegments(1);

        for (final String partitionID : PARTITION_IDs) {
            final ResourceSetPartition copy = copyPartition(this.getBlackboard().getPartition(partitionID), systemRoot);
            this.getBlackboard().addPartition(AT_COPY_PARTITION, copy);
            storePartition(monitor, AT_COPY_PARTITION);
        }
    }

    /**
     * @param systemRoot
     * @return a copy of the given partition
     */
    private ResourceSetPartition copyPartition(final ResourceSetPartition partition, final URI systemRoot) {
        final ResourceSetPartition partitionCopy = new ResourceSetPartition();
        final List<EObject> elementsCopy = EMFCopyHelper.deepCopyToEObjectList(partition.getResourceSet());

        int elementCounter = 0;
        for (final Resource r : partition.getResourceSet().getResources()) {
            final URI targetURI = systemRoot.appendSegment(MODEL_GEN_FOLDER_NAME).appendSegment(
                    r.getURI().lastSegment());
            final Resource resourceCopy = partitionCopy.getResourceSet().createResource(targetURI);

            for (int i = 0; i <= r.getContents().size(); i++) {
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
