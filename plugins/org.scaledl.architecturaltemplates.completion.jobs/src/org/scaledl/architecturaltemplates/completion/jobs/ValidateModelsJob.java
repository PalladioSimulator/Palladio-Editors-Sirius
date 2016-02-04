package org.scaledl.architecturaltemplates.completion.jobs;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
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
import org.scaledl.architecturaltemplates.ocl.StereotypeEnvironmentFactory;
import org.scaledl.architecturaltemplates.type.OCLConstraint;
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
        final List<ProfileApplication> profileApplications = this.getProfileApplications();
        for (final ProfileApplication profileApplication : profileApplications) {
            final EList<StereotypeApplication> stereotypeApplications = profileApplication.getStereotypeApplications();
            final OCL ocl = OCL.newInstance(new StereotypeEnvironmentFactory(this.myBlackboard));
            // create an OCL helper object
            final OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> helper = ocl.createOCLHelper();

            for (final StereotypeApplication stereotypeApplication : stereotypeApplications) {
                // set the OCL context classifier
                helper.setInstanceContext(stereotypeApplication);
                final EList<org.scaledl.architecturaltemplates.type.Constraint> constraints = this
                        .getConstraintsFromStereotypeApplication(stereotypeApplication);

                Constraint invariant = null;
                for (final org.scaledl.architecturaltemplates.type.Constraint constraint : constraints) {
                    if (constraint instanceof OCLConstraint) {
                        final OCLConstraint oclConstraint = (OCLConstraint) constraint;
                        try {
                            invariant = helper.createInvariant(oclConstraint.getConstraint());
                            final Query constraintEvaluation = ocl.createQuery(invariant);
                            if (!constraintEvaluation.check(stereotypeApplication)) {
                                this.logger.error("Constraint: " + oclConstraint.getEntityName() + " failed.");
                            } else {
                                this.logger.info("Constraint: " + oclConstraint.getEntityName() + " succeeded.");
                            }
                        } catch (final ParserException e) {
                            this.logger.error("Unable to parse expression " + oclConstraint.getEntityName() + ": "
                                    + e.getMessage());
                        }
                    }
                }
            }
        }
    }

    private List<ProfileApplication> getProfileApplications() {
        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.myBlackboard
                .getPartition(ATPartitionConstants.Partition.PCM.getPartitionId());
        org.palladiosimulator.pcm.system.System system = null;
        final List<ProfileApplication> profileApplications = new LinkedList<ProfileApplication>();
        org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment resourceEnvironment = null;
        try {
            system = pcmRepositoryPartition.getSystem();
            resourceEnvironment = pcmRepositoryPartition.getResourceEnvironment();
        } catch (final IndexOutOfBoundsException e) {
        }
        if (system != null & ProfileAPI.hasProfileApplication(system.eResource())) {
            profileApplications.add(ProfileAPI.getProfileApplication(system.eResource()));
        }
        if (resourceEnvironment != null & ProfileAPI.hasProfileApplication(resourceEnvironment.eResource())) {
            profileApplications.add(ProfileAPI.getProfileApplication(resourceEnvironment.eResource()));
        }
        return profileApplications;
    }

    private EList<org.scaledl.architecturaltemplates.type.Constraint> getConstraintsFromStereotypeApplication(
            final StereotypeApplication stereotypeApplication) {
        final Stereotype stereotype = stereotypeApplication.getStereotype();
        EList<org.scaledl.architecturaltemplates.type.Constraint> constraints = new BasicEList<org.scaledl.architecturaltemplates.type.Constraint>();
        final EStructuralFeature roleURI = stereotype.getTaggedValue("roleURI");
        if (roleURI != null) {
            final EObject eObject = EMFLoadHelper.loadAndResolveEObject(roleURI.getDefaultValueLiteral());
            final Role stereotypeRole = (Role) eObject;
            constraints = stereotypeRole.getConstraints();
            return stereotypeRole.getConstraints();
        }
        return constraints;
    }
}
