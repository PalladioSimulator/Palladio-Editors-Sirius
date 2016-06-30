package org.palladiosimulator.editors.dialogs.seff;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.editors.dialogs.OpenStoExDialog;
import org.palladiosimulator.pcm.seff.GuardedBranchTransition;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenBranchConditionDialog.
 */
public class OpenBranchConditionDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        GuardedBranchTransition transition = (GuardedBranchTransition) parent;
        RandomVariable rv = transition.getBranchCondition_GuardedBranchTransition();
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
        return TypeEnum.BOOL;
    }

}
