package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;

public class SetInnerDeclaration implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		InnerDeclaration innerDeclaration = (InnerDeclaration) parameters.get("innerDeclaration");
		DataType dt = DataTypeSelectionWizard.selectDataType(innerDeclaration);
		if (dt != null)
			innerDeclaration.setDatatype_InnerDeclaration(dt);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
