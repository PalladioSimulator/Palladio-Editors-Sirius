package org.palladiosimulator.editors.tabs.parameters;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;

/**
 * The class define a cell modifier is used to access the data model from a cell
 * editor.
 * 
 * @author Roman Andrej
 */
public class ComponentParametersCellModifier extends ObservableCellModifier {

	private List<String> columnNames;
	private VariableUsageWrapper wrapper;
	
	/** In property view selected VariableUsage. */
	private VariableUsage variableUsage;
	/** In diagram selected AssemblyContext. */
	private AssemblyContext context;
	/** The value wont for print VariableUsage name uses. */

	/**
	 * The transactional editing domain which is used to get the commands and
	 * alter the model
	 */
	protected TransactionalEditingDomain editingDomain = null;

	public ComponentParametersCellModifier() {
		this.columnNames = Arrays
				.asList(ComponentParametersEditorSection.columnNames);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean canModify(Object element, String property) {

		int columnIndex = columnNames.indexOf(property);

		switch (columnIndex) {
		case ComponentParametersEditorSection.ICON_COLUMN_INDEX:
			return false;
		case ComponentParametersEditorSection.VARIABLE_COLUMN_INDEX:
			return false;
		case ComponentParametersEditorSection.STOEX_COLUMN_INDEX:
			return true;
		default:
			break;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getValue(Object element, String property) {
		return (new ParametersTabItemProvider(null)).getColumnText(element,
				columnNames.indexOf(property));
	}

	/**
	 * {@inheritDoc}
	 */
	public void modify(Object element, String property, Object value) {
		// Find the index of the column
		int columnIndex = columnNames.indexOf(property);

		Assert.isNotNull(element);
		TableItem item = (TableItem) element;

		wrapper = (VariableUsageWrapper) item.getData();
		variableUsage = wrapper.getVariableUsage();

		switch (columnIndex) {
		case ComponentParametersEditorSection.ICON_COLUMN_INDEX:
			break;
		case ComponentParametersEditorSection.VARIABLE_COLUMN_INDEX:
			break;
		case ComponentParametersEditorSection.STOEX_COLUMN_INDEX:
			setSpecification(((String) value).trim(), item);
			break;
		default:
			break;
		}

	}

	/**
	 * Update Specification of selected VariableUsage.
	 */
	private void setSpecification(final String value, TableItem item) {
		editingDomain = TransactionUtil.getEditingDomain(variableUsage);

		VariableCharacterisation variableCharacterisation = variableUsage
				.getVariableCharacterisation_VariableUsage().get(0);

		if (!variableCharacterisation
				.getSpecification_VariableCharacterisation().getSpecification()
				.equals(value)) {

			RecordingCommand recCommand = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {

					// create/set RandomVariable
					PCMRandomVariable randomVariable = CoreFactory.eINSTANCE
							.createPCMRandomVariable();
					randomVariable.setSpecification(value);

					// test if VariableUsaged exist
					VariableUsage existedVariableUsage = isExisted(context,
							variableUsage);

					if (existedVariableUsage == null) {
						VariableUsage contexVariableUsage = null;

						// create VariableUsage for AssemblyContext
						ParameterUtil copier = new ParameterUtil();
						contexVariableUsage = (VariableUsage) copier
								.copy(variableUsage);
						copier.copyReferences();

						// create VariableCharacterisation for AssemblyContext
						VariableCharacterisation characterisation = contexVariableUsage
								.getVariableCharacterisation_VariableUsage()
								.get(0);
						// set specification
						characterisation
								.setSpecification_VariableCharacterisation(randomVariable);
						// add edited VariableUsage to AssemblyContext

						context.getConfigParameterUsages__AssemblyContext().add(
								contexVariableUsage);
					} else {
						// create VariableCharacterisation for AssemblyContext
						VariableCharacterisation characterisation = existedVariableUsage
								.getVariableCharacterisation_VariableUsage()
								.get(0);
						// set specification
						characterisation
								.setSpecification_VariableCharacterisation(randomVariable);

					}

				}
			};

			recCommand.setLabel("Set specification");
			editingDomain.getCommandStack().execute(recCommand);
			// set color for TableItem
			setTableItemColor(item);
			// make TableItem edited
			wrapper.setEdited(true);
			// update observer
			notifyObservers(wrapper);

		}
	}

	/**
	 * The Method test if VariableUsage already exist in the AssemblyContext.
	 * Return null if VarableUsage not exist this AssemblyContect.
	 * 
	 * @param contex
	 *            The associated AssembyContex
	 * @param newVariable
	 *            The associated VariableUsage
	 * @return
	 */
	private VariableUsage isExisted(AssemblyContext context,
			VariableUsage newVariable) {
		EList<VariableUsage> variables = context
				.getConfigParameterUsages__AssemblyContext();

		String newName = new PCMStoExPrettyPrintVisitor().prettyPrint(newVariable);
		for (VariableUsage existedVariable : variables) {
			String existedName = new PCMStoExPrettyPrintVisitor().prettyPrint(existedVariable);
			if (newName.equals(existedName)) {
				return existedVariable;
			}
		}
		return null;
	}

	private void setTableItemColor(TableItem item) {
		item.setForeground(item.getDisplay().getSystemColor(SWT.COLOR_BLACK));
	}

	/**
	 * @param context
	 *            the context to set
	 */
	public void setContext(AssemblyContext context) {
		this.context = context;
	}
}
