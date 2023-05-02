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

        println("Starting Graphs Traversal")
        val graphForTraversal = Graph()
        graphForTraversal.addEdge("A", "B")
        graphForTraversal.addEdge("A", "C")
        graphForTraversal.addEdge("B", "D")
        graphForTraversal.addEdge("C", "E")
        graphForTraversal.addEdge("D", "E")
        graphForTraversal.addEdge("D", "F")
        graphForTraversal.addEdge("E", "F")
        graphForTraversal.print()
        println(graphForTraversal.dfsRecursiveTraversal("A"))
        println(graphForTraversal.dfsIterativeTraversal("A"))
        println(graphForTraversal.bfsTraversal("A"))
    }
}