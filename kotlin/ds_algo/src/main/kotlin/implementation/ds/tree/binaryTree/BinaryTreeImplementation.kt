package implementation.ds.tree.binaryTree

import implementation.ds.stack.StackLinkedListImplementation
import implementation.ds.tree.Tree
import implementation.ds.tree.TreeNode
import kotlin.random.Random

class BinaryTreeImplementation<T>(
    data: T,
) {
    var root: TreeNode<T>? = TreeNode(left = null, data = data, right = null)
        private set

    fun appendRandomly(data: T) {
        val newNode = TreeNode(left = null, data = data, right = null)
        if (root == null) {
            root = newNode
        } else {
            var temp = root
            while (temp != null) {
                if (temp.left == null) {
                    temp.left = newNode
                    break
                } else if (temp.right == null) {
                    temp.right = newNode
                    break
                } else {
                    temp = if (Random.nextBoolean()) {
                        temp.left
                    } else {
                        temp.right
                    }
                }
            }
        }
    }

    fun preOrderTraversalRecursion(
        root: TreeNode<T>? = this.root,
        result: ArrayList<T> = ArrayList(),
    ): ArrayList<T> {
        if (root == null) {
            return result
        }
        result.add(root.data)
        preOrderTraversalRecursion(root.left, result)
        return preOrderTraversalRecursion(root.right, result)
    }

    fun preOrderTraversalStack(): List<T> {
        if (root == null) {
            println("Tree is empty")
            return emptyList()
        }

        val stack = StackLinkedListImplementation(data = root)
        stack.pop()
        val preorderTraversal = ArrayList<T>()
        var current = root

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                preorderTraversal.add(current.data)
                stack.push(current)
                current = current.left
            }
            if (!stack.isEmpty()) {
                current = stack.pop()?.data?.right
            }
        }

        return preorderTraversal.toList()
    }

    fun printBinaryTree() {
        println(root?.toString())
    }
}