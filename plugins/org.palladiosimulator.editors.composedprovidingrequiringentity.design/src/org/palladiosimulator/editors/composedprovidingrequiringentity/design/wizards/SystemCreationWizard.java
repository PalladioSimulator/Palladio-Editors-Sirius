package org.palladiosimulator.editors.composedprovidingrequiringentity.design.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
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
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.palladiosimulator.editors.composedprovidingrequiringentity.design.Activator;
import org.palladiosimulator.editors.composedprovidingrequiringentity.design.commands.CreateSystemModelCommand;
import org.palladiosimulator.pcm.system.System;

/**
 * Wizard for creating a {@link System}-model. Allows creating a corresponding
 * Sirius representation.
 * 
 * @author Max Schettler
 *
 */
public class SystemCreationWizard extends Wizard implements INewWizard {

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
				try {
				final Session session = SessionManager.INSTANCE.getSession(
						URI.createPlatformResourceURI("/" + systemURI.segment(1) + "/representations.aird", true),
						monitor);
				final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();

				monitor.worked(25);

				final CreateSystemModelCommand createSystemModelCommand = new CreateSystemModelCommand(domain, systemURI);
				domain.getCommandStack().execute(createSystemModelCommand);
				final System createdSystem = createSystemModelCommand.getCreatedSystem();
				domain.getCommandStack().execute(new AddSemanticResourceCommand(session, createdSystem.eResource().getURI(), monitor));
				monitor.worked(50);

				if (createRepresentation) {
					final CreateRepresentationCommand createRepresentationCommand = new CreateRepresentationCommand(session,
							Activator.getDefault().COMPOSED_PROVIDING_REQUIRING_ENTITY_DIAGRAM, createdSystem, representationName,
							monitor);
					domain.getCommandStack().execute(createRepresentationCommand);
					final DRepresentation createdRepresentation = createRepresentationCommand.getCreatedRepresentation();
					monitor.worked(75);

					DialectUIManager.INSTANCE.openEditor(session, createdRepresentation, monitor);
				}
				monitor.worked(100);
				}catch(Exception e) { e.printStackTrace(); }

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

		private static final String PAGE_NAME = "PAGE_NAME";
		private static final String INITIAL_FILE_NAME = "newSystem";
		private static final String FILE_EXTENSION = "system"; // $NON-NLS-N$
		private static final String MESSAGE = "Chose a file name and location";

		public SystemModelCreationPage(IStructuredSelection selection) {
			super(PAGE_NAME, selection);
			setFileName(INITIAL_FILE_NAME);
			setMessage(MESSAGE);
			setFileExtension(FILE_EXTENSION);
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
			final Composite composite = new Composite(parent, SWT.BORDER);
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
