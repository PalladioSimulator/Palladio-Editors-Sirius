package org.palladiosimulator.editors.dialogs.datatype;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.palladiosimulator.editors.dialogs.Messages;
import org.palladiosimulator.editors.dialogs.parameters.CreateEditorContents;
import org.palladiosimulator.editors.dialogs.parameters.UpDownButtonsValidator;
import org.palladiosimulator.pcm.repository.CollectionDataType;
import org.palladiosimulator.pcm.repository.CompositeDataType;
import org.palladiosimulator.pcm.repository.DataType;
import org.palladiosimulator.pcm.repository.InnerDeclaration;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

// TODO: Auto-generated Javadoc
/**
 * The class initialize the DataTypeDialog with the Palladio Component Model specific
 * characteristics.
 * 
 * @author Roman Andrej
 */
public class PalladioDataTypeDialog extends DataTypeDialog {

    /** The unnamed repository. */
    private final String UNNAMED_REPOSITORY = "<Unnamed Repository>";

    /** The adapter factory. */
    private ComposedAdapterFactory adapterFactory;

    /** The inner data type. */
    private DataType innerDataType;

    /** The edited data type. */
    private DataType editedDataType;

    /** The editor contents. */
    private CreateEditorContents editorContents;

    /** The edited repository. */
    private Repository editedRepository;

    /** The composite data type. */
    private CompositeDataType compositeDataType;

    /** The transactional editing domain which is used to get the commands and alter the model. */
    private TransactionalEditingDomain editingDomain = null;

    /**
     * Instantiates a new palladio data type dialog.
     * 
     * @param parentShell
     *            the parent shell
     * @param editingDomain
     *            the editing domain
     */
    public PalladioDataTypeDialog(Shell parentShell, TransactionalEditingDomain editingDomain) {
        super(parentShell);
        this.editingDomain = editingDomain;
    }

    /**
     * Instantiates a new palladio data type dialog.
     * 
     * @param parentShell
     *            the parent shell
     * @param editeDataType
     *            the edite data type
     */
    public PalladioDataTypeDialog(Shell parentShell, DataType editeDataType) {
        super(parentShell);
        this.editingDomain = TransactionUtil.getEditingDomain(editeDataType);
        this.editedDataType = editeDataType;
        initDialog(editeDataType);
    }

    /**
     * call if datatype set (edite button).
     * 
     * @param editeDataType
     *            the edite data type
     */
    private void initDialog(DataType editeDataType) {

        String entityName;
        String entityInnerType;
        String repository;

        if (editeDataType instanceof CollectionDataType) {
            CollectionDataType collectionDataType = (CollectionDataType) editeDataType;

            entityName = collectionDataType.getEntityName();
            repository = collectionDataType.getRepository__DataType().getEntityName();

            /**
             * PalladioLabelProvider - representation a inner DataType name whit Palladio look
             */
            entityInnerType = ParameterRepresentation.dataTypeToString(collectionDataType
                    .getInnerType_CollectionDataType());

            // create DataTypeDialog
            create();
            // Call constructor of DataTypeDialog
            super.init(DataTypeEnum.COLLECTION, repository, entityName, entityInnerType);
        }

        if (editeDataType instanceof CompositeDataType) {
            compositeDataType = (CompositeDataType) editeDataType;

            entityName = compositeDataType.getEntityName();
            repository = compositeDataType.getRepository__DataType().getEntityName();
            // create DataTypeDialog
            create();
            // Call constructor of DataTypeDialog
            super.init(DataTypeEnum.COMPOSITE, repository, entityName, null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.tabs.dialog.CreateDataTypeDialog#getLoadedReposetorys()
     */
    @Override
    public String[] getLoadedRepositories() {
        EList<Resource> resources = editingDomain.getResourceSet().getResources();

        List<String> tList = new ArrayList<String>();

        for (Resource r : resources) {
            URI uri = r.getURI();
            if (hasRepositoryExtension(uri) && !isPrimitiveTypesRepository(uri)
                    && (!r.getContents().isEmpty() && r.getContents().get(0) instanceof Repository)) {
                Repository repository = (Repository) r.getContents().get(0);
                String repositoryName = repository.getEntityName();
                tList.add(repositoryName == null ? UNNAMED_REPOSITORY : repositoryName);
            }
        }
        // convert to String[]
        return tList.toArray(new String[tList.size()]);
    }

    /**
     * Checks for repository extension.
     * 
     * @param uri
     *            the uri
     * @return true, if successful
     */
    private boolean hasRepositoryExtension(URI uri) {
        if (uri.fileExtension().equals("repository")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if is primitive types repository.
     * 
     * @param uri
     *            the uri
     * @return true, if is primitive types repository
     */
    private boolean isPrimitiveTypesRepository(URI uri) {
        String exp = "/PrimitiveTypes.repository";
        if (uri.path().endsWith(exp)) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.editors.tabs.dialogs.CreateDataTypeDialog#setEditedResource(java.lang.String)
     */
    @Override
    public void setEditedResource(String repositoryName) {
        EList<Resource> resources = editingDomain.getResourceSet().getResources();

        // Provide a list with loaded resources without primitive DataType
        for (Resource r : resources) {
            if (!r.getContents().isEmpty() && r.getContents().get(0) instanceof Repository) {
                Repository repository = (Repository) r.getContents().get(0);
                String entityName = repository.getEntityName() == null ? UNNAMED_REPOSITORY : repository
                        .getEntityName();

                if (entityName.contains(repositoryName)) {
                    editedRepository = repository;
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.editors.tabs.dialog.CreateDataTypeDialog#innerSectionCompositeDataType(org
     * .eclipse.swt.widgets.Composite)
     */
    @Override
    public void createInnerSectionCompositeGroup(Composite group) {

        adapterFactory = new ComposedAdapterFactory();
        adapterFactory.addAdapterFactory(new RepositoryItemProviderAdapterFactory());

        editorContents = CreateEditorContents.create(group);
        editorContents.setViewerContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        editorContents
                .setViewerLabelProvider(new AdapterFactoryLabelProvider(new InnerDeclarationItemProviderAdapterFactory(
                        new PalladioItemProviderAdapterFactory(adapterFactory))));
        editorContents.setViewerCellModifier(new InnerDeclarationCellModifier(this, editingDomain));
        editorContents.createNameColumnCellEditor();
        editorContents.createTypeColumnCellEditor(editingDomain);
        editorContents.setAddButtonActionListener(new AddInnerDeclarationAction(this, editingDomain));

        DeleteInnerDeclarationAction deleteInnerDeclarationAction = new DeleteInnerDeclarationAction(this,
                editingDomain);
        UpInnerDeclarationAction upInnerDeclarationAction = new UpInnerDeclarationAction(this, editingDomain);
        DownInnerDeclarationAction downInnerDeclarationAction = new DownInnerDeclarationAction(this, editingDomain);

        editorContents.setDeleteButtonActionListener(deleteInnerDeclarationAction);
        editorContents.setUpButtonActionListener(upInnerDeclarationAction);
        editorContents.setDownButtonActionListener(downInnerDeclarationAction);
        /** set SelectionChangedListener for viewer on the EditorContents */
        editorContents.setViewerSelectionChangedListener(deleteInnerDeclarationAction);
        editorContents.setViewerSelectionChangedListener(upInnerDeclarationAction);
        editorContents.setViewerSelectionChangedListener(downInnerDeclarationAction);
        editorContents.setViewerInput(editedDataType);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.editors.tabs.dialogs.CreateDataTypeDialog#defeniereActionTypeButton(org.eclipse
     * .swt.events.SelectionEvent)
     */
    @Override
    public String getSelectedInnerType(SelectionEvent e) {

        String selectedType = "null";

        ArrayList<Object> filterList = new ArrayList<Object>();
        filterList.add(DataType.class);
        filterList.add(Repository.class);

        CallDataTypeDialog dialog = new CallDataTypeDialog(e.display.getActiveShell(), filterList,
                new ArrayList<EReference>(), editingDomain.getResourceSet());
        dialog.setProvidedService(DataType.class);
        dialog.open();

        if (dialog.getResult() != null && dialog.getResult() instanceof DataType) {
            innerDataType = (DataType) dialog.getResult();

            selectedType = ParameterRepresentation.dataTypeToString(innerDataType);
        }
        return selectedType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.uka.ipd.sdq.dialogs.datatype.DataTypeDialog#validateCompositeDataType()
     */
    @Override
    public boolean validateCompositeDataType() {
        boolean state = true;

        if (compositeDataType == null || compositeDataType.getInnerDeclaration_CompositeDataType().isEmpty()) {
            setErrorMessage(Messages.DataTypeDialog_ErrorMsgInner);
            return false;
        } else {
            EList<InnerDeclaration> declarations = compositeDataType.getInnerDeclaration_CompositeDataType();
            for (InnerDeclaration declaration : declarations) {
                state &= UpDownButtonsValidator.getSingelton().validdateDeclarationInnerDataType(declaration, this);
            }
        }
        return state;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.tabs.dialogs.CreateDataTypeDialog#createCollectionDataType()
     */
    @Override
    public void createCollectionDataType() {
        new DataTypeCommand(editingDomain).createCollectionDataType(editedRepository, editedDataType, innerDataType,
                getEntityName());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.editors.tabs.dialogs.CreateDataTypeDialog#createCompositeDataType()
     */
    @Override
    public void createCompositeDataType() {
        new DataTypeCommand(editingDomain)
                .createCompositeDataType(editedRepository, compositeDataType, getEntityName());
    }

    /**
     * Gets the edited data type.
     * 
     * @return the edited data type
     */
    public DataType getEditedDataType() {
        return editedDataType;
    }

    /**
     * Gets the composite data type.
     * 
     * @return the compositeDataType
     */
    public CompositeDataType getCompositeDataType() {
        return compositeDataType;
    }

    /**
     * Sets the composite data type.
     * 
     * @param compositeDataType
     *            the compositeDataType to set
     */
    public void setCompositeDataType(CompositeDataType compositeDataType) {
        this.compositeDataType = compositeDataType;
    }

    /**
     * Gets the editor contents.
     * 
     * @return the editorContents
     */
    public CreateEditorContents getEditorContents() {
        return editorContents;
    }

    /**
     * Refresh.
     */
    public void refresh() {
        editorContents.getViewer().refresh();
        validateInput();
    }

    /**
     * Gets the inner data type.
     * 
     * @return the inner data type
     */
    protected DataType getInnerDataType() {
        return innerDataType;
    }

    /**
     * Gets the edited repository.
     * 
     * @return the edited repository
     */
    protected Repository getEditedRepository() {
        return editedRepository;
    }
}
