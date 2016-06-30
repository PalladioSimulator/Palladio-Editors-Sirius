/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.repository.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.RepositoryFactory;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.seff.SeffFactory;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.repository.BasicComponent} object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class BasicComponentItemProvider extends ImplementationComponentTypeItemProvider {

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
    public BasicComponentItemProvider(final AdapterFactory adapterFactory) {
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
            .add(RepositoryPackage.Literals.BASIC_COMPONENT__SERVICE_EFFECT_SPECIFICATIONS_BASIC_COMPONENT);
            this.childrenFeatures.add(RepositoryPackage.Literals.BASIC_COMPONENT__PASSIVE_RESOURCE_BASIC_COMPONENT);
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
     * This returns BasicComponent.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/BasicComponent"));
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
        return ((BasicComponent) object).getEntityName() + " [ID: " + ((BasicComponent) object).getId() + "]" + " <"
                + this.getString("_UI_BasicComponent_type") + ">";
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

        switch (notification.getFeatureID(BasicComponent.class))
        {
        case RepositoryPackage.BASIC_COMPONENT__SERVICE_EFFECT_SPECIFICATIONS_BASIC_COMPONENT:
        case RepositoryPackage.BASIC_COMPONENT__PASSIVE_RESOURCE_BASIC_COMPONENT:
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
                        (RepositoryPackage.Literals.BASIC_COMPONENT__SERVICE_EFFECT_SPECIFICATIONS_BASIC_COMPONENT,
                                SeffFactory.eINSTANCE.createResourceDemandingSEFF()));

        newChildDescriptors.add
        (this.createChildParameter
                        (RepositoryPackage.Literals.BASIC_COMPONENT__PASSIVE_RESOURCE_BASIC_COMPONENT,
                                RepositoryFactory.eINSTANCE.createPassiveResource()));
    }

}
