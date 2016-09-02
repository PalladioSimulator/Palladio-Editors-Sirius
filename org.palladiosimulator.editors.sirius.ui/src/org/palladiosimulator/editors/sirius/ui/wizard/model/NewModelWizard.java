package org.palladiosimulator.editors.sirius.ui.wizard.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

public abstract class NewModelWizard extends Wizard implements INewWizard {
	private static final String WINDOW_TITLE = "Create Model";

	protected ModelCreationPage modelCreationPage;
	private RepresentationCreationPage representationCreationPage;
	private URI modelURI;
	private final int work = 16;
	protected EObject modelObject;
	protected List<String> viewpointNames;
	protected RepresentationDescription representation;

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(WINDOW_TITLE);
		setNeedsProgressMonitor(true);

        this.representationCreationPage = new RepresentationCreationPage();
		
		init(selection);
		if (viewpointNames == null || modelObject == null || modelCreationPage == null)
			throw new NullPointerException("Attributes must be correctly initialized in the init method");
	}

	/**
	 * The implementation of this method must correctly initialize the
	 * modelObject, viewpointName, representation and modelCreationPage attributes
	 * @param selection selected element
	 */
	protected abstract void init(IStructuredSelection selection);

	@Override
	public void addPages() {
		super.addPages();
		addPage(this.modelCreationPage);
		addPage(this.representationCreationPage);
	}

	@Override
	public boolean performFinish() {
		modelURI = modelCreationPage.getPlatformURI();
		boolean createRepresentation = this.representationCreationPage.isRepresentationCreationEnabled();
        final String representationName = this.representationCreationPage.getRepresentationName();
		IRunnableWithProgress op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor) throws CoreException {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(modelURI.segment(1));
				createModel(project, createRepresentation, representationName, monitor);
			}
		};

		try {
			this.getContainer().run(true, true, op);
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	
	
	private void createModel(IProject project, boolean createRepresentation, String representationName, IProgressMonitor monitor) throws CoreException {

        if (!project.hasNature(ModelingProject.NATURE_ID)) {
            monitor.subTask("Converting to Modeling Project");
            ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
        }
        
        URI representationsURI = SiriusCustomUtil.getRepresentationsURI(project);
		Session session = SessionManager.INSTANCE.getSession(representationsURI, monitor);
		


		createResource(session, monitor);
		SiriusCustomUtil.selectViewpoints(session, viewpointNames, false, monitor);
		if (createRepresentation) {
			createRepresentation(session, representationName, monitor);
		}
		
	}
	
	private void createRepresentation(Session session, String representationName, IProgressMonitor monitor) {
		 monitor.subTask("Creating representation");
		 TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
         final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(
                 session, representation, modelObject,
                 representationName, new SubProgressMonitor(monitor, 1));
         domain.getCommandStack().execute(createRepresentationCommand);
         final DRepresentation createdRepresentation = createRepresentationCommand
                 .getCreatedRepresentation();
         monitor.worked(work);

         monitor.subTask("Opening representation");
         DialectUIManager.INSTANCE.openEditor(session, createdRepresentation,
                 new SubProgressMonitor(monitor, 1));
         monitor.worked(work);
		
	}

	private void createResource(Session session, IProgressMonitor monitor) {
		monitor.subTask("Setting Resource");
		final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final CreateModelCommand createModelCommand = new CreateModelCommand(domain,  modelURI, modelObject);
        domain.getCommandStack().execute(createModelCommand);
		domain.getCommandStack().execute(new AddSemanticResourceCommand(session, modelObject.eResource().getURI(), new SubProgressMonitor(monitor, 1)));
		monitor.worked(work);
	}



}
