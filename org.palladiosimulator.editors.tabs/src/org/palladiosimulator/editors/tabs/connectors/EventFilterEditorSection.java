package org.palladiosimulator.editors.tabs.connectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.palladiosimulator.editors.dialogs.stoex.StochasticExpressionEditDialog;
import org.palladiosimulator.editors.tabs.generic.EditorSection;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.analyser.visitors.TypeEnum;

public class EventFilterEditorSection extends EditorSection{

	public static final int ICON_COLUMN_INDEX = 0;
	public static final int FILTER_COLUMN_INDEX = 1;
	
	/**
	 * Columns of a table, which is used into operations table
	 */
	public final static String CONNECTOR_ICON_COLUMN	= "";
	public final static String FILTER_COLUMN 	= "FilterCondition";
	
	
	//	 Set column names of Table
	public static String[] columnNames = new String[] { CONNECTOR_ICON_COLUMN,
		FILTER_COLUMN };
	
	/** Define the Add-Button listener. Listener must by later initialized. */
	
	private EventFilterAddActionListener addButtonListener;
	private EventFilterDeleteActionListener deleteButtonListener;

	/** Constructor */
	public EventFilterEditorSection(Composite composite) {
		super(composite);
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createViewerCellEditors(org.eclipse.swt.widgets.Table)
	 */
	@Override
	protected CellEditor[] createViewerCellEditors(Table table) {
		CellEditor[] editors = new CellEditor[columnNames.length];

	
		editors[FILTER_COLUMN_INDEX] = new DialogCellEditor(table) {

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
			 */
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				PCMRandomVariable rv= getSelectedRandomVariable();
				
				StochasticExpressionEditDialog dialog = new StochasticExpressionEditDialog(
						cellEditorWindow.getShell(),TypeEnum.BOOL,rv);
				dialog.setInitialExpression(rv);
				dialog.open();
				if (dialog.getReturnCode() == Dialog.OK) {
					return new PCMStoExPrettyPrintVisitor().prettyPrint(dialog
							.getResult());
				}

				return null;
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
		return new EventFilterCellModifier();
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
		this.addButtonListener = new EventFilterAddActionListener();
		
		return addButtonListener;
	}

	/**
	 * Default deletion button.
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createDeleteButtonListener()
	 */
	@Override
	protected SelectionListener createDeleteButtonListener() {
		deleteButtonListener = new EventFilterDeleteActionListener();
		return deleteButtonListener;
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

	private PCMRandomVariable getSelectedRandomVariable() {
		return (PCMRandomVariable) getSelectedObject();
	}

	/**
	 * @return the addButtonListener
	 */
	public EventFilterAddActionListener getAddButtonListener() {
		return addButtonListener;
	}
	
	/**
	 * @return the deleteButtonListener
	 */
	public EventFilterDeleteActionListener getDeleteButtonListener() {
		return deleteButtonListener;
	}
	
	
}
