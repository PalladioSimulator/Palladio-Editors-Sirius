package org.palladiosimulator.editors.dialogs.parameters;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.palladiosimulator.editors.dialogs.DialogsImages;

// TODO: Auto-generated Javadoc
/**
 * This class create a dialog area for Parameter-, DataTypeDialog. In order to make functionality
 * possible from dialogue perfectly to, the following functions must be called:
 * 
 * setViewerContentProvider(IContentProvider contentProvider),
 * setViewerLabelProvider(IBaseLabelProvider labelProvider), setViewerCellModifier(ICellModifier
 * cellModifier), setAddButtonActionListener(SelectionListener listener),
 * setDeleteButtonActionListener(SelectionListener listener),
 * setUpButtonActionListener(SelectionListener listener),
 * setDownButtonActionListener(SelectionListener listener), setViewerInput(Object input){
 * 
 * @author Roman Andrej
 */
public final class CreateEditorContents {
    // local value
    /** The viewer. */
    private TableViewer viewer;

    /** The up item. */
    private ToolItem addItem, deleteItem, downItem, upItem;

    /** The table. */
    private Table table;
    // private TransactionalEditingDomain editingDomain;

    /** The Constant ICON_COLUMN_INDEX. */
    public static final int ICON_COLUMN_INDEX = 0;

    /** The Constant CONTEXT_COLUMN_INDEX. */
    public static final int CONTEXT_COLUMN_INDEX = 1;

    /** The Constant TYPE_COLUMN_INDEX. */
    public static final int TYPE_COLUMN_INDEX = 2;

    /** The Constant NAME_COLUMN_INDEX. */
    public static final int NAME_COLUMN_INDEX = 3;

    /** Columns of a table, which is used into ParameterEditDialog. */
    public static final String ATTRIBUTE_ICON_COLUMN = "";

    /** The Constant CONTEXT_COLUMN. */
    public static final String CONTEXT_COLUMN = "Context";

    /** The Constant TYPE_COLUMN. */
    public static final String TYPE_COLUMN = "Type";

    /** The Constant NAME_COLUMN. */
    public static final String NAME_COLUMN = "Name";

    // Set column names of Tabele
    /** The column names. */
    private static String[] columnNames = new String[] { ATTRIBUTE_ICON_COLUMN, CONTEXT_COLUMN, NAME_COLUMN,
            TYPE_COLUMN };

    /** Create the cell editors for Name, Type column. */
    private CellEditor[] editors = new CellEditor[columnNames.length];

    /**
     * Instantiates a new creates the editor contents.
     * 
     * @param composite
     *            the composite
     */
    private CreateEditorContents(Composite composite) {
        init(composite);
    }

    /**
     * Factory Method.
     * 
     * @param composite
     *            the composite
     * @return the creates the editor contents
     */
    public static CreateEditorContents create(Composite composite) {
        return new CreateEditorContents(composite);
    }

    /**
     * Creates the name column cell editor.
     */
    public void createNameColumnCellEditor() {
        editors[NAME_COLUMN_INDEX] = new TextCellEditor(table);
    }

    /**
     * Creates the type column cell editor.
     * 
     * @param editingDomain
     *            the editing domain
     */
    public void createTypeColumnCellEditor(TransactionalEditingDomain editingDomain) {
        editors[TYPE_COLUMN_INDEX] = new TypeDialogCellEditor(table, editingDomain);
    }

    /**
     * Sets the viewer content provider.
     * 
     * @param contentProvider
     *            the new viewer content provider
     */
    public void setViewerContentProvider(IContentProvider contentProvider) {
        viewer.setContentProvider(contentProvider);
    }

    /**
     * Sets the viewer label provider.
     * 
     * @param labelProvider
     *            the new viewer label provider
     */
    public void setViewerLabelProvider(IBaseLabelProvider labelProvider) {
        viewer.setLabelProvider(labelProvider);
    }

    /**
     * Sets the viewer cell modifier.
     * 
     * @param cellModifier
     *            the new viewer cell modifier
     */
    public void setViewerCellModifier(ICellModifier cellModifier) {
        viewer.setCellModifier(cellModifier);
    }

    /**
     * Sets the adds the button action listener.
     * 
     * @param listener
     *            the new adds the button action listener
     */
    public void setAddButtonActionListener(SelectionListener listener) {
        addItem.addSelectionListener(listener);
    }

    /**
     * Sets the delete button action listener.
     * 
     * @param listener
     *            the new delete button action listener
     */
    public void setDeleteButtonActionListener(SelectionListener listener) {
        deleteItem.addSelectionListener(listener);
    }

    /**
     * Sets the up button action listener.
     * 
     * @param listener
     *            the new up button action listener
     */
    public void setUpButtonActionListener(SelectionListener listener) {
        upItem.addSelectionListener(listener);
    }

    /**
     * Sets the down button action listener.
     * 
     * @param listener
     *            the new down button action listener
     */
    public void setDownButtonActionListener(SelectionListener listener) {
        downItem.addSelectionListener(listener);
    }

    /**
     * Sets the viewer selection changed listener.
     * 
     * @param listener
     *            the new viewer selection changed listener
     */
    public void setViewerSelectionChangedListener(ISelectionChangedListener listener) {
        viewer.addSelectionChangedListener(listener);
    }

    /**
     * Sets the viewer input.
     * 
     * @param input
     *            the new viewer input
     */
    public void setViewerInput(Object input) {
        viewer.setInput(input);
    }

    /**
     * create dialog area.
     * 
     * @param composite
     *            the composite
     */
    public void init(Composite composite) {

        FormData fdToolBar = new FormData();
        fdToolBar.top = new FormAttachment(0, 5);
        fdToolBar.right = new FormAttachment(100, -7);
        fdToolBar.left = new FormAttachment(100, -37);
        fdToolBar.bottom = new FormAttachment(94, 0);

        final ToolBar toolBar = new ToolBar(composite, SWT.VERTICAL | SWT.NONE);
        toolBar.setLayoutData(fdToolBar);

        addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.ADD));
        addItem.addSelectionListener(new SelectionAdapter() {

            /*
             * (non-Javadoc)
             * 
             * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.
             * SelectionEvent)
             */
            @Override
            public void widgetSelected(SelectionEvent e) {
                viewer.refresh();
            }
        });

        deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.DELETE));
        deleteItem.setEnabled(false);

        upItem = new ToolItem(toolBar, SWT.PUSH);
        upItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.UP));
        upItem.setEnabled(false);

        downItem = new ToolItem(toolBar, SWT.PUSH);
        downItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.DOWN));
        downItem.setEnabled(false);

        final FormData fdTableViewer = new FormData();
        fdTableViewer.right = new FormAttachment(toolBar, -5, SWT.LEFT);
        fdTableViewer.top = new FormAttachment(0, 5);
        fdTableViewer.left = new FormAttachment(0, 5);
        fdTableViewer.bottom = new FormAttachment(94, 0);

        viewer = new TableViewer(composite, SWT.FULL_SELECTION | SWT.BORDER);
        table = viewer.getTable();
        table.setLayoutData(fdTableViewer);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        // set column editoren
        viewer.setColumnProperties(columnNames);
        // Assign the cell editors to the viewer
        viewer.setCellEditors(editors);

        // Definere the table columns
        final TableColumn zeroColumn = new TableColumn(table, SWT.NONE);
        zeroColumn.setResizable(false);
        zeroColumn.setWidth(30);

        final TableColumn contextColumn = new TableColumn(table, SWT.NONE);
        contextColumn.setWidth(100);
        contextColumn.setText(CONTEXT_COLUMN);

        final TableColumn typeColumn = new TableColumn(table, SWT.NONE);
        typeColumn.setWidth(140);
        typeColumn.setText(TYPE_COLUMN);

        final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
        nameColumn.setWidth(100);
        nameColumn.setText(NAME_COLUMN);

        // Set EditorContent for Up/Down button validation
        UpDownButtonsValidator.getSingelton().setContents(this);
    }

    /**
     * set upItem - ToolItem enabled or disabled.
     * 
     * @param enabled
     *            the new up items enabled
     */
    public void setUpItemsEnabled(boolean enabled) {
        upItem.setEnabled(enabled);
    }

    /**
     * set downItem - ToolItem enabled or disabled.
     * 
     * @param enabled
     *            the new down items enabled
     */
    public void setDownItemsEnabled(boolean enabled) {
        downItem.setEnabled(enabled);
    }

    /**
     * set deleteItem - ToolItem enabled or disabled.
     * 
     * @param enabled
     *            the new delete items enabled
     */
    public void setDeleteItemsEnabled(boolean enabled) {
        deleteItem.setEnabled(enabled);
    }

    /**
     * Creates the separator.
     * 
     * @param composite
     *            the composite
     */
    public void createSeparator(Composite composite) {
        Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
        separator.setLayoutData(new GridData(492, SWT.DEFAULT));
    }

    /**
     * Gets the column names.
     * 
     * @return the column names
     */
    public static String[] getColumnNames() {
        return columnNames;
    }

    /**
     * Gets the viewer.
     * 
     * @return the viewer
     */
    public TableViewer getViewer() {
        return viewer;
    }
}
