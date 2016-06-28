package org.palladiosimulator.editors.dialogs.exception;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating ExceptionsItemProviderAdapter objects.
 */
public class ExceptionsItemProviderAdapterFactory extends DecoratorAdapterFactory {

    /**
     * Instantiates a new exceptions item provider adapter factory.
     * 
     * @param decoratedAdapterFactory
     *            the decorated adapter factory
     */
    public ExceptionsItemProviderAdapterFactory(AdapterFactory decoratedAdapterFactory) {
        super(decoratedAdapterFactory);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.emf.edit.provider.DecoratorAdapterFactory#createItemProviderDecorator(java.lang
     * .Object, java.lang.Object)
     */
    @Override
    protected IItemProviderDecorator createItemProviderDecorator(Object target, Object type) {
        IItemProviderDecorator decorator = new ExceptionsItemProvider(this);
        if (type == ITableItemLabelProvider.class) {
            decorator.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory.adapt(target,
                    IItemLabelProvider.class));
        } else {
            decorator.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory.adapt(target, type));
        }
        return decorator;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.edit.provider.DecoratorAdapterFactory#adapt(java.lang.Object,
     * java.lang.Object)
     */
    @Override
    public Object adapt(Object target, Object type) {
        if (type == ITableItemLabelProvider.class) {
            return createItemProviderDecorator(target, type);
        }
        return super.adapt(target, type);
    }
}
