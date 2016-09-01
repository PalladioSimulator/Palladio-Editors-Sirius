package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (.repository).
 */

public class NewPalladioTemplateWizardPage extends WizardPage implements ISelectionChangedListener {

	protected TableViewer wizardSelectionViewer;
	private DescriptionBox descriptionBrowser;
	private Set<PalladioTemplate> availableTemplates;

	private PalladioTemplate selectedTemplate;

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewPalladioTemplateWizardPage(Set<PalladioTemplate> availableTemplates) {
		super("Template Selection");
		setTitle("Palladio Model Template Selection");
		setDescription("Select a template to create an initial Palladio model.");
		this.availableTemplates = availableTemplates;
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(container, SWT.NONE);
		label.setText(getTitle());
		GridData gd = new GridData();
		label.setLayoutData(gd);

		SashForm sashForm = new SashForm(container, SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_BOTH);
		// limit the width of the sash form to avoid the wizard
		// opening very wide. This is just preferred size - 
		// it can be made bigger by the wizard
		// See bug #83356
		gd.widthHint = 300;
		sashForm.setLayoutData(gd);

		wizardSelectionViewer = new TableViewer(sashForm, SWT.BORDER);
		wizardSelectionViewer.setContentProvider(ArrayContentProvider.getInstance());
		wizardSelectionViewer.setLabelProvider(new TemplateLabelProvider());
		
		createDescriptionIn(sashForm);
		wizardSelectionViewer.setInput(availableTemplates);
		wizardSelectionViewer.addSelectionChangedListener(this);
		setControl(container);
	}

	/**
	 * Create the description box in the wizard.
	 * 
	 * @param composite The parent component to place in.
	 */
	public void createDescriptionIn(Composite composite) {
		descriptionBrowser = new DescriptionBox(SWT.FILL);
		descriptionBrowser.createControl(composite);
		Control c = descriptionBrowser.getControl();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 200;
		c.setLayoutData(gd);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		setErrorMessage(null);
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		PalladioTemplate currentTemplateSelection = null;
		Iterator iter = selection.iterator();
		if (iter.hasNext()) {
			currentTemplateSelection = (PalladioTemplate) iter.next();
		}
		if (currentTemplateSelection == null) {
			setSelectedTemplate(null);
			return;
		}
		setSelectedTemplate(currentTemplateSelection);
		getContainer().updateButtons();
	}

	/**
	 * Set the selected template.
	 * @param template
	 */
	private void setSelectedTemplate(PalladioTemplate template) {
		this.selectedTemplate = template;
		if(template == null || template.getDescription() == null){
			setDescriptionText("");
		} else {
			setDescriptionText(template.getDescription());
		}
	}

	/**
	 * Set the description in the description area.
	 * @param text The text to set.
	 */
	public void setDescriptionText(String text) {
		if (text == null) {
			text = "";
		}
		descriptionBrowser.setText(text);
	}
	
	/**
	 * @return the selectedTemplate
	 */
	public PalladioTemplate getSelectedTemplate() {
		//return availableTemplates.iterator().next();
		return selectedTemplate;
	}
}