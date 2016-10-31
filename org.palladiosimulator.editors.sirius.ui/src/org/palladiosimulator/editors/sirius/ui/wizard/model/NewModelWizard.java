package org.palladiosimulator.editors.sirius.ui.wizard.model;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

public abstract class NewModelWizard extends Wizard implements INewWizard {

    private static final String WINDOW_TITLE = "Create Model";

    protected ModelCreationPage modelCreationPage;
    private RepresentationCreationPage representationCreationPage;
    private URI modelURI;
    protected EObject modelObject;
    protected Viewpoint viewpoint;
    protected RepresentationDescription representationDescription;

    @Override
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        setWindowTitle(WINDOW_TITLE);
        setNeedsProgressMonitor(true);

        this.representationCreationPage = new RepresentationCreationPage();

        init(selection);
        if (this.viewpoint == null || this.modelObject == null || this.modelCreationPage == null)
            throw new NullPointerException("Attributes must be correctly initialized in the init method");
    }

    /**
     * The implementation of this method must correctly initialize the modelObject, viewpoints,
     * representation and modelCreationPage attributes
     * 
     * @param selection
     *            selected element
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
        this.modelURI = this.modelCreationPage.getPlatformURI();
        final boolean createRepresentation = this.representationCreationPage.isRepresentationCreationEnabled();
        final String representationName = this.representationCreationPage.getRepresentationName();
        final IRunnableWithProgress op = new WorkspaceModifyOperation() {

            @Override
            protected void execute(final IProgressMonitor monitor) throws CoreException {
                final IProject project = ResourcesPlugin.getWorkspace().getRoot()
                        .getProject(URI.decode(NewModelWizard.this.modelURI.segment(1)));
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

    private void createModel(final IProject project, final boolean createRepresentation,
            final String representationName, final IProgressMonitor monitor) throws CoreException {

    	monitor.beginTask("Creating Model File", 6000);
        if (!project.hasNature(ModelingProject.NATURE_ID)) {
            ModelingProjectManager.INSTANCE.convertToModelingProject(project, SubMonitor.convert(monitor, "Converting to Modeling Project", 1000));
        }

        final URI representationsURI = SiriusCustomUtil.getRepresentationsURI(project);
        final Session session = SessionManager.INSTANCE.getSession(representationsURI, SubMonitor.convert(monitor, "Getting Session", 1000));

        createResource(session, SubMonitor.convert(monitor, "Creating Resource", 1000));
        HashSet<Viewpoint> selectedViewpoints = new HashSet<Viewpoint>();
        selectedViewpoints.add(viewpoint);
        SiriusCustomUtil.selectViewpoints(session, selectedViewpoints, false, SubMonitor.convert(monitor, "Selecting Viewpoint", 1000));

        // Apparently the selected viewpoint's instance is not the same as the passed instance
        // We retrieve here the actually selected Viewpoint
        
        Viewpoint selectedViewpoint = SiriusCustomUtil.getSelectedViewpointByName(session, viewpoint.getName());
        RepresentationDescription actualRepresentationDescription = SiriusCustomUtil.findDescription(selectedViewpoint, representationDescription.getName());

        if (createRepresentation) {
            final DRepresentation createdRepresentation = SiriusCustomUtil.createRepresentation(session,
                    representationName, actualRepresentationDescription, this.modelObject, SubMonitor.convert(monitor, "Creating Representation", 1000));
            DialectUIManager.INSTANCE.openEditor(session, createdRepresentation,
                    SubMonitor.convert(monitor, "Opening representation", 1000));
        }
        monitor.done();

    }


	private void createResource(final Session session, final IProgressMonitor monitor) {
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        final CreateModelCommand createModelCommand = new CreateModelCommand(domain, this.modelURI, this.modelObject);
        domain.getCommandStack().execute(createModelCommand);
        domain.getCommandStack().execute(new AddSemanticResourceCommand(session, this.modelObject.eResource().getURI(),
                SubMonitor.convert(monitor)));
    }

}
