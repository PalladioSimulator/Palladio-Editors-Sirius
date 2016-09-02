package org.palladiosimulator.editors.sirius.custom.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
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
	
}
