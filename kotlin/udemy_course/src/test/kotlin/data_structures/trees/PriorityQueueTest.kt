package data_structures.trees

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PriorityQueueTest : TestCase() {
    private val priorityQueue = PriorityQueue()

    @Test
    fun enqueue() {
        priorityQueue.enqueue("one", 1)
        priorityQueue.enqueue("five", 5)
        priorityQueue.enqueue("two", 2)

        assertEquals("five", priorityQueue.heap[0].data)
        assertEquals("one", priorityQueue.heap[1].data)
        assertEquals("two", priorityQueue.heap[2].data)
    }

    @Test(expected = IllegalStateException::class)
    fun dequeue() {
        priorityQueue.enqueue("one", 1)
        priorityQueue.enqueue("five", 5)
        priorityQueue.enqueue("two", 2)

        assertEquals("five", priorityQueue.dequeue().data)
        assertEquals("two", priorityQueue.dequeue().data)
        assertEquals("one", priorityQueue.dequeue().data)

        priorityQueue.dequeue()
    }
}