/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.system.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.entity.EntityFactory;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.core.entity.provider.EntityItemProvider;
import org.palladiosimulator.pcm.qosannotations.QosannotationsFactory;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.system.SystemPackage;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.pcm.system.System} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class SystemItemProvider extends EntityItemProvider {

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
    public SystemItemProvider(final AdapterFactory adapterFactory) {
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

        }
        return this.itemPropertyDescriptors;
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
                    .add(CompositionPackage.Literals.COMPOSED_STRUCTURE__ASSEMBLY_CONTEXTS_COMPOSED_STRUCTURE);
            this.childrenFeatures
            .add(CompositionPackage.Literals.COMPOSED_STRUCTURE__RESOURCE_REQUIRED_DELEGATION_CONNECTORS_COMPOSED_STRUCTURE);
            this.childrenFeatures.add(CompositionPackage.Literals.COMPOSED_STRUCTURE__EVENT_CHANNEL_COMPOSED_STRUCTURE);
            this.childrenFeatures.add(CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE);
            this.childrenFeatures
            .add(EntityPackage.Literals.INTERFACE_PROVIDING_ENTITY__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY);
            this.childrenFeatures
            .add(EntityPackage.Literals.RESOURCE_INTERFACE_REQUIRING_ENTITY__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY);
            this.childrenFeatures
            .add(EntityPackage.Literals.INTERFACE_REQUIRING_ENTITY__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY);
            this.childrenFeatures.add(SystemPackage.Literals.SYSTEM__QOS_ANNOTATIONS_SYSTEM);
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
     * This returns System.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/System"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((org.palladiosimulator.pcm.system.System) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_System_type") :
                    this.getString("_UI_System_type") + " " + label;
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

        switch (notification.getFeatureID(org.palladiosimulator.pcm.system.System.class))
        {
        case SystemPackage.SYSTEM__ASSEMBLY_CONTEXTS_COMPOSED_STRUCTURE:
        case SystemPackage.SYSTEM__RESOURCE_REQUIRED_DELEGATION_CONNECTORS_COMPOSED_STRUCTURE:
        case SystemPackage.SYSTEM__EVENT_CHANNEL_COMPOSED_STRUCTURE:
        case SystemPackage.SYSTEM__CONNECTORS_COMPOSED_STRUCTURE:
        case SystemPackage.SYSTEM__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY:
        case SystemPackage.SYSTEM__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY:
        case SystemPackage.SYSTEM__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY:
        case SystemPackage.SYSTEM__QOS_ANNOTATIONS_SYSTEM:
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

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__ASSEMBLY_CONTEXTS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createAssemblyContext()));

        newChildDescriptors
        .add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__RESOURCE_REQUIRED_DELEGATION_CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createResourceRequiredDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__EVENT_CHANNEL_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createEventChannel()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createEventChannelSourceConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createEventChannelSinkConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createProvidedDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createRequiredDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createAssemblyConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createAssemblyEventConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createSourceDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createSinkDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createAssemblyInfrastructureConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createProvidedInfrastructureDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createRequiredInfrastructureDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (CompositionPackage.Literals.COMPOSED_STRUCTURE__CONNECTORS_COMPOSED_STRUCTURE,
                                CompositionFactory.eINSTANCE.createRequiredResourceDelegationConnector()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_PROVIDING_ENTITY__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY,
                                RepositoryFactory.eINSTANCE.createSinkRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_PROVIDING_ENTITY__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY,
                                RepositoryFactory.eINSTANCE.createOperationProvidedRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_PROVIDING_ENTITY__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY,
                                RepositoryFactory.eINSTANCE.createInfrastructureProvidedRole()));

        newChildDescriptors
        .add
        (this.createChildParameter
                        (EntityPackage.Literals.RESOURCE_INTERFACE_REQUIRING_ENTITY__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY,
                                EntityFactory.eINSTANCE.createResourceRequiredRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_REQUIRING_ENTITY__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY,
                                RepositoryFactory.eINSTANCE.createInfrastructureRequiredRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_REQUIRING_ENTITY__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY,
                                RepositoryFactory.eINSTANCE.createOperationRequiredRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (EntityPackage.Literals.INTERFACE_REQUIRING_ENTITY__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY,
                                RepositoryFactory.eINSTANCE.createSourceRole()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SystemPackage.Literals.SYSTEM__QOS_ANNOTATIONS_SYSTEM,
                                QosannotationsFactory.eINSTANCE.createQoSAnnotations()));
    }

}
