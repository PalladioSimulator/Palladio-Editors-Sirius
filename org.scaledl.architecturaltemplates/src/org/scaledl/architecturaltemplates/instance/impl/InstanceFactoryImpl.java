/**
 */
package org.scaledl.architecturaltemplates.instance.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.EnumParameter;
import org.scaledl.architecturaltemplates.instance.FloatParameter;
import org.scaledl.architecturaltemplates.instance.InstanceFactory;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.IntegerParameter;
import org.scaledl.architecturaltemplates.instance.Role2Component;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.instance.StringParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class InstanceFactoryImpl extends EFactoryImpl implements InstanceFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static InstanceFactory init() {
        try {
            final InstanceFactory theInstanceFactory = (InstanceFactory) EPackage.Registry.INSTANCE
                    .getEFactory(InstancePackage.eNS_URI);
            if (theInstanceFactory != null) {
                return theInstanceFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new InstanceFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InstanceFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case InstancePackage.AT_INSTANCE:
            return this.createATInstance();
        case InstancePackage.COMPONENT2_ROLE:
            return this.createComponent2Role();
        case InstancePackage.ROLE2_COMPONENT:
            return this.createRole2Component();
        case InstancePackage.ENUM_PARAMETER:
            return this.createEnumParameter();
        case InstancePackage.INTEGER_PARAMETER:
            return this.createIntegerParameter();
        case InstancePackage.FLOAT_PARAMETER:
            return this.createFloatParameter();
        case InstancePackage.STRING_PARAMETER:
            return this.createStringParameter();
        case InstancePackage.ROLE2_RESOURCE:
            return this.createRole2Resource();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ATInstance createATInstance() {
        final ATInstanceImpl atInstance = new ATInstanceImpl();
        return atInstance;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Component2Role createComponent2Role() {
        final Component2RoleImpl component2Role = new Component2RoleImpl();
        return component2Role;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Role2Component createRole2Component() {
        final Role2ComponentImpl role2Component = new Role2ComponentImpl();
        return role2Component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EnumParameter createEnumParameter() {
        final EnumParameterImpl enumParameter = new EnumParameterImpl();
        return enumParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public IntegerParameter createIntegerParameter() {
        final IntegerParameterImpl integerParameter = new IntegerParameterImpl();
        return integerParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FloatParameter createFloatParameter() {
        final FloatParameterImpl floatParameter = new FloatParameterImpl();
        return floatParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public <T> StringParameter<T> createStringParameter() {
        final StringParameterImpl<T> stringParameter = new StringParameterImpl<T>();
        return stringParameter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Role2Resource createRole2Resource() {
        final Role2ResourceImpl role2Resource = new Role2ResourceImpl();
        return role2Resource;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InstancePackage getInstancePackage() {
        return (InstancePackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static InstancePackage getPackage() {
        return InstancePackage.eINSTANCE;
    }

} // InstanceFactoryImpl
