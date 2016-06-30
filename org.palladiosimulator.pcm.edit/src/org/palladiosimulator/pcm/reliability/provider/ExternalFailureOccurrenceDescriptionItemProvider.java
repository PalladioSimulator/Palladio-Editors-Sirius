/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.reliability.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.palladiosimulator.pcm.reliability.ExternalFailureOccurrenceDescription;
import org.palladiosimulator.pcm.reliability.ReliabilityPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.reliability.ExternalFailureOccurrenceDescription} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ExternalFailureOccurrenceDescriptionItemProvider extends FailureOccurrenceDescriptionItemProvider {

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
    public ExternalFailureOccurrenceDescriptionItemProvider(final AdapterFactory adapterFactory) {
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

            this.addFailureType__ExternalFailureOccurrenceDescriptionPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Failure Type External Failure Occurrence Description
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFailureType__ExternalFailureOccurrenceDescriptionPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_ExternalFailureOccurrenceDescription_failureType__ExternalFailureOccurrenceDescription_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_ExternalFailureOccurrenceDescription_failureType__ExternalFailureOccurrenceDescription_feature",
                                        "_UI_ExternalFailureOccurrenceDescription_type"),
                                ReliabilityPackage.Literals.EXTERNAL_FAILURE_OCCURRENCE_DESCRIPTION__FAILURE_TYPE_EXTERNAL_FAILURE_OCCURRENCE_DESCRIPTION,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns ExternalFailureOccurrenceDescription.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/ExternalFailureOccurrenceDescription"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final ExternalFailureOccurrenceDescription externalFailureOccurrenceDescription = (ExternalFailureOccurrenceDescription) object;
        return this.getString("_UI_ExternalFailureOccurrenceDescription_type") + " "
        + externalFailureOccurrenceDescription.getFailureProbability();
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
