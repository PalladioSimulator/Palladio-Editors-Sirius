package org.palladiosimulator.editors.sirius.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallbackWithConfimation;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public abstract class NewModelWizard extends Wizard implements INewWizard {
	/*private static final String CONFIRMATION_TITLE = "Conversion confirmation";
	private static final String CONFIRMATION_QUESTION = "The project needs to be a Modeling Project in order to continue. Do you want to convert it now?";*/
	private static final String WINDOW_TITLE = "Create Model";

	private ModelCreationPage modelCreationPage;
	//private RepresentationCreationPage representationCreationPage;
	private URI modelURI;
	private final int work = 16;
	private EObject modelObject;
	private String viewpointName;


	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(WINDOW_TITLE);
		setNeedsProgressMonitor(true);
		modelObject = initModelObject();
		viewpointName = initViewpointName();
		modelCreationPage = initModelCreationPage();
	}

	protected abstract String initViewpointName();

	protected abstract EObject initModelObject();
	protected abstract ModelCreationPage initModelCreationPage();


	@Override
	public void addPages() {
		super.addPages();
		addPage(this.modelCreationPage);
		//addPage(this.representationCreationPage);
	}

	@Override
	public boolean performFinish() {
		modelURI = modelCreationPage.getPlatformURI();
		IRunnableWithProgress op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor) throws CoreException {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(modelURI.segment(1));
				if (!project.hasNature(ModelingProject.NATURE_ID)) {
					monitor.subTask("Converting to Modeling Project");
					ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
				}
				monitor.worked(work);
				
				Session session = getSession(monitor);
				final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
				createResource(session, domain, monitor);
				activateViewpoint(session, monitor);
				

			}
		};

		try {
			this.getContainer().run(true, true, op);
		} catch (InvocationTargetException | InterruptedException e) {
			return false;
		}

		return true;
	}


	private Session getSession(IProgressMonitor monitor) {
		monitor.subTask("Requesting Session");
		final Session session = SessionManager.INSTANCE.getSession(
				URI.createPlatformResourceURI("/" + modelURI.segment(1) + "/representations.aird", true),
				new SubProgressMonitor(monitor, 1));
		monitor.worked(work);
		return session;
	}

	private void createResource(Session session, TransactionalEditingDomain domain, IProgressMonitor monitor) {

		monitor.subTask("Creating Resource");
		CreateModelCommand cmd = new CreateModelCommand(domain, modelURI, modelObject);
		domain.getCommandStack().execute(cmd);

		monitor.worked(work);

		monitor.subTask("Adding as semantic resource");
		domain.getCommandStack().execute(new AddSemanticResourceCommand(session, modelObject.eResource().getURI(),
				new SubProgressMonitor(monitor, 1)));

		monitor.worked(work);
	}

	private void activateViewpoint(Session session, IProgressMonitor monitor) {
		monitor.subTask("Activating the " + viewpointName + " viewpoint");

		Viewpoint viewpointToActivate = findViewpointInRegistry(viewpointName);
		if (viewpointToActivate == null) {
			throw new IllegalStateException("Could not find viewpoint with name: " + viewpointName);
		}
		ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();
		@SuppressWarnings("restriction")
		RecordingCommand command = new ChangeViewpointSelectionCommand(session, callback,
				Collections.singleton(viewpointToActivate), new HashSet<Viewpoint>(), true, monitor);
		session.getTransactionalEditingDomain().getCommandStack().execute(command);
		monitor.worked(16);
	}

	private Viewpoint findViewpointInRegistry(String viewpointName) {
		final Set<Viewpoint> registry = ViewpointRegistry.getInstance().getViewpoints();
		Viewpoint candidateViewpoint = null;

		for (Viewpoint registeredViewpoint : registry) {
			if (registeredViewpoint.getName().equals(viewpointName)) {
				candidateViewpoint = registeredViewpoint;
				break;
			}
		}
		return candidateViewpoint;
	}

}
