/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.seff.seff_reliability.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.seff.SeffFactory;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;
import org.palladiosimulator.pcm.seff.seff_reliability.SeffReliabilityFactory;
import org.palladiosimulator.pcm.seff.seff_reliability.SeffReliabilityPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class RecoveryActionBehaviourItemProvider extends FailureHandlingEntityItemProvider {

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
    public RecoveryActionBehaviourItemProvider(final AdapterFactory adapterFactory) {
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

            this.addFailureHandlingAlternatives__RecoveryActionBehaviourPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Failure Handling Alternatives Recovery Action
     * Behaviour feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addFailureHandlingAlternatives__RecoveryActionBehaviourPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_RecoveryActionBehaviour_failureHandlingAlternatives__RecoveryActionBehaviour_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_RecoveryActionBehaviour_failureHandlingAlternatives__RecoveryActionBehaviour_feature",
                                        "_UI_RecoveryActionBehaviour_type"),
                                SeffReliabilityPackage.Literals.RECOVERY_ACTION_BEHAVIOUR__FAILURE_HANDLING_ALTERNATIVES_RECOVERY_ACTION_BEHAVIOUR,
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
            this.childrenFeatures.add(SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR);
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
     * This returns RecoveryActionBehaviour.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/RecoveryActionBehaviour"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((RecoveryActionBehaviour) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_RecoveryActionBehaviour_type") :
                    this.getString("_UI_RecoveryActionBehaviour_type") + " " + label;
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

        switch (notification.getFeatureID(RecoveryActionBehaviour.class))
        {
        case SeffReliabilityPackage.RECOVERY_ACTION_BEHAVIOUR__STEPS_BEHAVIOUR:
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
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffReliabilityFactory.eINSTANCE.createRecoveryAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createStopAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createBranchAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createStartAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createReleaseAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createLoopAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createForkAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createExternalCallAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createAcquireAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createCollectionIteratorAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createSetVariableAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createInternalCallAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createEmitEventAction()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR__STEPS_BEHAVIOUR,
                                SeffFactory.eINSTANCE.createInternalAction()));
    }

}
