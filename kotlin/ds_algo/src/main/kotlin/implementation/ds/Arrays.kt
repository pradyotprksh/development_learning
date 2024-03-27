package implementation.ds

object Arrays {
    fun implementation() {
        val numbers = intArrayOf(1, 2, 3)
        println(numbers.contentToString()) // [1, 2, 3]

        val myArray = IntArray(5)
        println(myArray.contentToString()) // [0, 0, 0, 0, 0]

        myArray[0] = 1
        myArray[1] = 2
        myArray[2] = 3
        myArray[3] = 4
        myArray[4] = 5
        println(myArray.contentToString()) // [1, 2, 3, 4, 5]

        myArray[4] = 6
        println(myArray.contentToString()) // [1, 2, 3, 4, 6]

        println("Length: ${myArray.size}")
        println("Last element: ${myArray[myArray.size - 1]}")

        for (a in myArray) {
            print("$a ")
        }
        println()

        for (i in myArray.indices) {
            println("Index = $i Item = ${myArray[i]}")
        }

        val arr = intArrayOf(3, 2, 4, 7, 10, 6, 5)
        println("Remove Even Elements from ${arr.contentToString()}. Result: ${removeEven(arr).contentToString()}") // [3, 7, 5]
        println("Reverse ${arr.contentToString()}. Result: ${reverseArray(arr).contentToString()}") // [5, 6, 10, 7, 4, 2, 3]
        println("Minimum element in ${arr.contentToString()} is ${findMin(arr)}") // 2
        println("Second max element in ${arr.contentToString()} is ${secondMax(arr)}") // 7

        val arr2 = intArrayOf(8, 1, 0, 2, 1, 0, 3)
        println("Moving all the zeroes in ${arr2.contentToString()} in the end. Result: ${moveZerosToEnd(arr2).contentToString()}") // [8, 1, 2, 1, 3, 0, 0]

        val arr3 = intArrayOf(2, 3, 5, 7, 1, 4, 8)
        println("Missing element in ${arr3.contentToString()} is ${findMissingElement(arr3)}") // 6

        println("Is madam plaindrome? - ${isPalindrome("madam")}") // true
        println("Is Madam plaindrome? - ${isPalindrome("Madam")}") // true
        println("Is Rust plaindrome? - ${isPalindrome("Rust")}") // false
    }

    private fun removeEven(arr: IntArray): IntArray {
        val oddArray = arrayListOf<Int>()
        for (a in arr) {
            if (a % 2 != 0) {
                oddArray.add(a)
            }
        }
        return oddArray.toIntArray()
    }

    private fun reverseArray(arr: IntArray): IntArray {
        var start = 0
        var end = arr.size - 1

        while (start < end) {
            val temp = arr[start]
            arr[start] = arr[end]
            arr[end] = temp
            ++start
            --end
        }

        return arr
    }

    private fun findMin(arr: IntArray): Int {
        var min = arr[0]

        for (a in arr) {
            if (a < min) {
                min = a
            }
        }

        return min
    }

    private fun secondMax(arr: IntArray): Int {
        var max = Int.MIN_VALUE
        var secondMax = Int.MIN_VALUE

        for (a in arr) {
            if (a > max) {
                secondMax = max
                max = a
            } else if (a > secondMax) {
                secondMax = a
            }
        }

        return secondMax
    }

    private fun moveZerosToEnd(arr: IntArray): IntArray {
        var j = 0

        for (i in arr.indices) {
            if (arr[i] != 0 && arr[j] == 0) {
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
            if (arr[j] != 0) {
                ++j
            }
        }

        return arr
    }

    private fun findMissingElement(arr: IntArray): Int {
        val sum = arr.sum()
        val n = arr.size + 1
        val numSum = (n * (n + 1) / 2)
        return numSum - sum
    }

    private fun isPalindrome(value: String): Boolean {
        val lowerValue = value.lowercase()
        var start = 0
        var end = value.length - 1

        while (start <= end) {
            if (lowerValue[start] != lowerValue[end]) {
                return false
            }
            ++start
            --end
        }

        return true
    }
}