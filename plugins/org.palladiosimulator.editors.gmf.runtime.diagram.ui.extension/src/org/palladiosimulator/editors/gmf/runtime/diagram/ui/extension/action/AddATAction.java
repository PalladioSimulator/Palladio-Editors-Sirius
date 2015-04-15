package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.action;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * A Sirius action that is used to add an ArchitecturalTemplate Role to the AssemblyContext it is applied to.
 * It requires that the {@link System} the {@link AssemblyContext} is defined in has an ArchitecturalTemplate applied.
 * Executing the action will create an dialog asking the user to select an Role.
 * 
 * FIXME: use org.eclipse.sirius.tools.api.ui.IExternalJavaAction2
 * 
 * @author max
 *
 */
public class AddATAction implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		// TODO Auto-generated method stub
		return false;
	}

}
