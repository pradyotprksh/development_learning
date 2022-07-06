package hackerrank

class Arrays {
    fun solveAllProblems() {
        hourglassSum()
    }

    // https://www.hackerrank.com/challenges/2d-array/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    private fun hourglassSum() {
        println("Solution for hour glass sum problem")
        val arr = listOf(
            listOf(1, 1, 1, 0, 0, 0),
            listOf(0, 1, 0, 0, 0, 0),
            listOf(1, 1, 1, 0, 0, 0),
            listOf(0, 0, 2, 4, 4, 0),
            listOf(0, 0, 0, 2, 0, 0),
            listOf(0, 0, 1, 2, 4, 0),
        )
        printHourGlass(arr)
        var maxSum = Int.MIN_VALUE
        for (row in arr.indices) {
            if (row + 2 >= arr.size) {
                continue
            }
            for (column in arr[row].indices) {
                if (column + 2 >= arr[row].size) {
                    continue
                }
                var sum = 0
                for (rF in 0..2) {
                    for (cF in 0..2) {
                        if (rF == 1) {
                            if (cF == 0 || cF == 2) {
                                continue
                            }
                        }
                        sum += arr[row + rF][column + cF]
                    }
                }
                maxSum = maxOf(sum, maxSum)
            }
        }
        println("The max sum in the given array is $maxSum")
    }

    private fun printHourGlass(arr: List<List<Int>>) {
        println("The array which we are going to use is")
        var message = ""
        for (item in arr) {
            var itemMessage = ""
            for (innerItem in item) {
                itemMessage = "$itemMessage $innerItem"
            }
            message = "$message\n$itemMessage"
        }
        println(message)
        println("Converting the array to hourglass images")
        for (row in arr.indices) {
            if (row + 2 >= arr.size) {
                continue
            }
            for (column in arr[row].indices) {
                if (column + 2 >= arr[row].size) {
                    continue
                }
                var sum = 0
                for (rF in 0..2) {
                    for (cF in 0..2) {
                        if (rF == 1) {
                            if (cF == 0 || cF == 2) {
                                continue
                            }
                        }
                        sum += arr[row + rF][column + cF]
                    }
                }
                val first = arr[row + 0][column + 0]
                val second = arr[row + 0][column + 1]
                val third = arr[row + 0][column + 2]
                val fourth = arr[row + 1][column + 1]
                val fifth = arr[row + 2][column + 0]
                val sixth = arr[row + 2][column + 1]
                val seventh = arr[row + 2][column + 2]

                println("$first $second $third $fourth $fifth $sixth $seventh : Sum=$sum")
            }
        }
    }
}