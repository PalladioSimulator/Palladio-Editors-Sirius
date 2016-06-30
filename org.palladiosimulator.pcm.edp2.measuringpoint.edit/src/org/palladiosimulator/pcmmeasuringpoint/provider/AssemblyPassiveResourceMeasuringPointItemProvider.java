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
import org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint} object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AssemblyPassiveResourceMeasuringPointItemProvider extends MeasuringPointItemProvider {

    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public AssemblyPassiveResourceMeasuringPointItemProvider(final AdapterFactory adapterFactory) {
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

            this.addAssemblyPropertyDescriptor(object);
            this.addPassiveResourcePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Assembly feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addAssemblyPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_AssemblyReference_assembly_feature"), this.getString(
                        "_UI_PropertyDescriptor_description", "_UI_AssemblyReference_assembly_feature",
                        "_UI_AssemblyReference_type"), PcmmeasuringpointPackage.Literals.ASSEMBLY_REFERENCE__ASSEMBLY,
                        true, false, true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Passive Resource feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPassiveResourcePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_PassiveResourceReference_passiveResource_feature"), this.getString(
                        "_UI_PropertyDescriptor_description", "_UI_PassiveResourceReference_passiveResource_feature",
                        "_UI_PassiveResourceReference_type"),
                        PcmmeasuringpointPackage.Literals.PASSIVE_RESOURCE_REFERENCE__PASSIVE_RESOURCE, true, false, true,
                        null, null, null));
    }

    /**
     * This returns AssemblyPassiveResourceMeasuringPoint.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object,
                this.getResourceLocator().getImage("full/obj16/AssemblyPassiveResourceMeasuringPoint"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AssemblyPassiveResourceMeasuringPoint) object).getStringRepresentation();
        return label == null || label.length() == 0 ? this.getString("_UI_AssemblyPassiveResourceMeasuringPoint_type")
                : this.getString("_UI_AssemblyPassiveResourceMeasuringPoint_type") + " " + label;
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
