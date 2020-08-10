package org.palladiosimulator.editors.sirius.repository;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Signature;

public class Services {
	public Services() {

	}

	public String printParameters(Signature sig) {
		String result = "";
		EList<Parameter> parameters = null;
		if (sig instanceof OperationSignature)
			parameters = ((OperationSignature) sig).getParameters__OperationSignature();
		else if(sig instanceof InfrastructureSignature) {
			parameters = ((InfrastructureSignature) sig).getParameters__InfrastructureSignature();
		}
		if(parameters == null) // invalid list
			return "";
		
		for (Parameter p : parameters) {
			DataType parameterType = p.getDataType__Parameter();
			String type = getDataTypeName(parameterType);
			result += type + " " + p.getParameterName() + ", ";
		}
		if (result.endsWith(", "))
			result = result.substring(0, result.length() - 2);
		return result;
	}
	
	public String getDataTypeName(DataType dataType) {
		String type = "";
		if (dataType == null) {
			type = "UNSPECIFIED DATATYPE";
		}
		else if (dataType instanceof PrimitiveDataType) {
			type = ((PrimitiveDataType) dataType).getType().name().toLowerCase();
		} else if ((dataType instanceof CollectionDataType) && ((CollectionDataType) dataType)
				.getInnerType_CollectionDataType() instanceof PrimitiveDataType) {
			type = ((PrimitiveDataType) ((CollectionDataType) dataType).getInnerType_CollectionDataType()).getType().name().toLowerCase() + "[]";
		} else {
			type = ((NamedElement) dataType).getEntityName();
		}

		return type;
	}
	
	// Returns true if s is a legal Java identifier.
    public Boolean isJavaIdentifier(EObject eObject, String s) {
        if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
            return false;
        }
        for (int i=1; i < s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    

}
