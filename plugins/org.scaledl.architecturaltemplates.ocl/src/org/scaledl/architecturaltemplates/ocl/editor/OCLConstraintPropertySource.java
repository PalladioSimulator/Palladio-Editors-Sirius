package org.scaledl.architecturaltemplates.ocl.editor;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

public class OCLConstraintPropertySource extends PropertySource {

    public OCLConstraintPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
        super(object, itemPropertySource);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IPropertyDescriptor createPropertyDescriptor(final IItemPropertyDescriptor itemPropertyDescriptor) {
        return new OCLConstraintPropertyDescriptor(object, itemPropertyDescriptor);
    }

}
