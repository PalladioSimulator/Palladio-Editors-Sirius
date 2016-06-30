package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

import org.palladiosimulator.pcm.repository.EventType;

/**
 * This class is a decorator for the generated EMF.Edit item providers. It
 * provides item providers which are used in the event types tab of the tabbed
 * properties sheet when editing event groups. It implements
 * ITableItemLabelProvider to display the given EObject in a tabular form.
 * Additionally it provided the labels by practical delegation to the original
 * IItemLabelProvider.
 * 
 * The OperationsTabItemProvider was used as template for this class.
 * 
 * @author Benjamin Klatt
 */
public class EventTypesTabItemProvider extends ItemProviderDecorator implements
		ITableItemLabelProvider, IItemLabelProvider {

	/**
	 * Inherited default constructor
	 * 
	 * @param factory
	 *            The factory which created this object
	 */
	public EventTypesTabItemProvider(AdapterFactory factory) {
		super(factory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getColumnImage(java.lang.Object,
	 *      int)
	 */
	@Override
	public Object getColumnImage(Object object, int columnIndex) {
		if (columnIndex == EventTypesEditorSection.ICON_COLUMN_INDEX)
			return this.getImage(object);
		return null;
	}

	/**
	 * Format the columns with the given index constant as string text for displaying.
	 *      
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
	 *      int) 
	 */
	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		EventType eventType = (EventType) element;
		ParameterRepresentation parser = new ParameterRepresentation();

		switch (columnIndex) {
		case EventTypesEditorSection.ICON_COLUMN_INDEX:
			break;
		case EventTypesEditorSection.EVENTTYPENAME_COLUMN_INDEX:
			result = eventType.getEntityName();
			break;
		case EventTypesEditorSection.PARAMETER_NAME_COLUMN_INDEX:
			if(eventType != null &&
					eventType.getParameter__EventType() != null &&
					eventType.getParameter__EventType().getParameterName() != null){
				result = eventType.getParameter__EventType().getParameterName();
			} else {
				result = "";
			}
			break;
		case EventTypesEditorSection.PARAMETER_TYPE_COLUMN_INDEX:
			if(eventType != null &&
					eventType.getParameter__EventType() != null &&
					eventType.getParameter__EventType().getDataType__Parameter() != null){
				result = parser.setDataTypeToString(eventType
												.getParameter__EventType()
												.getDataType__Parameter());
			} else {
				result = "";
			}
			break;
		default:
			break;
		}
		return parser.isNotNull(result);
	}
}
