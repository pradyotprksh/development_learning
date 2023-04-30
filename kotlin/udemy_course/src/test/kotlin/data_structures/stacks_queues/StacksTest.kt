package data_structures.stacks_queues

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StacksTest : TestCase() {
    private val stacks = Stacks()

    @Test
    fun initialSetup() {
        assertNull(stacks.top)
        assertTrue(stacks.length == 0)
    }

    @Test
    fun push() {
        stacks.push(1)

        assertEquals(1, stacks.top)
        assertEquals(1, stacks.length)

        stacks.push(2)
        stacks.push(3)
        stacks.push(4)

        assertEquals(4, stacks.top)
        assertEquals(4, stacks.length)
    }

    @Test(expected = IllegalStateException::class)
    fun pop() {
        stacks.push(1)
        stacks.push(2)
        stacks.push(3)
        stacks.push(4)

        assertEquals(4, stacks.pop())

        assertEquals(3, stacks.top)
        assertEquals(3, stacks.length)

        assertEquals(3, stacks.pop())
        assertEquals(2, stacks.pop())

        assertEquals(1, stacks.top)
        assertEquals(1, stacks.length)

        assertEquals(1, stacks.pop())

        assertNull(stacks.top)
        assertEquals(0, stacks.length)

        stacks.pop()
    }
}