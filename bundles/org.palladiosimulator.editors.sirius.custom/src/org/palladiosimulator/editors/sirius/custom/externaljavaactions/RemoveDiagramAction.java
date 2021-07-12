package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;

public class RemoveDiagramAction implements IExternalJavaAction {

    @Override
    public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
        EObject element = (EObject) parameters.get("element");
        String viewpointName = (String) parameters.get("Viewpoint");

        Session session = SessionManager.INSTANCE.getSession(element);
        Set<DRepresentationDescriptor> toDelete = session.getOwnedViews()
            .stream()
            .filter(v -> viewpointName.equals(v.getViewpoint()
                .getName()))
            .map(DView::getOwnedRepresentationDescriptors)
            .flatMap(Collection::stream)
            .filter(d -> d.getTarget() == element)
            .collect(Collectors.toSet());
        toDelete.forEach(EcoreUtil::delete);
    }

    @Override
    public boolean canExecute(Collection<? extends EObject> selections) {
        return true;
    }

}
