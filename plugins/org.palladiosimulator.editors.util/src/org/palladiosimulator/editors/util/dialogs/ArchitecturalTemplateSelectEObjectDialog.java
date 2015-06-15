package org.palladiosimulator.editors.util.dialogs;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
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

/**
 * A {@link SelectEObjectDialog} for selecting Architectural Templates ({@link AT}) and {@link Roles}.
 * 
 * @author max
 *
 */
public class ArchitecturalTemplateSelectEObjectDialog extends
		SelectEObjectDialog {

	/**
	 * The resource`s file extension.
	 */
	private static final String RESOURCE_EXTENSION = "Type";
	
	/**
	 * The filter list to be used when selecting an {@link AT}.
	 */
	private static final List<Object> AT_FILTER_LIST = Collections
			.unmodifiableList(Arrays.asList(Repository.class, AT.class));
	
	/**
	 * The filter list to be used when selecting an {@link Role}.
	 */
	private static final List<Object> ROLE_FILTER_LIST = Collections
			.unmodifiableList(Arrays.asList(Repository.class, Role.class));
	
    private static final ComposedAdapterFactory ADAPTER_FACTORY = new ComposedAdapterFactory();
    private static final IBaseLabelProvider LABEL_PROVIDER;

    static {
        ADAPTER_FACTORY.addAdapterFactory(new ResourceItemProviderAdapterFactory());
        ADAPTER_FACTORY.addAdapterFactory(new TypeItemProviderAdapterFactory());

        LABEL_PROVIDER = new AdapterFactoryLabelProvider(new PalladioItemProviderAdapterFactory(ADAPTER_FACTORY));
    }

    /**
     * An enum defining types of AT-items to be selected.
     * @author max
     *
     */
	public static enum Type {
		AT, ROLE,
	}

	/**
	 * Creates a new {@link ArchitecturalTemplateSelectEObjectDialog}.
	 * 
	 * @param parentShell the parent shell
	 * @param input the {@link ResourceSet} to be used or an {@link EObject} from the set
	 * @param type the type of item {@link Type}
	 */
	public ArchitecturalTemplateSelectEObjectDialog(Shell parentShell,
			Object input, Type type) {
		super(parentShell, 
				RESOURCE_EXTENSION, 
				input,
				new AdapterFactoryContentProvider(
						new FilteredItemsAdapterFactory(ADAPTER_FACTORY,
								getFilterList(type), Collections.emptyList())),
				LABEL_PROVIDER);
	}

	/**
	 * Returns the correct filter list ({@link #AT_FILTER_LIST} or {@link #ROLE_FILTER_LIST}) depending on the given {@link Type}.
	 * @param type the type for which the filter list is defined
	 * @return the filter list
	 */
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
