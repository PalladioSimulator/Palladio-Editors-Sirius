package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

import org.palladiosimulator.pcm.repository.InfrastructureSignature;

/**
 * @author Snowball
 * 
 * This class is a decorator for the generated EMF.Edit item providers. It
 * provides item providers which are used in the operations tab of the tabbed
 * properties sheet when editing interfaces. It implements
 * ITableItemLabelProvider to display the given EObject in a tabular form.
 * Additionally it provided the labels by partial delegation to the original
 * IItemLabelProvider.
 * 
 * @author groenda
 */
public class InfrastructureSignaturesTabItemProvider extends ItemProviderDecorator implements
		ITableItemLabelProvider, IItemLabelProvider {

	/**
	 * Inherited default constructor
	 * 
	 * @param factory
	 *            The factory which created this object
	 */
	public InfrastructureSignaturesTabItemProvider(AdapterFactory factory) {
		super(factory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getColumnImage(java.lang.Object,
	 *      int)
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		if (columnIndex == InfrastructureSignaturesEditorSection.ICON_COLUMN_INDEX)
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
		InfrastructureSignature signature = (InfrastructureSignature) element;
		ParameterRepresentation parser = new ParameterRepresentation();

		switch (columnIndex) {
		case InfrastructureSignaturesEditorSection.ICON_COLUMN_INDEX:
			break;
		case InfrastructureSignaturesEditorSection.SIGNATURENAME_COLUMN_INDEX:
			result = signature.getEntityName();
			break;
		case InfrastructureSignaturesEditorSection.PARAMETER_COLUMN_INDEX:
			result = parser.setParametersToString(signature
					.getParameters__InfrastructureSignature());
			break;
		case InfrastructureSignaturesEditorSection.EXCEPTIONS_COLUMN_INDEX:
			result = parser.setExceptionsToString(signature
					.getExceptions__Signature());
			break;
		default:
			break;
		}
		return parser.isNotNull(result);
	}
}
