package data_structures.trees

data class Node(var left: Node? = null, val data: Int, var right: Node? = null) {
    override fun toString(): String {
        return "$data -> {$left $right}"
    }
}

class Trees {
    fun startTrees() {
        println("Starting Trees")
        println("Starting Binary Search Tree")
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(3)
        binarySearchTree.print()
        binarySearchTree.insert(2)
        binarySearchTree.print()
        binarySearchTree.insert(5)
        binarySearchTree.print()
        binarySearchTree.insert(1)
        binarySearchTree.print()
        binarySearchTree.insert(4)
        binarySearchTree.print()
        println(binarySearchTree.find(5))

        println("Starting Tree Traversal")
        binarySearchTree.root?.let { root ->
            val treeTraversal = TreeTraversal()

            println(treeTraversal.breadthFirstSearch(root))

            println(treeTraversal.depthFirstSearchPreOrder(root))
        }
    }
}
