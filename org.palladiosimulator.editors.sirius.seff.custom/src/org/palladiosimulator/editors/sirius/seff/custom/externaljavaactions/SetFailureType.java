package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.reliability.InternalFailureOccurrenceDescription;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;
import org.palladiosimulator.pcm.repository.Repository;

public class SetFailureType implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		InternalFailureOccurrenceDescription element = (InternalFailureOccurrenceDescription) parameters.get("instance");
		EObjectQuery query = new EObjectQuery(element);
		Collection<Resource> resources = query.getSession().getSemanticResources();
		
		boolean found = false;
		Resource resource = null;
		for (Resource r : resources) {
			if(r.getURI().equals(URI.createURI("pathmap://PCM_MODELS/FailureTypes.repository"))) {
				found = true;
				resource = r;
				break;
			}
		}
		
		if (found) {
			Repository rep = (Repository) resource.getContents().iterator().next();
			for (EObject o : rep.eContents()) {
				FailureType failureType = (FailureType) o;
				if (failureType.getEntityName().equals("SoftwareInducedFailure")) {
					element.setSoftwareInducedFailureType__InternalFailureOccurrenceDescription(((SoftwareInducedFailureType) failureType));
					break;
				}
			}
		}
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {	
		return true;
	}

}
