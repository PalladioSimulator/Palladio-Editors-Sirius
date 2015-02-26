package org.scaledl.architecturaltemplates.completion.jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.impl.PcmmeasuringpointFactoryImpl;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointResourceImpl;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.util.ServicelevelObjectiveResourceImpl;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryFactoryImpl;
import org.palladiosimulator.simulizar.monitorrepository.util.MonitorrepositoryResourceImpl;
import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;
import org.scaledl.architecturaltemplates.completion.constants.ATPartitionConstants;
import org.scaledl.architecturaltemplates.completion.constants.ATPartitionConstants.Partition;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.CompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.QVTOCompletion;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.util.TypeSwitch;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationFactory;
import de.uka.ipd.sdq.pcm.allocation.util.AllocationResourceImpl;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ModelLocation;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.mdsd.emf.qvto.QVTOTransformationJob;
import de.uka.ipd.sdq.workflow.mdsd.emf.qvto.QVTOTransformationJobConfiguration;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Recursively applies AT completions until no AT completion is left anymore. Therefore, AT
 * completions have to remove stereotype applications that reference these. Results are directly
 * stored within the blackboard.
 * 
 * @author Sebastian Lehrig
 */
public class RunATCompletionJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    /** Folder with traces as created by the QVT-O engine */
    private static final String TRACESFOLDER = "traces";

    public RunATCompletionJob(final ATExtensionJobConfiguration configuration) {
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);

        final AT architecturalTemplate = this.getATFromSystem();

        if (architecturalTemplate != null) {
            // get QVT-O completion
            final QVTOCompletion completion;
            if (architecturalTemplate.getCompletion() instanceof QVTOCompletion) {
                completion = (QVTOCompletion) architecturalTemplate.getCompletion();
            } else {
                throw new RuntimeException("This jobs assumes a QVTOCompletion");
            }

            // Root folder of the AT
            final URI rootATUri = architecturalTemplate.eResource().getURI().trimFragment().trimSegments(1);

            // Initialize ModelLocation object for QVTo's in- and out-parameters
            final List<ModelLocation> modelLocations = new ArrayList<ModelLocation>(completion.getParameters().size());
            for (final CompletionParameter parameter : completion.getParameters()) {
                modelLocations.add(getModelLocation(architecturalTemplate, rootATUri, parameter));
            }

            // build file paths for trace and transformation files
            final URI traceFileURI = URI.createURI(TRACESFOLDER);
            final URI scriptFileURI = rootATUri.appendSegment("completions").appendSegment(completion.getQvtoFileURI());

            // configure the QVTO Job
            final QVTOTransformationJobConfiguration qvtoConfig = new QVTOTransformationJobConfiguration();
            qvtoConfig.setInoutModels(modelLocations.toArray(new ModelLocation[completion.getParameters().size()]));
            qvtoConfig.setTraceFileURI(traceFileURI);
            qvtoConfig.setScriptFileURI(scriptFileURI);
            qvtoConfig.setOptions(new HashMap<String, Object>());

            // create and add the qvto job
            final QVTOTransformationJob job = new QVTOTransformationJob(qvtoConfig);
            job.setBlackboard(this.getBlackboard());

            // execute transformation job
            try {
                job.execute(new NullProgressMonitor());
            } catch (JobFailedException e) {
                if (logger.isEnabledFor(Level.ERROR)) {
                    logger.error("Failed to perform Architectural Template completion: " + e.getMessage());
                }
                if (logger.isEnabledFor(Level.INFO)) {
                    logger.info("Trying to continue Architectural Template completion even though an internal failure occured");
                }
            }
        }
        // If no AT was found, let's hope the PCM model is complete...
    }

    private ModelLocation getModelLocation(final AT architecturalTemplate, final URI rootATUri,
            final CompletionParameter parameter) {
        final ResourceSetPartition pcmPartition = this.getBlackboard().getPartition(
                ATPartitionConstants.Partition.PCM.getPartitionId());
        final ResourceSetPartition pmsPartition = this.getBlackboard().getPartition(
                ATPartitionConstants.Partition.PMS.getPartitionId());

        final URI templateFolderURI = rootATUri.appendSegment("templates");
        final URI systemModelFolderURI = getSystemModelFolderURI();

        return new TypeSwitch<ModelLocation>() {

            /**
             * Load models from template
             */
            @Override
            public ModelLocation casePCMTemplateCompletionParameter(
                    org.scaledl.architecturaltemplates.type.PCMTemplateCompletionParameter object) {

                final String[] segments = URI.createURI(object.getTemplateFileURI()).segments();
                final URI templateURI = templateFolderURI.appendSegments(segments);
                final String lastSegment = templateURI.lastSegment();

                for (final Partition partition : ATPartitionConstants.Partition.values()) {
                    for (final String fileName : partition.getFileNames()) {
                        if (lastSegment.endsWith(fileName)) {
                            final ResourceSetPartition resourceSetPartition = getBlackboard().getPartition(
                                    partition.getPartitionId());
                            resourceSetPartition.loadModel(templateURI);
                            resourceSetPartition.resolveAllProxies();
                            return new ModelLocation(partition.getPartitionId(), templateURI);
                        }
                    }
                }

                throw new IllegalArgumentException("PCM Template Completion Parameter \"" + templateURI
                        + "\" not found");
            };

            /**
             * Find the models in blackboard
             */
            @Override
            public ModelLocation casePCMBlackboardCompletionParameter(PCMBlackboardCompletionParameter object) {
                final String parameterFileExtension = object.getFileExtension().getLiteral();

                for (final Partition partition : ATPartitionConstants.Partition.values()) {
                    final ResourceSetPartition resourceSetPartition = getBlackboard().getPartition(
                            partition.getPartitionId());

                    for (final Resource r : resourceSetPartition.getResourceSet().getResources()) {
                        final URI modelURI = r.getURI();
                        final String fileExtension = modelURI.fileExtension();

                        if (fileExtension.equals(parameterFileExtension)
                                && !modelURI.toString().startsWith("pathmap://")
                                && !modelURI.toString().contains("PrimitiveTypes.repository")) {
                            return new ModelLocation(partition.getPartitionId(), modelURI);
                        }
                    }
                }

                return null;
            };

            /**
             * Create new output model for QVTo transformation
             */
            @Override
            public ModelLocation casePCMOutputCompletionParameter(PCMOutputCompletionParameter object) {
                final String parameterFileExtension = object.getFileExtension().getLiteral();
                final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) pcmPartition;
                final ResourceSet resourceSet = new ResourceSetImpl();
                final Resource outResource = resourceSet.createResource(URI.createURI(systemModelFolderURI
                        + "/GeneratedOut" + parameterFileExtension + "." + parameterFileExtension));
                final URI uri = outResource.getURI();

                if (outResource instanceof AllocationResourceImpl) {
                    ResourceEnvironment resourceEnvironment = null;
                    try {
                        resourceEnvironment = pcmRepositoryPartition.getResourceEnvironment();
                        Allocation allocation = AllocationFactory.eINSTANCE.createAllocation();
                        allocation.setTargetResourceEnvironment_Allocation(resourceEnvironment);
                        outResource.getContents().add(allocation);
                    } catch (final IndexOutOfBoundsException e) {
                    }
                }

                else if (outResource instanceof PcmmeasuringpointResourceImpl) {
                    UsageModel usageModel = null;
                    try {
                        usageModel = pcmRepositoryPartition.getUsageModel();
                        EList<UsageScenario> usageScenarios = usageModel.getUsageScenario_UsageModel();
                        UsageScenarioMeasuringPoint usageScenarioMeasuringPoint = PcmmeasuringpointFactoryImpl.eINSTANCE
                                .createUsageScenarioMeasuringPoint();
                        usageScenarioMeasuringPoint.setUsageScenario(usageScenarios.get(0));
                        outResource.getContents().add(usageScenarioMeasuringPoint);
                    } catch (final IndexOutOfBoundsException e) {
                    }
                } else if (outResource instanceof ServicelevelObjectiveResourceImpl) {
                    ServicelevelObjectiveFactory sloFactory = ServicelevelObjectiveFactory.eINSTANCE;
                    ServiceLevelObjectiveRepository sloRepo = sloFactory.createServiceLevelObjectiveRepository();
                    outResource.getContents().add(sloRepo);
                } else if (outResource instanceof MonitorrepositoryResourceImpl) {
                    final MonitorrepositoryFactoryImpl pmsFactory = (MonitorrepositoryFactoryImpl) MonitorrepositoryFactoryImpl
                            .init();
                    MonitorRepository monitorRepository = pmsFactory.createMonitorRepository();
                    outResource.getContents().add(monitorRepository);
                }
                if (uri.lastSegment().endsWith("pms")) {
                    pmsPartition.setContents(uri, outResource.getContents());
                    pmsPartition.resolveAllProxies();
                    return new ModelLocation(ATPartitionConstants.Partition.PMS.getPartitionId(), uri);
                } else {
                    pcmPartition.setContents(uri, outResource.getContents());
                    pcmPartition.resolveAllProxies();
                    return new ModelLocation(ATPartitionConstants.Partition.PCM.getPartitionId(), uri);
                }
            };

        }.doSwitch(parameter);
    }

    /**
     * Receives the architectural template attached to a system. Such an attachment is realized via
     * a stereotype with "roleURI" as a tagged value. The tagged value references the concrete AT
     * role the system acts. If no such tagged value can be found, <code>null</code> is returned.
     * 
     * @return the architectural template applied to this system; <code>null</code> if no such
     *         template can be found.
     */
    private AT getATFromSystem() {
        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.myBlackboard
                .getPartition(ATPartitionConstants.Partition.PCM.getPartitionId());

        de.uka.ipd.sdq.pcm.system.System system = null;
        try {
            system = pcmRepositoryPartition.getSystem();
        } catch (final IndexOutOfBoundsException e) {
        }

        if (system != null) {
            for (final Stereotype stereotype : system.getAppliedStereotypes()) {
                final EStructuralFeature roleURI = stereotype.getTaggedValue("roleURI");
                if (roleURI != null) {
                    final EObject eObject = EMFLoadHelper.loadModel(roleURI.getDefaultValueLiteral());
                    final Role atRole = (Role) eObject;
                    return atRole.getAT();
                }
            }
        }

        return null;
    }

    private URI getSystemModelFolderURI() {
        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.myBlackboard
                .getPartition(ATPartitionConstants.Partition.PCM.getPartitionId());

        de.uka.ipd.sdq.pcm.system.System system = null;
        try {
            system = pcmRepositoryPartition.getSystem();
        } catch (final IndexOutOfBoundsException e) {
        }

        return system.eResource().getURI().trimFragment().trimSegments(1);
    }
}
