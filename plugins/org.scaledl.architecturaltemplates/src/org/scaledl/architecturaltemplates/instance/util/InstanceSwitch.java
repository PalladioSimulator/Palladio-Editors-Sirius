/**
 */
package org.scaledl.architecturaltemplates.instance.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.scaledl.architecturaltemplates.instance.ATInstance;
import org.scaledl.architecturaltemplates.instance.Component2Role;
import org.scaledl.architecturaltemplates.instance.EnumParameter;
import org.scaledl.architecturaltemplates.instance.FloatParameter;
import org.scaledl.architecturaltemplates.instance.InstancePackage;
import org.scaledl.architecturaltemplates.instance.IntegerParameter;
import org.scaledl.architecturaltemplates.instance.ParameterValue;
import org.scaledl.architecturaltemplates.instance.Role2Component;
import org.scaledl.architecturaltemplates.instance.Role2Resource;
import org.scaledl.architecturaltemplates.instance.StringParameter;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see org.scaledl.architecturaltemplates.instance.InstancePackage
 * @generated
 */
public class InstanceSwitch<T1> extends Switch<T1> {

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static InstancePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InstanceSwitch() {
        if (modelPackage == null) {
            modelPackage = InstancePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result;
     * it yields that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T1 doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case InstancePackage.AT_INSTANCE: {
            final ATInstance atInstance = (ATInstance) theEObject;
            T1 result = this.caseATInstance(atInstance);
            if (result == null) {
                result = this.caseEntity(atInstance);
            }
            if (result == null) {
                result = this.caseIdentifier(atInstance);
            }
            if (result == null) {
                result = this.caseNamedElement(atInstance);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.COMPONENT2_ROLE: {
            final Component2Role component2Role = (Component2Role) theEObject;
            T1 result = this.caseComponent2Role(component2Role);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.ROLE2_COMPONENT: {
            final Role2Component role2Component = (Role2Component) theEObject;
            T1 result = this.caseRole2Component(role2Component);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.PARAMETER_VALUE: {
            final ParameterValue parameterValue = (ParameterValue) theEObject;
            T1 result = this.caseParameterValue(parameterValue);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.ENUM_PARAMETER: {
            final EnumParameter enumParameter = (EnumParameter) theEObject;
            T1 result = this.caseEnumParameter(enumParameter);
            if (result == null) {
                result = this.caseParameterValue(enumParameter);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.INTEGER_PARAMETER: {
            final IntegerParameter integerParameter = (IntegerParameter) theEObject;
            T1 result = this.caseIntegerParameter(integerParameter);
            if (result == null) {
                result = this.caseParameterValue(integerParameter);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.FLOAT_PARAMETER: {
            final FloatParameter floatParameter = (FloatParameter) theEObject;
            T1 result = this.caseFloatParameter(floatParameter);
            if (result == null) {
                result = this.caseParameterValue(floatParameter);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.STRING_PARAMETER: {
            final StringParameter<?> stringParameter = (StringParameter<?>) theEObject;
            T1 result = this.caseStringParameter(stringParameter);
            if (result == null) {
                result = this.caseParameterValue(stringParameter);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case InstancePackage.ROLE2_RESOURCE: {
            final Role2Resource role2Resource = (Role2Resource) theEObject;
            T1 result = this.caseRole2Resource(role2Resource);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        default:
            return this.defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>AT Instance</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>AT Instance</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseATInstance(final ATInstance object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Component2 Role</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component2 Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseComponent2Role(final Component2Role object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Role2 Component</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role2 Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRole2Component(final Role2Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseParameterValue(final ParameterValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enum Parameter</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseEnumParameter(final EnumParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Parameter</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseIntegerParameter(final IntegerParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Float Parameter</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Float Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseFloatParameter(final FloatParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Parameter</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <T> T1 caseStringParameter(final StringParameter<T> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Role2 Resource</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role2 Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseRole2Resource(final Role2Resource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseIdentifier(final Identifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseNamedElement(final NamedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Entity</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T1 caseEntity(final Entity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T1 defaultCase(final EObject object) {
        return null;
    }

} // InstanceSwitch
