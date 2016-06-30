/**
 */
package org.palladiosimulator.pcmmeasuringpoint.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.palladiosimulator.edp2.models.measuringpoint.provider.MeasuringPointItemProvider;
import org.palladiosimulator.pcmmeasuringpoint.EntryLevelSystemCallMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcmmeasuringpoint.EntryLevelSystemCallMeasuringPoint} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class EntryLevelSystemCallMeasuringPointItemProvider extends MeasuringPointItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public EntryLevelSystemCallMeasuringPointItemProvider(final AdapterFactory adapterFactory) {
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
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            this.addEntryLevelSystemCallPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Entry Level System Call feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addEntryLevelSystemCallPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_EntryLevelSystemCallReference_entryLevelSystemCall_feature"), this.getString(
                        "_UI_PropertyDescriptor_description",
                        "_UI_EntryLevelSystemCallReference_entryLevelSystemCall_feature",
                        "_UI_EntryLevelSystemCallReference_type"),
                        PcmmeasuringpointPackage.Literals.ENTRY_LEVEL_SYSTEM_CALL_REFERENCE__ENTRY_LEVEL_SYSTEM_CALL, true,
                        false, true, null, null, null));
    }

    /**
     * This returns EntryLevelSystemCallMeasuringPoint.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/EntryLevelSystemCallMeasuringPoint"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((EntryLevelSystemCallMeasuringPoint) object).getStringRepresentation();
        return label == null || label.length() == 0 ? this.getString("_UI_EntryLevelSystemCallMeasuringPoint_type")
                : this.getString("_UI_EntryLevelSystemCallMeasuringPoint_type") + " " + label;
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

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return PcmmeasuringpointEditPlugin.INSTANCE;
    }

}
