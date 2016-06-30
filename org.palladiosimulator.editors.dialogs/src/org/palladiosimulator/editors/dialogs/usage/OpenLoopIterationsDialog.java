package org.palladiosimulator.editors.dialogs.usage;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.usagemodel.Loop;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
// Manually written open policy to open the StoEx Dialog. It's
// called via a CustomBehaviour in the genmap
/**
 * The Class OpenLoopIterationsDialog.
 */
public class OpenLoopIterationsDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.usage.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        Loop loop = (Loop) parent;
        RandomVariable rv = loop.getLoopIteration_Loop();
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
        return TypeEnum.INT;
    }

}
