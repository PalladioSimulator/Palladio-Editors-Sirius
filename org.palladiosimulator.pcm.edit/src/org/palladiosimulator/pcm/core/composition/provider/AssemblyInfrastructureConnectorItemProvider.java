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
import org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AssemblyInfrastructureConnectorItemProvider extends ConnectorItemProvider {

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
    public AssemblyInfrastructureConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addProvidedRole__AssemblyInfrastructureConnectorPropertyDescriptor(object);
            this.addRequiredRole__AssemblyInfrastructureConnectorPropertyDescriptor(object);
            this.addProvidingAssemblyContext__AssemblyInfrastructureConnectorPropertyDescriptor(object);
            this.addRequiringAssemblyContext__AssemblyInfrastructureConnectorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Provided Role Assembly Infrastructure Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addProvidedRole__AssemblyInfrastructureConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyInfrastructureConnector_providedRole__AssemblyInfrastructureConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyInfrastructureConnector_providedRole__AssemblyInfrastructureConnector_feature",
                                        "_UI_AssemblyInfrastructureConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_INFRASTRUCTURE_CONNECTOR__PROVIDED_ROLE_ASSEMBLY_INFRASTRUCTURE_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Required Role Assembly Infrastructure Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRequiredRole__AssemblyInfrastructureConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyInfrastructureConnector_requiredRole__AssemblyInfrastructureConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyInfrastructureConnector_requiredRole__AssemblyInfrastructureConnector_feature",
                                        "_UI_AssemblyInfrastructureConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_INFRASTRUCTURE_CONNECTOR__REQUIRED_ROLE_ASSEMBLY_INFRASTRUCTURE_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Providing Assembly Context Assembly Infrastructure
     * Connector feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addProvidingAssemblyContext__AssemblyInfrastructureConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyInfrastructureConnector_providingAssemblyContext__AssemblyInfrastructureConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyInfrastructureConnector_providingAssemblyContext__AssemblyInfrastructureConnector_feature",
                                        "_UI_AssemblyInfrastructureConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_INFRASTRUCTURE_CONNECTOR__PROVIDING_ASSEMBLY_CONTEXT_ASSEMBLY_INFRASTRUCTURE_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Requiring Assembly Context Assembly Infrastructure
     * Connector feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRequiringAssemblyContext__AssemblyInfrastructureConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyInfrastructureConnector_requiringAssemblyContext__AssemblyInfrastructureConnector_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyInfrastructureConnector_requiringAssemblyContext__AssemblyInfrastructureConnector_feature",
                                        "_UI_AssemblyInfrastructureConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_INFRASTRUCTURE_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_INFRASTRUCTURE_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns AssemblyInfrastructureConnector.gif. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/AssemblyInfrastructureConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AssemblyInfrastructureConnector) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_AssemblyInfrastructureConnector_type") :
                    this.getString("_UI_AssemblyInfrastructureConnector_type") + " " + label;
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
