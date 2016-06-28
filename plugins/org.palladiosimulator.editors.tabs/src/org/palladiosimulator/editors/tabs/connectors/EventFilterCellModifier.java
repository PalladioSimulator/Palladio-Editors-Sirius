package org.palladiosimulator.editors.tabs.connectors;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.widgets.TableItem;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.core.PCMRandomVariable;


public class EventFilterCellModifier extends ObservableCellModifier {

	private List<String> columnNames;
	private PCMRandomVariable rv;

	/**
	 * The transactional editing domain which is used to get the commands and
	 * alter the model
	 */
	protected TransactionalEditingDomain editingDomain = null;

	public EventFilterCellModifier() {
		this.columnNames = Arrays.asList(EventFilterEditorSection.columnNames);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICellModifier#canModify(Object element,
	 *      String property)
	 */
	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		return (new EventFilterTabItemProvider(null)).getColumnText(element,
				columnNames.indexOf(property));
	}

	public void modify(Object element, String property, Object value) {

		// Find the index of the column
		int columnIndex = columnNames.indexOf(property);

		Assert.isNotNull(element);
		TableItem item = (TableItem) element;
		rv = (PCMRandomVariable) item.getData();
		editingDomain = TransactionUtil.getEditingDomain(rv);

		switch (columnIndex) {
		case EventFilterEditorSection.ICON_COLUMN_INDEX: // COMPLETED_COLUMN
			break;
		case EventFilterEditorSection.FILTER_COLUMN_INDEX:
			setFilterCondition(((String) value).trim());
			break;
		default:
		}
	}

	private void setFilterCondition(final String value) {
		editingDomain = TransactionUtil.getEditingDomain(rv);
		if (!rv.getSpecification().equals(value)){
			
			RecordingCommand recCommand = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
				
					rv.setSpecification(value);	
				}
			};	
			recCommand.setLabel("Set filter condition");
			editingDomain.getCommandStack().execute(recCommand);
			// update observer
			notifyObservers();
			}
		}
		
	}



