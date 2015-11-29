package org.scaledl.architecturaltemplates.ocl.editor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.scaledl.architecturaltemplates.type.provider.TypeItemProviderAdapterFactory;

public class OCLConstraintsPropertiesSection extends AbstractPropertySection {

    protected PropertySheetPage page;

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        final Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        page = new PropertySheetPage();
        page.setPropertySourceProvider(
                new OCLConstraintAdapterFactoryContentProvider(new TypeItemProviderAdapterFactory()));

        page.createControl(composite);
        final FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        data.bottom = new FormAttachment(100, 0);
        page.getControl().setLayoutData(data);
    }

    @Override
    public void refresh() {
        page.refresh();
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection) {
        super.setInput(part, selection);
        page.selectionChanged(part, selection);
    }

    @Override
    public void dispose() {
        super.dispose();

        if (page != null) {
            page.dispose();
            page = null;
        }

    }
}
