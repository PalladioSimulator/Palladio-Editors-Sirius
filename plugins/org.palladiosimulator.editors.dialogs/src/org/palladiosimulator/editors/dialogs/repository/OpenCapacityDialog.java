package org.palladiosimulator.editors.dialogs.repository;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.dialogs.OpenStoExDialog;
import org.palladiosimulator.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenCapacityDialog.
 */
public class OpenCapacityDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        PassiveResource pr = (PassiveResource) parent;
        RandomVariable rv = pr.getCapacity_PassiveResource();
        return rv;
    }

    /**
     * Gets the expected type.
     *
     * @param rv the rv
     * @return the expected type
     * @see org.palladiosimulator.editors.dialogs.OpenStoExDialog#getExpectedType(de.uka.ipd.sdq.stoex.RandomVariable)
     */
    @Override
    protected TypeEnum getExpectedType(RandomVariable rv) {
        return TypeEnum.INT;
    }

    /**
     * Gets the dialog message.
     *
     * @return the dialog message
     * @see org.palladiosimulator.editors.dialogs.OpenStoExDialog#getDialogMessage()
     */
    @Override
    protected String getDialogMessage() {
        return "Please enter the new capacity of the Passive Resource";
    }

}
