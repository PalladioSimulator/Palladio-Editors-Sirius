package org.palladiosimulator.editors.sirius.services;

import org.palladiosimulator.pcm.parameter.VariableUsage;

import de.uka.ipd.sdq.stoex.AbstractNamedReference;
import de.uka.ipd.sdq.stoex.NamespaceReference;

public class VariableUsageNameService {
    public VariableUsageNameService() {
    }
    
    public String getFullName(VariableUsage variableUsage) {
        String out = "";
        AbstractNamedReference current = variableUsage.getNamedReference__VariableUsage();
        while (current != null) {
            out += current.getReferenceName();

            if ((current instanceof NamespaceReference)) {
                current = ((NamespaceReference) current).getInnerReference_NamespaceReference();
                out += ".";
            } else {
                current = null;
            }
        }
        return out;
    }
}
