package org.palladiosimulator.editors.tabs.operations;


import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.palladiosimulator.editors.dialogs.datatype.CallDataTypeDialog;
import org.palladiosimulator.editors.tabs.generic.EditorSection;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.EventType;
import org.palladiosimulator.pcm.repository.Repository;

public class EventTypesEditorSection extends EditorSection{

	public static final int ICON_COLUMN_INDEX = 0;
	public static final int EVENTTYPENAME_COLUMN_INDEX = 1;
	public static final int PARAMETER_NAME_COLUMN_INDEX = 2;
	public static final int PARAMETER_TYPE_COLUMN_INDEX = 3;
	
	/**
	 * Columns of a table, which is used into operations table
	 */
	public final static String OPERATIONS_ICON_COLUMN	= "";
	public final static String OWNEDPARAMETER_NAME_COLUMN 	= "Event Name";
	public final static String OWNEDPARAMETER_TYPE_COLUMN 	= "Event DataType";
	public final static String SERVICENAME_COLUMN 		= "EventTypeName";
	
	//	 Set column names of Table
	public static String[] columnNames = new String[] { OPERATIONS_ICON_COLUMN,
			SERVICENAME_COLUMN, OWNEDPARAMETER_NAME_COLUMN,OWNEDPARAMETER_TYPE_COLUMN };
	
	/** Define the Add-Button listener. Listener must by later initialized. */
	private EventTypeAddActionListener addButtonListener;

	/** Constructor */
	public EventTypesEditorSection(Composite composite) {
		super(composite);
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createViewerCellEditors(org.eclipse.swt.widgets.Table)
	 */
	@Override
	protected CellEditor[] createViewerCellEditors(Table table) {
		CellEditor[] editors = new CellEditor[columnNames.length];

		editors[EVENTTYPENAME_COLUMN_INDEX] = new TextCellEditor(table);

		// create 'DeleteCellValueListener' and as SelectionListener to the 'TableVewer'
		EventTypeDeleteCellValueListener cellValueListener = new EventTypeDeleteCellValueListener(viewer);
		viewer.addSelectionChangedListener(cellValueListener);

		editors[PARAMETER_NAME_COLUMN_INDEX] = new TextCellEditor(table);

		editors[PARAMETER_TYPE_COLUMN_INDEX] = new TypeDialogCellEditor(table,
				cellValueListener) {
			
			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
			 */
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				
				TransactionalEditingDomain editingDomain = TransactionUtil
				.getEditingDomain(getSelectedEventType());

				ArrayList<Object> filterList = new ArrayList<Object>();
				filterList.add(DataType.class);
				filterList.add(Repository.class);
				ArrayList<EReference> additionalReferences = new ArrayList<EReference>();
	
				CallDataTypeDialog dialog = new CallDataTypeDialog(
						cellEditorWindow.getShell(), filterList,
						additionalReferences, editingDomain.getResourceSet());
				dialog.setProvidedService(DataType.class);
				dialog.open();

				if (!(dialog.getResult() instanceof DataType))
					return null;

				return dialog.getResult();
			}
		};

		return editors;
	}
	
	/**
	 * Initialise the cell modifier.
	 * @return The cell modifier.
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createViewerCellModifier()
	 */
	@Override
	protected ObservableCellModifier createViewerCellModifier() {
		return new EventTypesCellModifier();
	}

	/**
	 * Create the table columns.
	 * @param table The table object to add the columns to.
	 * 
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createTableColumns(org.eclipse.swt.widgets.Table)
	 */
	@Override
	protected void createTableColumns(Table table) {
		// 1st column
		TableColumn column = new TableColumn(table, SWT.CENTER, 0);
		column.setText("");
		column.setWidth(25);

		for (int i = 1; i < columnNames.length; i++) {
			// n-te column with task Description
			column = new TableColumn(table, SWT.LEFT, i);
			column.setText((String) columnNames[i]);
			column.setWidth(140);
		}
	}
	
	/**
	 * Add Action button.
	 * Note: This method has been aligned with he OperationsEditorserction and there was no
	 * explanation why the implementation does exactly the opposite of the included comment.
	 * 
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createAddButtonActionListener()
	 */
	@Override
	protected SelectionListener createAddButtonActionListener() {
		// the value must by initialized! (don't return new AddActionListener()) 
		this.addButtonListener = new EventTypeAddActionListener();
		
		return addButtonListener;
	}

	/**
	 * Default deletion button.
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createDeleteButtonListener()
	 */
	@Override
	protected SelectionListener createDeleteButtonListener() {
		return new EventTypeDeleteActionListener();
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#getViewerColumnProperties()
	 */
	@Override
	protected String[] getTableColumnNames() {
		return columnNames;
	}
	
	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#canAddButonCreated()
	 */
	@Override
	protected boolean canAddButonCreated() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#canDeleteButonCreated()
	 */
	@Override
	protected boolean canDeleteButonCreated() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#inputValidation(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	protected boolean inputValidation(EObject object) {
		return true;
	}

	public EventType getSelectedEventType() {
		return (EventType) getSelectedObject();
	}

	/**
	 * @return the addButtonListener
	 */
	public EventTypeAddActionListener getAddButtonListener() {
		return addButtonListener;
	}
}
