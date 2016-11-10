package org.palladiosimulator.editors.sirius.allocation.custom.wizard;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.action.LoadResourceAction.LoadResourceDialog;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;


/**
 * This wizard allows the user to select a resource. It can be passed an already
 * selected resource which then can be changed. A value of null will leave it up
 * to the user to locate the resource.
 * 
 * The dialog will only succeed if a valid resource file has been selected.
 */
class ResourceSelectorPage extends WizardPage {

	/**
	 * The selection text.
	 */
	private Text mySelectionText;

	/**
	 * The resolved object.
	 */
	private EObject myResolvedObject;

	private String path;


	/**
	 * @return a valid resource, or null if no valid one has been selected
	 */
	protected EObject getResolvedObject() {
		return myResolvedObject;
	}
	
	protected EObject getResult(Session session) {
		EObject result = null;
        URI uri = URI.createURI(path, true);
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        ResourceSet resourceSet = domain.getResourceSet();
        try {
            Resource resource = resourceSet.getResource(uri, true);
            result = (EObject) resource.getContents().get(0);
        } catch (WrappedException ex) {
            // do nothing
        }
        return result;
	}

	/**
	 * Creates the resource selector page.
	 * 
	 * @param name
	 *            a name
	 */
	protected ResourceSelectorPage(String name) {
		super(name);
		setTitle("Diagram resource");
		setDescription("Select the resource to be used for the diagram.");
	}

	/**
	 * Creates a control.
	 * 
	 * @param parent
	 *            a Composite
	 */
	public void createControl(Composite parent) {
		initializeDialogUnits(parent);
		Composite topLevel = new Composite(parent, SWT.NONE);
		topLevel.setLayout(new GridLayout());
		topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		topLevel.setFont(parent.getFont());
		setControl(topLevel);
		createPageContent(topLevel);
		setPageComplete(doValidatePage());
	}

	/**
	 * creates the SWT widgets used to select and display the resource and
	 * initializes them.
	 * 
	 * @param parent
	 *            a Composite
	 */
	private void createPageContent(Composite parent) {
		Composite panel = new Composite(parent, SWT.NONE);
		panel.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout();
		panel.setLayout(layout);

		Label label = new Label(panel, SWT.NONE);
		label.setText("Selected resource:");
		label.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));

		mySelectionText = new Text(panel, SWT.READ_ONLY);
		mySelectionText.setText("none");
		if (myResolvedObject != null) {
			mySelectionText.setText(myResolvedObject.eResource().getURI().path());
		}
		mySelectionText.setLayoutData(new GridData(GridData.CENTER));
		mySelectionText.pack();

		Button button = new Button(panel, SWT.NONE);
		button.setText("Change");
		button.setLayoutData(new GridData(GridData.END));

		button.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.
			 * eclipse.swt.events. SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent e) {
				Shell shell = e.display.getActiveShell();
				LoadResourceDialog dialog = new LoadResourceDialog(shell);
				dialog.open();
				String uri = dialog.getURIText();
				if (uri != null) {
					mySelectionText.setText(dialog.getURIText());
				}
				mySelectionText.pack();
                resolveSelection();
				setPageComplete(validatePage());
			}

		});
	}

	/**
	 * @return true, if a valid resource file has been selected and resolved
	 */
	protected boolean validatePage() {
		if (myResolvedObject == null) {
			setErrorMessage("No or invalid resource selected");
			return false;
		}

		setErrorMessage(null);
		return true;
	}

	/**
	 * Validates a page.
	 * 
	 * @return a boolean value
	 */
	protected boolean doValidatePage() {
		return (myResolvedObject != null);
	}
	
    /**
     * checks if the selected file is a valid resource.
     */
    private void resolveSelection() {
        myResolvedObject = null;
        URI uri = URI.createURI(mySelectionText.getText(), true);
        TransactionalEditingDomain editingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain();
        ResourceSet resourceSet = editingDomain.getResourceSet();

        try {
            Resource resource = resourceSet.getResource(uri, true);
            myResolvedObject = (EObject) resource.getContents().get(0);
            path = mySelectionText.getText();

        } catch (WrappedException ex) {
            // do nothing
        }
       
    }

}