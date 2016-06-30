package org.palladiosimulator.editors.dialogs.variableusage;

import java.util.ArrayList;

import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.ParameterModifier;
import org.palladiosimulator.pcm.repository.RepositoryFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class VariableUsageOutputParameterContentProvider.
 */
public class VariableUsageOutputParameterContentProvider extends VariableUsageContentProvider {

    /**
     * Gets the children.
     *
     * @param parent the parent
     * @return the children
     * @see org.palladiosimulator.editors.dialogs.variableusage.VariableUsageContentProvider#getChildren(java.lang.Object)
     */
    @Override
    public Object[] getChildren(Object parent) {
        if (parent instanceof OperationSignature) {
            /*
             * Business Operation Signature
             */
            OperationSignature signature = (OperationSignature) parent;
            ArrayList<Parameter> outputParameter = new ArrayList<Parameter>();
            for (Parameter p : signature.getParameters__OperationSignature()) {
                if (p.getModifier__Parameter() == ParameterModifier.OUT
                        || p.getModifier__Parameter() == ParameterModifier.INOUT) {
                    outputParameter.add(p);
                }
            }
            if (signature.getReturnType__OperationSignature() != null) {
                Parameter ret = RepositoryFactory.eINSTANCE.createParameter();
                ret.setDataType__Parameter(signature.getReturnType__OperationSignature());
                ret.setParameterName("RETURN");
                outputParameter.add(ret);
            }
            return outputParameter.toArray();
        } else if (parent instanceof InfrastructureSignature) {
            // There are no output parameters for infrastructure signature.
            return new ArrayList<Parameter>().toArray();
        } else {
            return super.getChildren(parent);
        }
    }

}
