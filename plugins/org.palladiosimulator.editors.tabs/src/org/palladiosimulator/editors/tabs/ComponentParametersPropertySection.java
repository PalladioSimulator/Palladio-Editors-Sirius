/**
 * 
 */
package org.palladiosimulator.editors.tabs;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.palladiosimulator.editors.tabs.parameters.ComponentParametersEditorSection;
import org.palladiosimulator.editors.tabs.parameters.ParameterContentProvider;
import org.palladiosimulator.editors.tabs.parameters.ParametersTabItemProviderAdapterFactory;
import org.palladiosimulator.editors.tabs.parameters.VariableUsageWrapper;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

/**
 * @author Roman Andrej
 *
 */
public class ComponentParametersPropertySection extends AbstractPropertySection {
	
	/**
	 * The Property Sheet Page used to display the standard properties
	 */
	private ComposedAdapterFactory adapterFactory;
	private ComponentParametersEditorSection propertySection;
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		
		Composite composite = getWidgetFactory()
			.createFlatFormComposite(parent);
		
		adapterFactory = new ComposedAdapterFactory();
		adapterFactory
		.addAdapterFactory(new RepositoryItemProviderAdapterFactory());
		adapterFactory
		.addAdapterFactory(new SeffItemProviderAdapterFactory());
		adapterFactory
		.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
		.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		
		propertySection = new ComponentParametersEditorSection(composite);
//		propertySection.setViewerContentProvider(new AdapterFactoryContentProvider(
//				adapterFactory));
		propertySection.setViewerContentProvider(new ParameterContentProvider());
//		propertySection.setViewerLabelProvider(new ParameterLabelProvider());
		propertySection.setViewerLabelProvider(new AdapterFactoryLabelProvider(
				new ParametersTabItemProviderAdapterFactory(
						new PalladioItemProviderAdapterFactory(adapterFactory))));


	
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		
		if (input instanceof GraphicalEditPart){
			GraphicalEditPart ep = (GraphicalEditPart)input;
			input = ep.getModel();
		}
		if (input instanceof View){
			input = ((View)input).getElement();
		}
		
		Assert.isTrue(input instanceof EObject);
		// set input for 'TableViewer'
		propertySection.setViewerInput(input);
		propertySection.getViewer().getTable();
		// set in the diagram selected object to the TableViever cell modifier.
		propertySection.getCellModifier().setContext((AssemblyContext) input);
		
		setColorOfTableItems(propertySection.getViewer().getTable());
	}
	
	private void setColorOfTableItems(Table table) {

		// set color for items
		Display display = table.getDisplay();
		Color gray = display.getSystemColor(SWT.COLOR_GRAY);

		TableItem[] items = table.getItems();

		for (int i = 0; i < items.length; i++) {
			TableItem item = items[i];

			VariableUsageWrapper wrapper = (VariableUsageWrapper) item
					.getData();

			if (!wrapper.isEdited()) {
				item.setForeground(gray);
			}

		}
	}
	
}
