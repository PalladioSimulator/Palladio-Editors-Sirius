package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;
import org.palladiosimulator.architecturaltemplates.AT;
import org.palladiosimulator.architecturaltemplates.api.ArchitecturalTemplateAPI;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.editors.sirius.custom.util.SiriusCustomUtil;

/**
 * A wizard to create a new palladio model according to a chosen template.
 */
public class NewPalladioProjectWizard extends Wizard implements INewWizard {

    /** An AT catalog stores initiator templates in this folder. */
    private static final String INITIATOR_TEMPLATES_FOLDER = "initiatorTemplates";

    private WizardNewProjectCreationPage projectCreationPage;
    private NewPalladioTemplateWizardPage palladioTemplatePage;
    private IProject project;
    private IConfigurationElement config;
    private IWorkbench workbench;

    /**
     * Constructor for NewPCMWizard.
     */
    public NewPalladioProjectWizard() {
        super();
        setNeedsProgressMonitor(true);
    }

    @Override
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        this.workbench = workbench;
    }

    /**
     * Adding the page to the wizard.
     */

    @Override
    public void addPages() {
        // set the basic project page
        this.projectCreationPage = new WizardNewProjectCreationPage("NewPalladioProject");
        this.projectCreationPage.setDescription("Create a new Palladio project.");
        this.projectCreationPage.setTitle("New Palladio Project");
        addPage(this.projectCreationPage);

        // set the template page
        this.palladioTemplatePage = new NewPalladioTemplateWizardPage(ArchitecturalTemplateAPI.getInitiatorATs());
        addPage(this.palladioTemplatePage);
    }

    @Override
    public boolean performFinish() {
        final IProject projectHandle = this.projectCreationPage.getProjectHandle();

        final java.net.URI projectURI = (!this.projectCreationPage.useDefaults())
                ? this.projectCreationPage.getLocationURI() : null;

        final IWorkspace workspace = ResourcesPlugin.getWorkspace();

        final IProjectDescription desc = workspace.newProjectDescription(projectHandle.getName());
        desc.setLocationURI(projectURI);

        /*
         * Creating the project encapsulated in a workspace operation
         */
        final WorkspaceModifyOperation op = new WorkspaceModifyOperation() {

            @Override
            protected void execute(final IProgressMonitor monitor) throws CoreException {
                NewPalladioProjectWizard.this.project = createProject(desc, projectHandle, monitor);
            }
        };

        /*
         * This isn't as robust as the code in the BasicNewProjectResourceWizard class. Consider
         * beefing this up to improve error handling.
         */
        try {
            getContainer().run(true, true, op);
        } catch (final Exception e) {
            MessageDialog.openError(getShell(), "Error", "An unexpected error occured. See stack trace");
            e.printStackTrace();
            return false;
        }

        if (this.project == null) {
            return false;
        }

        BasicNewProjectResourceWizard.updatePerspective(this.config);
        BasicNewProjectResourceWizard.selectAndReveal(this.project, this.workbench.getActiveWorkbenchWindow());

        return true;
    }

    /**
     * This creates the project in the workspace.
     * 
     * @param description
     *            The description to set for the project.
     * @param projectHandle
     * @param monitor
     * @throws CoreException
     * @throws OperationCanceledException
     */
    private IProject createProject(final IProjectDescription description, final IProject projectHandle,
            final IProgressMonitor monitor) throws CoreException, OperationCanceledException {
        try {

            monitor.beginTask("", 2000);
            projectHandle.create(description, new SubProgressMonitor(monitor, 1000));
            if (monitor.isCanceled()) {
                throw new OperationCanceledException();
            }
            projectHandle.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(monitor, 1000));

            // check if a template was selected and produce the model files
            final AT selectedTemplate = this.palladioTemplatePage.getSelectedTemplate();
            if (selectedTemplate != null) {
                final URI templateFolderURI = getRootURI(selectedTemplate).appendSegment(INITIATOR_TEMPLATES_FOLDER);
                final String[] segments = URI.createURI(selectedTemplate.getDefaultInstanceURI()).segments();
                final URI templateURI = templateFolderURI.appendSegments(segments);
                final String templatePath = templateURI.toString();

                for (final File file : FileHelper.getFiles(templatePath)) {
                    addModelFile(templatePath + file.getName(), file.getName(), projectHandle, monitor);
                }
            }

            // Convert to modeling project
            ModelingProjectManager.INSTANCE.convertToModelingProject(projectHandle, monitor);

            // Activate viewpoints
            final URI representationsURI = SiriusCustomUtil.getRepresentationsURI(projectHandle);
            final Session session = SessionManager.INSTANCE.getSession(representationsURI, monitor);

            activateCorrespondingViewpoints(session, monitor);

        } finally {
            monitor.done();
        }
        return projectHandle;
    }

    /**
     * Root folder of the eObject.
     * 
     * @param eObject
     *            the eObject where the root folder shall be found for.
     * @return the root folder.
     */
    private URI getRootURI(final EObject eObject) {
        return eObject.eResource().getURI().trimFragment().trimSegments(1);
    }

    /**
     * Create a file in the project.
     * 
     * @param sourceFile
     *            The URI path to the source file to add (e.g. plattform://pluginid/...")
     * @param targetFileName
     *            The filename of the target file to write.
     * @param container
     *            The container to place the target file in.
     * @param monitor
     *            The monitor to track the progress.
     * @throws CoreException
     *             Identifying that the file could not be written.
     */
    private void addModelFile(final String sourceFileURI, final String targetFileName, final IContainer container,
            final IProgressMonitor monitor) throws CoreException {
        InputStream resourceStream = null;

        try {

            final URL url = new URL(sourceFileURI);
            resourceStream = url.openConnection().getInputStream();
            if (resourceStream != null) {
                addFileToProject(container, new Path(targetFileName), resourceStream, monitor);
            }
        } catch (final IOException ioe) {
            throwCoreException(ioe.getLocalizedMessage());
        } finally {
            if (resourceStream != null) {
                try {
                    resourceStream.close();
                } catch (final IOException e) {
                    throwCoreException(e.getLocalizedMessage());
                }
            }
        }
    }

    /**
     * Adds a new file to the project.
     * 
     * @param container
     * @param path
     * @param contentStream
     * @param monitor
     * @throws CoreException
     */
    private void addFileToProject(final IContainer container, final Path path, final InputStream contentStream,
            final IProgressMonitor monitor) throws CoreException {
        final IFile file = container.getFile(path);
        if (file.exists()) {
            file.setContents(contentStream, true, true, monitor);
        } else {
            file.create(contentStream, true, monitor);
        }
    }

    /**
     * Throw a core exception based on a given error message.
     * 
     * @param message
     *            The message to present.
     * @throws CoreException
     *             The exception to throw.
     */
    private void throwCoreException(final String message) throws CoreException {
        final IStatus status = new Status(IStatus.ERROR, "org.palladiosimulator.editors.sirius.custom.wizard",
                IStatus.OK, message, null);
        throw new CoreException(status);
    }

    private void activateCorrespondingViewpoints(final Session session, final IProgressMonitor monitor) {
        final Set<Viewpoint> registry = ViewpointRegistry.getInstance().getViewpoints();
        final HashSet<Viewpoint> viewpoints = new HashSet<>();
        final List<String> extensions = getExtensions(session);
        for (final Viewpoint viewpoint : registry) {
            final String ext = viewpoint.getModelFileExtension();
            if (extensions.contains(ext)) {
                viewpoints.add(viewpoint);
            }
        }
        SiriusCustomUtil.selectViewpoints(session, viewpoints, true, monitor);

    }

    private List<String> getExtensions(final Session session) {
        final List<String> extensions = new ArrayList<>();
        for (final Resource r : session.getSemanticResources()) {
            if (r.getClass().getPackage().getName().startsWith("org.palladiosimulator.pcm.")) {
                if (r.getURI().isPlatform()) {
                    extensions.add(r.getURI().fileExtension());
                }
            }
        }
        return extensions;
    }

}