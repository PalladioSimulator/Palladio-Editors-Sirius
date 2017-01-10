package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetLinkingResourceLatency extends SetRandomVariable {

    @Override
    public TypeEnum getExpectedType() {
        return TypeEnum.ANY;
    }

    @Override
    public RandomVariable getRandomVariable(EObject element) {
        CommunicationLinkResourceSpecification specification = (CommunicationLinkResourceSpecification) element;
        return specification.getLatency_CommunicationLinkResourceSpecification();
    }

}
