package org.palladiosimulator.editors.tabs.operations;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.palladiosimulator.editors.dialogs.parameters.ParametersDialog;
import org.palladiosimulator.editors.tabs.generic.EditorSection;
import org.palladiosimulator.editors.tabs.generic.ObservableCellModifier;
import org.palladiosimulator.pcm.repository.Signature;

/**Editor section for infrastructure signatures.
 * Allows to display and edit the list of signatures for a given infrastructure interface.
 * @author groenda
 *
 */
public class InfrastructureSignaturesEditorSection extends EditorSection{

	/** Index of the icon column in the table. */
	public static final int ICON_COLUMN_INDEX = 0;
	/** Index of the signature column in the table. */
	public static final int SIGNATURENAME_COLUMN_INDEX = 1;
	/** Index of the parameter column in the table. */
	public static final int PARAMETER_COLUMN_INDEX = 2;
	/** Index of the exception column in the table. */
	public static final int EXCEPTIONS_COLUMN_INDEX = 3;
	
	/**
	 * Columns of a table, which is used into operations table
	 */
	public final static String OPERATIONS_ICON_COLUMN	= "";
	public final static String OWNEDPARAMETER_COLUMN 	= "Owned Parameter(s)";
	public final static String SERVICENAME_COLUMN 		= "Signature Name";
	public final static String EXEPTIONTYPE_COLUM		= "Exception Type";
	
	/** Column to name mapping. */
	public static String[] columnNames = new String[] { OPERATIONS_ICON_COLUMN,
			SERVICENAME_COLUMN, OWNEDPARAMETER_COLUMN, EXEPTIONTYPE_COLUM };
	
	/** Define the Add-Button listener. Listener must by later initialized. */
	private InfrastructureSignatureAddActionListener addButtonListener;

	/** Constructor */
	public InfrastructureSignaturesEditorSection(Composite composite) {
		super(composite);
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createViewerCellEditors(org.eclipse.swt.widgets.Table)
	 */
	@Override
	protected CellEditor[] createViewerCellEditors(Table table) {
		CellEditor[] editors = new CellEditor[columnNames.length];

		editors[SIGNATURENAME_COLUMN_INDEX] = new TextCellEditor(table);

		// create 'DeleteCellValueListener' and as SelectionListener to the 'TableVewer'
		InfrastructureSignatureDeleteCellValueListener cellValueListener = new InfrastructureSignatureDeleteCellValueListener(viewer);
		viewer.addSelectionChangedListener(cellValueListener);

		editors[PARAMETER_COLUMN_INDEX] = new DialogCellEditor(table) {
			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				ParametersDialog dialog = new ParametersDialog(cellEditorWindow
						.getShell(), getSelectedSignature());
				if (dialog.open() == Dialog.OK)
					viewer.refresh();
				return null;
			}
		};

		return editors;
	}
	
	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createViewerCellModifier()
	 */
	@Override
	protected ObservableCellModifier createViewerCellModifier() {
		return new InfrastructureSignaturesCellModifier();
	}

	/* (non-Javadoc)
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
	
	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createAddButtonActionListener()
	 */
	@Override
	protected SelectionListener createAddButtonActionListener() {
		// the value must by initialized! (don't return new AddActionListener()) 
		this.addButtonListener = new InfrastructureSignatureAddActionListener();
		
		return addButtonListener;
	}

	/* (non-Javadoc)
	 * @see org.palladiosimulator.editors.tabs.generic.EditorSection#createDeleteButtonListener()
	 */
	@Override
	protected SelectionListener createDeleteButtonListener() {
		return new InfrastructureSignatureDeleteActionListener();
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

	public Signature getSelectedSignature() {
		return (Signature) getSelectedObject();
	}

	/**
	 * @return the addButtonListener
	 */
	public InfrastructureSignatureAddActionListener getAddButtonListener() {
		return addButtonListener;
	}
}
