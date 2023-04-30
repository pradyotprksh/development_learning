package data_structures.trees

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BinarySearchTreeTest : TestCase() {
    private val binarySearchTree = BinarySearchTree()

    @Test
    fun initialSetup() {
        assertNull(binarySearchTree.root)
    }

    @Test
    fun insert() {
        assertNull(binarySearchTree.root)

        binarySearchTree.insert(3)

        assertEquals(3, binarySearchTree.root?.data)

        binarySearchTree.insert(2)
        binarySearchTree.insert(5)
        binarySearchTree.insert(1)
        binarySearchTree.insert(4)

        assertEquals(2, binarySearchTree.root?.left?.data)
        assertEquals(5, binarySearchTree.root?.right?.data)
        assertEquals(1, binarySearchTree.root?.left?.left?.data)
        assertEquals(4, binarySearchTree.root?.right?.left?.data)
    }

    @Test(expected = IllegalStateException::class)
    fun find() {
        binarySearchTree.find(3)

        binarySearchTree.insert(3)
        binarySearchTree.insert(2)
        binarySearchTree.insert(5)
        binarySearchTree.insert(1)
        binarySearchTree.insert(4)

        assertNotNull(binarySearchTree.find(3))
        assertEquals(4, binarySearchTree.find(5)?.left?.data)
        assertNull(binarySearchTree.find(6))
    }
}