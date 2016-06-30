/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.resourceenvironment.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.core.CoreFactory;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;

import de.uka.ipd.sdq.identifier.provider.IdentifierItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommunicationLinkResourceSpecificationItemProvider extends IdentifierItemProvider {

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
    public CommunicationLinkResourceSpecificationItemProvider(final AdapterFactory adapterFactory) {
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

            this.addFailureProbabilityPropertyDescriptor(object);
            this.addCommunicationLinkResourceType_CommunicationLinkResourceSpecificationPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Failure Probability feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFailureProbabilityPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_CommunicationLinkResourceSpecification_failureProbability_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_CommunicationLinkResourceSpecification_failureProbability_feature",
                                        "_UI_CommunicationLinkResourceSpecification_type"),
                                ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__FAILURE_PROBABILITY,
                                true,
                                false,
                                false,
                                ItemPropertyDescriptor.REAL_VALUE_IMAGE,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Communication Link Resource Type Communication Link
     * Resource Specification feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCommunicationLinkResourceType_CommunicationLinkResourceSpecificationPropertyDescriptor(
            final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_CommunicationLinkResourceSpecification_communicationLinkResourceType_CommunicationLinkResourceSpecification_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_CommunicationLinkResourceSpecification_communicationLinkResourceType_CommunicationLinkResourceSpecification_feature",
                                        "_UI_CommunicationLinkResourceSpecification_type"),
                                ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__COMMUNICATION_LINK_RESOURCE_TYPE_COMMUNICATION_LINK_RESOURCE_SPECIFICATION,
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
            .add(ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__LATENCY_COMMUNICATION_LINK_RESOURCE_SPECIFICATION);
            this.childrenFeatures
            .add(ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__THROUGHPUT_COMMUNICATION_LINK_RESOURCE_SPECIFICATION);
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
     * This returns CommunicationLinkResourceSpecification.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/CommunicationLinkResourceSpecification"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> FB: Adapted method
     * to produce a more informative label for display in non-PCM editors. In PCM editors, display
     * is not controlled by this method but through a more general mechanism (I'n not sure where
     * it's actually controlled, maybe the
     * org.palladiosimulator.editors.tabs.PropertyLabelProvider.getText() method?) <!-- end-user-doc
     * -->
     *
     * @generated not
     */
    @Override
    public String getText(final Object object) {
        String linkingResourceName = "UNSPECIFIED";
        try {
            linkingResourceName = ((CommunicationLinkResourceSpecification) object)
                    .getLinkingResource_CommunicationLinkResourceSpecification().getEntityName();
        } catch (final Exception e) {
        }
        return "ResourceSpecification" + " (" + linkingResourceName + ")" + " [ID: "
        + ((CommunicationLinkResourceSpecification) object).getId() + "]" + " <"
        + this.getString("_UI_CommunicationLinkResourceSpecification_type") + ">";
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

        switch (notification.getFeatureID(CommunicationLinkResourceSpecification.class))
        {
        case ResourceenvironmentPackage.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__FAILURE_PROBABILITY:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case ResourceenvironmentPackage.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__LATENCY_COMMUNICATION_LINK_RESOURCE_SPECIFICATION:
        case ResourceenvironmentPackage.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__THROUGHPUT_COMMUNICATION_LINK_RESOURCE_SPECIFICATION:
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
                        (ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__LATENCY_COMMUNICATION_LINK_RESOURCE_SPECIFICATION,
                                CoreFactory.eINSTANCE.createPCMRandomVariable()));

        newChildDescriptors
        .add
        (this.createChildParameter
                        (ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__THROUGHPUT_COMMUNICATION_LINK_RESOURCE_SPECIFICATION,
                                CoreFactory.eINSTANCE.createPCMRandomVariable()));
    }

    /**
     * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCreateChildText(final Object owner, final Object feature, final Object child,
            final Collection<?> selection) {
        final Object childFeature = feature;
        final Object childObject = child;

        final boolean qualify =
                childFeature == ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__LATENCY_COMMUNICATION_LINK_RESOURCE_SPECIFICATION
                ||
                childFeature == ResourceenvironmentPackage.Literals.COMMUNICATION_LINK_RESOURCE_SPECIFICATION__THROUGHPUT_COMMUNICATION_LINK_RESOURCE_SPECIFICATION;

        if (qualify)
        {
            return this.getString(
                    "_UI_CreateChild_text2",
                    new Object[] { this.getTypeText(childObject), this.getFeatureText(childFeature),
                            this.getTypeText(owner) });
        }
        return super.getCreateChildText(owner, feature, child, selection);
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
