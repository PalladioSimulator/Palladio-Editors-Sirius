package org.palladiosimulator.editors.sirius.repository.custom.properties;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.AbstractEEFCustomWidgetController;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

public class TableController extends AbstractEEFCustomWidgetController {

	private static final String VALUE_EXPRESSION_ID = "valueExpression"; //$NON-NLS-1$
	
	public TableController(EEFCustomWidgetDescription description, IVariableManager variableManager, IInterpreter interpreter,
			EditingContextAdapter contextAdapter) {
		super(description, variableManager, interpreter, contextAdapter);
	}

	public Object getValue() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.putAll(this.variableManager.getVariables());
		IEvaluationResult evaluationResult = this.interpreter.evaluateExpression(variables, getCustomExpression(VALUE_EXPRESSION_ID));
		return evaluationResult.getValue();
	}
	
	@Override
	protected EEFCustomWidgetDescription getDescription() {
		return this.description;
	}

	@Override
	public void refresh() {
		super.refresh();
	}
	
}
