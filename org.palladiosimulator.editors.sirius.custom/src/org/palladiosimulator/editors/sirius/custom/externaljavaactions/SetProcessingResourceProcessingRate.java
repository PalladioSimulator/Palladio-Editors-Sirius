package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetProcessingResourceProcessingRate extends SetRandomVariable {

    @Override
    public TypeEnum getExpectedType() {
        return TypeEnum.ANY;
    }

    @Override
    public RandomVariable getRandomVariable(EObject element) {
        ProcessingResourceSpecification specification = (ProcessingResourceSpecification) element;
        return specification.getProcessingRate_ProcessingResourceSpecification();
    }

}
