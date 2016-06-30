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
import org.palladiosimulator.pcm.reliability.ReliabilityPackage;
import org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.reliability.SoftwareInducedFailureType} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class SoftwareInducedFailureTypeItemProvider extends FailureTypeItemProvider {

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
    public SoftwareInducedFailureTypeItemProvider(final AdapterFactory adapterFactory) {
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

            this.addInternalFailureOccurrenceDescriptions__SoftwareInducedFailureTypePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Internal Failure Occurrence Descriptions Software
     * Induced Failure Type feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addInternalFailureOccurrenceDescriptions__SoftwareInducedFailureTypePropertyDescriptor(
            final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SoftwareInducedFailureType_internalFailureOccurrenceDescriptions__SoftwareInducedFailureType_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_SoftwareInducedFailureType_internalFailureOccurrenceDescriptions__SoftwareInducedFailureType_feature",
                                        "_UI_SoftwareInducedFailureType_type"),
                                ReliabilityPackage.Literals.SOFTWARE_INDUCED_FAILURE_TYPE__INTERNAL_FAILURE_OCCURRENCE_DESCRIPTIONS_SOFTWARE_INDUCED_FAILURE_TYPE,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This returns SoftwareInducedFailureType.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/SoftwareInducedFailureType"));
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
        return ((SoftwareInducedFailureType) object).getEntityName() + " [ID: "
                + ((SoftwareInducedFailureType) object).getId() + "]" + " <"
                + this.getString("_UI_SoftwareInducedFailureType_type") + ">";
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
