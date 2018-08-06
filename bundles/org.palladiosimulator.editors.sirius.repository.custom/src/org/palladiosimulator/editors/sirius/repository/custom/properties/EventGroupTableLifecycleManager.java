package org.palladiosimulator.editors.sirius.repository.custom.properties;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.editors.commons.tabs.operations.EventTypesTabItemProviderAdapterFactory;
import org.palladiosimulator.editors.sirius.repository.custom.properties.editorsections.EventTypesEditorSection;
import org.palladiosimulator.pcm.repository.EventGroup;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

public class EventGroupTableLifecycleManager extends TableLifecycleManager {

	public EventGroupTableLifecycleManager(EEFCustomWidgetDescription description,
			IVariableManager variableManager, IInterpreter interpreter, EditingContextAdapter contextAdapter) {
		super(description, variableManager, interpreter, contextAdapter);
	}

	@Override
	protected void setPropertySection(Composite parent) {
		editorSection = new EventTypesEditorSection(parent);
		editorSection
				.setViewerContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		editorSection
				.setViewerLabelProvider(new AdapterFactoryLabelProvider(
						new EventTypesTabItemProviderAdapterFactory(
								new PalladioItemProviderAdapterFactory(
										adapterFactory))));
		
		
		EventGroup input = (EventGroup) controller.getValue();
		editorSection.setViewerInput(input);
		((EventTypesEditorSection) editorSection).getAddButtonListener().setSelectedInterface((EventGroup) input);

	}

}
