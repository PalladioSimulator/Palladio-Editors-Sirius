package org.palladiosimulator.editors.sirius.repository;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.PrimitiveDataType;
import org.palladiosimulator.pcm.repository.Signature;

public class ParametersService {
	public ParametersService() {

	}

	public String getParameters(Signature sig) {
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
			String type = "";
			DataType parameterType = p.getDataType__Parameter();
			if (parameterType == null) {
				type = "UNSPECIFIED DATATYPE";
			}
			else if (parameterType instanceof PrimitiveDataType) {
				type = ((PrimitiveDataType) parameterType).getType().name().toLowerCase();
			} else if ((parameterType instanceof CollectionDataType) && ((CollectionDataType) parameterType)
					.getInnerType_CollectionDataType() instanceof PrimitiveDataType) {
				type = ((PrimitiveDataType) ((CollectionDataType) parameterType).getInnerType_CollectionDataType()).getType().name().toLowerCase() + "[]";
			} else {
				type = ((NamedElement) parameterType).getEntityName();
			}

			result += type + " " + p.getParameterName() + ", ";
		}
		if (result.endsWith(", "))
			result = result.substring(0, result.length() - 2);
		return result;
	}
}
