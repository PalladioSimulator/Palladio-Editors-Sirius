package org.palladiosimulator.editors.dialogs.parameters;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.uka.ipd.sdq.dialogs.selection.FilteredItemsAdapterFactory;

import org.palladiosimulator.editors.dialogs.Messages;
import org.palladiosimulator.pcm.repository.Parameter;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ParametersDialog.
 *
 * @author Roman Andrej
 * 
 * The dialogue serves the input of parameter names and types in table cells. It a simple
 * editor implemented for providing and deletion of types.
 */
public class ParametersDialog extends TitleAreaDialog {

    /** The adapter factory. */
    private ComposedAdapterFactory adapterFactory;

    /** The signature. */
    private Signature signature;

    /**
     * Creates a dialog with the given parent and edited properties name.
     *
     * @param parentShell -object that returns the current parent shell columnName - edited properties
     * @param signature the signature
     */
    public ParametersDialog(Shell parentShell, Signature signature) {
        super(parentShell);
        this.signature = signature;

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
        newShell.setText("OwnedParameters");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        setTitle(Messages.ParametersDialog_Title);

        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayout(new FormLayout());
        container.setLayoutData(new GridData(GridData.FILL_BOTH));

        ArrayList<Object> filterList = new ArrayList<Object>();
        filterList.add(Parameter.class);
        ArrayList<EReference> additionalReferences = new ArrayList<EReference>();

        adapterFactory = new ComposedAdapterFactory();
        adapterFactory.addAdapterFactory(new RepositoryItemProviderAdapterFactory());

        CreateEditorContents editorContents = CreateEditorContents.create(container);

        editorContents.setViewerContentProvider(new AdapterFactoryContentProvider(new FilteredItemsAdapterFactory(
                adapterFactory, filterList, additionalReferences)));
        editorContents.setViewerLabelProvider(new AdapterFactoryLabelProvider(new ParametersItemProviderAdapterFactory(
                new PalladioItemProviderAdapterFactory(adapterFactory))));
        editorContents.setViewerCellModifier(new ParametersCellModifier(editorContents.getViewer(), TransactionUtil
                .getEditingDomain(signature)));
        editorContents.createNameColumnCellEditor();
        editorContents.createTypeColumnCellEditor(TransactionUtil.getEditingDomain(signature));
        editorContents.setAddButtonActionListener(new AddParameterAction(signature));

        DeleteParameterAction deleteParameterAction = new DeleteParameterAction(signature);
        UpParameterAction upParameterAction = new UpParameterAction(signature);
        DownParameterAction downParameterAction = new DownParameterAction(signature);

        editorContents.setDeleteButtonActionListener(deleteParameterAction);
        editorContents.setUpButtonActionListener(upParameterAction);
        editorContents.setDownButtonActionListener(downParameterAction);

        editorContents.setViewerSelectionChangedListener(deleteParameterAction);
        editorContents.setViewerSelectionChangedListener(upParameterAction);
        editorContents.setViewerSelectionChangedListener(downParameterAction);

        editorContents.setViewerInput(signature);

        /** create separatot to button area */
        ((CreateEditorContents) editorContents).createSeparator(parent);

        return area;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, false);
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
}
