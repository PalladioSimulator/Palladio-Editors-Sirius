package org.palladiosimulator.editors.commons.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * Executes {@link org.eclipse.emf.edit.ui.action.LoadResourceAction}. 
 * TODO remove this and find a better way to call a {@link org.eclipse.jface.action.Action}
 * 
 * @author max
 * @see  org.eclipse.emf.edit.ui.action.LoadResourceAction
 *
 */
public class LoadResourceAction implements IExternalJavaAction {

	private static final String SELF_KEY = "self";

	@Override
	public void execute(Collection<? extends EObject> selections,
			Map<String, Object> parameters) {
        final Object parameter = parameters.get(SELF_KEY);
        if (parameter == null || !(parameter instanceof EObject)) {
        	return;
        }
		final EditingDomain domain = SessionManager.INSTANCE.getSession((EObject) parameter).getTransactionalEditingDomain();
		new org.eclipse.emf.edit.ui.action.LoadResourceAction(domain).run();
	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
