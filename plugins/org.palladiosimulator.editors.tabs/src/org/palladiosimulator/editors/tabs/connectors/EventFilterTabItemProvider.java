package org.palladiosimulator.editors.tabs.connectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.RandomVariable;

public class EventFilterTabItemProvider  extends ItemProviderDecorator implements
ITableItemLabelProvider, IItemLabelProvider {

public EventFilterTabItemProvider(AdapterFactory factory) {
super(factory);
new PCMStoExPrettyPrintVisitor();
}

/* (non-Javadoc)
* @see org.eclipse.emf.edit.provider.ItemProviderDecorator#getColumnImage(java.lang.Object,
*      int)
*/
@Override
public Object getColumnImage(Object object, int columnIndex) {
if (columnIndex == EventFilterEditorSection.ICON_COLUMN_INDEX)
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
switch (columnIndex) {
case EventFilterEditorSection.ICON_COLUMN_INDEX:
	break;
case EventFilterEditorSection.FILTER_COLUMN_INDEX:
	result = ((RandomVariable) element).getSpecification();
	break;
default:
	break;
}
return result;

}
}
