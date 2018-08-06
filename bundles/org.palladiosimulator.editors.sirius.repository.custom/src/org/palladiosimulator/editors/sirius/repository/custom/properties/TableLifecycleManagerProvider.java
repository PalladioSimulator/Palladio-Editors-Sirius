package org.palladiosimulator.editors.sirius.repository.custom.properties;
import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManager;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManagerProvider;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

public class TableLifecycleManagerProvider implements IEEFLifecycleManagerProvider {
	/**
	 * The identifier of the control description supported.
	 */
	private static final String OPERATION_INTERFACE_ID = "org.palladiosimulator.editors.sirius.repository.custom.properties.OperationInterface";
	private static final String INFRASTRUCTURE_INTERFACE_ID = "org.palladiosimulator.editors.sirius.repository.custom.properties.InfrastructureInterface";
	private static final String EVENT_GROUP_ID = "org.palladiosimulator.editors.sirius.repository.custom.properties.EventGroup";

	@Override
	public boolean canHandle(EEFControlDescription controlDescription) {
		// only support custom widgets with the proper identifier
		return isValid(controlDescription.getIdentifier()) && controlDescription instanceof EEFCustomWidgetDescription;
	}

	private boolean isValid(String identifier) {
		return OPERATION_INTERFACE_ID.equals(identifier) || INFRASTRUCTURE_INTERFACE_ID.equals(identifier) || EVENT_GROUP_ID.equals(identifier);
	}

	@Override
	public IEEFLifecycleManager getLifecycleManager(EEFControlDescription controlDescription, IVariableManager variableManager,
			IInterpreter interpreter, EditingContextAdapter contextAdapter) {
		if (controlDescription instanceof EEFCustomWidgetDescription) {
			TableLifecycleManager manager = null;
			switch (controlDescription.getIdentifier()) {
			case OPERATION_INTERFACE_ID:
				manager = new OperationInterfaceTableLifecycleManager((EEFCustomWidgetDescription) controlDescription, variableManager, interpreter, contextAdapter);
				break;
			case INFRASTRUCTURE_INTERFACE_ID:
				manager = new InfrastructureInterfaceTableLifecycleManager((EEFCustomWidgetDescription) controlDescription, variableManager, interpreter, contextAdapter);
				break;
			case EVENT_GROUP_ID:
				manager = new EventGroupTableLifecycleManager((EEFCustomWidgetDescription) controlDescription, variableManager, interpreter, contextAdapter);
				break;
			default:
				break;
			}
			return manager;
		}
		throw new IllegalArgumentException();
	}
}