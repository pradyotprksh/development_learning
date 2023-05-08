package algorithms

class Algorithms {
    fun startAlgorithms() {
        println("Starting algorithms")

        println("Starting Search Algorithms")
        val searchAlgorithms = SearchAlgorithms()
        println(searchAlgorithms.linearSearch(listOf(1, 2, 4, 5, 9, 10, 34), 12))
        println(searchAlgorithms.linearSearch(listOf(1, 2, 4, 5, 9, 10, 34), 9))
        println(searchAlgorithms.binarySearch(listOf(1, 2, 4, 5, 9, 10, 34), 9))
        println(searchAlgorithms.binarySearch(listOf(1, 2, 4, 5, 9, 10, 34), 12))
        println(searchAlgorithms.naiveStringSearch(str = "wowomgzomg", key = "omg"))

        println("Starting Sorting Algorithms")
        val sortingAlgorithms = SortingAlgorithms()
        println(sortingAlgorithms.bubbleSort(a = listOf(2, 3, 6, 1, 9, 8, 0)))
        println(sortingAlgorithms.selectionSort(a = listOf(2, 3, 6, 1, 9, 8, 0)))
        println(sortingAlgorithms.insertionSort(a = listOf(2, 3, 6, 1, 9, 8, 0)))
    }
}