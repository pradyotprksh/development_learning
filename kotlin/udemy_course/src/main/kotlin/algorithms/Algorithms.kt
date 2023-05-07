package algorithms

class Algorithms {
    fun startAlgorithms() {
        println("Starting algorithms")
        val searchAlgorithms = SearchAlgorithms()
        println(searchAlgorithms.linearSearch(listOf(1, 2, 4, 5, 9, 10, 34), 12))
        println(searchAlgorithms.linearSearch(listOf(1, 2, 4, 5, 9, 10, 34), 9))
        println(searchAlgorithms.binarySearch(listOf(1, 2, 4, 5, 9, 10, 34), 9))
        println(searchAlgorithms.binarySearch(listOf(1, 2, 4, 5, 9, 10, 34), 12))
        println(searchAlgorithms.naiveStringSearch(str = "wowomgzomg", key = "omg"))
    }
}