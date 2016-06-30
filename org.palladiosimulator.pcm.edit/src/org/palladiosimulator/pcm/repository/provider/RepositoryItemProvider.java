/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.repository.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.core.entity.provider.EntityItemProvider;
import org.palladiosimulator.pcm.reliability.ReliabilityFactory;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.subsystem.SubsystemFactory;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.pcm.repository.Repository}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class RepositoryItemProvider extends EntityItemProvider {

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
    public RepositoryItemProvider(final AdapterFactory adapterFactory) {
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

            this.addRepositoryDescriptionPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Repository Description feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRepositoryDescriptionPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_Repository_repositoryDescription_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_Repository_repositoryDescription_feature",
                                        "_UI_Repository_type"),
                                RepositoryPackage.Literals.REPOSITORY__REPOSITORY_DESCRIPTION,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
            this.childrenFeatures.add(RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY);
            this.childrenFeatures.add(RepositoryPackage.Literals.REPOSITORY__INTERFACES_REPOSITORY);
            this.childrenFeatures.add(RepositoryPackage.Literals.REPOSITORY__FAILURE_TYPES_REPOSITORY);
            this.childrenFeatures.add(RepositoryPackage.Literals.REPOSITORY__DATA_TYPES_REPOSITORY);
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
     * This returns Repository.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/Repository"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((Repository) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_Repository_type") :
                    this.getString("_UI_Repository_type") + " " + label;
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

        switch (notification.getFeatureID(Repository.class))
        {
        case RepositoryPackage.REPOSITORY__REPOSITORY_DESCRIPTION:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case RepositoryPackage.REPOSITORY__COMPONENTS_REPOSITORY:
        case RepositoryPackage.REPOSITORY__INTERFACES_REPOSITORY:
        case RepositoryPackage.REPOSITORY__FAILURE_TYPES_REPOSITORY:
        case RepositoryPackage.REPOSITORY__DATA_TYPES_REPOSITORY:
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
                        (RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createBasicComponent()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createCompleteComponentType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createProvidesComponentType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createCompositeComponent()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__COMPONENTS_REPOSITORY,
                                SubsystemFactory.eINSTANCE.createSubSystem()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__INTERFACES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createEventGroup()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__INTERFACES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createInfrastructureInterface()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__INTERFACES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createOperationInterface()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__FAILURE_TYPES_REPOSITORY,
                                ReliabilityFactory.eINSTANCE.createHardwareInducedFailureType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__FAILURE_TYPES_REPOSITORY,
                                ReliabilityFactory.eINSTANCE.createSoftwareInducedFailureType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__FAILURE_TYPES_REPOSITORY,
                                ReliabilityFactory.eINSTANCE.createNetworkInducedFailureType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__FAILURE_TYPES_REPOSITORY,
                                ReliabilityFactory.eINSTANCE.createResourceTimeoutFailureType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__DATA_TYPES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createPrimitiveDataType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__DATA_TYPES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createCollectionDataType()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.REPOSITORY__DATA_TYPES_REPOSITORY,
                                RepositoryFactory.eINSTANCE.createCompositeDataType()));
    }

}
