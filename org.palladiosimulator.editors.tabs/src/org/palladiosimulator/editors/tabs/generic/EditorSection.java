package org.palladiosimulator.editors.tabs.generic;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.palladiosimulator.editors.tabs.PCMBenchTabsImages;

/**
 * @author Roman Andrej 
 */
public abstract class EditorSection implements Observer {

	/** Define the value for TableSection. */
	private Composite composite;
	private ToolItem addButton, deleteButton;
	/** TableViewer */
	protected TableViewer viewer;
	/** Define selected element in the section. */
	private EObject selectedObject = null;

	public EditorSection(Composite composite) {
		composite.getParent().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));
		this.composite = composite;

		createEditorSection();
	}

	private void createEditorSection() {
		createTableSection(createToolBar());
	}


	protected ToolBar createToolBar() {

		ToolBar toolBar = new ToolBar(composite, SWT.VERTICAL | SWT.FLAT
				| SWT.RIGHT);
		FormData formData = new FormData();
		formData.left = new FormAttachment(96, 0);
		formData.top = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.bottom = new FormAttachment(100, 0);
		toolBar.setLayoutData(formData);

		/** Create Add-Button by ToolBar */
		if (canAddButonCreated()) {
			addButton = new ToolItem(toolBar, SWT.PUSH);
			addButton.setImage(PCMBenchTabsImages.imageRegistry
					.get(PCMBenchTabsImages.ADD_SIGN));
			addButton.addSelectionListener(createAddButtonActionListener());
		}
		

		/** Create Delete-Button by ToolBar */
		if (canDeleteButonCreated()) {
			deleteButton = new ToolItem(toolBar, SWT.PUSH);
			deleteButton.setImage(PCMBenchTabsImages.imageRegistry
					.get(PCMBenchTabsImages.DELETE_SIGN));
			deleteButton.setEnabled(false);
		}

		return toolBar;
	}
	
	/** Create the section with Table and TableViewer. */
	protected void createTableSection(ToolBar toolBar){
		
		// style the style of table to construct
		int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.HIDE_SELECTION;
		// Width board inside table and toolbar
		int widthBoard = 6;

		Table table = new Table(composite, style);

		// create table layout
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(toolBar, widthBoard);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);

		table.setLayoutData(data);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		viewer = new TableViewer(table);
		viewer.setUseHashlookup(true);
		viewer.setColumnProperties(getTableColumnNames());
		viewer.setCellEditors(createViewerCellEditors(table));
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			/* (non-Javadoc)
			 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
			 */
			public void selectionChanged(SelectionChangedEvent event) {
				Object input = ((IStructuredSelection) event.getSelection())
						.getFirstElement();

				if (input instanceof EObject) {
					selectedObject = (EObject) input;
					setDeleteButtonEnabled(inputValidation(selectedObject));
				} else {
					setDeleteButtonEnabled(false);
				}
			}
		});
		
		// define the listener for Delete-Button
		setDeleteButtonListener(viewer);
		// set CellModifier for Viewer
		setViewerCellModifier(viewer);
		// create column
		createTableColumns(table);
		
		
	}
	
	/** Create the table columns. */
	protected abstract void createTableColumns(Table table);
	
	protected abstract boolean inputValidation(EObject eObject);
	
	/**
	 * Create a SelectionListener for the Add-Button and set him to TableViewer
	 * as 'SelectionChangedListener'. Thus those becomes available element,
	 * which was selected in the TableViewer, for the DeleteAction
	 * 
	 * @param viewer
	 *            instance of TableViewer
	 */
	protected void setDeleteButtonListener(TableViewer viewer) {
		SelectionListener listener = createDeleteButtonListener();
		viewer
				.addSelectionChangedListener((ISelectionChangedListener) listener);
		if (canDeleteButonCreated()) {
			deleteButton.addSelectionListener(listener);
		}
	}
	
	/**
	 * Create a ICellModifier for TableViewer with 'EditorSection' as Observer for him.
	 * 
	 * @param viewer
	 *            instance of TableViewer
	 */
	protected void setViewerCellModifier(TableViewer viewer) {
		ObservableCellModifier cellModifier = createViewerCellModifier();
		cellModifier.addObserver(this);
		viewer.setCellModifier(cellModifier);
	}
	
	/** Set a SelectionListener for the Delete-Button */
	protected abstract SelectionListener createDeleteButtonListener();
	
	protected abstract String[] getTableColumnNames();
	
	protected abstract boolean canAddButonCreated();
	protected abstract boolean canDeleteButonCreated();
	
	/** Create a CellEditors for Viewer. */
	protected abstract CellEditor[] createViewerCellEditors(Table table);
	
	protected abstract ObservableCellModifier createViewerCellModifier();
	
	/** Set a CellModifier for TabelViewer. */
	public void setViewerCellModifier(ICellModifier modifier){
		Assert.isNotNull(viewer);
		viewer.setCellModifier(modifier);
	}
	
	public void addViewerSelectionChangedListener(ISelectionChangedListener listener){
		Assert.isNotNull(viewer);
		viewer.addSelectionChangedListener(listener);
	}
	
	/** Create a SelectionListener for the Add-Button */
	protected abstract SelectionListener createAddButtonActionListener();
	
	/** Set a input for the TableViewer */
	public void setViewerInput(Object input) {
		Assert.isNotNull(viewer);
		viewer.setInput(input);
	}
	
	
	/** Set a ContentProvider for the TableViewer */
	public void setViewerContentProvider(IContentProvider contentProvider) {
		Assert.isNotNull(viewer);
		viewer.setContentProvider(contentProvider);
	}

	/** Set a LabelProvider for the TableViewer */
	public void setViewerLabelProvider(IBaseLabelProvider labelProvider) {
		Assert.isNotNull(viewer);
		viewer.setLabelProvider(labelProvider);
	}

	public void refresh() {
		Assert.isNotNull(viewer);
		viewer.refresh();
	}

	protected void setDeleteButtonEnabled(boolean enabled) {
		deleteButton.setEnabled(enabled);
	}

	public EObject getSelectedObject() {
		return selectedObject;
	}
	
	public TableViewer getViewer(){
		return viewer;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		if (viewer != null){
			viewer.refresh();
		}
	}
}
