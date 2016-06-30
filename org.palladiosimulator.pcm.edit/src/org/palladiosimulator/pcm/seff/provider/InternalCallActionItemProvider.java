/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.seff.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.seff.InternalCallAction;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.seff_performance.SeffPerformanceFactory;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.pcm.seff.InternalCallAction}
 * object. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class InternalCallActionItemProvider extends CallActionItemProvider {

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
    public InternalCallActionItemProvider(final AdapterFactory adapterFactory) {
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

            this.addPredecessor_AbstractActionPropertyDescriptor(object);
            this.addSuccessor_AbstractActionPropertyDescriptor(object);
            this.addCalledResourceDemandingInternalBehaviourPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Predecessor Abstract Action feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPredecessor_AbstractActionPropertyDescriptor(final Object object)
    {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AbstractAction_predecessor_AbstractAction_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AbstractAction_predecessor_AbstractAction_feature",
                                        "_UI_AbstractAction_type"),
                                SeffPackage.Literals.ABSTRACT_ACTION__PREDECESSOR_ABSTRACT_ACTION,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Successor Abstract Action feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSuccessor_AbstractActionPropertyDescriptor(final Object object)
    {
        this.itemPropertyDescriptors.add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_AbstractAction_successor_AbstractAction_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_AbstractAction_successor_AbstractAction_feature",
                                        "_UI_AbstractAction_type"),
                                SeffPackage.Literals.ABSTRACT_ACTION__SUCCESSOR_ABSTRACT_ACTION,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Called Resource Demanding Internal Behaviour feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addCalledResourceDemandingInternalBehaviourPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_InternalCallAction_calledResourceDemandingInternalBehaviour_feature"),
                                this.getString("_UI_PropertyDescriptor_description",
                                        "_UI_InternalCallAction_calledResourceDemandingInternalBehaviour_feature",
                                        "_UI_InternalCallAction_type"),
                                SeffPackage.Literals.INTERNAL_CALL_ACTION__CALLED_RESOURCE_DEMANDING_INTERNAL_BEHAVIOUR,
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
                    .add(SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__RESOURCE_DEMAND_ACTION);
            this.childrenFeatures
            .add(SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__INFRASTRUCTURE_CALL_ACTION);
            this.childrenFeatures.add(SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__RESOURCE_CALL_ACTION);
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
     * This returns InternalCallAction.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/InternalCallAction"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((InternalCallAction) object).getId();
        return label == null || label.length() == 0 ?
                this.getString("_UI_InternalCallAction_type") :
                    this.getString("_UI_InternalCallAction_type") + " " + label;
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

        switch (notification.getFeatureID(InternalCallAction.class))
        {
        case SeffPackage.INTERNAL_CALL_ACTION__RESOURCE_DEMAND_ACTION:
        case SeffPackage.INTERNAL_CALL_ACTION__INFRASTRUCTURE_CALL_ACTION:
        case SeffPackage.INTERNAL_CALL_ACTION__RESOURCE_CALL_ACTION:
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
                        (SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__RESOURCE_DEMAND_ACTION,
                                SeffPerformanceFactory.eINSTANCE.createParametricResourceDemand()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__INFRASTRUCTURE_CALL_ACTION,
                                SeffPerformanceFactory.eINSTANCE.createInfrastructureCall()));

        newChildDescriptors.add
        (this.createChildParameter
                        (SeffPackage.Literals.ABSTRACT_INTERNAL_CONTROL_FLOW_ACTION__RESOURCE_CALL_ACTION,
                                SeffPerformanceFactory.eINSTANCE.createResourceCall()));
    }

}
