package org.palladiosimulator.editors.dialogs.usage;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenThinkTimeDialog.
 */
public class OpenThinkTimeDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.usage.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        ClosedWorkload cw = (ClosedWorkload) parent;
        RandomVariable rv = cw.getThinkTime_ClosedWorkload();
        return rv;
    }

    /**
     * Gets the expected type.
     *
     * @param rv the rv
     * @return the expected type
     * @see org.palladiosimulator.editors.dialogs.usage.OpenStoExDialog#getExpectedType(de.uka.ipd.sdq.stoex.RandomVariable)
     */
    @Override
    protected TypeEnum getExpectedType(RandomVariable rv) {
        return TypeEnum.DOUBLE;
    }

}
