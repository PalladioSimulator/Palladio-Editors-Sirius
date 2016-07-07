package org.palladiosimulator.editors.repository.custom.externaljavaactions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.DataType;

public class SetInnerType implements IExternalJavaAction {

	@Override
	public void execute(Collection<? extends EObject> selections, Map<String, Object> parameters) {
		CollectionDataType cdt = (CollectionDataType) parameters.get("collection");
		DataType dt = DataTypeSelectionWizard.selectDataType(cdt);
		if (dt != null)
			cdt.setInnerType_CollectionDataType(dt);

	}

	@Override
	public boolean canExecute(Collection<? extends EObject> selections) {
		return true;
	}

}
