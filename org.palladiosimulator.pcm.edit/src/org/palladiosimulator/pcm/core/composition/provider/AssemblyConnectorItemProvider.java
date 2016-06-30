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
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.core.composition.AssemblyConnector} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssemblyConnectorItemProvider extends ConnectorItemProvider {

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
    public AssemblyConnectorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
            this.addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(object);
            this.addProvidedRole_AssemblyConnectorPropertyDescriptor(object);
            this.addRequiredRole_AssemblyConnectorPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Requiring Assembly Context Assembly Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRequiringAssemblyContext_AssemblyConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyConnector_requiringAssemblyContext_AssemblyConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyConnector_requiringAssemblyContext_AssemblyConnector_feature",
                                        "_UI_AssemblyConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Providing Assembly Context Assembly Connector
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addProvidingAssemblyContext_AssemblyConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyConnector_providingAssemblyContext_AssemblyConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyConnector_providingAssemblyContext_AssemblyConnector_feature",
                                        "_UI_AssemblyConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDING_ASSEMBLY_CONTEXT_ASSEMBLY_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Provided Role Assembly Connector feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addProvidedRole_AssemblyConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyConnector_providedRole_AssemblyConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyConnector_providedRole_AssemblyConnector_feature",
                                        "_UI_AssemblyConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__PROVIDED_ROLE_ASSEMBLY_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Required Role Assembly Connector feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addRequiredRole_AssemblyConnectorPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AssemblyConnector_requiredRole_AssemblyConnector_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AssemblyConnector_requiredRole_AssemblyConnector_feature",
                                        "_UI_AssemblyConnector_type"),
                                CompositionPackage.Literals.ASSEMBLY_CONNECTOR__REQUIRED_ROLE_ASSEMBLY_CONNECTOR,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns AssemblyConnector.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/AssemblyConnector"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AssemblyConnector) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_AssemblyConnector_type") :
                    this.getString("_UI_AssemblyConnector_type") + " " + label;
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
