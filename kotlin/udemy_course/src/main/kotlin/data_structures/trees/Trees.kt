package data_structures.trees

data class TreeNode(var left: TreeNode? = null, val data: Int, var right: TreeNode? = null) {
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
            println(treeTraversal.depthFirstSearchPostOrder(root))
            println(treeTraversal.depthFirstSearchInOrder(root))
        }

        println("Starting Binary Heap Tree")
        val maxBinaryHeap = MaxBinaryHeap()
        maxBinaryHeap.insert(41)
        maxBinaryHeap.insert(39)
        maxBinaryHeap.insert(33)
        maxBinaryHeap.insert(18)
        maxBinaryHeap.insert(27)
        maxBinaryHeap.insert(12)
        maxBinaryHeap.print()

        maxBinaryHeap.remove()
        maxBinaryHeap.print()

        maxBinaryHeap.insert(55)
        maxBinaryHeap.print()

        println(maxBinaryHeap.height())

        println("Starting Priority Queue")
        val priorityQueue = PriorityQueue()
        priorityQueue.enqueue("one", 1)
        priorityQueue.enqueue("five", 5)
        priorityQueue.enqueue("two", 2)

        priorityQueue.print()

        println(priorityQueue.dequeue())
        println(priorityQueue.dequeue())
        println(priorityQueue.dequeue())
    }
}
