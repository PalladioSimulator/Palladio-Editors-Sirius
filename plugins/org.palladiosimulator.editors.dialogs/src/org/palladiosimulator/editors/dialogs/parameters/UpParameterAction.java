package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * This adapter class provides default implementations for the methods described by the
 * SelectionListener interface to upItem - Button in the ParameterDialog.
 * 
 * @author Roman Andrej
 */
public class UpParameterAction extends EditorContentsSelectionAction implements SelectionListener {

    /** The parent signature. */
    private Signature parentSignature;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    protected TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new up parameter action.
     * 
     * @param signature
     *            the signature
     */
    public UpParameterAction(Signature signature) {
        this.parentSignature = signature;
        this.editingDomain = TransactionUtil.getEditingDomain(signature);
    }

    /**
     * Widget selected.
     * 
     * @param e
     *            the e
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent
     *      )
     */
    public void widgetSelected(SelectionEvent e) {
        final Parameter selectedParameter = (Parameter) getSelectedDeclaration();
        final EList<Parameter> parameters = ParametersUtil.getParametersOfSignature(parentSignature);

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                int index = parameters.indexOf(selectedParameter);
                if (index != 0) {
                    parameters.move(index, index - 1);
                    try {
                        UpDownButtonsValidator.getSingelton().validate(index - 1, parameters.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        recCommand.setDescription("Up ...");
        editingDomain.getCommandStack().execute(recCommand);
    }

    /**
     * Widget default selected.
     * 
     * @param e
     *            the e
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
        // TODO Auto-generated method stub

    }
}
