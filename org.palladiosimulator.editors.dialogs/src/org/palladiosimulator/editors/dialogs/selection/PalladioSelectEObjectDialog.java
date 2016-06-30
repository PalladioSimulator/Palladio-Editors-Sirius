package org.palladiosimulator.editors.dialogs.selection;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Shell;

import de.uka.ipd.sdq.dialogs.selection.FilteredItemsAdapterFactory;
import de.uka.ipd.sdq.dialogs.selection.SelectEObjectDialog;
import org.palladiosimulator.pcm.allocation.provider.AllocationItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import org.palladiosimulator.pcm.core.provider.CoreItemProviderAdapterFactory;
import org.palladiosimulator.pcm.parameter.provider.ParameterItemProviderAdapterFactory;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.resourceenvironment.provider.ResourceenvironmentItemProviderAdapterFactory;
import org.palladiosimulator.pcm.resourcetype.provider.ResourcetypeItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.subsystem.provider.SubsystemItemProviderAdapterFactory;
import org.palladiosimulator.pcm.system.provider.SystemItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;
import org.palladiosimulator.pcm.usagemodel.provider.UsagemodelItemProviderAdapterFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class PalladioSelectEObjectDialog.
 * 
 * @author Steffen Becker, Sebastian Lehrig
 */
public class PalladioSelectEObjectDialog extends SelectEObjectDialog {

    private static final String SYSTEM = "System";
    private static final String REPOSITORY = "Repository";
    private static final String RESOURCE_REPOSITORY = "ResourceRepository";
    private static final String RESOURCE_TYPE = "ResourceType";
    private static final ComposedAdapterFactory ADAPTER_FACTORY = new ComposedAdapterFactory();
    private static final IBaseLabelProvider LABEL_PROVIDER;

    static {
        ADAPTER_FACTORY.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new RepositoryItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new SeffItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new SystemItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new AllocationItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new UsagemodelItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new ResourceenvironmentItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new ParameterItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new ResourcetypeItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new CoreItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new CompositionItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new SubsystemItemProviderAdapterFactory());

        LABEL_PROVIDER = new AdapterFactoryLabelProvider(new PalladioItemProviderAdapterFactory(ADAPTER_FACTORY));
    }

    /**
     * Creates a selection dialog.
     * 
     * @param parent
     *            Shell of active workbench window
     * @param filterList
     *            Objects to show (positive filter). Also include respective super types here
     * @param additionalChildReferences
     *            Usually this should be the EReference which should be set
     * @param input
     *            ResourceSet or object of which resource set should be taken
     */
    public PalladioSelectEObjectDialog(Shell parent, Collection<Object> filterList,
            Collection<EReference> additionalChildReferences, Object input) {
        super(parent, getInputDialogResourceName(filterList), input, new AdapterFactoryContentProvider(new FilteredItemsAdapterFactory(ADAPTER_FACTORY,
                filterList, additionalChildReferences)), LABEL_PROVIDER);
    }

    /**
     * Instantiates a new Palladio select EObject dialog.
     * 
     * @param parent
     *            the parent
     * @param filterList
     *            the filter list
     * @param input
     *            the input
     */
    public PalladioSelectEObjectDialog(Shell parent, Collection<Object> filterList, Object input) {
        this(parent, filterList, new ArrayList<EReference>(), input);
    }

    protected static String getInputDialogResourceName(final Collection<Object> filterList) {
        final StringBuilder result = new StringBuilder();

        for (final Object object : filterList) {
            final String name = ((Class<?>) object).getSimpleName();

            if (name.equals(SYSTEM) || name.equals(REPOSITORY)) {
                result.append(name);
                result.append(" ");
            } else if (name.equals(RESOURCE_REPOSITORY)) {
                result.append(RESOURCE_TYPE);
                result.append(" ");
            }
        }

        return result.toString();
    }
}
