package org.palladiosimulator.editors.sirius.repository.custom.properties.editorsections;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.palladiosimulator.editors.commons.tabs.PCMBenchTabsImages;
import org.palladiosimulator.editors.commons.tabs.generic.ObservableCellModifier;

/**
 * Modified version of {@link org.palladiosimulator.editors.commons.tabs.generic.EditorSection} to suit the properties sheet of the Sirius Editors
 * @author Roman Andrej
 * @author Amine Kechaou
 * 
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
		
		this.composite = composite.getParent().getParent();

		GridLayout parentLayout = (GridLayout)this.composite.getLayout();
		this.composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		parentLayout.numColumns = 2;
		
		createEditorSection();
	}

	private void createEditorSection() {
		ToolBar toolBar = createToolBar();
		createTableSection(toolBar);
	}


	protected ToolBar createToolBar() {

		ToolBar toolBar = new ToolBar(composite, SWT.VERTICAL);
		
		GridData data = new GridData(SWT.LEFT, SWT.FILL, false, true);
		toolBar.setLayoutData(data);

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
		Table table = new Table(composite, style);


        GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);

		table.setLayoutData(data);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.moveAbove(composite.getChildren()[0]);
		toolBar.moveBelow(composite.getChildren()[0]);
		
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
	
	public void setEnabled(boolean isEnabled) {
		if (!this.viewer.getTable().isDisposed()) {
			this.viewer.getTable().setEnabled(isEnabled);			
		}
		if (!this.addButton.isDisposed()) {
			this.addButton.setEnabled(isEnabled);
		}
		if (!this.deleteButton.isDisposed()) {
			this.deleteButton.setEnabled(isEnabled);
		}
	}
}
