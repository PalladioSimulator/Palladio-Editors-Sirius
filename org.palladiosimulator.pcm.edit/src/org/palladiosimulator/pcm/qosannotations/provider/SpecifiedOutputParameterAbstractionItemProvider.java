/**
 * Copyright 2005-2009 by SDQ, IPD, University of Karlsruhe, Germany
 */
package org.palladiosimulator.pcm.qosannotations.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChildCreationExtender;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.parameter.ParameterFactory;
import org.palladiosimulator.pcm.qosannotations.QosannotationsPackage;
import org.palladiosimulator.pcm.qosannotations.SpecifiedOutputParameterAbstraction;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.pcm.qosannotations.SpecifiedOutputParameterAbstraction} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class SpecifiedOutputParameterAbstractionItemProvider extends ItemProviderAdapter
implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider,
IItemLabelProvider, IItemPropertySource {

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
    public SpecifiedOutputParameterAbstractionItemProvider(final AdapterFactory adapterFactory) {
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

            this.addSignature_SpecifiedOutputParameterAbstractionPropertyDescriptor(object);
            this.addRole_SpecifiedOutputParameterAbstractionPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Signature Specified Output Parameter Abstraction
     * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addSignature_SpecifiedOutputParameterAbstractionPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SpecifiedOutputParameterAbstraction_signature_SpecifiedOutputParameterAbstraction_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_SpecifiedOutputParameterAbstraction_signature_SpecifiedOutputParameterAbstraction_feature",
                                        "_UI_SpecifiedOutputParameterAbstraction_type"),
                                QosannotationsPackage.Literals.SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION__SIGNATURE_SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION,
                                true,
                                false,
                                true,
                                null,
                                null,
                                null));
    }

    /**
     * This adds a property descriptor for the Role Specified Output Parameter Abstraction feature.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRole_SpecifiedOutputParameterAbstractionPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
        .add
        (this.createItemPropertyDescriptor
                        (((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(),
                                this.getResourceLocator(),
                                this.getString("_UI_SpecifiedOutputParameterAbstraction_role_SpecifiedOutputParameterAbstraction_feature"),
                                this.getString(
                                        "_UI_PropertyDescriptor_description",
                                        "_UI_SpecifiedOutputParameterAbstraction_role_SpecifiedOutputParameterAbstraction_feature",
                                        "_UI_SpecifiedOutputParameterAbstraction_type"),
                                QosannotationsPackage.Literals.SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION__ROLE_SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION,
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
            .add(QosannotationsPackage.Literals.SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION__EXPECTED_EXTERNAL_OUTPUTS_SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION);
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
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        return this.getString("_UI_SpecifiedOutputParameterAbstraction_type");
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

        switch (notification.getFeatureID(SpecifiedOutputParameterAbstraction.class))
        {
        case QosannotationsPackage.SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION__EXPECTED_EXTERNAL_OUTPUTS_SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION:
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
                        (QosannotationsPackage.Literals.SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION__EXPECTED_EXTERNAL_OUTPUTS_SPECIFIED_OUTPUT_PARAMETER_ABSTRACTION,
                                ParameterFactory.eINSTANCE.createVariableUsage()));
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
