package org.palladiosimulator.editors.sirius.custom.util;

import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.session.UserSession;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Class containing some useful static methods to work with Sirius
 * @see  {@link UserSession}  {@link SiriusUtil}
 * @author Amine Kechaou
 *
 */
public class SiriusCustomUtil {
	
	public static URI getRepresentationsURI(IProject project) {
		return URI.createPlatformResourceURI(project.getFullPath().append("/representations." + SiriusUtil.SESSION_RESOURCE_EXTENSION).toOSString(), true);
	}
	
	public static void selectViewpoints(Session session, HashSet<Viewpoint> viewpoints, IProgressMonitor monitor) {
        final ViewpointSelectionCallback selectionCallback = new ViewpointSelectionCallback();
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
		final Command command = new ChangeViewpointSelectionCommand(session, selectionCallback, viewpoints, new HashSet<Viewpoint>(), true, monitor);
        domain.getCommandStack().execute(command);
	}
	
}
