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
import org.palladiosimulator.pcm.core.composition.AssemblyEventConnector;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.AssemblyEventConnector} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AssemblyEventConnectorItemProvider extends ConnectorItemProvider {

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
    public AssemblyEventConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addSinkRole__AssemblyEventConnectorPropertyDescriptor(object);
            this.addSourceRole__AssemblyEventConnectorPropertyDescriptor(object);
            this.addSinkAssemblyContext__AssemblyEventConnectorPropertyDescriptor(object);
            this.addSourceAssemblyContext__AssemblyEventConnectorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Sink Role Assembly Event Connector feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSinkRole__AssemblyEventConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyEventConnector_sinkRole__AssemblyEventConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyEventConnector_sinkRole__AssemblyEventConnector_feature",
                                        "_UI_AssemblyEventConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__SINK_ROLE_ASSEMBLY_EVENT_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Source Role Assembly Event Connector feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSourceRole__AssemblyEventConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyEventConnector_sourceRole__AssemblyEventConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyEventConnector_sourceRole__AssemblyEventConnector_feature",
                                        "_UI_AssemblyEventConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__SOURCE_ROLE_ASSEMBLY_EVENT_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Sink Assembly Context Assembly Event Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSinkAssemblyContext__AssemblyEventConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyEventConnector_sinkAssemblyContext__AssemblyEventConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyEventConnector_sinkAssemblyContext__AssemblyEventConnector_feature",
                                        "_UI_AssemblyEventConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__SINK_ASSEMBLY_CONTEXT_ASSEMBLY_EVENT_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Source Assembly Context Assembly Event Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSourceAssemblyContext__AssemblyEventConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyEventConnector_sourceAssemblyContext__AssemblyEventConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyEventConnector_sourceAssemblyContext__AssemblyEventConnector_feature",
                                        "_UI_AssemblyEventConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__SOURCE_ASSEMBLY_CONTEXT_ASSEMBLY_EVENT_CONNECTOR,
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
            .add(CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__FILTER_CONDITION_ASSEMBLY_EVENT_CONNECTOR);
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
     * This returns AssemblyEventConnector.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/AssemblyEventConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AssemblyEventConnector) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_AssemblyEventConnector_type") :
                    this.getString("_UI_AssemblyEventConnector_type") + " " + label;
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

        switch (notification.getFeatureID(AssemblyEventConnector.class))
        {
        case CompositionPackage.ASSEMBLY_EVENT_CONNECTOR__FILTER_CONDITION_ASSEMBLY_EVENT_CONNECTOR:
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
                        (CompositionPackage.Literals.ASSEMBLY_EVENT_CONNECTOR__FILTER_CONDITION_ASSEMBLY_EVENT_CONNECTOR,
                                CoreFactory.eINSTANCE.createPCMRandomVariable()));
    }

}
