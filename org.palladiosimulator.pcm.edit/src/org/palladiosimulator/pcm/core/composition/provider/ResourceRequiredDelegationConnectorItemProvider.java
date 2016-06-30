/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.ResourceRequiredDelegationConnector} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ResourceRequiredDelegationConnectorItemProvider extends ItemProviderAdapter
implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
IItemLabelProvider, IItemPropertySource {

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
    public ResourceRequiredDelegationConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addInnerResourceRequiredRole_ResourceRequiredDelegationConnectorPropertyDescriptor(object);
            this.addOuterResourceRequiredRole_ResourceRequiredDelegationConnectorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Inner Resource Required Role Resource Required
     * Delegation Connector feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInnerResourceRequiredRole_ResourceRequiredDelegationConnectorPropertyDescriptor(
            final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_ResourceRequiredDelegationConnector_innerResourceRequiredRole_ResourceRequiredDelegationConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_ResourceRequiredDelegationConnector_innerResourceRequiredRole_ResourceRequiredDelegationConnector_feature",
                                        "_UI_ResourceRequiredDelegationConnector_type"),
                                CompositionPackage.Literals.RESOURCE_REQUIRED_DELEGATION_CONNECTOR__INNER_RESOURCE_REQUIRED_ROLE_RESOURCE_REQUIRED_DELEGATION_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Outer Resource Required Role Resource Required
     * Delegation Connector feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addOuterResourceRequiredRole_ResourceRequiredDelegationConnectorPropertyDescriptor(
            final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_ResourceRequiredDelegationConnector_outerResourceRequiredRole_ResourceRequiredDelegationConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_ResourceRequiredDelegationConnector_outerResourceRequiredRole_ResourceRequiredDelegationConnector_feature",
                                        "_UI_ResourceRequiredDelegationConnector_type"),
                                CompositionPackage.Literals.RESOURCE_REQUIRED_DELEGATION_CONNECTOR__OUTER_RESOURCE_REQUIRED_ROLE_RESOURCE_REQUIRED_DELEGATION_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns ResourceRequiredDelegationConnector.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/ResourceRequiredDelegationConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        return this.getString("_UI_ResourceRequiredDelegationConnector_type");
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

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ((IChildCreationExtender) this.adapterFactory).getResourceLocator();
    }

}
