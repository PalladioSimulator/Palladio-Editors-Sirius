package org.palladiosimulator.editors.sirius.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CompleteComponentType;
import org.palladiosimulator.pcm.repository.ImplementationComponentType;
public class ReconnectSourceOfConformsCompleteRelation implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		ImplementationComponentType newSource = (ImplementationComponentType) parameters.get("target");
		ImplementationComponentType oldSource = (ImplementationComponentType) parameters.get("source");
		
		DNodeContainer otherEnd = (DNodeContainer) parameters.get("otherEnd");
		CompleteComponentType target = (CompleteComponentType) otherEnd.getTarget();
		
		newSource.getParentCompleteComponentTypes().add(target);
		oldSource.getParentCompleteComponentTypes().remove(target);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
