package org.palladiosimulator.editors.sirius.ui.wizard.project;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.palladiosimulator.architecturaltemplates.AT;
import org.palladiosimulator.architecturaltemplates.Catalog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (.repository).
 */

public class NewPalladioTemplateWizardPage extends WizardPage implements ISelectionChangedListener {
	protected TreeViewer wizardTreeViewer;
	private DescriptionBox descriptionBrowser;
	private final Set<AT> initiatorATs;
	private final Map<Catalog, List<AT>> allInitiatorATs;

	private AT selectedTemplate;
	private boolean useTemplate;
	private boolean advancedView;

	private SashForm sashForm;
	private Button useTemplateCheckBox;
	private Button advancedViewCheckBox;

	// see
	// https://github.com/PalladioSimulator/Palladio-Addon-ArchitecturalTemplates/blob/master/bundles/
	// org.palladiosimulator.architecturaltemplates.catalog/Default.architecturaltemplates
	private final String DEFAULT_CATALOG_ID = "_2eRmoCOzEeSLXszsY50NUA";

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewPalladioTemplateWizardPage(final Set<AT> initiatorATs) {
		super("Architectural Template Selection");
		setTitle("Initiator Architectural Template Selection");
		setDescription("Select a template to create an initial Palladio model.");

		this.allInitiatorATs = initiatorATs.stream().collect(Collectors.groupingBy(it -> it.getCatalog()));
		this.initiatorATs = allInitiatorATs.entrySet().stream()
				.filter(it -> it.getKey().getId().equals(DEFAULT_CATALOG_ID)).map(it -> Set.copyOf(it.getValue()))
				.findAny().orElse(Collections.emptySet());

		this.useTemplate = false;
		this.advancedView = false;
	}

	public class TreeContentProvider implements ITreeContentProvider {
		@Override
		public boolean hasChildren(Object element) {
			return (element instanceof Map.Entry<?, ?>);
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Map) {
				var typedInputElement = (Map<Catalog, List<AT>>) inputElement;
				return ArrayContentProvider.getInstance().getElements(typedInputElement.entrySet());
			}

			return ArrayContentProvider.getInstance().getElements(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			var typedParentElement = (Map.Entry<Catalog, List<AT>>) parentElement;
			return ArrayContentProvider.getInstance().getElements(typedParentElement.getValue());
		}
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	@Override
	public void createControl(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		useTemplateCheckBox = new Button(container, useTemplate ? (SWT.CHECK | SWT.SELECTED) : SWT.CHECK);
		useTemplateCheckBox.setText("Create a Palladio project using one of the initiator templates.");

		advancedViewCheckBox = new Button(container, advancedView ? (SWT.CHECK | SWT.SELECTED) : SWT.CHECK);
		advancedViewCheckBox.setText("Show advanced view (all examples).");

		GridData gd = new GridData();

		sashForm = new SashForm(container, SWT.HORIZONTAL);
		gd = new GridData(GridData.FILL_BOTH);
		// limit the width of the sash form to avoid the wizard
		// opening very wide. This is just preferred size -
		// it can be made bigger by the wizard
		// See bug #83356
		gd.widthHint = 300;

		gd.heightHint = 300;
		sashForm.setLayoutData(gd);

		useTemplateCheckBox
				.addSelectionListener(SelectionListener.widgetSelectedAdapter(this::updateEnabledStateOfSelection));
		advancedViewCheckBox
				.addSelectionListener(SelectionListener.widgetSelectedAdapter(this::updateWizardTreeViewerInput));

		this.wizardTreeViewer = new TreeViewer(sashForm, SWT.BORDER);
		this.wizardTreeViewer.setContentProvider(new TreeContentProvider());
		updateWizardTreeViewerInput(null);
		this.wizardTreeViewer.setLabelProvider(new TemplateTreeLabelProvider());
		this.wizardTreeViewer.setComparator(new ViewerComparator());
		this.wizardTreeViewer.addSelectionChangedListener(this);

		createDescriptionIn(sashForm);
		setControl(container);

		// set correct colors and enabled state for initial state of useTemplate
		updateEnabledStateOfSelection(null);
	}

	private void updateWizardTreeViewerInput(SelectionEvent event) {
		wizardTreeViewer.setInput(advancedViewCheckBox.getSelection() ? allInitiatorATs : initiatorATs);
	}

	private void updateEnabledStateOfSelection(SelectionEvent event) {
		useTemplate = useTemplateCheckBox.getSelection();
		Color color = useTemplate //
				? getCurrentDisplayColor(SWT.COLOR_LIST_BACKGROUND) //
				: getCurrentDisplayColor(SWT.COLOR_TEXT_DISABLED_BACKGROUND);

		sashForm.setEnabled(useTemplate);
		wizardTreeViewer.getTree().setBackground(color);
		descriptionBrowser.formText.setBackground(color);
	}

	private Color getCurrentDisplayColor(int colorId) {
		return Display.getCurrent().getSystemColor(colorId);
	}

	/**
	 * Create the description box in the wizard.
	 * 
	 * @param composite The parent component to place in.
	 */
	public void createDescriptionIn(final Composite composite) {
		this.descriptionBrowser = new DescriptionBox(SWT.FILL);
		this.descriptionBrowser.createControl(composite);
		final Control c = this.descriptionBrowser.getControl();
		final GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 200;
		c.setLayoutData(gd);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void selectionChanged(final SelectionChangedEvent event) {
		setErrorMessage(null);
		final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		AT currentTemplateSelection = null;
		final Iterator iter = selection.iterator();
		if (iter.hasNext()) {
			Object nextElement = iter.next();
			if (nextElement instanceof AT) {
				currentTemplateSelection = (AT) nextElement;
			}
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
	 * 
	 * @param template
	 */
	private void setSelectedTemplate(final AT template) {
		this.selectedTemplate = template;
		if (template == null || template.getDocumentation() == null) {
			setDescriptionText("");
		} else {
			setDescriptionText(template.getDocumentation());
		}
	}

	/**
	 * Set the description in the description area.
	 * 
	 * @param text The text to set.
	 */
	public void setDescriptionText(String text) {
		if (text == null) {
			text = "";
		}
		this.descriptionBrowser.setText(text);
	}

	/**
	 * @return the selectedTemplate
	 */
	public AT getSelectedTemplate() {
		return this.selectedTemplate;
	}

	public boolean getUseTemplate() {
		return this.useTemplate;
	}
}