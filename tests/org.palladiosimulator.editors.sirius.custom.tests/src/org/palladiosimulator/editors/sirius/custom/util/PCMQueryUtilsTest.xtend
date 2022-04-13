package org.palladiosimulator.editors.sirius.custom.util

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.palladiosimulator.pcm.repository.OperationInterface
import org.palladiosimulator.pcm.repository.Repository
import org.palladiosimulator.pcm.repository.RepositoryFactory

import static org.junit.Assert.assertEquals
import java.util.HashSet

class PCMQueryUtilsTest {

    val static extension RepositoryFactory REPO_FACTORY = RepositoryFactory.eINSTANCE
    var Repository repo

    @BeforeEach
    def void setup() {
        this.repo = createTestRepo()
    }

    @Test
    def void testNoInheritance() {
        val expected = #{repo.getByName("Parent Parent Parent")}
        val actual = PCMQueryUtils.getTransitiveParentInterfaceClosureIncludingSelf(repo.getByName("Parent Parent Parent"))
        assertEquals(expected, actual)
    }
    
    @Test
    def void testSingleInheritance() {
        val expected = #{repo.getByName("Parent Parent"), repo.getByName("Parent Parent Parent")}
        val actual = PCMQueryUtils.getTransitiveParentInterfaceClosureIncludingSelf(repo.getByName("Parent Parent"))
        assertEquals(expected, actual)
    }
    
    @Test
    def void testDiamondInheritance() {
        val expected = new HashSet(repo.interfaces__Repository)
        val actual = PCMQueryUtils.getTransitiveParentInterfaceClosureIncludingSelf(repo.getByName("Current"))
        assertEquals(expected, actual)
    }

    protected def getByName(Repository repo, String name) {
        repo.interfaces__Repository.filter(OperationInterface).findFirst[entityName == name]
    }

    protected static def createTestRepo() {
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

        ]
    }
}
