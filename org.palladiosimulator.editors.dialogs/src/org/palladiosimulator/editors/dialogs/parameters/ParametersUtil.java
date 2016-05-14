/**
 * 
 */
package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.emf.common.util.EList;

import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Signature;

// TODO: Auto-generated Javadoc
/**
 * Utility class for parameter handling.
 * 
 * @author groenda
 * 
 */
public class ParametersUtil {

    /**
     * Returns all parameters of a given signature.
     * 
     * @param signature
     *            The signature.
     * @return The parameters.
     */
    public static EList<Parameter> getParametersOfSignature(final Signature signature) {
        if (signature instanceof OperationSignature) {
            return ((OperationSignature) signature).getParameters__OperationSignature();
        }
        if (signature instanceof InfrastructureSignature) {
            return ((InfrastructureSignature) signature).getParameters__InfrastructureSignature();
        }
        throw new IllegalArgumentException("Unkown signature type experienced: " + signature.eClass().getName());
    }
}
