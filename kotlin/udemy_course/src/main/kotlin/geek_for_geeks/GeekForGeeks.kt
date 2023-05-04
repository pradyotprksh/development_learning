package geek_for_geeks

class GeekForGeeks {
    fun startGeekForGeeks() {
        println("Starting problems from GeekForGeeks [https://practice.geeksforgeeks.org/explore?page=1]")
        println(palinArray(a = listOf(121, 131, 20)))
        println(palinArray(a = listOf(111, 222, 333, 444, 555)))
        printAl(a = listOf(1, 2, 3, 4))
        printAl(a = listOf(1, 2, 3, 4, 5))
        println()
        println(sumElement(a = listOf(3, 2, 1)))
        println(sumElement(a = listOf(1, 2, 3, 4)))
        println(IsPerfect(a = listOf(1, 2, 3, 2, 1)))
        println(IsPerfect(a = listOf(1, 2, 3, 4, 5)))
        println(IsPerfect(a = listOf(1, 2, 3, 4)))
        println(IsPerfect(a = listOf(1, 2, 2, 1)))
        println(IsPerfect(a = listOf(1, 2, 1, 1)))
        println(findIndex(a = listOf(1, 2, 3, 4, 5, 5), k = 5))
        println(findIndex(a = listOf(6, 5, 4, 3, 1, 2), k = 4))
        println(findIndex(a = listOf(6, 5, 4, 3, 1, 2), k = 8))
        println(seriesSum(1))
        println(seriesSum(5))
        println(findElements(a = listOf(2, 8, 7, 1, 5)))
        println(findElements(a = listOf(7, -2, 3, 4, 9, -1)))
        println(fascinating(192))
        println(fascinating(853))
        println(valueEqualToIndex(a = listOf(15, 2, 45, 12, 7)))
        println(valueEqualToIndex(a = listOf(1)))
        println(valueEqualToIndex(a = listOf(1, 2, 3, 4, 6)))
        println(scores(a = listOf(4, 2, 7), b = listOf(5, 6, 3)))
        println(scores(a = listOf(4, 2, 7), b = listOf(5, 2, 8)))
        println(swapKth(a = listOf(1, 2, 3, 4, 5, 6, 7, 8), k = 3))
        println(swapKth(a = listOf(5, 3, 6, 1, 2), k = 2))
        println(print2largest(a = listOf(12, 35, 1, 10, 34, 1)))
        println(print2largest(a = listOf(10, 5, 10)))
        println(getMoreAndLess(a = listOf(1, 2, 8, 10, 11, 12, 19), x = 0))
        println(getMoreAndLess(a = listOf(1, 2, 8, 10, 11, 12, 19), x = 5))
        println(streamAvg(a = listOf(10, 20, 30, 40, 50)))
        println(streamAvg(a = listOf(12, 2)))
    }

    private fun streamAvg(a: List<Int>): List<Int> {
        val avg = ArrayList<Int>()

        var sum = 0

        for ((i, num) in a.withIndex()) {
            sum += num
            avg.add(sum / (i + 1))
        }

        return avg
    }

    private fun getMoreAndLess(a: List<Int>, x: Int): List<Int> {
        var lessNum = 0
        var moreNum = 0

        for (num in a) {
            if (num <= x) {
                ++lessNum
            }
            if (num >= x) {
                ++moreNum
            }
        }

        return listOf(lessNum, moreNum)
    }

    private fun print2largest(a: List<Int>): Int {
        if (a.size == 1) {
            return -1
        }

        var largestNum = a[0]
        var secondLargestNum = -1

        for (num in a) {
            if (num > largestNum) {
                secondLargestNum = largestNum
                largestNum = num
            } else if (num > secondLargestNum && num != largestNum) {
                secondLargestNum = num
            }
        }

        return secondLargestNum
    }

    private fun swapKth(a: List<Int>, k: Int): List<Int> {
        val swapList = ArrayList<Int>(a)

        val temp = swapList[k - 1]
        swapList[k - 1] = swapList[swapList.size - k]
        swapList[swapList.size - k] = temp

        return swapList
    }

    private fun scores(a: List<Int>, b: List<Int>): List<Int> {
        var firstScore = 0
        var secondScore = 0

        for (i in a.indices) {
            if (a[i] > b[i]) {
                ++firstScore
            } else if (a[i] < b[i]) {
                ++secondScore
            }
        }

        return listOf(firstScore, secondScore)
    }

    private fun valueEqualToIndex(a: List<Int>): List<Int> {
        val elements = ArrayList<Int>()
        for ((index, value) in a.withIndex()) {
            if (value == index + 1) {
                elements.add(value)
            }
        }
        return elements
    }

    private fun fascinating(a: Int): Boolean {
        if (a.toString().length < 3) {
            return false
        } else {
            val num1 = a * 2
            val num2 = a * 3

            val finalNum = "$a$num1$num2"

            for (i in 1..9) {
                if (!finalNum.contains(i.toString())) {
                    return false
                }
            }
        }

        return true
    }

    private fun findElements(a: List<Int>): List<Int> {
        val sorted = a.sorted()
        return sorted.subList(0, sorted.size - 2)
    }

    private fun seriesSum(n: Int): Int {
        var sum = 0
        for (i in 1..n) {
            sum += i
        }
        return sum
    }

    private fun findIndex(a: List<Int>, k: Int): Any {
        var start = -1
        var end = -1

        for (i in a.indices) {
            if (a[i] == k && start == -1) {
                start = i
            }
            if (a[a.size - 1 - i] == k && end == -1) {
                end = a.size - 1 - i
            }
        }

        if (start == -1 && end == -1) {
            return -1
        }
        return listOf(start, end)
    }

    private fun IsPerfect(a: List<Int>): Boolean {
        for (i in 0 until a.size / 2) {
            if (a[i] != a[a.size - 1 - i]) {
                return false
            }
        }
        return true
    }

    private fun sumElement(a: List<Int>): Int {
        var sum = 0
        for (i in a.indices) {
            sum += a[i]
        }
        return sum
    }

    private fun printAl(a: List<Int>) {
        for (i in a.indices) {
            if (i % 2 != 0) continue
            print(a[i])
        }
    }

    private fun palinArray(a: List<Int>): Int {
        for (num in a) {
            if (!isPalindrome(num.toString())) {
                return 0
            }
        }
        return 1
    }

    private fun isPalindrome(data: String): Boolean {
        for (i in data.indices) {
            if (data[i] != data[data.length - 1 - i]) {
                return false
            }
        }
        return true
    }
}