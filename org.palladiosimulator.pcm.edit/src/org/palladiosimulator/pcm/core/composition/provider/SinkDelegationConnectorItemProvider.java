/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.SinkDelegationConnector;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.SinkDelegationConnector} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class SinkDelegationConnectorItemProvider extends DelegationConnectorItemProvider {

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
    public SinkDelegationConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addAssemblyContext__SinkDelegationConnectorPropertyDescriptor(object);
            this.addInnerSinkRole__SinkRolePropertyDescriptor(object);
            this.addOuterSinkRole__SinkRolePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Assembly Context Sink Delegation Connector feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAssemblyContext__SinkDelegationConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SinkDelegationConnector_assemblyContext__SinkDelegationConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_SinkDelegationConnector_assemblyContext__SinkDelegationConnector_feature",
                                        "_UI_SinkDelegationConnector_type"),
                                CompositionPackage.Literals.SINK_DELEGATION_CONNECTOR__ASSEMBLY_CONTEXT_SINK_DELEGATION_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Inner Sink Role Sink Role feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addInnerSinkRole__SinkRolePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SinkDelegationConnector_innerSinkRole__SinkRole_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_SinkDelegationConnector_innerSinkRole__SinkRole_feature",
                                        "_UI_SinkDelegationConnector_type"),
                                CompositionPackage.Literals.SINK_DELEGATION_CONNECTOR__INNER_SINK_ROLE_SINK_ROLE,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Outer Sink Role Sink Role feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addOuterSinkRole__SinkRolePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SinkDelegationConnector_outerSinkRole__SinkRole_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_SinkDelegationConnector_outerSinkRole__SinkRole_feature",
                                        "_UI_SinkDelegationConnector_type"),
                                CompositionPackage.Literals.SINK_DELEGATION_CONNECTOR__OUTER_SINK_ROLE_SINK_ROLE,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns SinkDelegationConnector.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/SinkDelegationConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((SinkDelegationConnector) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_SinkDelegationConnector_type") :
                    this.getString("_UI_SinkDelegationConnector_type") + " " + label;
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
