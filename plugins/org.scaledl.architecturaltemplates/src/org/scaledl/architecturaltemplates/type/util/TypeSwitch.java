/**
 */
package org.scaledl.architecturaltemplates.type.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.scaledl.architecturaltemplates.type.AT;
import org.scaledl.architecturaltemplates.type.Completion;
import org.scaledl.architecturaltemplates.type.CompletionParameter;
import org.scaledl.architecturaltemplates.type.Constraint;
import org.scaledl.architecturaltemplates.type.GenericBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.GenericOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.GenericTemplateCompletionParameter;
import org.scaledl.architecturaltemplates.type.OCLConstraint;
import org.scaledl.architecturaltemplates.type.PCMBlackboardCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMOutputCompletionParameter;
import org.scaledl.architecturaltemplates.type.PCMTemplateCompletionParameter;
import org.scaledl.architecturaltemplates.type.Parameter;
import org.scaledl.architecturaltemplates.type.QVTOCompletion;
import org.scaledl.architecturaltemplates.type.Repository;
import org.scaledl.architecturaltemplates.type.Role;
import org.scaledl.architecturaltemplates.type.TemplateProvidingEntity;
import org.scaledl.architecturaltemplates.type.TypePackage;

import de.uka.ipd.sdq.identifier.Identifier;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.core.entity.NamedElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.scaledl.architecturaltemplates.type.TypePackage
 * @generated
 */
public class TypeSwitch<T> extends Switch<T> {

    /**
     * The cached model package
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected static TypePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TypeSwitch() {
        if (modelPackage == null) {
            modelPackage = TypePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case TypePackage.AT: {
            AT at = (AT) theEObject;
            T result = caseAT(at);
            if (result == null)
                result = caseEntity(at);
            if (result == null)
                result = caseIdentifier(at);
            if (result == null)
                result = caseNamedElement(at);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.CONSTRAINT: {
            Constraint constraint = (Constraint) theEObject;
            T result = caseConstraint(constraint);
            if (result == null)
                result = caseEntity(constraint);
            if (result == null)
                result = caseIdentifier(constraint);
            if (result == null)
                result = caseNamedElement(constraint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.PARAMETER: {
            Parameter parameter = (Parameter) theEObject;
            T result = caseParameter(parameter);
            if (result == null)
                result = caseEntity(parameter);
            if (result == null)
                result = caseIdentifier(parameter);
            if (result == null)
                result = caseNamedElement(parameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.REPOSITORY: {
            Repository repository = (Repository) theEObject;
            T result = caseRepository(repository);
            if (result == null)
                result = caseEntity(repository);
            if (result == null)
                result = caseIdentifier(repository);
            if (result == null)
                result = caseNamedElement(repository);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.ROLE: {
            Role role = (Role) theEObject;
            T result = caseRole(role);
            if (result == null)
                result = caseEntity(role);
            if (result == null)
                result = caseIdentifier(role);
            if (result == null)
                result = caseNamedElement(role);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.OCL_CONSTRAINT: {
            OCLConstraint oclConstraint = (OCLConstraint) theEObject;
            T result = caseOCLConstraint(oclConstraint);
            if (result == null)
                result = caseConstraint(oclConstraint);
            if (result == null)
                result = caseEntity(oclConstraint);
            if (result == null)
                result = caseIdentifier(oclConstraint);
            if (result == null)
                result = caseNamedElement(oclConstraint);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.QVTO_COMPLETION: {
            QVTOCompletion qvtoCompletion = (QVTOCompletion) theEObject;
            T result = caseQVTOCompletion(qvtoCompletion);
            if (result == null)
                result = caseCompletion(qvtoCompletion);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.COMPLETION: {
            Completion completion = (Completion) theEObject;
            T result = caseCompletion(completion);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.COMPLETION_PARAMETER: {
            CompletionParameter completionParameter = (CompletionParameter) theEObject;
            T result = caseCompletionParameter(completionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.GENERIC_TEMPLATE_COMPLETION_PARAMETER: {
            GenericTemplateCompletionParameter genericTemplateCompletionParameter = (GenericTemplateCompletionParameter) theEObject;
            T result = caseGenericTemplateCompletionParameter(genericTemplateCompletionParameter);
            if (result == null)
                result = caseGenericBlackboardCompletionParameter(genericTemplateCompletionParameter);
            if (result == null)
                result = caseTemplateProvidingEntity(genericTemplateCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(genericTemplateCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.GENERIC_BLACKBOARD_COMPLETION_PARAMETER: {
            GenericBlackboardCompletionParameter genericBlackboardCompletionParameter = (GenericBlackboardCompletionParameter) theEObject;
            T result = caseGenericBlackboardCompletionParameter(genericBlackboardCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(genericBlackboardCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.PCM_BLACKBOARD_COMPLETION_PARAMETER: {
            PCMBlackboardCompletionParameter pcmBlackboardCompletionParameter = (PCMBlackboardCompletionParameter) theEObject;
            T result = casePCMBlackboardCompletionParameter(pcmBlackboardCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(pcmBlackboardCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.PCM_TEMPLATE_COMPLETION_PARAMETER: {
            PCMTemplateCompletionParameter pcmTemplateCompletionParameter = (PCMTemplateCompletionParameter) theEObject;
            T result = casePCMTemplateCompletionParameter(pcmTemplateCompletionParameter);
            if (result == null)
                result = casePCMBlackboardCompletionParameter(pcmTemplateCompletionParameter);
            if (result == null)
                result = caseTemplateProvidingEntity(pcmTemplateCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(pcmTemplateCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.TEMPLATE_PROVIDING_ENTITY: {
            TemplateProvidingEntity templateProvidingEntity = (TemplateProvidingEntity) theEObject;
            T result = caseTemplateProvidingEntity(templateProvidingEntity);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.PCM_OUTPUT_COMPLETION_PARAMETER: {
            PCMOutputCompletionParameter pcmOutputCompletionParameter = (PCMOutputCompletionParameter) theEObject;
            T result = casePCMOutputCompletionParameter(pcmOutputCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(pcmOutputCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case TypePackage.GENERIC_OUTPUT_COMPLETION_PARAMETER: {
            GenericOutputCompletionParameter genericOutputCompletionParameter = (GenericOutputCompletionParameter) theEObject;
            T result = caseGenericOutputCompletionParameter(genericOutputCompletionParameter);
            if (result == null)
                result = caseCompletionParameter(genericOutputCompletionParameter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>AT</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>AT</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAT(AT object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConstraint(Constraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParameter(Parameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Repository</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepository(Repository object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Role</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRole(Role object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>OCL Constraint</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>OCL Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOCLConstraint(OCLConstraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>QVTO Completion</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>QVTO Completion</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseQVTOCompletion(QVTOCompletion object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Completion</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Completion</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompletion(Completion object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCompletionParameter(CompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generic Template Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generic Template Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGenericTemplateCompletionParameter(GenericTemplateCompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generic Blackboard Completion Parameter</em>'.
     * <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generic Blackboard Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGenericBlackboardCompletionParameter(GenericBlackboardCompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>PCM Blackboard Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PCM Blackboard Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePCMBlackboardCompletionParameter(PCMBlackboardCompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>PCM Template Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PCM Template Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePCMTemplateCompletionParameter(PCMTemplateCompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template Providing Entity</em>'.
     * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template Providing Entity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplateProvidingEntity(TemplateProvidingEntity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>PCM Output Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PCM Output Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePCMOutputCompletionParameter(PCMOutputCompletionParameter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generic Output Completion Parameter</em>'.
     * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generic Output Completion Parameter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGenericOutputCompletionParameter(GenericOutputCompletionParameter object) {
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
    public T caseIdentifier(Identifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamedElement(NamedElement object) {
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
    public T caseEntity(Entity object) {
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
    public T defaultCase(EObject object) {
        return null;
    }

} // TypeSwitch
