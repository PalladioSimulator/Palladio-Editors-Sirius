/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.resourcetype.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourcetypeFactory;
import org.palladiosimulator.pcm.resourcetype.ResourcetypePackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.resourcetype.ResourceRepository} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ResourceRepositoryItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

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
    public ResourceRepositoryItemProvider(final AdapterFactory adapterFactory) {
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
            .add(ResourcetypePackage.Literals.RESOURCE_REPOSITORY__RESOURCE_INTERFACES_RESOURCE_REPOSITORY);
            this.childrenFeatures
            .add(ResourcetypePackage.Literals.RESOURCE_REPOSITORY__SCHEDULING_POLICIES_RESOURCE_REPOSITORY);
            this.childrenFeatures
            .add(ResourcetypePackage.Literals.RESOURCE_REPOSITORY__AVAILABLE_RESOURCE_TYPES_RESOURCE_REPOSITORY);
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
     * This returns ResourceRepository.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ResourceRepository"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        return this.getString("_UI_ResourceRepository_type");
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

        switch (notification.getFeatureID(ResourceRepository.class))
        {
        case ResourcetypePackage.RESOURCE_REPOSITORY__RESOURCE_INTERFACES_RESOURCE_REPOSITORY:
        case ResourcetypePackage.RESOURCE_REPOSITORY__SCHEDULING_POLICIES_RESOURCE_REPOSITORY:
        case ResourcetypePackage.RESOURCE_REPOSITORY__AVAILABLE_RESOURCE_TYPES_RESOURCE_REPOSITORY:
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
                        (ResourcetypePackage.Literals.RESOURCE_REPOSITORY__RESOURCE_INTERFACES_RESOURCE_REPOSITORY,
                                ResourcetypeFactory.eINSTANCE.createResourceInterface()));

        newChildDescriptors.add
        (this.createChildParameter
                        (ResourcetypePackage.Literals.RESOURCE_REPOSITORY__SCHEDULING_POLICIES_RESOURCE_REPOSITORY,
                                ResourcetypeFactory.eINSTANCE.createSchedulingPolicy()));

        newChildDescriptors
                .add
        (this.createChildParameter
                        (ResourcetypePackage.Literals.RESOURCE_REPOSITORY__AVAILABLE_RESOURCE_TYPES_RESOURCE_REPOSITORY,
                                ResourcetypeFactory.eINSTANCE.createProcessingResourceType()));

        newChildDescriptors
                .add
        (this.createChildParameter
                        (ResourcetypePackage.Literals.RESOURCE_REPOSITORY__AVAILABLE_RESOURCE_TYPES_RESOURCE_REPOSITORY,
                                ResourcetypeFactory.eINSTANCE.createCommunicationLinkResourceType()));
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
