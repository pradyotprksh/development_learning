package implementation.ds.tree.binaryTree

object BinaryTree {
    fun implementation() {
        val bt = BinaryTreeImplementation(1)

        for (i in 2 .. 7) {
            bt.appendRandomly(i)
        }

        bt.printBinaryTree()

        println("Preorder traversal using stack:     ${bt.preOrderTraversalStack()}")
        println("Preorder traversal using recursion: ${bt.preOrderTraversalRecursion()}")
    }
}