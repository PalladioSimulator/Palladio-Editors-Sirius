package org.palladiosimulator.editors.sirius.custom.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Class containing some useful static methods to work with Sirius
 * @see {@link SiriusUtil}
 * @author Amine Kechaou
 *
 */
public class SiriusCustomUtil {
	
	public static URI getRepresentationsURI(IProject project) {
		return URI.createPlatformResourceURI(project.getFullPath().append("/representations." + SiriusUtil.SESSION_RESOURCE_EXTENSION).toOSString(), true);
	}
	
	public static void selectViewpoints(Session session, HashSet<Viewpoint> viewpoints, boolean createRepresentation, IProgressMonitor monitor) {
        final ViewpointSelectionCallback selectionCallback = new ViewpointSelectionCallback();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
        for (Viewpoint v : viewpoints) {
        	if (selectedViewpoints.contains(v))
        		viewpoints.remove(v);
        }
		@SuppressWarnings("restriction")
		final Command command = new ChangeViewpointSelectionCommand(session, selectionCallback, viewpoints, new HashSet<Viewpoint>(), createRepresentation, monitor);
        domain.getCommandStack().execute(command);
	}
	
	public static void selectViewpoints(Session session, List<String> viewpointNames, boolean createRepresentation, IProgressMonitor monitor) {
		final Set<Viewpoint> registry = ViewpointRegistry.getInstance().getViewpoints();
		HashSet<Viewpoint> viewpoints = new HashSet<Viewpoint>();
		for (Viewpoint viewpoint : registry) {
			if (viewpointNames.contains(viewpoint.getName())) {
				viewpoints.add(viewpoint);
			}
		}
		selectViewpoints(session, viewpoints, createRepresentation, monitor);
	}
	
	public static DRepresentation createRepresentation(Session session, String representationName, RepresentationDescription description, EObject semantic, IProgressMonitor monitor) {
		monitor.subTask("Creating representation");
		TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(
                session, description, semantic,
                representationName, new NullProgressMonitor());
        domain.getCommandStack().execute(createRepresentationCommand);
        return createRepresentationCommand.getCreatedRepresentation();
	}
	
	public static Viewpoint findViewpoint(String viewpointName) {
		Viewpoint viewpoint = null;
		for (Viewpoint v: ViewpointRegistry.getInstance().getViewpoints()) {
			if (v.getName().equals(viewpointName))
				viewpoint = v;
		}
		return viewpoint;
	}
	
	public static RepresentationDescription findDescription(Viewpoint viewpoint, String descriptionName) {
		RepresentationDescription description = null;
		for (RepresentationDescription d : viewpoint.getOwnedRepresentations()) {
			if (d.getName().equals(descriptionName))
				description = d;
		}
		return description;
	}
}
