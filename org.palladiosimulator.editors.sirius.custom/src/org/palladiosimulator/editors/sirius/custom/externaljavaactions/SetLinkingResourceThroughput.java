package org.palladiosimulator.editors.sirius.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;

import de.uka.ipd.sdq.stoex.RandomVariable;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class SetLinkingResourceThroughput extends SetRandomVariable {

    @Override
    public RandomVariable getRandomVariable(EObject element) {
        CommunicationLinkResourceSpecification linkResource = (CommunicationLinkResourceSpecification) element;
        return linkResource.getThroughput_CommunicationLinkResourceSpecification();
    }
    @Override
    public TypeEnum getExpectedType() {
        return TypeEnum.ANY;
    }

}
