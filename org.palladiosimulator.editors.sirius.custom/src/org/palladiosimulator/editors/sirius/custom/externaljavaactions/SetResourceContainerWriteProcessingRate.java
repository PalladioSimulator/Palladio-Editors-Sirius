package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.resourceenvironment.HDDProcessingResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetResourceContainerWriteProcessingRate extends SetRandomVariable {

    @Override
    public TypeEnum getExpectedType() {
        return TypeEnum.ANY;
    }

    @Override
    public RandomVariable getRandomVariable(EObject element) {
        HDDProcessingResourceSpecification specification = (HDDProcessingResourceSpecification) element;
        return specification.getWriteProcessingRate();
    }

}
