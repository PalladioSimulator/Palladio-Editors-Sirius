package org.palladiosimulator.editors.dialogs.exception;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.palladiosimulator.pcm.repository.ExceptionType;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * The Class AddExceptionTypeAction.
 *
 * @author roman
 */
public class AddExceptionTypeAction extends SelectionAdapter {

    /** The parent signature. */
    private Signature parentSignature;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    protected TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new adds the exception type action.
     *
     * @param parentSignature the parent signature
     */
    public AddExceptionTypeAction(Signature parentSignature) {
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

        final EList<ExceptionType> exceptions = parentSignature.getExceptions__Signature();

        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                ExceptionType exceptionType = RepositoryFactory.eINSTANCE.createExceptionType();
                exceptionType.setExceptionName("ExceptionName" + (exceptions.size() + 1));
                exceptions.add(exceptionType);
            }
        };

        recCommand.setDescription("Add new ExceptionType to the signature");
        editingDomain.getCommandStack().execute(recCommand);
    }
}
