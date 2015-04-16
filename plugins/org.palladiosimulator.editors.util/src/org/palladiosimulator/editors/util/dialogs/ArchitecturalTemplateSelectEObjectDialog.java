package org.palladiosimulator.editors.util.dialogs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Repository;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.provider.TypeItemProviderAdapterFactory;

import de.uka.ipd.sdq.dialogs.selection.FilteredItemsAdapterFactory;
import de.uka.ipd.sdq.dialogs.selection.SelectEObjectDialog;
import de.uka.ipd.sdq.pcmbench.ui.provider.PalladioItemProviderAdapterFactory;

public class ArchitecturalTemplateSelectEObjectDialog extends
		SelectEObjectDialog {

	private static final String RESOURCE_NAME = "AT-Repository";
	private static final List<Object> AT_FILTER_LIST = Collections
			.unmodifiableList(Arrays.asList(Repository.class, AT.class));
	private static final List<Object> ROLE_FILTER_LIST = Collections
			.unmodifiableList(Arrays.asList(Repository.class, Role.class));
    private static final ComposedAdapterFactory ADAPTER_FACTORY = new ComposedAdapterFactory();
    private static final IBaseLabelProvider LABEL_PROVIDER;

    static {
        ADAPTER_FACTORY.addAdapterFactory(new ResourceItemProviderAdapterFactory());
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
