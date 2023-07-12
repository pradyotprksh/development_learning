package algorithms

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchAlgorithmsTest : TestCase() {

    private val searchAlgorithms = SearchAlgorithms()

    @Test
    fun linearSearch() {
        val items = listOf(1, 3, 23, 56, 12, 78, 324)

        assertEquals(1, searchAlgorithms.linearSearch(a = items, key = 3))
        assertEquals(-1, searchAlgorithms.linearSearch(a = items, key = 9))
    }

    @Test
    fun binarySearch() {
        val items = listOf(1, 9, 10, 45, 50, 65, 190)

        assertEquals(1, searchAlgorithms.linearSearch(a = items, key = 9))
        assertEquals(-1, searchAlgorithms.linearSearch(a = items, key = 200))
    }

    @Test
    fun naiveStringSearch() {
        assertEquals(
                1,
                searchAlgorithms.naiveStringSearch(
                        str = "Pradyot Prakash is learning DS & Algo so that he can clear technical interview",
                        key = "t Prakash"
                )
        )

        assertEquals(
                0,
                searchAlgorithms.naiveStringSearch(
                        str = "Pradyot Prakash is learning DS & Algo so that he can clear technical interview",
                        key = "Algorithm"
                )
        )
    }
}