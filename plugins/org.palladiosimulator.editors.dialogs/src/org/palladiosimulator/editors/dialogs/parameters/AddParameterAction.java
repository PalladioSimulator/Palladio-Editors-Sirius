package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * The class define an action, which a new parameter for the signature adds.
 * 
 * @author Roman Andrej
 */
public class AddParameterAction extends SelectionAdapter {

    /** Default name of a newly created parameter. Not guaranteed to be unique. */
    private static final String DEFAULT_PARAMETER_NAME = "parameter";

    /** The parent signature. */
    private Signature parentSignature = null;
    
    /** The parameter name. */
    private String parameterName = DEFAULT_PARAMETER_NAME;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    private TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new adds the parameter action.
     *
     * @param parentSignature the parent signature
     */
    public AddParameterAction(Signature parentSignature) {
        this.parentSignature = parentSignature;
        this.editingDomain = TransactionUtil.getEditingDomain(parentSignature);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent
     * )
     */
    @Override
    public void widgetSelected(SelectionEvent e) {
        Assert.isNotNull(parentSignature);

        final EList<Parameter> parameters = ParametersUtil.getParametersOfSignature(parentSignature);

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                Parameter parameter = RepositoryFactory.eINSTANCE.createParameter();
                parameter.setParameterName(parameterName + parameters.size());
                parameters.add(parameter);
            }
        };

        recCommand.setDescription("Add new parameter to the signature");
        editingDomain.getCommandStack().execute(recCommand);
    }
}