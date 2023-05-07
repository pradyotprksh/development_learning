package algorithms

class SearchAlgorithms {
    fun linearSearch(a: List<Int>, key: Int): Int {
        for ((i, item) in a.withIndex()) {
            if (key == item) {
                return i
            }
        }

        return -1
    }

    @Suppress("KotlinConstantConditions")
    fun binarySearch(a: List<Int>, key: Int): Int {
        var start = 0
        var end = a.size - 1
        var middle = (start + end) / 2

        while (start <= end) {
            if (a[middle] == key) {
                return middle
            } else {
                if (a[middle] > key) {
                    end = middle - 1
                } else {
                    start = middle + 1
                }
                middle = (start + end) / 2
            }
        }

        return -1
    }

    fun naiveStringSearch(str: String, key: String): Int {
        var match = 0
        var index = 0

        while (index < str.length) {
            var found = true
            for (i in key.indices) {
                if (key[i] != str[index + i]) {
                    found = false
                    break
                }
            }
            if (found) {
                ++match
                index += key.length
            } else {
                ++index
            }
        }

        return match
    }
}