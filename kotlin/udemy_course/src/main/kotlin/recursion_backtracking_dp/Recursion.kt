package recursion_backtracking_dp

import kotlin.random.Random

class Recursion {
    fun startRecursion() {
        println("Starting with recursion")

        tail(5)
        head(5)

        println(factorialHeadRecursion(4))
        println(factorialTailRecursion(4))

        println(fibonacciHeadRecursion(5))
        println(fibonacciTailRecursion(5))
        println(fibonacciHeadRecursion(6))
        println(fibonacciTailRecursion(6))

        println(reverseString("abc"))
        println(reverseString("Pradyot Prakash"))

        towersOfHanoi(3)
        towersOfHanoi(6)

        println(fibonacciIterative(5))
        println(fibonacciIterative(6))

        println(euclideanAlgorithmRecursion(45, 10))
        println(euclideanAlgorithmRecursion(24, 9))
        println(euclideanAlgorithmIterative(45, 10))
        println(euclideanAlgorithmIterative(24, 9))

        SearchAlgorithms().startSearchAlgorithms()
        SelectionAlgorithms().startSelectionAlgorithms()

        println(powerRecursive(2, 10))
    }

    private fun powerRecursive(n: Int, p: Int): Int {
        if (p == 0) return 1
        return n * powerRecursive(n, p - 1)
    }

    private fun euclideanAlgorithmIterative(a: Int, b: Int): Int {
        var tempA = a
        var tempB = b

        while (tempA % tempB != 0) {
            val temp = tempB
            tempB = tempA % tempB
            tempA = temp
        }

        return tempB
    }

    private fun euclideanAlgorithmRecursion(a: Int, b: Int): Int {
        if (a % b == 0) return b
        return euclideanAlgorithmRecursion(b, a % b)
    }

    private fun fibonacciIterative(n: Int): Int {
        var a = 0
        if (n <= 1) return a

        var b = 1

        for (i in 2..n) {
            val tempB = b
            b += a
            a = tempB
        }

        return b
    }

    private fun towersOfHanoi(disk: Int, start: String = "A", middle: String = "B", destination: String = "C") {
        if (disk == 1) {
            println("Disk $disk from $start to $destination")
            return
        }

        towersOfHanoi(disk - 1, start, destination, middle)
        println("Disk $disk from $start to $destination")
        towersOfHanoi(disk - 1, middle, start, destination)
    }

    private fun reverseString(s: String): String {
        if (s.isEmpty()) return s
        return s.last() + reverseString(s.substring(0, s.length - 1))
    }

    private fun fibonacciTailRecursion(n: Int, a: Int = 0, b: Int = 1): Int {
        if (n == 0) return a
        if (n == 1) return b
        return fibonacciTailRecursion(n - 1, b, a + b)
    }

    private fun fibonacciHeadRecursion(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1
        val fib1 = fibonacciHeadRecursion(n - 1)
        val fib2 = fibonacciHeadRecursion(n - 2)
        return fib1 + fib2
    }

    private fun factorialTailRecursion(n: Int, acc: Int = 1): Int {
        if (n <= 1) return acc
        return factorialTailRecursion(n - 1, n * acc)
    }

    private fun factorialHeadRecursion(n: Int): Int {
        if (n <= 1) return 1
        return factorialHeadRecursion(n - 1) * n
    }

    private fun tail(n: Int) {
        if (n == 0) return
        println(n)
        tail(n - 1)
    }

    private val head = DeepRecursiveFunction<Int, Unit> { n ->
        if (n == 0) return@DeepRecursiveFunction
        callRecursive(n - 1)
        println(n)
    }

    class SearchAlgorithms {
        fun startSearchAlgorithms() {
            println("Starting with search algorithms")

            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)

            println(linearSearchIterative(list, 4))
            println(linearSearchIterative(list, 10))

            println(linearSearchRecursion(list, 4))
            println(linearSearchRecursion(list, 10))

            println(binarySearchRecursion(list, 4, 0, list.size - 1))
            println(binarySearchRecursion(list, 10, 0, list.size - 1))
        }

        private fun binarySearchRecursion(container: List<Int>, item: Int, start: Int, end: Int): Int {
            if (start > end) return -1
            val middle = (start + end) / 2
            return if (container[middle] == item) middle
            else if (container[middle] > item) binarySearchRecursion(container, item, start, middle - 1)
            else binarySearchRecursion(container, item, middle + 1, end)
        }

        private fun linearSearchRecursion(container: List<Int>, item: Int, index: Int = 0): Int {
            if (index >= container.size) return -1
            if (container[index] == item) return index
            return linearSearchRecursion(container, item, index + 1)
        }

        private fun linearSearchIterative(container: List<Int>, item: Int): Int {
            for (i in container.indices) {
                if (container[i] == item) return i
            }
            return -1
        }
    }

    class SelectionAlgorithms {
        enum class Type {
            Max,
            Min
        }

        fun startSelectionAlgorithms() {
            println("Starting with selection algorithms")

            val items = intArrayOf(1, -2, 8, 7, 6, 5)
            println(quickSelect(items, 0, items.size - 1, 1, Type.Min))
            println(quickSelect(items, 0, items.size - 1, 1, Type.Max))

            println(quickSort(intArrayOf(1, -2, 9, 100, 8, 7, 41, -874, 6, 5), Type.Min).toList())
            println(quickSort(intArrayOf(1, -2, 9, 100, 8, 7, 41, -874, 6, 5), Type.Max).toList())

            println(medianOfMediansAlgorithm(items, 1, Type.Min))
            println(medianOfMediansAlgorithm(items, 1, Type.Max))
        }

        private fun medianOfMediansAlgorithm(container: IntArray, k: Int, type: Type): Int {
            // Divide the items into multiple arrays with max 5 items each
            val chunks = mutableListOf<List<Int>>()
            val temp = mutableListOf<Int>()
            for (i in container.indices) {
                if (i > 0 && i % 5 == 0) {
                    chunks.add(temp.toList())
                    temp.clear()
                    temp.add(container[i])
                } else {
                    temp.add(container[i])
                }
            }
            if (temp.isNotEmpty()) {
                chunks.add(temp)
            }

            // Find the median in all the chunks, sorted chunk
            val medians = mutableListOf<Int>()
            for (chunk in chunks) {
                medians.add(chunk.sorted()[chunk.size / 2])
            }

            // Pivot
            val pivotValue = medians.sorted()[medians.size / 2]

            // Partition
            val leftArray = mutableListOf<Int>()
            val rightArray = mutableListOf<Int>()
            for (n in container) {
                if (n < pivotValue) {
                    if (type == Type.Min) {
                        leftArray.add(n)
                    } else {
                        rightArray.add(n)
                    }
                } else if (n > pivotValue) {
                    if (type == Type.Min) {
                        rightArray.add(n)
                    } else {
                        leftArray.add(n)
                    }
                }
            }

            // Selection
            val pivotIndex = leftArray.size
            if (pivotIndex > k - 1) {
                return medianOfMediansAlgorithm(leftArray.toIntArray(), k, type)
            } else if (pivotIndex < k - 1) {
                return medianOfMediansAlgorithm(rightArray.toIntArray(), k - leftArray.size - 1, type)
            }
            return pivotValue
        }

        private fun quickSelect(container: IntArray, startIndex: Int, endIndex: Int, k: Int, type: Type): Int {
            val pivot = partitions(container, startIndex, endIndex, type)
            if (pivot > k - 1) {
                return quickSelect(container, startIndex, pivot - 1, k, type)
            } else if (pivot < k - 1) {
                return quickSelect(container, pivot + 1, endIndex, k, type)
            }
            return container[k - 1]
        }

        private fun quickSort(container: IntArray, type: Type): IntArray {
            val newList = mutableListOf<Int>()

            for (i in 1..container.size) {
                newList.add(quickSelect(container, 0, container.size - 1, i, type))
            }

            return newList.toIntArray()
        }

        private fun partitions(container: IntArray, startIndex: Int, endIndex: Int, type: Type): Int {
            val pivot = Random.nextInt(startIndex, endIndex + 1)

            swap(container, pivot, endIndex)

            var indexFirst = startIndex

            for (i in startIndex until endIndex) {
                when (type) {
                    Type.Max -> {
                        if (container[i] > container[endIndex]) {
                            swap(container, indexFirst, i)
                            ++indexFirst
                        }
                    }

                    Type.Min -> {
                        if (container[i] < container[endIndex]) {
                            swap(container, indexFirst, i)
                            ++indexFirst
                        }
                    }
                }
            }

            swap(container, indexFirst, endIndex)

            return indexFirst
        }

        private fun swap(container: IntArray, i: Int, j: Int) {
            val temp = container[i]
            container[i] = container[j]
            container[j] = temp
        }
    }
}