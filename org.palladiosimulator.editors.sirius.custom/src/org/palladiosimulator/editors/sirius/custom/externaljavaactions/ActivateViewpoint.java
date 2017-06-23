package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

public class ActivateViewpoint implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject element = (EObject) parameters.get("element");
		Session session = SessionManager.INSTANCE.getSession(element);

		String viewpointName = (String) parameters.get("Viewpoint");
		
		List<String> selectedViewpoints = new ArrayList<String>();
		selectedViewpoints.add(viewpointName);
		SiriusCustomUtil.selectViewpoints(session, selectedViewpoints, true, new NullProgressMonitor());

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
