package data_structures.trees

class TreeTraversal {
    fun breadthFirstSearch(root: Node): List<Int> {
        val queue = arrayListOf(root)
        val visited = arrayListOf<Int>()

        while (queue.isNotEmpty()) {
            val top = queue.removeAt(0)
            if (top.left != null) {
                queue.add(top.left!!)
            }
            if (top.right != null) {
                queue.add(top.right!!)
            }
            visited.add(top.data)
        }

        return visited
    }

    fun depthFirstSearchPreOrder(root: Node, order: ArrayList<Int> = ArrayList()): List<Int> {
        order.add(root.data)
        root.left?.let { left ->
            depthFirstSearchPreOrder(left, order)
        }
        root.right?.let { right ->
            depthFirstSearchPreOrder(right, order)
        }
        return order
    }
}