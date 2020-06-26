package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
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
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
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

    private static final String PERSPECTIVE_ID = "org.palladiosimulator.pcmbench.perspectives.palladio";

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
                ? this.projectCreationPage.getLocationURI()
                : null;

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

        if (!getCurrentPerspectiveId().equals(PERSPECTIVE_ID)) {
            boolean confirm = MessageDialog.openConfirm(getShell(), "Palladio Perspective",
                    "This project is associated with the Palladio perspective.\n\nDo you want to open this perspective now?");
            if (confirm)
                openPalladioPerspective();
        }

        return true;
    }

    private void openPalladioPerspective() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        try {
            workbench.showPerspective(PERSPECTIVE_ID, window);
        } catch (WorkbenchException e) {
            MessageDialog.openError(getShell(), "Error", "Could not open Palladio Perspective");
            e.printStackTrace();
        }
    }

    private String getCurrentPerspectiveId() {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = window.getActivePage();
        IPerspectiveDescriptor perspective = page.getPerspective();
        return perspective.getId();
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
            monitor.beginTask("Creating Project", 8000);
            createAndOpenProject(description, projectHandle, SubMonitor.convert(monitor, "Main Task", 2000));
            handleTemplate(projectHandle, SubMonitor.convert(monitor, "Initializing based on AT", 2000));
            convertToModelingProject(projectHandle,
                    SubMonitor.convert(monitor, "Converting to Modeling Project", 2000));
            activateViewpoints(projectHandle, SubMonitor.convert(monitor, "Activating Viewpoints", 2000));
        } finally {
            monitor.done();
        }
        return projectHandle;
    }

    private void createAndOpenProject(final IProjectDescription description, final IProject projectHandle,
            final SubMonitor subMonitor) throws CoreException {
        projectHandle.create(description, subMonitor.split(1000));
        if (subMonitor.isCanceled()) {
            throw new OperationCanceledException();
        }
        projectHandle.open(IResource.BACKGROUND_REFRESH, subMonitor.split(1000));
    }

    /**
     * Check if a template was selected and produce the model files.
     * 
     * @param projectHandle
     * @param monitor
     * @throws CoreException
     */
    private void handleTemplate(final IProject projectHandle, final SubMonitor subMonitor) throws CoreException {
        final AT selectedTemplate = this.palladioTemplatePage.getSelectedTemplate();
        if (selectedTemplate != null) {
            TemplateInitiationContext context = new TemplateInitiationContext(projectHandle, selectedTemplate);
            context.addToProject(computeTemplatePath(selectedTemplate), projectHandle, subMonitor);
        }
    }

    /**
     * Encapsulates one invocation of the template loading.
     */
    private static class TemplateInitiationContext {
        public final IProject projectHandle;
        public final AT template;

        public TemplateInitiationContext(IProject projectHandle, AT template) {
            this.projectHandle = projectHandle;
            this.template = template;
        }

        private void addToProject(final URI path, final IContainer target, final SubMonitor subMonitor)
                throws CoreException {
            for (final File source : FileHelper.getFiles(path.toString())) {
                final IPath newTarget = new Path(source.getName());
                if (source.isDirectory()) {
                    addFolderToProject(path, source, target.getFolder(newTarget), subMonitor);
                } else {
                    addFileToProject(source, target.getFile(newTarget), subMonitor);
                }
            }
        }

        private void addFolderToProject(final URI path, final File source, final IFolder target,
                final SubMonitor subMonitor) throws CoreException {
            if (!target.exists()) {
                target.create(IResource.NONE, true, null);
            }

            addToProject(path.appendSegment(source.getName()), target, subMonitor);
        }

        private void addFileToProject(final File source, final IFile target, final SubMonitor subMonitor)
                throws CoreException {
            try (final InputStream contentStream = loadInputFile(source)) {
                if (target.exists()) {
                    target.setContents(contentStream, true, true, subMonitor);
                } else {
                    target.create(contentStream, true, subMonitor);
                }
            } catch (final FileNotFoundException e) {
                throwCoreException("File " + source.getAbsolutePath() + " does not exist!");
            } catch (final IOException e) {
                throwCoreException(
                        "Cannot create input stream on file " + source.getAbsolutePath() + "! " + e.getMessage());
            } catch (final CoreException e) {
                throwCoreException(e.getMessage());
            }
        }

        private InputStream loadInputFile(File source) throws IOException {
            // launch configurations should be small enough to load
            if (source.getName()
                .endsWith(".launch")) {
                String fileContent = Files.readString(source.toPath());
                fileContent = fileContent.replace(createPlatformUriStart(template.getEntityName()),
                        createPlatformUriStart(projectHandle.getName()));
                return new ByteArrayInputStream(fileContent.getBytes());
            }

            return new FileInputStream(source);
        }
    }

    private static String createPlatformUriStart(String projectName) {
        return "platform:/resource/" + projectName + "/";
    }

    private URI computeTemplatePath(final AT selectedTemplate) {
        final URI templateFolderURI = getRootURI(selectedTemplate).appendSegment(INITIATOR_TEMPLATES_FOLDER);
        final String[] segments = URI.createURI(selectedTemplate.getDefaultInstanceURI())
            .segments();
        return templateFolderURI.appendSegments(segments);
    }

    /**
     * Root folder of the eObject.
     * 
     * @param eObject
     *            the eObject where the root folder shall be found for.
     * @return the root folder.
     */
    private URI getRootURI(final EObject eObject) {
        return eObject.eResource()
            .getURI()
            .trimFragment()
            .trimSegments(1);
    }

    /**
     * Throw a core exception based on a given error message.
     * 
     * @param message
     *            The message to present.
     * @throws CoreException
     *             The exception to throw.
     */
    private static void throwCoreException(final String message) throws CoreException {
        final IStatus status = new Status(IStatus.ERROR, "org.palladiosimulator.editors.sirius.custom.wizard",
                IStatus.OK, message, null);
        throw new CoreException(status);
    }

    /**
     * Convert to modeling project.
     */
    private void convertToModelingProject(final IProject projectHandle, final SubMonitor subMonitor)
            throws CoreException {
        ModelingProjectManager.INSTANCE.convertToModelingProject(projectHandle, subMonitor);
    }

    /**
     * Activate viewpoints.
     */
    private void activateViewpoints(final IProject projectHandle, final SubMonitor subMonitor) {
        final URI representationsURI = SiriusCustomUtil.getRepresentationsURI(projectHandle);
        final Session session = SessionManager.INSTANCE.getSession(representationsURI, subMonitor);
        final Set<Viewpoint> registry = ViewpointRegistry.getInstance()
            .getViewpoints();
        final HashSet<Viewpoint> viewpoints = new HashSet<>();
        final List<String> extensions = getExtensions(session);
        for (final Viewpoint viewpoint : registry) {
            final String ext = viewpoint.getModelFileExtension();
            if (extensions.contains(ext)) {
                viewpoints.add(viewpoint);
            }
        }
        SiriusCustomUtil.selectViewpoints(session, viewpoints, true, subMonitor);
    }

    private List<String> getExtensions(final Session session) {
        final List<String> extensions = new ArrayList<>();
        for (final Resource r : session.getSemanticResources()) {
            if (r.getClass()
                .getPackage()
                .getName()
                .startsWith("org.palladiosimulator.pcm.")) {
                if (r.getURI()
                    .isPlatform()) {
                    extensions.add(r.getURI()
                        .fileExtension());
                }
            }
        }
        return extensions;
    }

}