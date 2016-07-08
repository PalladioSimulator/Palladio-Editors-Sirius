package org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.wizards;

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
import org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.Activator;
import org.palladiosimulator.editors.sirius.composedprovidingrequiringentity.commands.CreateSystemModelCommand;
import org.palladiosimulator.pcm.system.System;

/**
 * Wizard for creating a {@link System}-model. Allows creating a corresponding
 * Sirius representation.
 * 
 * @author Max Schettler
 *
 */
@SuppressWarnings("restriction") // TODO remove this when the Sirius API classes
									// get added to the API.....
public class SystemCreationWizard extends Wizard implements INewWizard {

	private static final String CONFIRMATION_TITLE = "Conversion confirmation";
	private static final String CONFIRMATION_QUESTION = "The project needs to be a Modeling Project in order to continue. Do you want to convert it now?";
	private static final String WINDOW_TITLE = "Create System";

	private SystemModelCreationPage fileCreationPage;
	private RepresentationCreationPage representationCreationPage;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		final URI systemURI = fileCreationPage.getPlatformURI();
		// TODO use optional
		final boolean createRepresentation = representationCreationPage.isRepresentationCreationEnabled();
		final String representationName = representationCreationPage.getRepresentationName();

		final IRunnableWithProgress operation = new WorkspaceModifyOperation() {

			@Override
			protected void execute(final IProgressMonitor monitor)
					throws CoreException, InvocationTargetException, InterruptedException {
				if (!systemURI.isPlatform())
					return;
				
				monitor.beginTask("Create System and representation:", 100);
				
				final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(systemURI.segment(1));
				if (!project.hasNature(ModelingProject.NATURE_ID)) {
					monitor.subTask("Converting to Modeling Project");
					// Ask user whether he wants to add the modeling nature
					final MessageDialog confirmationDialog = new MessageDialog(
							PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), CONFIRMATION_TITLE, null,
							CONFIRMATION_QUESTION,
							MessageDialog.CONFIRM, new String[] { IDialogConstants.YES_LABEL, IDialogConstants.CANCEL_LABEL }, 0);
					if (confirmationDialog.open() != Dialog.OK)
						return;

					ModelingProjectManager.INSTANCE.convertToModelingProject(project, monitor);
				}

				monitor.subTask("Requesting Session");
				final Session session = SessionManager.INSTANCE.getSession(
						URI.createPlatformResourceURI("/" + systemURI.segment(1) + "/representations.aird", true),
						new SubProgressMonitor(monitor, 1));
				final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

				monitor.worked(16);

				monitor.subTask("Creating System");
				final CreateSystemModelCommand createSystemModelCommand = new CreateSystemModelCommand(domain, systemURI);
				domain.getCommandStack().execute(createSystemModelCommand);

				monitor.worked(16);

				monitor.subTask("Adding as semantic resource");
				final System createdSystem = createSystemModelCommand.getCreatedSystem();
				domain.getCommandStack().execute(new AddSemanticResourceCommand(session, createdSystem.eResource().getURI(),
						new SubProgressMonitor(monitor, 1)));

				monitor.worked(16);

				if (createRepresentation) {
					monitor.subTask("Selecting viewpoint");
					final Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(true);
					if (!selectedViewpoints.contains(Activator.getDefault().SYSTEM_DESIGN)) {
						domain.getCommandStack()
								.execute(new ChangeViewpointSelectionCommand(session, new ViewpointSelectionCallback(),
										Collections.singleton(Activator.getDefault().SYSTEM_DESIGN),
										Collections.<Viewpoint> emptySet(), new SubProgressMonitor(monitor, 1)));
					}
					monitor.worked(16);

					monitor.subTask("Creating representation");
					final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session,
							Activator.getDefault().COMPOSED_PROVIDING_REQUIRING_ENTITY_DIAGRAM, createdSystem, representationName,
							new SubProgressMonitor(monitor, 1));
					domain.getCommandStack().execute(createRepresentationCommand);
					final DRepresentation createdRepresentation = createRepresentationCommand.getCreatedRepresentation();
					monitor.worked(16);

					monitor.subTask("Opening representation");
					DialectUIManager.INSTANCE.openEditor(session, createdRepresentation, new SubProgressMonitor(monitor, 1));
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
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		fileCreationPage = new SystemModelCreationPage(selection);
		representationCreationPage = new RepresentationCreationPage();
		setWindowTitle(WINDOW_TITLE);
		setNeedsProgressMonitor(true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addPages() {
		super.addPages();

		addPage(fileCreationPage);
		addPage(representationCreationPage);
	}

	/**
	 * Page for creating a System model file.
	 * 
	 * @author Max Schettler
	 *
	 */
	private class SystemModelCreationPage extends WizardNewFileCreationPage {

		private static final String PAGE_NAME = "Create System";
		private static final String INITIAL_FILE_NAME = "newSystem"; // $NON-NLS-N$
		private static final String FILE_EXTENSION = "system"; // $NON-NLS-N$
		private static final String MESSAGE = "Choose a file name and location";

		public SystemModelCreationPage(IStructuredSelection selection) {
			super(PAGE_NAME, selection);
			setFileName(INITIAL_FILE_NAME);
			setMessage(MESSAGE);
			setFileExtension(FILE_EXTENSION);
		}

		@Override
		public void createControl(Composite parent) {
			super.createControl(parent);
			setMessage(MESSAGE);
			setTitle(PAGE_NAME);
		}

		/**
		 * Need to override this to set the correct message. The
		 * super-implementation sets it to null.
		 */
		@Override
		protected boolean validatePage() {
			boolean valid = super.validatePage();
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
	 * @author Max Schettler
	 *
	 */
	private class RepresentationCreationPage extends WizardPage implements SelectionListener, ModifyListener {

		private static final String PAGE_NAME = "Model creation";
		private static final String ENABLE = "Create representation";
		private static final String REPRESENTATION_NAME = "Representation name:";
		private static final String TITLE = "Create representation";
		private static final String MESSAGE = "Select whether you want to create a representation";
		private static final String DEFAULT_REPRESENTATION_NAME = "new System Diagram";

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
			return !enabledCheckbox.getSelection()
					|| (representationNameInput.getText() != null && !representationNameInput.getText().equals("")); // $NON-NLS-N$
		}

		private void setEnabled(boolean enabled) {
			enabledCheckbox.setSelection(enabled);
			representationComposite.setEnabled(enabled);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void createControl(Composite parent) {
			// Create composite
			final Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout());
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));

			{ // Enabled button
				enabledCheckbox = new Button(composite, SWT.CHECK);
				enabledCheckbox.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
				enabledCheckbox.setText(ENABLE);
				enabledCheckbox.addSelectionListener(this);
			}

			{ // Representation name
				representationComposite = new Composite(composite, SWT.BORDER);
				representationComposite.setEnabled(false);
				representationComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

				final GridLayout representationLayout = new GridLayout();
				representationLayout.marginHeight = 10;
				representationLayout.marginWidth = 10;
				representationComposite.setLayout(representationLayout);

				final Label label = new Label(representationComposite, SWT.NONE);
				label.setText(REPRESENTATION_NAME);
				label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

				representationNameInput = new Text(representationComposite, SWT.SINGLE | SWT.BORDER);
				representationNameInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				representationNameInput.addModifyListener(this);
				representationNameInput.setText(DEFAULT_REPRESENTATION_NAME);
			}

			setEnabled(true);
			setControl(composite);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (enabledCheckbox.equals(e.getSource())) {
				setEnabled(((Button) e.getSource()).getSelection());
			}
			getWizard().getContainer().updateButtons();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
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
			return representationNameInput.getText();
		}

		/**
		 * Returns whether the user wants to create a representation or not.
		 * 
		 * @return the decision
		 */
		public boolean isRepresentationCreationEnabled() {
			return enabledCheckbox.getSelection();
		}

		@Override
		public void modifyText(ModifyEvent e) {
			getWizard().getContainer().updateButtons();
		}

	}

}
