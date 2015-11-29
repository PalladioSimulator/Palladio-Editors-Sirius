package org.scaledl.architecturaltemplates.ocl.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.ui.views.properties.IPropertySource;

public class OCLConstraintAdapterFactoryContentProvider extends AdapterFactoryContentProvider {

    public OCLConstraintAdapterFactoryContentProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IPropertySource createPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
        return new OCLConstraintPropertySource(object, itemPropertySource);
    }
}
