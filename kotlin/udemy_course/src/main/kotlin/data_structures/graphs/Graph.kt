package data_structures.graphs

class Graph {
    var adjacencyList = HashMap<String, ArrayList<String>>()
        private set

    fun addVertex(vertex: String) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList[vertex] = ArrayList()
        }
    }

    fun addEdge(first: String, second: String) {
        if (!adjacencyList.containsKey(first) || !adjacencyList.containsKey(second)) {
            addVertex(first)
            addVertex(second)
        }

        if (adjacencyList[first]?.contains(second) != true) {
            adjacencyList[first]?.add(second)
        }
        if (adjacencyList[second]?.contains(first) != true) {
            adjacencyList[second]?.add(first)
        }
    }

    fun removeEdge(first: String, second: String) {
        if (adjacencyList.containsKey(first) && adjacencyList.containsKey(second)) {
            adjacencyList[first]?.remove(second)
            adjacencyList[second]?.remove(first)
        } else {
            throw IllegalStateException("Vertices does not exists")
        }
    }

    fun removeVertex(vertex: String) {
        if (adjacencyList.containsKey(vertex)) {
            val edges = (adjacencyList[vertex] ?: emptyList()).toMutableList()

            for (node in edges) {
                removeEdge(vertex, node)
            }

            adjacencyList.remove(vertex)
        } else {
            throw IllegalStateException("Vertex does not exists")
        }
    }

    fun dfsRecursiveTraversal(
            start: String,
            result: ArrayList<String> = ArrayList(),
            visited: HashMap<String, Boolean> = HashMap(),
    ): List<String> {
        if (adjacencyList.isEmpty()) {
            throw IllegalStateException("Graph is empty")
        }
        if (!adjacencyList.containsKey(start)) {
            throw IllegalStateException("Start vertex is not present")
        }
        visited[start] = true
        result.add(start)
        adjacencyList[start]?.forEach { neighbour ->
            if (!visited.getOrDefault(neighbour, false)) {
                dfsRecursiveTraversal(neighbour, result, visited)
            }
        }
        return result
    }

    fun dfsIterativeTraversal(start: String): List<String> {
        if (adjacencyList.isEmpty()) {
            throw IllegalStateException("Graph is empty")
        }
        if (!adjacencyList.containsKey(start)) {
            throw IllegalStateException("Start vertex is not present")
        }

        val result = ArrayList<String>()
        val stack = ArrayList<String>()
        val visited = ArrayList<String>()

        stack.add(start)

        while (stack.isNotEmpty()) {
            val vertex = stack.removeLast()
            if (!visited.contains(vertex)) {
                visited.add(vertex)
                result.add(vertex)
                adjacencyList[vertex]?.forEach { neighbour ->
                    stack.add(neighbour)
                }
            }
        }

        return result
    }

    fun bfsTraversal(start: String): List<String> {
        if (adjacencyList.isEmpty()) {
            throw IllegalStateException("Graph is empty")
        }
        if (!adjacencyList.containsKey(start)) {
            throw IllegalStateException("Start vertex is not present")
        }

        val result = ArrayList<String>()
        val queue = ArrayList<String>()
        val visited = ArrayList<String>()

        queue.add(start)

        while (queue.isNotEmpty()) {
            val vertex = queue.removeFirst()
            if (!visited.contains(vertex)) {
                visited.add(vertex)
                result.add(vertex)
                adjacencyList[vertex]?.forEach { neighbour ->
                    queue.add(neighbour)
                }
            }
        }

        return result
    }

    fun print() {
        println("{")
        for (key in adjacencyList.keys) {
            println("    $key = ${adjacencyList[key]}")
        }
        println("}")
    }
}