package hackerrank

class Arrays {
    fun solveAllProblems() {
        println("Solution for hour glass sum problem")
        val hourglassSumArr = arrayOf(
            arrayOf(1, 1, 1, 0, 0, 0),
            arrayOf(0, 1, 0, 0, 0, 0),
            arrayOf(1, 1, 1, 0, 0, 0),
            arrayOf(0, 0, 2, 4, 4, 0),
            arrayOf(0, 0, 0, 2, 0, 0),
            arrayOf(0, 0, 1, 2, 4, 0),
        )
        printHourGlass(hourglassSumArr)
        println("The max sum in the given array is ${hourglassSum(hourglassSumArr)}")
        println()

        println("Solution for rotate left problem")
        val leftRotationArr = arrayOf(1, 2, 3, 4, 5)
        println("Using array ${leftRotationArr.toList()}")
        val numberOfTurns = (1..10).random()
        println("After $numberOfTurns rotation new array is ${rotLeft(leftRotationArr, numberOfTurns).toList()}")
        println()

        println("Solution for minimum bribes")
        val minimumBribesArr = arrayOf(1, 2, 5, 3, 7, 8, 6, 4)
        println("Using array ${minimumBribesArr.toList()}")
        minimumBribes(minimumBribesArr)
    }

    private fun minimumBribes(q: Array<Int>) {
        var numberOfBribes = 0
        for (i in q.indices) {
            var bribe = 0
            if (i < q[i] - 1) bribe = q[i] - (i + 1)
            if (bribe > 2) {
                println("Too chaotic")
                break
            } else {
                numberOfBribes += bribe
                if (i == q.size - 1) {
                    println(numberOfBribes)
                }
            }
        }
    }

    private fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
        if (d % a.size != 0) {
            for (i in 1..d) {
                val first = a.first()
                for (index in 1 until a.size) {
                    a[index - 1] = a[index]
                }
                a[a.size - 1] = first
            }
        }
        return a
    }

    private fun hourglassSum(arr: Array<Array<Int>>): Int {
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
        return maxSum
    }

    private fun printHourGlass(arr: Array<Array<Int>>) {
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