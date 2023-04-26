package data_structures.linked_lists

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.IllegalArgumentException

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
        singlyLinkedList.pop()

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
    }
}