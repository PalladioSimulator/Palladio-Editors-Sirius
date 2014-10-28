/**
 */
package org.scaledl.architecturaltemplates.instance.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.InstanceFactory;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.type.provider.ArchitecturaltemplatesEditPlugin;

import de.uka.ipd.sdq.pcm.core.entity.provider.EntityItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.scaledl.architecturaltemplates.instance.ATInstance} object. <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class ATInstanceItemProvider extends EntityItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ATInstanceItemProvider(final AdapterFactory adapterFactory) {
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

            this.addAT2ComponentsPropertyDescriptor(object);
            this.addTypePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the AT2 Components feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addAT2ComponentsPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ATInstance_AT2Components_feature"), this.getString(
                        "_UI_PropertyDescriptor_description", "_UI_ATInstance_AT2Components_feature",
                        "_UI_ATInstance_type"), InstancePackage.Literals.AT_INSTANCE__AT2_COMPONENTS, true, false,
                        true, null, null, null));
    }

    /**
     * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    protected void addTypePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_ATInstance_type_feature"), this.getString("_UI_PropertyDescriptor_description",
                        "_UI_ATInstance_type_feature", "_UI_ATInstance_type"),
                InstancePackage.Literals.AT_INSTANCE__TYPE, true, false, true, null, null, null));
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
        if (this.childrenFeatures == null) {
            super.getChildrenFeatures(object);
            this.childrenFeatures.add(InstancePackage.Literals.AT_INSTANCE__ROLE2COMPONENTS);
            this.childrenFeatures.add(InstancePackage.Literals.AT_INSTANCE__COMPONENT2ROLES);
            this.childrenFeatures.add(InstancePackage.Literals.AT_INSTANCE__PARAMETER_VALUES);
            this.childrenFeatures.add(InstancePackage.Literals.AT_INSTANCE__ROLE2RESOURCES);
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
     * This returns ATInstance.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ATInstance"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((ATInstance) object).getId();
        return label == null || label.length() == 0 ? this.getString("_UI_ATInstance_type") : this
                .getString("_UI_ATInstance_type") + " " + label;
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

        switch (notification.getFeatureID(ATInstance.class)) {
        case InstancePackage.AT_INSTANCE__ROLE2COMPONENTS:
        case InstancePackage.AT_INSTANCE__COMPONENT2ROLES:
        case InstancePackage.AT_INSTANCE__PARAMETER_VALUES:
        case InstancePackage.AT_INSTANCE__ROLE2RESOURCES:
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

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__ROLE2COMPONENTS,
                InstanceFactory.eINSTANCE.createRole2Component()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__COMPONENT2ROLES,
                InstanceFactory.eINSTANCE.createComponent2Role()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__PARAMETER_VALUES,
                InstanceFactory.eINSTANCE.createEnumParameter()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__PARAMETER_VALUES,
                InstanceFactory.eINSTANCE.createIntegerParameter()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__PARAMETER_VALUES,
                InstanceFactory.eINSTANCE.createFloatParameter()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__PARAMETER_VALUES,
                InstanceFactory.eINSTANCE.createStringParameter()));

        newChildDescriptors.add(this.createChildParameter(InstancePackage.Literals.AT_INSTANCE__ROLE2RESOURCES,
                InstanceFactory.eINSTANCE.createRole2Resource()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ArchitecturaltemplatesEditPlugin.INSTANCE;
    }

}
