package org.palladiosimulator.editors.sirius.seff.custom.tests

import java.util.function.Consumer
import java.util.function.Function
import org.apache.commons.lang3.mutable.MutableObject
import org.junit.jupiter.api.Test
import org.palladiosimulator.editors.sirius.seff.custom.externaljavaactions.AddExternalCallAction
import org.palladiosimulator.pcm.repository.BasicComponent
import org.palladiosimulator.pcm.repository.OperationInterface
import org.palladiosimulator.pcm.repository.OperationRequiredRole
import org.palladiosimulator.pcm.repository.Repository
import org.palladiosimulator.pcm.repository.RepositoryFactory
import org.palladiosimulator.pcm.seff.ExternalCallAction
import org.palladiosimulator.pcm.seff.SeffFactory

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertNull
import static org.junit.Assert.assertTrue

class AddExternalCallActionTest {

    static val extension RepositoryFactory REPO_FACTORY = RepositoryFactory.eINSTANCE
    static val extension SeffFactory SEFF_FACTORY = SeffFactory.eINSTANCE

    @Test
    def void testCanExecuteAlwaysReturnsTrue() {
        assertTrue(new AddExternalCallAction().canExecute(null))
    }

    @Test
    def void testInvalidExternalCallAction() {
        runTest(
            [repo|],
            [repo|null],
            [repo|null],
            [repo|null],
            [repo|#[]]
        )
    }

    @Test
    def void testWithSimpleInterface() {
        val ecaWrapper = new MutableObject<ExternalCallAction>
        val interfaceWrapper = new MutableObject<OperationInterface>
        val roleWrapper = new MutableObject<OperationRequiredRole>
        runTest(
            [repo|
                ecaWrapper.value = repo.eAllContents.filter(ExternalCallAction).findFirst[true]
                interfaceWrapper.value = repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == "Parent Parent Parent"]
                roleWrapper.value = (repo.components__Repository.get(0) as BasicComponent).requiredRoles_InterfaceRequiringEntity.get(0) as OperationRequiredRole
                roleWrapper.value.requiredInterface__OperationRequiredRole = interfaceWrapper.value
            ],
            [repo|ecaWrapper.value],
            [repo|interfaceWrapper.value],
            [repo|roleWrapper.value],
            [repo|#[interfaceWrapper.value]]
        )
    }

    @Test
    def void testWithComplexInheritanceHierarchy() {
        val ecaWrapper = new MutableObject<ExternalCallAction>
        val interfaceWrapper = new MutableObject<OperationInterface>
        val roleWrapper = new MutableObject<OperationRequiredRole>
        runTest(
            [repo|
                ecaWrapper.value = repo.eAllContents.filter(ExternalCallAction).findFirst[true]
                interfaceWrapper.value = repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == "Current"]
                roleWrapper.value = (repo.components__Repository.get(0) as BasicComponent).requiredRoles_InterfaceRequiringEntity.get(0) as OperationRequiredRole
            ],
            [repo|ecaWrapper.value],
            [repo|interfaceWrapper.value],
            [repo|roleWrapper.value],
            [repo|repo.interfaces__Repository.filter(OperationInterface)]
        )
    }

    @Test
    def void testWithNoSelectedSignature() {
        val ecaWrapper = new MutableObject<ExternalCallAction>
        runTest(
            [repo|
                ecaWrapper.value = repo.eAllContents.filter(ExternalCallAction).findFirst[true]
            ],
            [repo|ecaWrapper.value],
            [repo|null],
            [repo|null],
            [repo|repo.interfaces__Repository.filter(OperationInterface)]
        )
        assertNull(ecaWrapper.value.calledService_ExternalService)
        assertNull(ecaWrapper.value.role_ExternalService)
    }

    @Test
    def void testWithNoAvailableRequiredRole() {
        val ecaWrapper = new MutableObject<ExternalCallAction>
        val interfaceWrapper = new MutableObject<OperationInterface>
        runTest(
            [repo|
                ecaWrapper.value = repo.eAllContents.filter(ExternalCallAction).findFirst[true]
                interfaceWrapper.value = repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == "Current"]
                val requiredRole = (repo.components__Repository.get(0) as BasicComponent).requiredRoles_InterfaceRequiringEntity.get(0) as OperationRequiredRole
                requiredRole.requiredInterface__OperationRequiredRole = repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == "Parent Parent Parent"]
            ],
            [repo|ecaWrapper.value],
            [repo|interfaceWrapper.value],
            [repo|null],
            [repo|repo.interfaces__Repository.filter(OperationInterface).filter[entityName == "Parent Parent Parent"]]
        )
    }

    @Test
    def void testWithMultiplePotentialRoles() {
        // TODO test might have to be changed as part of EDITORS-215
        val ecaWrapper = new MutableObject<ExternalCallAction>
        val interfaceWrapper = new MutableObject<OperationInterface>
        val roleWrapper = new MutableObject<OperationRequiredRole> // we always expect the first role to be selected
        runTest(
            [repo|
                ecaWrapper.value = repo.eAllContents.filter(ExternalCallAction).findFirst[true]
                interfaceWrapper.value = repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == "Parent Parent Parent"]
                roleWrapper.value = (repo.components__Repository.get(0) as BasicComponent).requiredRoles_InterfaceRequiringEntity.get(0) as OperationRequiredRole
                roleWrapper.value.requiredInterface__OperationRequiredRole = interfaceWrapper.value
                val component = roleWrapper.value.requiringEntity_RequiredRole
                component.requiredRoles_InterfaceRequiringEntity += createOperationRequiredRole => [
                    requiredInterface__OperationRequiredRole = interfaceWrapper.value
                ]            
            ],
            [repo|ecaWrapper.value],
            [repo|interfaceWrapper.value],
            [repo|roleWrapper.value],
            [repo|#[interfaceWrapper.value]]
        )
    }

    protected def runTest(Consumer<Repository> repoModifier, Function<Repository, ExternalCallAction> ecaProvider,
        Function<Repository, OperationInterface> expectedInterfaceProvider,
        Function<Repository, OperationRequiredRole> expectedRoleProvider,
        Function<Repository, Iterable<OperationInterface>> expectedInterfacesProvider) {
            
        val repo = createDefaultRepository
        repoModifier.accept(repo)
        val externalCallAction = ecaProvider.apply(repo)
        val expectedInterface = expectedInterfaceProvider.apply(repo)
        val expectedInterfaces = expectedInterfacesProvider.apply(repo).toSet
        val expectedRole = expectedRoleProvider.apply(repo)
        val expectedSignature = expectedInterface?.signatures__OperationInterface?.iterator?.next

        val subject = new AddExternalCallAction([eca, interfaces |
            assertEquals(externalCallAction, eca)
            assertEquals(expectedInterfaces, interfaces)
            expectedSignature
        ])
        subject.execute(#[], #{"instance" -> externalCallAction})
        if (externalCallAction !== null) {
            assertEquals(expectedSignature, externalCallAction.calledService_ExternalService)
            assertEquals(expectedRole, externalCallAction.role_ExternalService)     
        }
    }

    protected def Repository createDefaultRepository() {
        createRepository => [
            
            val parentParentParentInterface = createOperationInterface => [
                entityName = "Parent Parent Parent"
                signatures__OperationInterface += createOperationSignature => [
                    entityName = "parentParentParent"
                ]
            ]
            val parentParentInterface = createOperationInterface => [
                entityName = "Parent Parent"
                signatures__OperationInterface += createOperationSignature => [
                    entityName = "parentParent"
                ]
                parentInterfaces__Interface += parentParentParentInterface
            ]
            val parentInterfaceLeft = createOperationInterface => [
                entityName = "Parent Left"
                signatures__OperationInterface += createOperationSignature => [
                    entityName = "parentLeft"
                ]
                parentInterfaces__Interface += parentParentInterface
                parentInterfaces__Interface += parentParentParentInterface
            ]
            val parentInterfaceRight = createOperationInterface => [
                entityName = "Parent Right"
                signatures__OperationInterface += createOperationSignature => [
                    entityName = "parentRight"
                ]
                parentInterfaces__Interface += parentParentInterface
            ]
            val currentInterface = createOperationInterface => [
                entityName = "Current"
                signatures__OperationInterface += createOperationSignature => [
                    entityName = "current"
                ]
                parentInterfaces__Interface += parentInterfaceLeft
                parentInterfaces__Interface += parentInterfaceRight
            ]
            interfaces__Repository += parentParentParentInterface
            interfaces__Repository += parentParentInterface
            interfaces__Repository += parentInterfaceLeft
            interfaces__Repository += parentInterfaceRight
            interfaces__Repository += currentInterface
            
            components__Repository += createBasicComponent => [
                entityName = "Component"
                requiredRoles_InterfaceRequiringEntity += createOperationRequiredRole => [
                    requiredInterface__OperationRequiredRole = currentInterface
                ]
                serviceEffectSpecifications__BasicComponent += createResourceDemandingSEFF => [
                    describedService__SEFF = currentInterface.signatures__OperationInterface.iterator.next
                    val startAction = createStartAction
                    val ecAction = createExternalCallAction
                    ecAction.predecessor_AbstractAction = startAction
                    val stopAction = createStopAction
                    stopAction.predecessor_AbstractAction = ecAction
                    steps_Behaviour += startAction
                    steps_Behaviour += ecAction
                    steps_Behaviour += stopAction
                ]
            ]
            
        ]
    }

}
