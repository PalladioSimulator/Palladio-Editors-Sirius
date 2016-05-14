package org.palladiosimulator.editors.dialogs.usage;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.usagemodel.Delay;
import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

// TODO: Auto-generated Javadoc
//Manually written open policy to open the StoEx Dialog. It's
//called via a CustomBehaviour in the genmap
/**
 * The Class DelaySpecificationDialog.
 */
public class DelaySpecificationDialog extends OpenStoExDialog {

    /**
     * Gets the random variable.
     *
     * @param parent the parent
     * @return the random variable
     * @see org.palladiosimulator.editors.dialogs.usage.OpenStoExDialog#getRandomVariable(org.eclipse.emf.ecore.EObject)
     */
    @Override
    protected RandomVariable getRandomVariable(EObject parent) {
        Delay d = (Delay) parent;
        RandomVariable rv = d.getTimeSpecification_Delay();
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
