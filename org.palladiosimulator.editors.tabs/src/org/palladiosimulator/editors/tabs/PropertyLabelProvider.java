package org.palladiosimulator.editors.tabs;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.repository.provider.RepositoryItemProviderAdapterFactory;
import org.palladiosimulator.pcm.seff.provider.SeffItemProviderAdapterFactory;


/**
 * @author Snowball
 * An Label Provider (see the EMF bible for details on LabelProvider) for the labels of the heading
 * of any single tab sheet.
 * This label provider mostly delegates the calls to it to the generated
 * LabelProvider in the PCM.Edit plugin.
 */
public class PropertyLabelProvider 
extends LabelProvider 
implements
		ILabelProvider {

	private ComposedAdapterFactory adapterFactory;

	private AdapterFactoryLabelProvider labelProvider = null;

	/**
	 * Constructor. Sets up a LabelProvider by using an adapter factory
	 * provided by the generated PCM.Edit plugin
	 */
	public PropertyLabelProvider() {
		super();
		adapterFactory = new ComposedAdapterFactory();
		adapterFactory
			.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory
			.addAdapterFactory(new RepositoryItemProviderAdapterFactory());
		adapterFactory
			.addAdapterFactory(new SeffItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory)
		{

			/* (non-Javadoc)
			 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
			 */
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				fireLabelProviderChanged();
			}

		};

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 * GetImage is delegated to the PCM.Edit label provider
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) element;
			if (selection.getFirstElement() instanceof EObject) {
				EObject selectedObject = (EObject) selection.getFirstElement();
				return labelProvider.getImage(selectedObject);
			}
		}
		return super.getImage(element);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 * GetText delegated to the PCM.Edit LabelProvider. If the object is an
	 * entity we change the label to include the UUID of the object
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) element;
			if (selection.getFirstElement() instanceof EObject) {
				EObject selectedObject = (EObject) selection.getFirstElement();
				String result = labelProvider.getText(selectedObject); 
				if (selectedObject instanceof Entity)
				{
					Entity e = (Entity)selectedObject;
					result = e.getEntityName() + " [ID: " + e.getId() +"]";
				}
				return result;
			}
		}
		return super.getText(element);
	}

	@Override
	public void dispose() {
		labelProvider.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		super.addListener(listener);
		labelProvider.addListener(listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.LabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		labelProvider.removeListener(listener);
		super.removeListener(listener);
	}
	
}
