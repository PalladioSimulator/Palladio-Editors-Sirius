package org.palladiosimulator.editors.resourceenvironment.design.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallback;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.ui.tools.api.project.ModelingProjectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.palladiosimulator.editors.resourceenvironment.design.Activator;
import org.palladiosimulator.editors.resourceenvironment.design.commands.CreateResourceenvironmentCommand;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;

/**
 * Wizard for creating a {@link ResourceEnvironment}-model. Allows creating a corresponding Sirius
 * representation.
 * 
 * @author Edith Kegel
 *
 */
@SuppressWarnings("restriction") // TODO remove this when the Sirius API classes
                                 // get added to the API.....
public class ResourceEnvironmentCreationWizard extends Wizard implements INewWizard {

    private static final String CONFIRMATION_TITLE = "Conversion confirmation";
    private static final String CONFIRMATION_QUESTION = "The project needs to be a Modeling Project in order to continue. Do you want to convert it now?";
    private static final String WINDOW_TITLE = "Create ResourceEnvironment";

    private ResourceEnvironmentModelCreationPage fileCreationPage;
    private RepresentationCreationPage representationCreationPage;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean performFinish() {
        final URI resourceURI = this.fileCreationPage.getPlatformURI();
        // TODO use optional
        final boolean createRepresentation = this.representationCreationPage.isRepresentationCreationEnabled();
        final String representationName = this.representationCreationPage.getRepresentationName();

        final IRunnableWithProgress operation = new WorkspaceModifyOperation() {

            @Override
            protected void execute(final IProgressMonitor monitor)
                    throws CoreException, InvocationTargetException, InterruptedException {
                if (!resourceURI.isPlatform())
                    return;

                monitor.beginTask("Create ResourceEnvironment and representation:", 100);

                final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(resourceURI.segment(1));
                if (!project.hasNature(ModelingProject.NATURE_ID)) {
                    monitor.subTask("Converting to Modeling Project");
                    // Ask user whether he wants to add the modeling nature
                    final MessageDialog confirmationDialog = new MessageDialog(
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), CONFIRMATION_TITLE, null,
                            CONFIRMATION_QUESTION, MessageDialog.CONFIRM,
                            new String[] { IDialogConstants.YES_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
                    if (confirmationDialog.open() != Dialog.OK)
                        return;

                    ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
                }

                monitor.subTask("Requesting Session");
                final Session session = SessionManager.INSTANCE.getSession(
                        URI.createPlatformResourceURI("/" + resourceURI.segment(1) + "/representations.aird", true),
                        new SubProgressMonitor(monitor, 1));
                final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

                monitor.worked(16);

                monitor.subTask("Creating Resource Environment");
                final CreateResourceenvironmentCommand createResourceenvironmentCommand = new CreateResourceenvironmentCommand(
                        domain, resourceURI);
                domain.getCommandStack().execute(createResourceenvironmentCommand);

                monitor.worked(16);

                monitor.subTask("Adding as semantic resource");
                final ResourceEnvironment createdResEnvironment = createResourceenvironmentCommand
                        .getcreatedResourceEnvironment();
                domain.getCommandStack().execute(new AddSemanticResourceCommand(session,
                        createdResEnvironment.eResource().getURI(), new SubProgressMonitor(monitor, 1)));

                monitor.worked(16);

                if (createRepresentation) {
                    monitor.subTask("Selecting viewpoint");
                    final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
                    if (!selectedViewpoints.contains(Activator.getDefault().RESOURCEENVIRONMENT_DESIGN)) {
                        domain.getCommandStack()
                                .execute(new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(),
                                        Collections.singleton(Activator.getDefault().RESOURCEENVIRONMENT_DESIGN),
                                        Collections.<Viewpoint> emptySet(), new SubProgressMonitor(monitor, 1)));
                    }
                    monitor.worked(16);

                    monitor.subTask("Creating representation");
                    final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(
                            session, Activator.getDefault().RESOURCEENVIRONMENT_DIAGRAM, createdResEnvironment,
                            representationName, new SubProgressMonitor(monitor, 1));
                    domain.getCommandStack().execute(createRepresentationCommand);
                    final DRepresentation createdRepresentation = createRepresentationCommand
                            .getCreatedRepresentation();
                    monitor.worked(16);

                    monitor.subTask("Opening representation");
                    DialectUIManager.INSTANCE.openEditor(session, createdRepresentation,
                            new SubProgressMonitor(monitor, 1));
                    monitor.worked(16);
                }
            }
        };
        try {
            this.getContainer().run(false, false, operation);
        } catch (InvocationTargetException | InterruptedException e) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IWorkbench workbench, final IStructuredSelection selection) {
        this.fileCreationPage = new ResourceEnvironmentModelCreationPage(selection);
        this.representationCreationPage = new RepresentationCreationPage();
        setWindowTitle(WINDOW_TITLE);
        setNeedsProgressMonitor(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPages() {
        super.addPages();

        addPage(this.fileCreationPage);
        addPage(this.representationCreationPage);
    }

    /**
     * Page for creating a Resource Environment model file.
     * 
     * @author Edith Kegel
     *
     */
    private class ResourceEnvironmentModelCreationPage extends WizardNewFileCreationPage {

        private static final String PAGE_NAME = "Create Resource Environment";
        private static final String INITIAL_FILE_NAME = "newResourceEnvironment"; // $NON-NLS-N$
        private static final String FILE_EXTENSION = "resourceenvironment"; // $NON-NLS-N$
        private static final String MESSAGE = "Choose a file name and location";

        public ResourceEnvironmentModelCreationPage(final IStructuredSelection selection) {
            super(PAGE_NAME, selection);
            setFileName(INITIAL_FILE_NAME);
            setMessage(MESSAGE);
            setFileExtension(FILE_EXTENSION);
        }

        @Override
        public void createControl(final Composite parent) {
            super.createControl(parent);
            setMessage(MESSAGE);
            setTitle(PAGE_NAME);
        }

        /**
         * Need to override this to set the correct message. The super-implementation sets it to
         * null.
         */
        @Override
        protected boolean validatePage() {
            final boolean valid = super.validatePage();
            if (valid)
                setMessage(MESSAGE);
            return valid;
        }

        public URI getPlatformURI() {
            return URI.createPlatformResourceURI(getContainerFullPath().append(getFileName()).toOSString(), true);
        }

    }

    /**
     * Class for selecting a representation name.
     * 
     * @author Edith Kegel
     *
     */
    private class RepresentationCreationPage extends WizardPage implements SelectionListener, ModifyListener {

        private static final String PAGE_NAME = "Model creation";
        private static final String ENABLE = "Create representation";
        private static final String REPRESENTATION_NAME = "Representation name:";
        private static final String TITLE = "Create representation";
        private static final String MESSAGE = "Select whether you want to create a representation";
        private static final String DEFAULT_REPRESENTATION_NAME = "new Resource Environment Diagram";

        private Button enabledCheckbox;
        private Text representationNameInput;
        private Composite representationComposite;

        protected RepresentationCreationPage() {
            super(PAGE_NAME);
            setTitle(TITLE);
            setMessage(MESSAGE);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPageComplete() {
            return !this.enabledCheckbox.getSelection() || (this.representationNameInput.getText() != null
                    && !this.representationNameInput.getText().equals("")); // $NON-NLS-N$
        }

        private void setEnabled(final boolean enabled) {
            this.enabledCheckbox.setSelection(enabled);
            this.representationComposite.setEnabled(enabled);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void createControl(final Composite parent) {
            // Create composite
            final Composite composite = new Composite(parent, SWT.NONE);
            composite.setLayout(new GridLayout());
            composite.setLayoutData(new GridData(GridData.FILL_BOTH));

            { // Enabled button
                this.enabledCheckbox = new Button(composite, SWT.CHECK);
                this.enabledCheckbox.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
                this.enabledCheckbox.setText(ENABLE);
                this.enabledCheckbox.addSelectionListener(this);
            }

            { // Representation name
                this.representationComposite = new Composite(composite, SWT.BORDER);
                this.representationComposite.setEnabled(false);
                this.representationComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

                final GridLayout representationLayout = new GridLayout();
                representationLayout.marginHeight = 10;
                representationLayout.marginWidth = 10;
                this.representationComposite.setLayout(representationLayout);

                final Label label = new Label(this.representationComposite, SWT.NONE);
                label.setText(REPRESENTATION_NAME);
                label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

                this.representationNameInput = new Text(this.representationComposite, SWT.SINGLE | SWT.BORDER);
                this.representationNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                this.representationNameInput.addModifyListener(this);
                this.representationNameInput.setText(DEFAULT_REPRESENTATION_NAME);
            }

            setEnabled(true);
            setControl(composite);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetSelected(final SelectionEvent e) {
            if (this.enabledCheckbox.equals(e.getSource())) {
                setEnabled(((Button) e.getSource()).getSelection());
            }
            getWizard().getContainer().updateButtons();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void widgetDefaultSelected(final SelectionEvent e) {
        }

        /**
         * Returns the entered representation name. Only valid if
         * {@link #isRepresentationCreationEnabled()} returns true.
         * 
         * @return the representation name
         * 
         * @see #isRepresentationCreationEnabled()
         */
        public String getRepresentationName() {
            return this.representationNameInput.getText();
        }

        /**
         * Returns whether the user wants to create a representation or not.
         * 
         * @return the decision
         */
        public boolean isRepresentationCreationEnabled() {
            return this.enabledCheckbox.getSelection();
        }

        @Override
        public void modifyText(final ModifyEvent e) {
            getWizard().getContainer().updateButtons();
        }

    }

}
