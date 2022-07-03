package practice.maze

data class Node(val indices: Pair<Int, Int>, var nodes: List<Node> = emptyList()) {
    override fun toString(): String = "Node(${indices.first},${indices.second})"

    override fun equals(other: Any?): Boolean {
        if (other is Node) {
            return other.indices.first == indices.first && other.indices.second == indices.second
        }
        return false
    }

    override fun hashCode(): Int {
        return indices.hashCode()
    }
}

class MazeProblem {
    private var head: Node? = null
    private lateinit var maze: List<List<Node>>
    private val exit = Node(indices = Pair(Int.MAX_VALUE, Int.MAX_VALUE), nodes = emptyList())

    private val node33 = Node(indices = Pair(3, 3))
    private val node32 = Node(indices = Pair(3, 2))
    private val node31 = Node(indices = Pair(3, 1))
    private val node30 = Node(indices = Pair(3, 0))
    private val node23 = Node(indices = Pair(2, 3))
    private val node22 = Node(indices = Pair(2, 2))
    private val node21 = Node(indices = Pair(2, 1))
    private val node20 = Node(indices = Pair(2, 0))
    private val node13 = Node(indices = Pair(1, 3))
    private val node12 = Node(indices = Pair(1, 2))
    private val node11 = Node(indices = Pair(1, 1))
    private val node10 = Node(indices = Pair(1, 0))
    private val node03 = Node(indices = Pair(0, 3))
    private val node02 = Node(indices = Pair(0, 2))
    private val node01 = Node(indices = Pair(0, 1))
    private val node00 = Node(indices = Pair(0, 0))

    fun createTheMaze() {
        node33.nodes = emptyList()
        node32.nodes = listOf(node22)
        node31.nodes = listOf(node32)
        node30.nodes = listOf(node31)
        node23.nodes = listOf(node33, exit)
        node22.nodes = listOf(node21, node23)
        node21.nodes = listOf(node20)
        node20.nodes = listOf(node30)
        node13.nodes = emptyList()
        node12.nodes = listOf(node02, node13)
        node11.nodes = listOf(node10, node12, node21)
        node10.nodes = emptyList()
        node03.nodes = emptyList()
        node02.nodes = listOf(node03)
        node01.nodes = listOf(node11)
        node00.nodes = listOf(node01)

        maze = listOf(
            listOf(node00, node01, node02, node03),
            listOf(node10, node11, node12, node13),
            listOf(node20, node21, node22, node23),
            listOf(node30, node31, node32, node33),
        )

        head = maze.first().first()
    }

    fun findTheExitNode() {
        val nodeStack = arrayListOf(head)
        while (true) {
            if (nodeStack.isNotEmpty()) {
                val item = nodeStack.removeFirstOrNull()
                if (item != null) {
                    for (node in item.nodes) {
                        if (node == exit) {
                            println("Found the exit at $item")
                            break
                        } else {
                            nodeStack.add(node)
                        }
                    }
                }
            } else {
                println("No exit found for the current maze")
                break
            }
        }
    }

    fun showMazeDetails() {
        if (this::maze.isInitialized) {
            var rowMessage = ""
            for (row in maze) {
                for (item in row) {
                    var itemMessage = "$item"
                    var itemNodes = ""
                    for (node in item.nodes) {
                        itemNodes =
                            if (itemNodes.isNotEmpty())
                                "$itemNodes,$node"
                            else
                                "$node"
                    }
                    itemMessage = "$itemMessage[$itemNodes]"
                    rowMessage = "$rowMessage   $itemMessage"
                }
                rowMessage = "$rowMessage\n"
            }
            println(rowMessage)
        } else {
            println("Maze is not created yet")
        }
    }
}