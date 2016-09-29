package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

/**
 * External java action that opens representations of the selected element if
 * they already exist or creates a new representation and opens it if none
 * exists.
 * Three parameters must be specified :
 * 	Viewpoint: the name of the viewpoint to be selected
 * 	RepresentationDescription: the name of the specific RepresentationDescription in the chosen viewpoint
 * 	element : the element on which the double click was triggered
 * 
 * @author Amine Kechaou
 *
 */
public class OpenRepresentation implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		EObject semantic = (EObject) parameters.get("element");
		Session session = SessionManager.INSTANCE.getSession(semantic);

		String viewpointName = (String) parameters.get("Viewpoint");
		String representationDescriptionName = (String) parameters.get("RepresentationDescription");

		Viewpoint viewpoint = SiriusCustomUtil.findViewpoint(viewpointName);
		RepresentationDescription description = SiriusCustomUtil.findDescription(viewpoint,
				representationDescriptionName);
		
		// Select viewpoint
		HashSet<Viewpoint> selectedViewpoints = new HashSet<Viewpoint>();
		selectedViewpoints.add(viewpoint);
		SiriusCustomUtil.selectViewpoints(session, selectedViewpoints, false, new NullProgressMonitor());
		
		// Find representations
		Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(semantic, session);
		// create a new representation if none exists and open it
		if (representations.isEmpty()) { 
			String representationName = "new " + ((description.getLabel() == null) ? description.getName() : description.getLabel());
			DRepresentation representation = SiriusCustomUtil.createRepresentation(session, representationName,
					description, semantic, new NullProgressMonitor());
			DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
		} else { // open available representations
			for (DRepresentation representation : representations) {
				DialectUIManager.INSTANCE.openEditor(session, representation, new NullProgressMonitor());
			}
		}

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
