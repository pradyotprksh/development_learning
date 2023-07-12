package algorithms

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SortingAlgorithmsTest : TestCase() {
    private val sortingAlgorithms = SortingAlgorithms()

    @Test
    fun bubbleSort() {
        assertEquals(
                "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]",
                sortingAlgorithms.bubbleSort(a = listOf(3, 4, 1, 2, 5, 8, 9, 7, 11, 10, 6)).toString()
        )
    }

    @Test
    fun selectionSort() {
        assertEquals(
                "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]",
                sortingAlgorithms.selectionSort(a = listOf(3, 4, 1, 2, 5, 8, 9, 7, 11, 10, 6)).toString()
        )
    }
}