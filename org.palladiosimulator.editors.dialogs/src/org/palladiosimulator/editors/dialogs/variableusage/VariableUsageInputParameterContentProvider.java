package org.palladiosimulator.editors.dialogs.variableusage;

import java.util.ArrayList;

import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.ParameterModifier;

// TODO: Auto-generated Javadoc
/**
 * This content provider collects the parameters of a parent object to be used in a variable usage.
 * 
 * @author Benjamin Klatt
 * @author groenda
 */
public class VariableUsageInputParameterContentProvider extends VariableUsageContentProvider {

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
             * Operation Signature Parameter
             */
            OperationSignature signature = (OperationSignature) parent;
            ArrayList<Parameter> inputParameter = new ArrayList<Parameter>();
            for (Parameter p : signature.getParameters__OperationSignature()) {
                if (p.getModifier__Parameter() == ParameterModifier.NONE
                        || p.getModifier__Parameter() == ParameterModifier.IN
                        || p.getModifier__Parameter() == ParameterModifier.INOUT) {
                    inputParameter.add(p);
                }
            }
            return inputParameter.toArray();
        } else if (parent instanceof EventType) {
            /*
             * Event Type Parameter
             */
            EventType eventType = (EventType) parent;
            return new Object[] { eventType.getParameter__EventType() };
        } else if (parent instanceof InfrastructureSignature) {
            /*
             * Infrastructure Signature Parameters
             */
            InfrastructureSignature signature = (InfrastructureSignature) parent;
            ArrayList<Parameter> inputParameter = new ArrayList<Parameter>();
            for (Parameter p : signature.getParameters__InfrastructureSignature()) {
                if (p.getModifier__Parameter() == ParameterModifier.NONE
                        || p.getModifier__Parameter() == ParameterModifier.IN
                        || p.getModifier__Parameter() == ParameterModifier.INOUT) {
                    inputParameter.add(p);
                }
            }
            return inputParameter.toArray();
        } else {
            return super.getChildren(parent);
        }
    }

}
