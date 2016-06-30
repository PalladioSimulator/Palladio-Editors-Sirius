package org.palladiosimulator.editors.dialogs.usage;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.stoex.RandomVariable;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenVariableCharacterisationDialog.
 */
public class OpenVariableCharacterisationDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.usage.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        VariableCharacterisation cw = (VariableCharacterisation) parent;
        RandomVariable rv = cw.getSpecification_VariableCharacterisation();
        return rv;
    }
}
