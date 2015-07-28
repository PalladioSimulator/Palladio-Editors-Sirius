package org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.wizards;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
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
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.ComposedProvidingRequiringEntityEditorConstants;
import org.palladiosimulator.editors.gmf.runtime.diagram.ui.extension.ExtensionActivator;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.pcm.system.SystemFactory;

/**
 * Wizard for creating a {@link System}-model. Allows creating a corresponding
 * Sirius representation.
 * 
 * @author Max Schettler
 *
 */
public class SystemCreationWizard extends Wizard implements INewWizard {

	private static final String NEW_SYSTEM_NAME = "defaultSystem";
	private static final String WINDOW_TITLE = "Create System";
	private static final String RESOURCE_CREATION_ERROR_TITLE = "Operation failed";
	private static final String RESOURCE_CREATION_ERROR_MESSAGE = "Error while creating new System-resource";

	private SystemModelCreationPage fileCreationPage;
	private RepresentationCreationPage representationCreationPage;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		final URI systemURI = fileCreationPage.getPlatformURI();

		final IRunnableWithProgress operation = representationCreationPage.isRepresentationCreationEnabled()
				? new CreateSystemResourceAndRepresentationOperation(systemURI, representationCreationPage.getRepresentationName())
				: new CreateSystemResourceAndRepresentationOperation(systemURI);

		try {
			this.getContainer().run(false, false, operation);
		} catch (InvocationTargetException | InterruptedException e) {
			final ErrorDialog errorDialog = new ErrorDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), RESOURCE_CREATION_ERROR_TITLE,
					RESOURCE_CREATION_ERROR_MESSAGE,
					new Status(Status.ERROR, ExtensionActivator.PLUGIN_ID, RESOURCE_CREATION_ERROR_MESSAGE, e), 0);
			errorDialog.open();
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
	 * TODO check whether the selected file is inside of a modelling project
	 * 
	 * @author Max Schettler
	 *
	 */
	private class SystemModelCreationPage extends WizardNewFileCreationPage {

		private static final String PAGE_NAME = "PAGE_NAME";
		private static final String INITIAL_FILE_NAME = "newSystem";
		private static final String FILE_EXTENSION = "system"; // $NON-NLS-N$

		public SystemModelCreationPage(IStructuredSelection selection) {
			super(PAGE_NAME, selection);
			setFileName(INITIAL_FILE_NAME);
			setFileExtension(FILE_EXTENSION);
		}

		public URI getPlatformURI() {
			return URI.createPlatformResourceURI(getContainerFullPath().append(getFileName()).toOSString(), true);
		}

	}

	/**
	 * Class for selecting a representation name. TODO make only enabled if the
	 * selected project is an modelling project
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
				enabledCheckbox.setSelection(false);
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
			}

			setControl(composite);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			if (enabledCheckbox.equals(e.getSource())) {
				representationComposite.setEnabled(((Button) e.getSource()).getSelection());
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

	private class CreateSystemResourceAndRepresentationOperation extends WorkspaceModifyOperation {

		private final URI systemURI;
		private final String representationName;

		public CreateSystemResourceAndRepresentationOperation(final URI systemURI) {
			this.systemURI = systemURI;
			this.representationName = null;
		}

		public CreateSystemResourceAndRepresentationOperation(final URI systemURI, final String representationName) {
			this.systemURI = systemURI;
			this.representationName = representationName;
		}

		@Override
		protected void execute(final IProgressMonitor progressMonitor) {
			try {
final ResourceSet resourceSet = new ResourceSetImpl();
final Resource systemResource = resourceSet.createResource(systemURI);

final System newSystem = SystemFactory.eINSTANCE.createSystem();
newSystem.setEntityName(NEW_SYSTEM_NAME);
systemResource.getContents().add(newSystem);

systemResource.save(Collections.EMPTY_MAP);
progressMonitor.worked(50);

if (representationName != null) {
    final URI representationFileURI = URI
            .createPlatformResourceURI("/" + systemURI.segment(1) + "/representations.aird", true);
    final Session session = SessionManager.INSTANCE.getExistingSession(representationFileURI);
    if (session == null) {
        throw new RuntimeException("Not a modelling project");
    }
    
    Viewpoint systemViewpoint = null; // FIXME expose with a constant
    for (Viewpoint possibleViewpoint : session.getSelectedViewpoints(true)) {
        if (ComposedProvidingRequiringEntityEditorConstants.SYSTEM_DESIGN_NAME.equals(possibleViewpoint.getName())) {
            systemViewpoint = possibleViewpoint;
            break;
        }
    }
    if (systemViewpoint == null) { // FIXME expose with a constant
        throw new RuntimeException("No suitable Viewpoint is registered");
    }
    
    RepresentationDescription representationDescription = null;
    for (RepresentationDescription possibleRepresentationDescription : DialectManager.INSTANCE
            .getAvailableRepresentationDescriptions(
                    Collections
                            .singleton(systemViewpoint),
                    newSystem)) {
        representationDescription = possibleRepresentationDescription;
        break;
    }
    if (representationDescription == null) {
        throw new RuntimeException("No suitable RepresentationDescription has been found");
    }

    DRepresentation representation = DialectManager.INSTANCE.createRepresentation(representationName,
            newSystem, representationDescription, session, progressMonitor);
    
    DialectUIManager.INSTANCE.openEditor(session, representation, progressMonitor);
}

			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				progressMonitor.done();
			}
		}

	}

}
