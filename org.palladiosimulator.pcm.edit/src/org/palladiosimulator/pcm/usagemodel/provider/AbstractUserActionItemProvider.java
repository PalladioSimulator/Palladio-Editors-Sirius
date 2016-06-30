/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.usagemodel.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.palladiosimulator.pcm.core.entity.provider.EntityItemProvider;
import org.palladiosimulator.pcm.usagemodel.AbstractUserAction;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.usagemodel.AbstractUserAction} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class AbstractUserActionItemProvider extends EntityItemProvider {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright 2005-2015 by palladiosimulator.org";

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public AbstractUserActionItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object) {
        if (this.itemPropertyDescriptors == null)
        {
            super.getPropertyDescriptors(object);

            this.addSuccessorPropertyDescriptor(object);
            this.addPredecessorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Successor feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addSuccessorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AbstractUserAction_successor_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AbstractUserAction_successor_feature",
                                        "_UI_AbstractUserAction_type"),
                                UsagemodelPackage.Literals.ABSTRACT_USER_ACTION__SUCCESSOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Predecessor feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addPredecessorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AbstractUserAction_predecessor_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AbstractUserAction_predecessor_feature",
                                        "_UI_AbstractUserAction_type"),
                                UsagemodelPackage.Literals.ABSTRACT_USER_ACTION__PREDECESSOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AbstractUserAction) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_AbstractUserAction_type") :
                    this.getString("_UI_AbstractUserAction_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(final Notification notification) {
        this.updateChildren(notification);
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
