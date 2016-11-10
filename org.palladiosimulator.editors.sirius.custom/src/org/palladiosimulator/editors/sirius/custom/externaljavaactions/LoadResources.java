package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

/**
 * This External Java Action loads resources from URIs passed as parameters
 * @author Amine Kechaou
 *
 */
public class LoadResources implements IExternalJavaAction {


	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject element = selections.iterator().next();
		Session session = SessionManager.INSTANCE.getSession(element);
		for (Object p : parameters.values()) 
			session.addSemanticResource(URI.createURI(p.toString()), new NullProgressMonitor());

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
