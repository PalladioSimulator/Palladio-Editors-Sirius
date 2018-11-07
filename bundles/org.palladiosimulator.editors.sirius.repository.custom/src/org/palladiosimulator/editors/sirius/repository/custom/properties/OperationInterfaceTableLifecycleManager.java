package org.palladiosimulator.editors.sirius.repository.custom.properties;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.editors.commons.tabs.operations.OperationsTabItemProviderAdapterFactory;
import org.palladiosimulator.editors.sirius.repository.custom.properties.editorsections.OperationsEditorSection;
import org.palladiosimulator.pcm.repository.OperationInterface;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

public class OperationInterfaceTableLifecycleManager extends TableLifecycleManager {

	public OperationInterfaceTableLifecycleManager(EEFCustomWidgetDescription description,
			IVariableManager variableManager, IInterpreter interpreter, EditingContextAdapter contextAdapter) {
		super(description, variableManager, interpreter, contextAdapter);
	}

	@Override
	protected void setPropertySection(Composite parent) {
		editorSection = new OperationsEditorSection(parent);
		editorSection
				.setViewerContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		editorSection
				.setViewerLabelProvider(new AdapterFactoryLabelProvider(
						new OperationsTabItemProviderAdapterFactory(
								new PalladioItemProviderAdapterFactory(
										adapterFactory))));
		
		
		OperationInterface input = (OperationInterface) controller.getValue();
		editorSection.setViewerInput(input);
		((OperationsEditorSection) editorSection).getAddButtonListener().setSelectedInterface(input);

	}

}
