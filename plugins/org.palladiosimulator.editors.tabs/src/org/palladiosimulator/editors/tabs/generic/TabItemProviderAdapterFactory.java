package org.palladiosimulator.editors.tabs.generic;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.palladiosimulator.editors.tabs.operations.OperationsTabItemProvider;

public class TabItemProviderAdapterFactory<T extends ItemProviderDecorator> extends
		DecoratorAdapterFactory {

	public TabItemProviderAdapterFactory(AdapterFactory decoratedAdapterFactory) {
		super(decoratedAdapterFactory);
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.DecoratorAdapterFactory#createItemProviderDecorator(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target,
			Object type) {
		IItemProviderDecorator decorator = new OperationsTabItemProvider(this);
		if (type == ITableItemLabelProvider.class) {
			decorator
					.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory
							.adapt(target, IItemLabelProvider.class));
		} else {
			decorator
					.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory
							.adapt(target, type));
		}
		return decorator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.DecoratorAdapterFactory#adapt(java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object adapt(Object target, Object type) {
		if (type == ITableItemLabelProvider.class)
			return createItemProviderDecorator(target, type);
		return super.adapt(target, type);
	}
	
//	private T getItemProviderDecorator(Class<T> decoratorClass, ){
//		try {
//			return decoratorClass.;
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
