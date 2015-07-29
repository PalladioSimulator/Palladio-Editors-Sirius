package org.scaledl.architecturaltemplates.completion.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.helper.OCLHelper;
import org.modelversioning.emfprofile.Stereotype;
import org.modelversioning.emfprofileapplication.ProfileApplication;
import org.modelversioning.emfprofileapplication.StereotypeApplication;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.mdsdprofiles.api.ProfileAPI;
import org.scaledl.architecturaltemplates.completion.config.ATExtensionJobConfiguration;
import org.scaledl.architecturaltemplates.completion.constants.ATPartitionConstants;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Validates blackboard models according to the given AT constraints.
 *
 * @author Sebastian Lehrig, Daria Giacinto
 */
public class ValidateModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

    public ValidateModelsJob(final ATExtensionJobConfiguration configuration) {
    }

    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        super.execute(monitor);
        this.logger.info("Validating AT Constraints.");
        final ProfileApplication systemProfileApplication = this.getProfileApplicationFromSystem();
        if (systemProfileApplication != null) {
            final EList<StereotypeApplication> stereotypeApplications = systemProfileApplication
                    .getStereotypeApplications();
            final OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);

            final OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> helper = ocl.createOCLHelper();
            for (final StereotypeApplication stereotypeApplication : stereotypeApplications) {

                final EList<org.scaledl.architecturaltemplates.type.Constraint> constraints = this
                        .getConstraintsFromStereotypeApplication(stereotypeApplication);
                helper.setInstanceContext(stereotypeApplication);
                Constraint invariant = null;

                for (final org.scaledl.architecturaltemplates.type.Constraint constraint : constraints) {
                    try {
                        invariant = helper.createInvariant(constraint.getEntityName());
                        final Query constraintEvaluation = ocl.createQuery(invariant);
                        if (!constraintEvaluation.check(stereotypeApplication)) {
                            this.logger.error("Constraint: " + invariant.toString() + " failed.");
                        }
                    } catch (final ParserException e) {
                        this.logger.error("Unable to parse expression \"" + e.getMessage());
                    }
                }
            }
        }
    }

    private ProfileApplication getProfileApplicationFromSystem() {
        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.myBlackboard
                .getPartition(ATPartitionConstants.Partition.PCM.getPartitionId());
        org.palladiosimulator.pcm.system.System system = null;
        try {
            system = pcmRepositoryPartition.getSystem();
        } catch (final IndexOutOfBoundsException e) {
        }
        if (system != null & ProfileAPI.hasProfileApplication(system.eResource())) {

            return ProfileAPI.getProfileApplication(system.eResource());
        }
        return null;
    }

    private EList<org.scaledl.architecturaltemplates.type.Constraint> getConstraintsFromStereotypeApplication(
            final StereotypeApplication stereotypeApplication) {
        final Stereotype stereotype = stereotypeApplication.getStereotype();
        final EStructuralFeature roleURI = stereotype.getTaggedValue("roleURI");
        if (roleURI != null) {
            final EObject eObject = EMFLoadHelper.loadAndResolveEObject(roleURI.getDefaultValueLiteral());
            final Role stereotypeRole = (Role) eObject;
            return stereotypeRole.getConstraints();
        }
        return null;
    }
}
