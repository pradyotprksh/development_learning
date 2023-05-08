package algorithms

class SortingAlgorithms {
    fun bubbleSort(a: List<Int>): List<Int> {
        val temp = ArrayList(a)

        for (i in temp.indices) {

            for (j in i + 1 until temp.size) {
                if (temp[i] > temp[j]) {
                    val num = temp[i]
                    temp[i] = temp[j]
                    temp[j] = num
                }
            }
        }

        return temp
    }

    fun selectionSort(a: List<Int>): List<Int> {
        val temp = ArrayList(a)
        for (i in temp.indices) {
            var lowest = i

            for (j in i + 1 until temp.size) {
                if (temp[j] < temp[lowest]) {
                    lowest = j
                }
            }

            val num = temp[i]
            temp[i] = temp[lowest]
            temp[lowest] = num
        }

        return temp
    }

    fun insertionSort(a: List<Int>): List<Int> {
        // TODO: Need to implement this
        return a
    }
}