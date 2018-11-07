package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompleteComponentType;
import org.palladiosimulator.pcm.repository.ProvidesComponentType;

public class ReconnectSourceOfConformsProvidesRelation implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {

		CompleteComponentType newSource = (CompleteComponentType) parameters.get("target");
		CompleteComponentType oldSource = (CompleteComponentType) parameters.get("source");
		
		DNodeContainer otherEnd = (DNodeContainer) parameters.get("otherEnd");
		ProvidesComponentType target = (ProvidesComponentType) otherEnd.getTarget();
		
		newSource.getParentProvidesComponentTypes().add(target);
		oldSource.getParentProvidesComponentTypes().remove(target);
			
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
