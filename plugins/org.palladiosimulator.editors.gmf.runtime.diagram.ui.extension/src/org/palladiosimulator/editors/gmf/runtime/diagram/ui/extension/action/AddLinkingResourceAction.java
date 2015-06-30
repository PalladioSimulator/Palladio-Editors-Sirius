package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.editors.dialogs.resource.OpenLatencyDialog;

public class AddLinkingResourceAction implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		OpenLatencyDialog openLatencyDialog = new OpenLatencyDialog();
		//installEditPolicy <- openLatencyDialog ist das? 
		
		System.out.println("huhu");
	}

}
