package data_structures.linked_lists

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DoublyLinkedListTest : TestCase() {
    private val doublyLinkedList = DoublyLinkedList()

    @Test
    fun initialSetup() {
        assertNull(doublyLinkedList.head)
        assertNull(doublyLinkedList.tail)
        assertTrue(doublyLinkedList.length == 0)
    }

    @Test
    fun push() {
        doublyLinkedList.push(1)

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(1, doublyLinkedList.length)

        doublyLinkedList.push(2)

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(2, doublyLinkedList.tail?.data)
        assertEquals(2, doublyLinkedList.length)
    }

    @Test(expected = IllegalStateException::class)
    fun pop() {
        doublyLinkedList.pop()

        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.push(4)

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(4, doublyLinkedList.tail?.data)
        assertEquals(4, doublyLinkedList.length)

        doublyLinkedList.pop()
        doublyLinkedList.pop()

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(2, doublyLinkedList.tail?.data)
        assertEquals(2, doublyLinkedList.length)

        doublyLinkedList.pop()

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(1, doublyLinkedList.length)
    }

    @Test(expected = IllegalStateException::class)
    fun shift() {
        doublyLinkedList.shift()

        doublyLinkedList.unshift(1)
        doublyLinkedList.unshift(2)
        doublyLinkedList.unshift(3)
        doublyLinkedList.unshift(4)

        assertEquals(4, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(4, doublyLinkedList.length)

        assertEquals(4, doublyLinkedList.shift())

        assertEquals(3, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(3, doublyLinkedList.length)

        doublyLinkedList.shift()
        doublyLinkedList.shift()

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(1, doublyLinkedList.length)

        doublyLinkedList.shift()

        assertNull(doublyLinkedList.head?.data)
        assertNull(doublyLinkedList.tail?.data)
        assertEquals(0, doublyLinkedList.length)
    }

    @Test
    fun unshift() {
        doublyLinkedList.unshift(1)
        doublyLinkedList.unshift(2)
        doublyLinkedList.unshift(3)
        doublyLinkedList.unshift(4)

        assertEquals(4, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
        assertEquals(4, doublyLinkedList.length)

        doublyLinkedList.push(5)

        assertEquals(4, doublyLinkedList.head?.data)
        assertEquals(5, doublyLinkedList.tail?.data)
        assertEquals(5, doublyLinkedList.length)
    }

    @Test(expected = Exception::class)
    fun get() {
        doublyLinkedList.get(0)

        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.push(4)

        assertEquals(1, doublyLinkedList.get(0)?.data)

        doublyLinkedList.unshift(5)

        assertEquals(5, doublyLinkedList.get(0)?.data)
        assertEquals(4, doublyLinkedList.get(4)?.data)
    }

    @Test(expected = Exception::class)
    fun set() {
        doublyLinkedList.set(0, 1)

        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.push(4)

        assertEquals(1, doublyLinkedList.get(0)?.data)

        doublyLinkedList.set(0, 5)

        assertEquals(5, doublyLinkedList.get(0)?.data)

        doublyLinkedList.set(3, 6)

        assertEquals(6, doublyLinkedList.get(3)?.data)
    }

    @Test(expected = Exception::class)
    fun insert() {
        doublyLinkedList.insert(0, 1)

        doublyLinkedList.push(1)
        doublyLinkedList.insert(0, 2)
        doublyLinkedList.insert(1, 3)

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(3, doublyLinkedList.tail?.data)
        assertEquals(3, doublyLinkedList.length)

        doublyLinkedList.insert(1, 4)
        assertEquals(4, doublyLinkedList.get(1)?.data)
        assertEquals(1, doublyLinkedList.get(0)?.data)
        assertEquals(3, doublyLinkedList.get(2)?.data)
        assertEquals(4, doublyLinkedList.length)
    }

    @Test(expected = Exception::class)
    fun remove() {
        doublyLinkedList.remove(0)

        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.push(4)

        assertEquals(1, doublyLinkedList.remove(0))

        assertEquals(2, doublyLinkedList.head?.data)
        assertEquals(4, doublyLinkedList.tail?.data)
        assertEquals(3, doublyLinkedList.length)

        assertEquals(4, doublyLinkedList.remove(2))

        assertEquals(2, doublyLinkedList.head?.data)
        assertEquals(3, doublyLinkedList.tail?.data)
        assertEquals(2, doublyLinkedList.length)
    }

    @Test(expected = IllegalStateException::class)
    fun reverse() {
        doublyLinkedList.reverse()

        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.push(4)

        assertEquals(1, doublyLinkedList.head?.data)
        assertEquals(4, doublyLinkedList.tail?.data)

        doublyLinkedList.reverse()

        assertEquals(4, doublyLinkedList.head?.data)
        assertEquals(1, doublyLinkedList.tail?.data)
    }
}