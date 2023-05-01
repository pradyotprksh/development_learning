package data_structures.trees

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MaxBinaryHeapTest : TestCase() {
    private val maxBinaryHeap = MaxBinaryHeap()

    @Test
    fun initialSetup() {
        assertEquals(0, maxBinaryHeap.heap.size)
    }

    @Test
    fun insert() {
        maxBinaryHeap.insert(41)
        maxBinaryHeap.insert(39)
        maxBinaryHeap.insert(33)
        maxBinaryHeap.insert(18)
        maxBinaryHeap.insert(27)
        maxBinaryHeap.insert(12)

        assertEquals("[41, 39, 33, 18, 27, 12]", maxBinaryHeap.heap.toString())

        maxBinaryHeap.insert(55)

        assertEquals("[55, 39, 41, 18, 27, 12, 33]", maxBinaryHeap.heap.toString())
    }

    @Test(expected = IllegalStateException::class)
    fun remove() {
        maxBinaryHeap.remove()

        maxBinaryHeap.insert(41)
        maxBinaryHeap.insert(39)
        maxBinaryHeap.insert(33)
        maxBinaryHeap.insert(18)
        maxBinaryHeap.insert(27)
        maxBinaryHeap.insert(12)
        maxBinaryHeap.insert(55)

        assertEquals(55, maxBinaryHeap.remove())

        assertEquals("[41, 39, 33, 18, 27, 12]", maxBinaryHeap.heap.toString())
    }
}