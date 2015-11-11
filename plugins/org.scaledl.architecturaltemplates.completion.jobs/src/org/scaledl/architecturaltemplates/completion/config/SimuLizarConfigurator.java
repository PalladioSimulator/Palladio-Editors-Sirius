package org.scaledl.architecturaltemplates.completion.config;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.modelversioning.emfprofile.Stereotype;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.simulizar.launcher.IConfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.scaledl.architecturaltemplates.completion.constants.ATPartitionConstants;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Role;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class SimuLizarConfigurator implements IConfigurator {

    public SimuLizarConfigurator() {
    }

    @Override
    public void configure(final SimuLizarWorkflowConfiguration configuration, final MDSDBlackboard blackboard) {
        final AT architecturalTemplate = getATFromSystem(blackboard);
        if (architecturalTemplate == null) {
            return;
        }

        if (architecturalTemplate.getReconfigurationRuleFolder() == null) {
            return;
        }

        configuration
                .setReconfigurationRulesFolder(architecturalTemplate.getReconfigurationRuleFolder().getFolderURI());
    }

    /**
     * Receives the architectural template attached to a system. Such an attachment is realized via
     * a stereotype with "roleURI" as a tagged value. The tagged value references the concrete AT
     * role the system acts. If no such tagged value can be found, <code>null</code> is returned.
     * 
     * @return the architectural template applied to this system; <code>null</code> if no such
     *         template can be found.
     */
    private AT getATFromSystem(final MDSDBlackboard blackboard) {
        final PCMResourceSetPartition pcmRepositoryPartition = (PCMResourceSetPartition) blackboard
                .getPartition(ATPartitionConstants.Partition.PCM.getPartitionId());

        org.palladiosimulator.pcm.system.System system = null;
        try {
            system = pcmRepositoryPartition.getSystem();
        } catch (final IndexOutOfBoundsException e) {
        }

        if (system != null) {
            for (final Stereotype stereotype : StereotypeAPI.getAppliedStereotypes(system)) {
                final EStructuralFeature roleURI = stereotype.getTaggedValue("roleURI");
                if (roleURI != null) {
                    final EObject eObject = EMFLoadHelper.loadAndResolveEObject(roleURI.getDefaultValueLiteral());
                    final Role atRole = (Role) eObject;
                    return atRole.getAT();
                }
            }
        }

        return null;
    }
}
