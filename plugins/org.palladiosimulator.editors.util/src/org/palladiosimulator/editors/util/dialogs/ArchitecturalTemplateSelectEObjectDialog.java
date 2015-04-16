package org.palladiosimulator.editors.util.dialogs;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.provider.TypeItemProviderAdapterFactory;

import de.uka.ipd.sdq.dialogs.selection.FilteredItemsAdapterFactory;
import de.uka.ipd.sdq.dialogs.selection.SelectEObjectDialog;
import de.uka.ipd.sdq.pcm.allocation.provider.AllocationItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.core.composition.provider.CompositionItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.core.provider.CoreItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.parameter.provider.ParameterItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.resourceenvironment.provider.ResourceenvironmentItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.resourcetype.provider.ResourcetypeItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.seff.provider.SeffItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.subsystem.provider.SubsystemItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.system.provider.SystemItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcm.usagemodel.provider.UsagemodelItemProviderAdapterFactory;
import de.uka.ipd.sdq.pcmbench.ui.provider.PalladioItemProviderAdapterFactory;

public class ArchitecturalTemplateSelectEObjectDialog extends
		SelectEObjectDialog {

	private static final String RESOURCE_NAME = "AT-Repository";
	private static final List<Object> AT_FILTER_LIST = Collections
			.singletonList(AT.class);
	private static final List<Object> ROLE_FILTER_LIST = Collections
			.singletonList(Role.class);
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
        ADAPTER_FACTORY.addAdapterFactory(new TypeItemProviderAdapterFactory());

        LABEL_PROVIDER = new AdapterFactoryLabelProvider(new PalladioItemProviderAdapterFactory(ADAPTER_FACTORY));
    }

	public static enum Type {
		AT, ROLE,
	}

	public ArchitecturalTemplateSelectEObjectDialog(Shell parentShell,
			Object input, Type type) {
		super(parentShell, 
				RESOURCE_NAME, 
				input,
				new AdapterFactoryContentProvider(
						new FilteredItemsAdapterFactory(ADAPTER_FACTORY,
								getFilterList(type), Collections.emptyList())),
				LABEL_PROVIDER);
	}

	private static Collection<Object> getFilterList(final Type type) {
		switch (type) {
		case AT:
			return AT_FILTER_LIST;
		case ROLE:
			return ROLE_FILTER_LIST;
		default:
			return null;
		}
	}
}
