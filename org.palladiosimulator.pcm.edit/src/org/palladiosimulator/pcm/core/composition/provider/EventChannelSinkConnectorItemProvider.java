/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.core.composition.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.EventChannelSinkConnector;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.EventChannelSinkConnector} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class EventChannelSinkConnectorItemProvider extends ConnectorItemProvider {

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
    public EventChannelSinkConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addSinkRole__EventChannelSinkConnectorPropertyDescriptor(object);
            this.addAssemblyContext__EventChannelSinkConnectorPropertyDescriptor(object);
            this.addEventChannel__EventChannelSinkConnectorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Sink Role Event Channel Sink Connector feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSinkRole__EventChannelSinkConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_EventChannelSinkConnector_sinkRole__EventChannelSinkConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_EventChannelSinkConnector_sinkRole__EventChannelSinkConnector_feature",
                                        "_UI_EventChannelSinkConnector_type"),
                                CompositionPackage.Literals.EVENT_CHANNEL_SINK_CONNECTOR__SINK_ROLE_EVENT_CHANNEL_SINK_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Assembly Context Event Channel Sink Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addAssemblyContext__EventChannelSinkConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_EventChannelSinkConnector_assemblyContext__EventChannelSinkConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_EventChannelSinkConnector_assemblyContext__EventChannelSinkConnector_feature",
                                        "_UI_EventChannelSinkConnector_type"),
                                CompositionPackage.Literals.EVENT_CHANNEL_SINK_CONNECTOR__ASSEMBLY_CONTEXT_EVENT_CHANNEL_SINK_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Event Channel Event Channel Sink Connector feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEventChannel__EventChannelSinkConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_EventChannelSinkConnector_eventChannel__EventChannelSinkConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_EventChannelSinkConnector_eventChannel__EventChannelSinkConnector_feature",
                                        "_UI_EventChannelSinkConnector_type"),
                                CompositionPackage.Literals.EVENT_CHANNEL_SINK_CONNECTOR__EVENT_CHANNEL_EVENT_CHANNEL_SINK_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate
     * feature for an {@link org.eclipse.emf.edit.command.AddCommand},
     * {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(final Object object) {
        if (this.childrenFeatures == null)
        {
            super.getChildrenFeatures(object);
            this.childrenFeatures
            .add(CompositionPackage.Literals.EVENT_CHANNEL_SINK_CONNECTOR__FILTER_CONDITION_EVENT_CHANNEL_SINK_CONNECTOR);
        }
        return this.childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(final Object object, final Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns EventChannelSinkConnector.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/EventChannelSinkConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((EventChannelSinkConnector) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_EventChannelSinkConnector_type") :
                    this.getString("_UI_EventChannelSinkConnector_type") + " " + label;
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

        switch (notification.getFeatureID(EventChannelSinkConnector.class))
        {
        case CompositionPackage.EVENT_CHANNEL_SINK_CONNECTOR__FILTER_CONDITION_EVENT_CHANNEL_SINK_CONNECTOR:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
            return;
        }
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

        newChildDescriptors
        .add
        (this.createChildParameter
                        (CompositionPackage.Literals.EVENT_CHANNEL_SINK_CONNECTOR__FILTER_CONDITION_EVENT_CHANNEL_SINK_CONNECTOR,
                                CoreFactory.eINSTANCE.createPCMRandomVariable()));
    }

}
