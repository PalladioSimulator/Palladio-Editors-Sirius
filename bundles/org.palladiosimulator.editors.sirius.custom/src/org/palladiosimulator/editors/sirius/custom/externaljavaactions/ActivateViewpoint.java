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
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

public class ActivateViewpoint implements IExternalJavaAction {

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get("element");
        Session session = SessionManager.INSTANCE.getSession(element);

        String viewpointName = (String) parameters.get("Viewpoint");

        List<String> selectedViewpoints = new ArrayList<String>();
        selectedViewpoints.add(viewpointName);

        if (parameters.containsKey("Diagram Name") && parameters.containsKey("RepresentationDescription")) {
            SiriusCustomUtil.selectViewpoints(session, selectedViewpoints, false, new NullProgressMonitor());
            String representationDescriptionName = (String) parameters.get("RepresentationDescription");
            String diagramName = (String) parameters.get("Diagram Name");

            Viewpoint viewpoint = SiriusCustomUtil.getSelectedViewpointByName(session, viewpointName);
            RepresentationDescription description = SiriusCustomUtil.findDescription(viewpoint,
                    representationDescriptionName);
            String representationName = null;
            if (diagramName != null && !diagramName.isEmpty()) {
                representationName = diagramName;
            } else {
                representationName = ((description.getLabel() == null) ? description.getName()
                        : description.getLabel());
            }
            SiriusCustomUtil.createRepresentation(session, representationName, description, element,
                    new NullProgressMonitor());

        } else {
            SiriusCustomUtil.selectViewpoints(session, selectedViewpoints, true, new NullProgressMonitor());
        }

    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}
