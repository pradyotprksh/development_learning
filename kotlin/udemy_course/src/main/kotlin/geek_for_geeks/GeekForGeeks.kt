package geek_for_geeks

import data_structures.trees.BinarySearchTree
import data_structures.trees.MaxBinaryHeap
import data_structures.trees.Node

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
        println(isPerfect(a = listOf(1, 2, 3, 2, 1)))
        println(isPerfect(a = listOf(1, 2, 3, 4, 5)))
        println(isPerfect(a = listOf(1, 2, 3, 4)))
        println(isPerfect(a = listOf(1, 2, 2, 1)))
        println(isPerfect(a = listOf(1, 2, 1, 1)))
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
        println(leftElement(a = listOf(7, 8, 3, 4, 2, 9, 5)))
        println(leftElement(a = listOf(8, 1, 2, 9, 4, 3, 7, 5)))
        println(longest(a = listOf("Geek", "Geeks", "Geeksfor", "GeeksforGeek", "GeeksforGeeks")))
        println(getSum(a = listOf(1, 2, 3, 4)))
        println(getSum(a = listOf(5, 8, 3, 10, 22, 45)))
        println(transpose(a = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9))))
        println(transpose(a = listOf(listOf(1, 2), listOf(1, 2))))
        val node = Node(data = 1)
        node.left = Node(data = 4)
        node.left?.left = Node(data = 4)
        node.left?.right = Node(data = 2)
        preorder(node)
        println()
        heapHeight()
        getHeight()
    }

    private fun getHeight() {
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insert(1)
        binarySearchTree.insert(10)
        binarySearchTree.insert(39)
        binarySearchTree.insert(5)

        println(binarySearchTree.getSize())
    }

    private fun heapHeight() {
        val maxBinaryHeap1 = MaxBinaryHeap()
        maxBinaryHeap1.insert(1)
        maxBinaryHeap1.insert(3)
        maxBinaryHeap1.insert(6)
        maxBinaryHeap1.insert(5)
        maxBinaryHeap1.insert(9)
        maxBinaryHeap1.insert(8)

        maxBinaryHeap1.print()

        println(maxBinaryHeap1.height())

        val maxBinaryHeap2 = MaxBinaryHeap()
        maxBinaryHeap2.insert(3)
        maxBinaryHeap2.insert(6)
        maxBinaryHeap2.insert(9)
        maxBinaryHeap2.insert(2)
        maxBinaryHeap2.insert(15)
        maxBinaryHeap2.insert(10)
        maxBinaryHeap2.insert(14)
        maxBinaryHeap2.insert(5)
        maxBinaryHeap2.insert(12)

        maxBinaryHeap2.print()

        println(maxBinaryHeap2.height())
    }

    private fun preorder(root: Node?) {
        if (root == null) return
        print("${root.data} ")
        preorder(root.left)
        preorder(root.right)
    }

    private fun transpose(a: List<List<Int>>): List<List<Int>> {
        val temp = ArrayList<ArrayList<Int>>()

        for (i in a.indices) {
            val row = ArrayList<Int>()
            for (j in a[i].indices) {
                row.add(a[j][i])
            }
            temp.add(row)
        }

        return temp
    }

    private fun getSum(a: List<Int>): Int {
        var sum = 0

        for (num in a) {
            sum += num
        }

        return sum
    }

    private fun longest(a: List<String>): String {
        var longestString = ""

        for (str in a) {
            if (str.length > longestString.length) {
                longestString = str
            }
        }

        return longestString
    }

    private fun leftElement(a: List<Int>): Int {
        val temp = ArrayList(a)

        var removeMax = true

        while (temp.size > 1) {
            var num = if (removeMax) Int.MIN_VALUE else Int.MAX_VALUE
            var numIndex = -1

            for ((i, item) in temp.withIndex()) {
                if (removeMax) {
                    if (item > num) {
                        num = item
                        numIndex = i
                    }
                } else {
                    if (item < num) {
                        num = item
                        numIndex = i
                    }
                }
            }

            temp.removeAt(numIndex)

            removeMax = !removeMax
        }

        return temp.first()
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

    private fun isPerfect(a: List<Int>): Boolean {
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