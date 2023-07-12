package data_structures.trees

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TreeTraversalTest : TestCase() {
    private val treeTraversal = TreeTraversal()

    @Test
    fun breadthFirstSearch() {
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(10)
        binarySearchTree.insert(6)
        binarySearchTree.insert(15)
        binarySearchTree.insert(3)
        binarySearchTree.insert(8)
        binarySearchTree.insert(20)

        assertEquals(
                "[10, 6, 15, 3, 8, 20]",
                treeTraversal.breadthFirstSearch(binarySearchTree.root!!).toString()
        )
    }

    @Test
    fun depthFirstSearchPreOrder() {
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(10)
        binarySearchTree.insert(6)
        binarySearchTree.insert(15)
        binarySearchTree.insert(3)
        binarySearchTree.insert(8)
        binarySearchTree.insert(20)

        assertEquals(
                "[10, 6, 3, 8, 15, 20]",
                treeTraversal.depthFirstSearchPreOrder(binarySearchTree.root!!).toString()
        )
    }

    @Test
    fun depthFirstSearchPostOrder() {
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(10)
        binarySearchTree.insert(6)
        binarySearchTree.insert(15)
        binarySearchTree.insert(3)
        binarySearchTree.insert(8)
        binarySearchTree.insert(20)

        assertEquals(
                "[3, 8, 6, 20, 15, 10]",
                treeTraversal.depthFirstSearchPostOrder(binarySearchTree.root!!).toString()
        )
    }

    @Test
    fun depthFirstSearchInOrder() {
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(10)
        binarySearchTree.insert(6)
        binarySearchTree.insert(15)
        binarySearchTree.insert(3)
        binarySearchTree.insert(8)
        binarySearchTree.insert(20)

        assertEquals(
                "[3, 6, 8, 10, 15, 20]",
                treeTraversal.depthFirstSearchInOrder(binarySearchTree.root!!).toString()
        )
    }
}