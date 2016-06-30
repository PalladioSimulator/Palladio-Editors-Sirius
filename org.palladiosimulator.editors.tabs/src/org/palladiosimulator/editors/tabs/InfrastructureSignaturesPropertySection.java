package org.palladiosimulator.editors.tabs;

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
import org.palladiosimulator.editors.tabs.operations.InfrastructureSignaturesEditorSection;
import org.palladiosimulator.editors.tabs.operations.InfrastructureSignaturesTabItemProviderAdapterFactory;
import org.palladiosimulator.pcm.repository.InfrastructureInterface;
import org.palladiosimulator.pcm.repository.InfrastructureSignature;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;
import org.palladiosimulator.pcm.ui.provider.PalladioItemProviderAdapterFactory;

/**
 * "Operations" Tab section for infrastructure signatures.
 * 
 * @author groenda
 */
public class InfrastructureSignaturesPropertySection extends AbstractPropertySection {

	/**	Content provider. */
	private ComposedAdapterFactory adapterFactory;
	/** Property editor section for infrastructure signatures. */
	private InfrastructureSignaturesEditorSection editorSection;

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

		editorSection = new InfrastructureSignaturesEditorSection(composite);
		editorSection
				.setViewerContentProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		editorSection
				.setViewerLabelProvider(new AdapterFactoryLabelProvider(
						new InfrastructureSignaturesTabItemProviderAdapterFactory(
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
		if (input instanceof InfrastructureSignature) {
			InfrastructureSignature signature = (InfrastructureSignature) input;
			input = signature.getInfrastructureInterface__InfrastructureSignature();
		}
		
		Assert.isTrue(input instanceof EObject);
		// set input for 'TableViewer'
		editorSection.setViewerInput(input);
		// set in the diagram selected object to the add button action listener.
		editorSection.getAddButtonListener().setSelectedInterface((InfrastructureInterface) input);
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
