package org.palladiosimulator.editors.tabs.parameters;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;

/**
 * @author Snowball This class is a decorator for the generated EMF.Edit item
 *         providers. It provides item providers which are used in the
 *         operations tab of the tabbed properties sheet when editing
 *         interfaces. It implements ITableItemLabelProvider to display the
 *         given EObject in a tabular form. Additionally it provided the labels
 *         by partcial delegation to the original IItemLabelProvider.
 */
public class ParametersTabItemProvider extends ItemProviderDecorator implements
		ITableItemLabelProvider, IItemLabelProvider {
	
	/**
	 * Inherited default constructor
	 * 
	 * @param factory
	 *            The factory which created this object
	 */
	public ParametersTabItemProvider(AdapterFactory factory) {
		super(factory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getColumnImage(java.lang.Object,
	 *      int)
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		if (columnIndex == ComponentParametersEditorSection.ICON_COLUMN_INDEX)
			return this.getImage(object);
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
	 *      int) Format the columns with the given index constant as string text
	 *      for displaying
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		VariableUsageWrapper variable = (VariableUsageWrapper) element;

		switch (columnIndex) {
		case ComponentParametersEditorSection.ICON_COLUMN_INDEX:
			break;
		case ComponentParametersEditorSection.VARIABLE_COLUMN_INDEX:
			result = new PCMStoExPrettyPrintVisitor().prettyPrint(variable.getVariableUsage());
			break;
		case ComponentParametersEditorSection.STOEX_COLUMN_INDEX:
			result = getSpecification(variable.getVariableUsage());
			break;
		default:
			break;
		}
		return result;
	}
	
	
	private String getSpecification(VariableUsage variable) {
		String specification = "";
		EList<VariableCharacterisation> characterisation = variable
				.getVariableCharacterisation_VariableUsage();
		if (characterisation.size() > 0) {
			PCMRandomVariable randomVariable = characterisation.get(0)
					.getSpecification_VariableCharacterisation();
			specification = randomVariable.getSpecification();
		}
		return specification;
	}

}
