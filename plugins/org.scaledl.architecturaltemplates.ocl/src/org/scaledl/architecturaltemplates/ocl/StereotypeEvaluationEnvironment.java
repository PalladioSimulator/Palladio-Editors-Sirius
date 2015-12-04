package org.scaledl.architecturaltemplates.ocl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.EvaluationEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.pcm.core.entity.Entity;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class StereotypeEvaluationEnvironment extends EcoreEvaluationEnvironment {

    private final MDSDBlackboard blackboard;

    public StereotypeEvaluationEnvironment(final EcoreEnvironmentFactory factory, final MDSDBlackboard blackboard) {
        super(factory);
        this.blackboard = blackboard;
    }

    StereotypeEvaluationEnvironment(
            final EvaluationEnvironment<EClassifier, EOperation, EStructuralFeature, EClass, EObject> parent,
            final MDSDBlackboard blackboard) {
        super(parent);
        this.blackboard = blackboard;
    }

    @Override
    public Object callOperation(final EOperation operation, final int opcode, final Object source,
            final Object[] args) {
        if (operation.getEAnnotation("StereotypeEnvironment") == null) {
            // not our custom stereotype test operation
            return super.callOperation(operation, opcode, source, args);
        }

        if ("hasAppliedStereotype".equals(operation.getName())) {
            return StereotypeAPI.isStereotypeApplied((Entity) source, (String) args[0]);
        }
        if ("getDoubleTaggedValue".equals(operation.getName())) {
            return StereotypeAPI.getTaggedValue((Entity) source, (String) args[0], (String) args[1]);
        }

        if ("parseDouble".equals(operation.getName())) {
            return Double.parseDouble((String) source);
        }
        if ("getAllocation".equals(operation.getName())) {
            final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.blackboard
                    .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
            org.palladiosimulator.pcm.allocation.Allocation allocation = null;
            try {
                allocation = pcmRepositoryPartition.getAllocation();
            } catch (final IndexOutOfBoundsException e) {
            }
            if (allocation == null) {
                throw new RuntimeException("Did not find Allocation.");
            }
            return allocation;
        }
        if ("getResourceEnvironment".equals(operation.getName())) {
            final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) this.blackboard
                    .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
            org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment resEnv = null;
            try {
                resEnv = pcmRepositoryPartition.getResourceEnvironment();
            } catch (final IndexOutOfBoundsException e) {
            }
            if (resEnv == null) {
                throw new RuntimeException("Did not find Allocation.");
            }
            return resEnv;
        }
        throw new UnsupportedOperationException(); // unknown operation
    }
}
