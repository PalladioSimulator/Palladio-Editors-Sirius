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
 * TODO Implement
 * 
 * @author Sebastian Lehrig
 */
public class ValidateModelsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {
    public ValidateModelsJob(final ATExtensionJobConfiguration configuration) {
    }
    
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
    		super.execute(monitor);
        	logger.info("Validating AT Constraints.");
        	final ProfileApplication systemProfileApplication = this.getProfileApplicationFromSystem();
        	EList<StereotypeApplication> stereotypeApplications = systemProfileApplication.getStereotypeApplications();
        	//final org.palladiosimulator.pcm.system.System system = getSystemFromBlackboard();
        	OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
        
        	OCLHelper<EClassifier, EOperation, EStructuralFeature, Constraint> helper = ocl.createOCLHelper();
        	for(StereotypeApplication stereotypeApplication : stereotypeApplications){

	        	EList<org.scaledl.architecturaltemplates.type.Constraint> constraints = this.getConstraintsFromStereotypeApplication(stereotypeApplication);
	        	helper.setContext(stereotypeApplication.getExtension().getTarget());
	        	//helper.setInstanceContext(stereotypeApplication);
	        	 Constraint invariant = null;
	
	        	for(org.scaledl.architecturaltemplates.type.Constraint constraint : constraints){
	        		 try{
	             		invariant = helper.createInvariant(constraint.getEntityName());
	             		Query constraintEvaluation = ocl.createQuery(invariant);
	             		/*if(!constraintEvaluation.check(stereotypeApplication)){
	             			logger.error("Constraint: " + invariant.getSpecification().toString() +" failed on " + stereotypeApplication.toString());
			        	 }*/
	             		if(!constraintEvaluation.check(stereotypeApplication.getAppliedTo())){
		        			logger.error("Constraint: " + invariant.getSpecification().toString() +" failed on " + stereotypeApplication.getAppliedTo().toString());
		        		}
	             	 }catch(ParserException e){
	             		 logger.error("Unable to parse expression \"" + e.getMessage() + '"'+ " on context "+ stereotypeApplication.getAppliedTo());
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
        if (system != null) {
        	return ProfileAPI.getProfileApplication(system.eResource());
        	}
        return null;
    }
    
    private EList<org.scaledl.architecturaltemplates.type.Constraint> getConstraintsFromStereotypeApplication(StereotypeApplication stereotypeApplication){
    	Stereotype stereotype = stereotypeApplication.getStereotype();
    	   final EStructuralFeature roleURI = stereotype.getTaggedValue("roleURI");
           if (roleURI != null) {
               final EObject eObject = EMFLoadHelper.loadAndResolveEObject(roleURI.getDefaultValueLiteral());
               final Role stereotypeRole = (Role) eObject;
               return stereotypeRole.getConstraints();
           }
    	return null;
    }
}
