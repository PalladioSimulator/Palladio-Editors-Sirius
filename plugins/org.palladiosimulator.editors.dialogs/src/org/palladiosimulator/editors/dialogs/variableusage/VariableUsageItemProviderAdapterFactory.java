/**
 * 
 */
package org.palladiosimulator.editors.dialogs.variableusage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating VariableUsageItemProviderAdapter objects.
 *
 * @author admin
 */
public class VariableUsageItemProviderAdapterFactory extends DecoratorAdapterFactory {

    /**
     * Instantiates a new variable usage item provider adapter factory.
     *
     * @param decoratedAdapterFactory the decorated adapter factory
     */
    public VariableUsageItemProviderAdapterFactory(AdapterFactory decoratedAdapterFactory) {
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
        IItemProviderDecorator decorator = new VariableUsageLabelProvider(this);
        if (type == IItemLabelProvider.class) {
            decorator.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory.adapt(target,
                    IItemLabelProvider.class));
        } else {
            decorator.setDecoratedItemProvider((IChangeNotifier) decoratedAdapterFactory.adapt(target, type));
        }
        return decorator;
    }
}
