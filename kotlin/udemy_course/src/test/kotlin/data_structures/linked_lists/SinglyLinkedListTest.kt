package data_structures.linked_lists

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SinglyLinkedListTest : TestCase() {
    private val singlyLinkedList = SinglyLinkedList()

    @Test
    fun initialSetup() {
        assertNull(singlyLinkedList.head)
        assertNull(singlyLinkedList.tail)
        assertTrue(singlyLinkedList.length == 0)
    }

    @Test
    fun push() {
        singlyLinkedList.push(1)
        assertTrue(singlyLinkedList.length == 1)
        assertTrue(singlyLinkedList.head?.data == 1)
        assertNull(singlyLinkedList.head?.next)
        assertTrue(singlyLinkedList.tail?.data == 1)
        assertNull(singlyLinkedList.tail?.next)

        singlyLinkedList.push(2)
        assertTrue(singlyLinkedList.length == 2)
        assertTrue(singlyLinkedList.head?.data == 1)
        assertNotNull(singlyLinkedList.head?.next)
        assertTrue(singlyLinkedList.tail?.data == 2)
        assertNull(singlyLinkedList.tail?.next)
    }

    @Test(expected = IllegalStateException::class)
    fun pop() {
        assertEquals(0, singlyLinkedList.length)

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)

        assertEquals(3, singlyLinkedList.length)
        assertTrue(singlyLinkedList.pop() == 3)
        assertEquals(2, singlyLinkedList.length)
        assertTrue(singlyLinkedList.pop() == 2)
        assertEquals(1, singlyLinkedList.length)
        assertTrue(singlyLinkedList.pop() == 1)
        assertEquals(0, singlyLinkedList.length)
        singlyLinkedList.pop()
    }

    @Test(expected = IllegalStateException::class)
    fun shift() {
        assertEquals(0, singlyLinkedList.length)

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)

        assertEquals(3, singlyLinkedList.length)
        assertTrue(singlyLinkedList.shift() == 1)
        assertEquals(2, singlyLinkedList.length)
        assertTrue(singlyLinkedList.shift() == 2)
        assertEquals(1, singlyLinkedList.length)
        assertTrue(singlyLinkedList.shift() == 3)
        assertEquals(0, singlyLinkedList.length)
        singlyLinkedList.shift()
    }

    @Test
    fun unshift() {
        assertEquals(0, singlyLinkedList.length)

        singlyLinkedList.unshift(1)
        assertEquals(singlyLinkedList.head?.data, singlyLinkedList.tail?.data)
        assertEquals(1, singlyLinkedList.length)

        singlyLinkedList.unshift(2)
        assertTrue(singlyLinkedList.head?.data == 2)
        assertTrue(singlyLinkedList.tail?.data == 1)
        assertEquals(2, singlyLinkedList.length)
    }

    @Test(expected = Exception::class)
    fun get() {
        singlyLinkedList.get(0)

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)

        assertEquals(1, singlyLinkedList.get(0)?.data)
        assertEquals(3, singlyLinkedList.get(2)?.data)

        singlyLinkedList.unshift(4)

        assertEquals(4, singlyLinkedList.get(0)?.data)
    }

    @Test(expected = Exception::class)
    fun set() {
        singlyLinkedList.set(0, 1)

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)
        singlyLinkedList.push(4)

        assertEquals(1, singlyLinkedList.head?.data)

        singlyLinkedList.set(0, 5)
        assertEquals(5, singlyLinkedList.head?.data)

        singlyLinkedList.set(3, 6)
        assertEquals(6, singlyLinkedList.get(3)?.data)
        assertEquals(6, singlyLinkedList.tail?.data)
    }

    @Test(expected = Exception::class)
    fun insert() {
        singlyLinkedList.insert(0, 1)

        singlyLinkedList.push(1)
        singlyLinkedList.insert(0, 2)
        singlyLinkedList.insert(2, 3)

        assertEquals(2, singlyLinkedList.head?.data)
        assertEquals(3, singlyLinkedList.tail?.data)

        singlyLinkedList.insert(3, 4)
        singlyLinkedList.insert(4, 5)
        singlyLinkedList.insert(6, 6)

        assertEquals(6, singlyLinkedList.tail?.data)
        assertEquals(4, singlyLinkedList.get(4)?.data)
    }

    @Test(expected = Exception::class)
    fun remove() {
        singlyLinkedList.remove(0)

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)
        singlyLinkedList.push(4)
        singlyLinkedList.push(5)

        assertEquals(1, singlyLinkedList.remove(0))
        assertEquals(5, singlyLinkedList.remove(3))

        assertEquals(4, singlyLinkedList.tail?.data)
        assertEquals(2, singlyLinkedList.head?.data)

        assertEquals(3, singlyLinkedList.remove(1))
    }

    @Test(expected = IllegalStateException::class)
    fun reverse() {
        singlyLinkedList.reverse()

        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)
        singlyLinkedList.push(4)
        singlyLinkedList.push(5)

        assertEquals(5, singlyLinkedList.tail?.data)
        assertEquals(1, singlyLinkedList.head?.data)

        singlyLinkedList.reverse()

        assertEquals(5, singlyLinkedList.head?.data)
        assertEquals(1, singlyLinkedList.tail?.data)
    }
}