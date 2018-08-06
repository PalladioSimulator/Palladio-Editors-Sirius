package org.palladiosimulator.editors.sirius.ui.wizard.model;

import org.eclipse.jface.wizard.WizardPage;
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

public class RepresentationCreationPage extends WizardPage implements SelectionListener, ModifyListener {
    private static final String PAGE_NAME = "Model creation";
    private static final String ENABLE = "Create representation";
    private static final String REPRESENTATION_NAME = "Representation name:";
    private static final String TITLE = "Create representation";
    private static final String MESSAGE = "Select whether you want to create a representation";
    private String DEFAULT_REPRESENTATION_NAME = "new representation";

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
    
    public void setDefaultRepresentationName(String defaultRepresentationName) {
    	this.DEFAULT_REPRESENTATION_NAME = defaultRepresentationName;
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
