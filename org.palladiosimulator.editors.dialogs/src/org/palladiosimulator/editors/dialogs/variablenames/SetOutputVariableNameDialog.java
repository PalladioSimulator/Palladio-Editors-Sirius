package org.palladiosimulator.editors.dialogs.variablenames;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.uka.ipd.sdq.stoex.AbstractNamedReference;
import de.uka.ipd.sdq.stoex.NamespaceReference;
import de.uka.ipd.sdq.stoex.StoexFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class SetOutputVariableNameDialog.
 */
public class SetOutputVariableNameDialog extends TitleAreaDialog {

    /** The dialog title. */
    private String DIALOG_TITLE = "Enter Local Variable Name for Storing Output Variable";
    
    /** The set name text. */
    private Text setNameText;
    
    /** The result. */
    private String result;

    /**
     * The listener interface for receiving setNameValidation events.
     * The class that is interested in processing a setNameValidation
     * event implements this interface, and the object created
     * with that class is registered with a component using the
     * component's <code>addSetNameValidationListener<code> method. When
     * the setNameValidation event occurs, that object's appropriate
     * method is invoked.
     *
     * @see SetNameValidationEvent
     */
    private class SetNameValidationListener extends SelectionAdapter implements ModifyListener {
        
        /**
         * Modify text.
         *
         * @param e the e
         * @see org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
         */
        public void modifyText(ModifyEvent e) {

            result = setNameText.getText();
        }
    }

    /** The listener. */
    private SetNameValidationListener listener = new SetNameValidationListener();

    /**
     * Instantiates a new sets the output variable name dialog.
     *
     * @param parentShell the parent shell
     */
    public SetOutputVariableNameDialog(Shell parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub

    }

    /**
     * Creates the dialog area.
     *
     * @param parent the parent
     * @return the control
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        // container.setLayout(new FillLayout());
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        this.setTitle(DIALOG_TITLE);

        final GridLayout myGL = new GridLayout();
        myGL.numColumns = 2;
        container.setLayout(myGL);

        Label setNameLabel = new Label(container, SWT.NONE);
        setNameLabel.setText("Enter Local Variable Name:");
        setNameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));

        setNameText = new Text(container, SWT.SINGLE | SWT.BORDER);
        setNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        setNameText.addModifyListener(listener);

        return super.createDialogArea(parent);
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    public String getResult() {
        return result;
    }

    /**
     * Cancel pressed.
     *
     * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
     */
    @Override
    protected void cancelPressed() {
        // TODO Auto-generated method stub
        super.cancelPressed();
        result = null;
    }

    /**
     * Gets the output variable reference.
     *
     * @return the output variable reference
     */
    public AbstractNamedReference getOutputVariableReference() {
        String[] enteredNameSplitted = result.split("\\.");
        AbstractNamedReference namedReference = referenceFactory(enteredNameSplitted[enteredNameSplitted.length - 1],
                true);

        for (int i = enteredNameSplitted.length - 2; i >= 0; i--) {
            NamespaceReference nr = (NamespaceReference) referenceFactory(enteredNameSplitted[i], false);
            nr.setInnerReference_NamespaceReference(namedReference);
            namedReference = nr;
        }
        return namedReference;
    }

    /**
     * Create the AbstractNamedReference and set a string parameter.
     *
     * @param string the string
     * @param shouldGenerateVariableReference the should generate variable reference
     * @return the abstract named reference
     */
    private AbstractNamedReference referenceFactory(String string, boolean shouldGenerateVariableReference) {
        AbstractNamedReference parameterReference = null;
        if (shouldGenerateVariableReference) {
            parameterReference = StoexFactory.eINSTANCE.createVariableReference();
        } else {
            parameterReference = StoexFactory.eINSTANCE.createNamespaceReference();
        }
        parameterReference.setReferenceName(string);
        return parameterReference;
    }

}
