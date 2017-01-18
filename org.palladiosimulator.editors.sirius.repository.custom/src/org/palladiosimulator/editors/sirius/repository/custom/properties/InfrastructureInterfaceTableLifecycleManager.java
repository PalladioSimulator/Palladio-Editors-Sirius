package org.palladiosimulator.editors.sirius.repository.custom.properties;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.editors.commons.tabs.operations.InfrastructureSignaturesTabItemProviderAdapterFactory;
import org.palladiosimulator.editors.sirius.repository.custom.properties.editorsections.InfrastructureSignaturesEditorSection;
import org.palladiosimulator.pcm.repository.InfrastructureInterface;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

public class InfrastructureInterfaceTableLifecycleManager extends TableLifecycleManager {

	public InfrastructureInterfaceTableLifecycleManager(EEFCustomWidgetDescription description,
			IVariableManager variableManager, IInterpreter interpreter, EditingContextAdapter contextAdapter) {
		super(description, variableManager, interpreter, contextAdapter);
	}

	@Override
	protected void setPropertySection(Composite parent) {

		editorSection = new InfrastructureSignaturesEditorSection(parent);
		editorSection
				.setViewerContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		editorSection
				.setViewerLabelProvider(new AdapterFactoryLabelProvider(
						new InfrastructureSignaturesTabItemProviderAdapterFactory(
								new PalladioItemProviderAdapterFactory(
										adapterFactory))));
		
		InfrastructureInterface input = (InfrastructureInterface) controller.getValue();
		editorSection.setViewerInput(input);
		((InfrastructureSignaturesEditorSection) editorSection).getAddButtonListener().setSelectedInterface((InfrastructureInterface) input);

	}

}
