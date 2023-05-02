package data_structures.graphs

class Graphs {
    fun startGraphs() {
        println("Starting Graphs")

        val graph = Graph()
        graph.addVertex("A")
        graph.addVertex("B")
        graph.addVertex("C")
        graph.print()
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("D", "A")
        graph.print()
        graph.removeEdge("A", "D")
        graph.removeEdge("C", "D")
        graph.print()
        graph.addEdge("D", "A")
        graph.print()
        graph.removeVertex("A")
        graph.print()
    }
}

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

    fun print() {
        println("{")
        for (key in adjacencyList.keys) {
            println("    $key = ${adjacencyList[key]}")
        }
        println("}")
    }
}