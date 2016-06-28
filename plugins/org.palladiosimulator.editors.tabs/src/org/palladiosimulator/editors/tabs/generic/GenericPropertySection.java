/**
 * 
 */
package org.palladiosimulator.editors.tabs.generic;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.palladiosimulator.editors.tabs.operations.OperationsEditorSection;
import org.palladiosimulator.editors.tabs.operations.OperationsTabItemProviderAdapterFactory;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

/**
 * @author Roman Andrej
 *
 */
public class GenericPropertySection extends AbstractPropertySection {
	
	/**
	 * The Property Sheet Page used to display the standard properties
	 */
	private ComposedAdapterFactory adapterFactory;
	private EditorSection editorSection;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
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
		adapterFactory.addAdapterFactory(new SeffItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		editorSection = new OperationsEditorSection(composite);
		editorSection
				.setViewerContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		editorSection
				.setViewerLabelProvider(new AdapterFactoryLabelProvider(
						new OperationsTabItemProviderAdapterFactory(
								new PalladioItemProviderAdapterFactory(
										adapterFactory))));
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
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
		if (input instanceof OperationSignature) {
			OperationSignature signature = (OperationSignature) input;
			input = signature.getInterface__OperationSignature();
		}
		if (input instanceof InfrastructureSignature) {
			InfrastructureSignature signature = (InfrastructureSignature) input;
			input = signature.getInfrastructureInterface__InfrastructureSignature();
		}
		
		Assert.isTrue(input instanceof EObject);
		editorSection.setViewerInput(input);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh() {
		editorSection.refresh();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

}
