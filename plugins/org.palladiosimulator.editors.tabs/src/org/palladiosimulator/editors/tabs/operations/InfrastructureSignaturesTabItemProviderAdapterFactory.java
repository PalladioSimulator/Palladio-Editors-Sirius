package org.palladiosimulator.editors.tabs.operations;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;

public class InfrastructureSignaturesTabItemProviderAdapterFactory extends
		DecoratorAdapterFactory {

	public InfrastructureSignaturesTabItemProviderAdapterFactory(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target,
			Object type) {
		IItemProviderDecorator decorator = new InfrastructureSignaturesTabItemProvider(this);
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

	@Override
	public Object adapt(Object target, Object type) {
		if (type == ITableItemLabelProvider.class)
			return createItemProviderDecorator(target, type);
		return super.adapt(target, type);
	}
}
