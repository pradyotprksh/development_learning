package data_structures.trees

class BinarySearchTree {
    var root: Node? = null
        private set

    fun insert(data: Int) {
        val node = Node(data = data)
        if (root == null) {
            root = node
        } else {
            var temp = root
            var parent: Node? = null

            while (temp != null) {
                parent = temp
                if (temp.data < data) {
                    temp = temp.right
                } else if (temp.data > data) {
                    temp = temp.left
                }
            }

            if (parent != null) {
                if (parent.data < data) {
                    parent.right = node
                } else {
                    parent.left = node
                }
            }
        }
    }

    fun find(data: Int): Node? {
        if (root == null) {
            throw IllegalStateException("Tree is empty")
        }

        var temp = root
        while (temp != null) {
            if (temp.data == data) {
                return temp
            } else {
                temp = if (temp.data < data) {
                    temp.right
                } else {
                    temp.left
                }
            }
        }

        return null
    }

    fun getSize(): Int {
        var size = 0

        val queue = arrayListOf(root)

        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            ++size

            node?.left?.let {
                queue.add(it)
            }
            node?.right?.let {
                queue.add(it)
            }
        }

        return size
    }

    fun print() {
        println(root.toString())
    }
}