package data_structures.stacks_queues

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class QueuesTest : TestCase() {
    private val queues = Queues()

    @Test
    fun initialSetup() {
        assertNull(queues.top)
        assertTrue(queues.length == 0)
    }

    @Test
    fun enqueue() {
        queues.enqueue(1)
        queues.enqueue(2)
        queues.enqueue(3)
        queues.enqueue(4)

        assertEquals(4, queues.top)
        assertEquals(4, queues.length)

        queues.enqueue(6)

        assertEquals(6, queues.top)
        assertEquals(5, queues.length)
    }

    @Test(expected = IllegalStateException::class)
    fun dequeue() {
        queues.enqueue(1)
        queues.enqueue(2)
        queues.enqueue(3)
        queues.enqueue(4)

        queues.dequeue()

        assertEquals(3, queues.top)
        assertEquals(3, queues.length)

        queues.dequeue()
        queues.dequeue()
        queues.dequeue()

        assertNull(queues.top)
        assertEquals(0, queues.length)

        queues.dequeue()
    }
}