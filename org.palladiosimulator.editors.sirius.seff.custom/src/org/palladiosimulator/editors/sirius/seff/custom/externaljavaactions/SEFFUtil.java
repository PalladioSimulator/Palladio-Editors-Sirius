package org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;

public class SEFFUtil {

    public static ServiceEffectSpecification getEnclosingSEFF(EObject eObject) {
        if (eObject == null)
            throw new IllegalArgumentException("The specified element (e.g. ResourceDemandingBehavior) is not contained inside a "
                    + "ServiceEffectSpecification nor is itself a ServiceEffectSpecification");
        else if (eObject instanceof ServiceEffectSpecification)
            return (ServiceEffectSpecification) eObject;
        else {
            return getEnclosingSEFF(eObject.eContainer());
        }
    }
}
