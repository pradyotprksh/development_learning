package data_structures.graphs

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GraphTest : TestCase() {
    private val graph = Graph()

    @Test
    fun initialSetup() {
        assertTrue(graph.adjacencyList.isEmpty())
    }

    @Test
    fun addVertex() {
        graph.addVertex("A")
        graph.addVertex("B")

        assertTrue(graph.adjacencyList.containsKey("A"))
        assertTrue(graph.adjacencyList.containsKey("B"))
        assertTrue(graph.adjacencyList["A"]?.isEmpty() == true)
        assertFalse(graph.adjacencyList.containsKey("C"))
    }

    @Test
    fun addEdge() {
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "D")
        graph.addEdge("D", "A")

        assertTrue(graph.adjacencyList.containsKey("A"))
        assertTrue(graph.adjacencyList.containsKey("B"))
        assertTrue(graph.adjacencyList.containsKey("C"))
        assertTrue(graph.adjacencyList.containsKey("D"))

        assertTrue(graph.adjacencyList["A"]?.toString() == "[B, D]")
        assertTrue(graph.adjacencyList["D"]?.toString() == "[C, A]")
    }

    @Test(expected = IllegalStateException::class)
    fun removeEdge() {
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "D")
        graph.addEdge("D", "A")

        graph.removeEdge("E", "B")

        graph.removeEdge("A", "B")
        assertTrue(graph.adjacencyList["A"]?.toString() == "[D]")
        assertTrue(graph.adjacencyList["B"]?.toString() == "[]")
    }

    @Test(expected = IllegalStateException::class)
    fun removeVertex() {
        graph.addEdge("A", "B")
        graph.addEdge("B", "C")
        graph.addEdge("C", "D")
        graph.addEdge("D", "A")

        graph.removeVertex("E")

        graph.removeVertex("A")
        assertFalse(graph.adjacencyList.containsKey("A"))
        assertFalse(graph.adjacencyList["D"]?.contains("A") == true)
    }

    @Test(expected = IllegalStateException::class)
    fun dfsRecursive() {
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        assertEquals(
            "[A, B, D, E, C, F]",
            graph.dfsRecursiveTraversal("A").toString()
        )

        graph.dfsRecursiveTraversal("G")
    }

    @Test(expected = IllegalStateException::class)
    fun dfsIterative() {
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        assertEquals(
            "[A, C, E, F, D, B]",
            graph.dfsIterativeTraversal("A").toString()
        )

        assertNotSame(
            graph.dfsRecursiveTraversal("A").toString(),
            graph.dfsIterativeTraversal("A").toString()
        )

        graph.dfsIterativeTraversal("G")
    }

    @Test(expected = IllegalStateException::class)
    fun bfsTraversal() {
        graph.addEdge("A", "B")
        graph.addEdge("A", "C")
        graph.addEdge("B", "D")
        graph.addEdge("C", "E")
        graph.addEdge("D", "E")
        graph.addEdge("D", "F")
        graph.addEdge("E", "F")

        assertEquals(
            "[A, B, C, D, E, F]",
            graph.bfsTraversal("A").toString()
        )

        graph.bfsTraversal("G")
    }
}