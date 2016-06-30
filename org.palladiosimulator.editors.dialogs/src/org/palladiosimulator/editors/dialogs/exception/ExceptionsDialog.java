package org.palladiosimulator.editors.dialogs.exception;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import de.uka.ipd.sdq.dialogs.selection.FilteredItemsAdapterFactory;

import org.palladiosimulator.editors.dialogs.DialogsImages;
import org.palladiosimulator.pcm.repository.ExceptionType;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceptionsDialog.
 */
public class ExceptionsDialog extends TitleAreaDialog {

    /** The signature. */
    private Signature signature;

    /** The seleced exception type. */
    private ExceptionType selecedExceptionType;

    /** The message field. */
    private Text messageField;

    /** The delete action adapter. */
    private SelectionAdapter deleteActionAdapter;

    /** The Constant ICON_COLUMN_INDEX. */
    public static final int ICON_COLUMN_INDEX = 0;

    /** The Constant CONTEXT_COLUMN_INDEX. */
    public static final int CONTEXT_COLUMN_INDEX = 1;

    /** The Constant NAME_COLUMN_INDEX. */
    public static final int NAME_COLUMN_INDEX = 2;

    /** Columns of a table, which is used into ParameterEditDialog. */
    public static final String ICON_COLUMN = "";

    /** The Constant CONTEXT_COLUMN. */
    public static final String CONTEXT_COLUMN = "Context";

    /** The Constant NAME_COLUMN. */
    public static final String NAME_COLUMN = "Name";

    /** The title dialog. */
    private final String TITLE_DIALOG = "Create/Edit a ExceptionType...";

    // Set column names of Table
    /** The column names. */
    private static String[] columnNames = new String[] { ICON_COLUMN, CONTEXT_COLUMN, NAME_COLUMN };

    /** The viewer. */
    private TableViewer viewer;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    protected TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new exceptions dialog.
     * 
     * @param parentShell
     *            the parent shell
     * @param signature
     *            the signature
     */
    public ExceptionsDialog(Shell parentShell, Signature signature) {
        super(parentShell);
        this.signature = signature;
        this.deleteActionAdapter = new DeleteExctentionAction(signature);
        this.editingDomain = TransactionUtil.getEditingDomain(signature);

        /**
         * the result of combining the constants which are required to produce a typical application
         * top level shell
         */
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.MIN | SWT.MAX);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Exceptions");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {

        Composite area = (Composite) super.createDialogArea(parent);

        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        container.setLayout(new GridLayout());

        final Group exceptionsGroup = new Group(container, SWT.NONE);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        exceptionsGroup.setLayout(gridLayout);
        final GridData gd_group = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_group.widthHint = 476;
        exceptionsGroup.setLayoutData(gd_group);
        exceptionsGroup.setText("Exeptions");

        final Table table = new Table(exceptionsGroup, SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        final GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_table.widthHint = 401;
        table.setLayoutData(gd_table);

        // Definere the table columns
        final TableColumn zeroColumn = new TableColumn(table, SWT.NONE);
        zeroColumn.setResizable(false);
        zeroColumn.setWidth(30);

        final TableColumn contextColumn = new TableColumn(table, SWT.NONE);
        contextColumn.setWidth(100);
        contextColumn.setText(CONTEXT_COLUMN);

        final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
        nameColumn.setWidth(140);
        nameColumn.setText(NAME_COLUMN);

        /** set create TableViewer instance */
        createTableViewer(table);

        final ToolBar toolBar = new ToolBar(exceptionsGroup, SWT.VERTICAL);
        final GridData gd_toolBar = new GridData(SWT.FILL, SWT.FILL, false, true);
        toolBar.setLayoutData(gd_toolBar);

        ToolItem addItem = new ToolItem(toolBar, SWT.PUSH);
        addItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.ADD));
        addItem.addSelectionListener(new AddExceptionTypeAction(signature));

        ToolItem deleteItem = new ToolItem(toolBar, SWT.PUSH);
        deleteItem.setImage(DialogsImages.imageRegistry.get(DialogsImages.DELETE));
        deleteItem.addSelectionListener(deleteActionAdapter);

        final Group exceptionMessageGroup = new Group(container, SWT.NONE);
        exceptionMessageGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        exceptionMessageGroup.setText("Exception Message");
        exceptionMessageGroup.setLayout(new GridLayout());

        messageField = new Text(exceptionMessageGroup, SWT.BORDER | SWT.MULTI);
        final GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, true, false);
        gd_text.heightHint = 60;
        messageField.setLayoutData(gd_text);
        messageField.addModifyListener(new ModifyListener() {

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.eclipse.swt.events.ModifyListener#modifyText(org.eclipse.swt.events.ModifyEvent)
             */
            public void modifyText(ModifyEvent e) {
                setExceptionTypeMessage(messageField.getText());
            }
        });

        setTitle(TITLE_DIALOG);

        return container;
    }

    /**
     * Initialization of TableViewer.
     * 
     * @param table
     *            the table
     */
    private void createTableViewer(Table table) {

        ArrayList<Object> filterList = new ArrayList<Object>();
        filterList.add(ExceptionType.class);
        ArrayList<EReference> additionalReferences = new ArrayList<EReference>();

        viewer = new TableViewer(table);
        viewer.setColumnProperties(columnNames);

        /** Create the cell editors for Name column */
        CellEditor[] editors = new CellEditor[columnNames.length];

        editors[NAME_COLUMN_INDEX] = new TextCellEditor(table);

        /** Assign the cell editors to the viewer */
        viewer.setCellEditors(editors);
        viewer.setCellModifier(new ExceptionsCellModifier(viewer, TransactionUtil.getEditingDomain(signature)));
        viewer.addSelectionChangedListener((ISelectionChangedListener) deleteActionAdapter);

        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
        adapterFactory.addAdapterFactory(new RepositoryItemProviderAdapterFactory());
        viewer.setContentProvider(new AdapterFactoryContentProvider(new FilteredItemsAdapterFactory(adapterFactory,
                filterList, additionalReferences)));
        viewer.setLabelProvider(new AdapterFactoryLabelProvider(new ExceptionsItemProviderAdapterFactory(
                new PalladioItemProviderAdapterFactory(adapterFactory))));
        viewer.addSelectionChangedListener(new ISelectionChangedListener() {
            public void selectionChanged(SelectionChangedEvent event) {
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                Object selection = sel.getFirstElement();
                selecedExceptionType = (ExceptionType) selection;
                if (selecedExceptionType != null && selecedExceptionType.getExceptionMessage() != null) {
                    messageField.setText(selecedExceptionType.getExceptionMessage());
                } else {
                    messageField.setText("");
                }
            }
        });
        viewer.setInput(signature);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.TitleAreaDialog#getInitialSize()
     */
    @Override
    protected Point getInitialSize() {
        return new Point(500, 375);
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
     * Set a message of ExceptionType.
     * 
     * @param msg
     *            the new exception type message
     */
    private void setExceptionTypeMessage(final String msg) {
        RecordingCommand recCommand = new RecordingCommand(editingDomain) {
            @Override
            protected void doExecute() {
                selecedExceptionType.setExceptionMessage(msg);
            }
        };

        recCommand.setDescription("Delete ...");
        editingDomain.getCommandStack().execute(recCommand);
    }

}
