package leet_code

import data_structures.linked_lists.ListNode
import data_structures.trees.TreeNode
import java.util.*
import kotlin.math.abs
import kotlin.math.max

class LeetCode {
    fun startLeetCode() {
        println("Starting problems from LeetCode")
        println(lengthOfLongestSubstring("cdd"))
        println(lengthOfLongestSubstring("abcabcbb"))
        println(lengthOfLongestSubstring("pwwkew"))
        println(lengthOfLongestSubstring(" "))
        println(lengthOfLongestSubstring("dvdf"))
        println(lengthOfLongestSubstring("pwwkew"))

        println(findMedianSortedArrays(intArrayOf(1, 3), intArrayOf(2)))
        println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)))

        println(longestPalindrome("babad"))
        println(longestPalindrome("cbbd"))
        println(longestPalindrome("ab"))

        println(reverse(123))
        println(reverse(120))
        println(reverse(-123))
        println(reverse(1534236469))

        println(isPalindrome(121))
        println(isPalindrome(-121))
        println(isPalindrome(10))

        println(isMatch("aa", "a"))
        println(isMatch("aa", "a*"))
        println(isMatch("ab", ".*"))

        println(longestCommonPrefix(arrayOf("flower", "flow", "flight")))

        println(
                removeNthFromEnd(
                        ListNode(
                                data = 1,
                                next = ListNode(data = 2, next = ListNode(3, next = ListNode(4, next = ListNode(data = 5))))
                        ), 2
                )
        )
        println(removeNthFromEnd(ListNode(data = 1, next = ListNode(data = 2)), 1))
        println(removeNthFromEnd(ListNode(data = 1), 1))
        println(removeNthFromEnd(ListNode(data = 1, next = ListNode(data = 2)), 2))

        println(isValid("()"))
        println(isValid("()[]{}"))
        println(isValid("(]"))
        println(isValid("("))
        println(isValid("){"))

        println(
                mergeTwoLists(
                        ListNode(data = 1, next = ListNode(data = 2, ListNode(data = 4))),
                        ListNode(data = 1, next = ListNode(data = 3, ListNode(data = 4))),
                )
        )
        println(mergeTwoLists(null, null))
        println(
                mergeTwoLists(
                        null,
                        ListNode(data = 0),
                )
        )

        println(swapPairs(ListNode(data = 1, next = ListNode(data = 2, next = ListNode(3, next = ListNode(4))))))
        println(swapPairs(ListNode(data = 1)))
        println(swapPairs(null))
        println(swapPairs(ListNode(data = 1, next = ListNode(data = 2, next = ListNode(3)))))

        println(removeDuplicates(intArrayOf(1, 1, 2)))
        println(removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))

        println(maxApples(1, listOf(1, 2, 3, 2, 4)))

        println(removeElement(intArrayOf(3, 2, 2, 3), 3))
        println(removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2))

        println(strStr("sadbutsad", "sad"))
        println(strStr("leetcode", "leeto"))
        println(strStr("hello", "ll"))

        println(findSubstring("barfoothefoobarman", arrayOf("foo", "bar")))
        println(findSubstring("wordgoodgoodgoodbestword", arrayOf("word", "good", "best", "word")))
        println(findSubstring("barfoofoobarthefoobarman", arrayOf("bar", "foo", "the")))
        println(findSubstring("wordgoodgoodgoodbestword", arrayOf("word", "good", "best", "good")))
        println(findSubstring("abababab", arrayOf("ab", "ba")))
        println(findSubstring("abaababbaba", arrayOf("ab", "ba", "ab", "ba")))

        println(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).toList())
        println(searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 6).toList())
        println(searchRange(intArrayOf(), 0).toList())
        println(searchRange(intArrayOf(1), 1).toList())
        println(searchRange(intArrayOf(2, 2), 2).toList())

        println(searchInsert(intArrayOf(1, 3, 5, 6), 5))
        println(searchInsert(intArrayOf(1, 3, 5, 6), 2))
        println(searchInsert(intArrayOf(1, 3, 5, 6), 7))
        println(searchInsert(intArrayOf(1), 1))

        println(merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3))

        println(removeDuplicates2(intArrayOf(1, 1, 1, 2, 2, 3)))

        println(majorityElement(intArrayOf(3, 2, 3)))
        println(majorityElement(intArrayOf(2, 2, 1, 1, 1, 2, 2)))
        println(majorityElement(intArrayOf(3, 3, 4)))

        val rotateArray1 = intArrayOf(1, 2, 3, 4, 5, 6, 7)
        rotate(rotateArray1, 3)
        println(rotateArray1.toList())
        val rotateArray2 = intArrayOf(-1, -100, 3, 99)
        rotate(rotateArray2, 2)
        println(rotateArray2.toList())
        val rotateArray3 = intArrayOf(-1)
        rotate(rotateArray3, 2)
        println(rotateArray3.toList())

        println(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4)))

        println(maxProfit2(intArrayOf(7, 1, 5, 3, 6, 4)))
        println(maxProfit2(intArrayOf(1, 2, 3, 4, 5)))

        println(lengthOfLastWord("Hello World"))
        println(lengthOfLastWord("   fly me   to   the moon  "))

        println(isPalindrome("A man, a plan, a canal: Panama"))
        println(isPalindrome("race a car"))
        println(isPalindrome(" "))

        println(isSubsequence("abc", "ahbgdc"))
        println(isSubsequence("axc", "ahbgdc"))
        println(isSubsequence("b", "abc"))

        println(canConstruct("a", "b"))
        println(canConstruct("aa", "ab"))
        println(canConstruct("aa", "aab"))

        println(isIsomorphic("egg", "add"))
        println(isIsomorphic("foo", "bar"))
        println(isIsomorphic("paper", "title"))
        println(isIsomorphic("badc", "baba"))

        println(isAnagram("anagram", "nagaram"))
        println(isAnagram("rat", "car"))

        println(twoSum(intArrayOf(2, 7, 11, 15), 9).toList())
        println(twoSum(intArrayOf(3, 2, 4), 6).toList())
        println(twoSum(intArrayOf(3, 3), 6).toList())

        println(isHappy(19))
        println(isHappy(2))
        println(isHappy(7))

        println(containsNearbyDuplicate(intArrayOf(1, 2, 3, 1), 3))
        println(containsNearbyDuplicate(intArrayOf(1, 0, 1, 1), 1))
        println(containsNearbyDuplicate(intArrayOf(1, 2, 3, 1, 2, 3), 2))

        val listNode3 = ListNode(3)
        val listNode2 = ListNode(2)
        val listNode0 = ListNode(0)
        val listNode4 = ListNode(4)
        listNode3.next = listNode2
        listNode2.next = listNode0
        listNode0.next = listNode4
        listNode4.next = listNode2
        println(hasCycle(listNode3))
        val listNode1 = ListNode(1)
        listNode1.next = listNode2
        listNode2.next = listNode1
        println(hasCycle(listNode1))
        listNode1.next = null
        println(hasCycle(listNode1))

        println(maxDepth(TreeNode(data = 3, left = TreeNode(data = 9), right = TreeNode(data = 20, left = TreeNode(data = 15), right = TreeNode(data = 7)))))

        println(isSameTree(TreeNode(data = 1, left = TreeNode(data = 2), right = TreeNode(data = 3)), TreeNode(data = 1, left = TreeNode(data = 2), right = TreeNode(data = 3))))
        println(isSameTree(TreeNode(data = 1, left = TreeNode(data = 2)), TreeNode(data = 1, right = TreeNode(data = 2))))
        println(isSameTree(TreeNode(data = 1, left = TreeNode(data = 2), right = TreeNode(data = 1)), TreeNode(data = 1, left = TreeNode(data = 1), right = TreeNode(data = 2))))

        println(plusOne(intArrayOf(1, 2, 3)).toList())
        println(plusOne(intArrayOf(4, 3, 2, 1)).toList())
        println(plusOne(intArrayOf(9)).toList())
        println(plusOne(intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)).toList())

        println(mySqrt(4))
        println(mySqrt(8))

        println(twoSum2(intArrayOf(2, 7, 11, 15), 9).toList())
        println(twoSum2(intArrayOf(2, 3, 4), 6).toList())
        println(twoSum2(intArrayOf(-1, 0), -1).toList())

        println(wordPattern("abba", "dog cat cat dog"))
        println(wordPattern("abba", "dog cat cat fish"))
        println(wordPattern("aaaa", "dog cat cat dog"))

        println(groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
        println(groupAnagrams(arrayOf("ddddddddddg", "dgggggggggg")))

        println(longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2)))
        println(longestConsecutive(intArrayOf(0, 3, 7, 2, 5, 8, 4, 6, 0, 1)))

        println(addTwoNumbers(ListNode(2, ListNode(4, ListNode(3))), ListNode(5, ListNode(6, ListNode(4)))))
        println(addTwoNumbers(ListNode(0), ListNode(0)))
        println(addTwoNumbers(ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9, ListNode(9))))))), ListNode(9, ListNode(9, ListNode(9, ListNode(9))))))

        println(reverseBetween(ListNode(1, ListNode(2, ListNode(3, ListNode(4, ListNode(5))))), 2, 4))
        println(reverseBetween(ListNode(5), 1, 1))
        println(reverseBetween(ListNode(3, ListNode(5)), 1, 2))

        println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
        println(threeSum(intArrayOf(0, 1, 1)))
        println(threeSum(intArrayOf(0, 0, 0)))

        println(summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)))
        println(summaryRanges(intArrayOf(0, 2, 3, 4, 6, 8, 9)))

        println(simplifyPath("/home/"))
        println(simplifyPath("/../"))
        println(simplifyPath("/home//foo/"))
        println(simplifyPath("/a//b////c/d//././/.."))
        println(simplifyPath("/a/../../b/../c//.//"))

        println(evalRPN(arrayOf("2", "1", "+", "3", "*")))
        println(evalRPN(arrayOf("4", "13", "5", "/", "+")))
        println(evalRPN(arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")))

        println(reverseWords("the sky is blue"))
        println(reverseWords("  hello world  "))
        println(reverseWords("a good   example"))

        val m1 = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
        rotate(m1)
        println(m1.toList().map { it.toList() })

        val m2 = arrayOf(intArrayOf(1, 1, 1), intArrayOf(1, 0, 1), intArrayOf(1, 1, 1))
        setZeroes(m2)
        println(m2.toList().map { it.toList() })

        println(spiralOrder(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))))
        println(spiralOrder(arrayOf(intArrayOf(1, 2, 3, 4), intArrayOf(5, 6, 7, 8), intArrayOf(9, 10, 11, 12))))

        println(invertTree(TreeNode(data = 4, left = TreeNode(data = 2, left = TreeNode(data = 1), right = TreeNode(data = 3)), right = TreeNode(data = 7, left = TreeNode(data = 6), right = TreeNode(data = 9)))))
        println(invertTree(TreeNode(data = 3, left = TreeNode(data = 1, right = TreeNode(data = 2)))))

        println(isSymmetric(TreeNode(data = 1, left = TreeNode(data = 2, right = TreeNode(data = 3)), right = TreeNode(data = 2, right = TreeNode(data = 3)))))

        println(letterCombinations("23"))
        println(letterCombinations(""))
        println(letterCombinations("2"))
        println(letterCombinations("234"))

        println(findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
        println(findKthLargest(intArrayOf(3, 2, 3, 1, 2, 4, 5, 5, 6), 4))

        println(mergeAlternately("abc", "pqr"))
        println(mergeAlternately("ab", "pqrs"))

        println(gcdOfStrings("ABCABC", "ABC"))
        println(gcdOfStrings("ABABAB", "ABAB"))
        println(gcdOfStrings("LEET", "CODE"))
        println(gcdOfStrings("ABCDEF", "ABC"))

        println(kidsWithCandies(intArrayOf(2, 3, 5, 1, 3), 3))
        println(kidsWithCandies(intArrayOf(4, 2, 1, 1, 2), 1))
        println(kidsWithCandies(intArrayOf(12, 1, 12), 1))
        println(kidsWithCandies(intArrayOf(2, 8, 7), 1))

        println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 1))
        println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 2))

        println(reverseVowels("hello"))
        println(reverseVowels("leetcode"))

        println(productExceptSelf(intArrayOf(1, 2, 3, 4)).toList())

        println(increasingTriplet(intArrayOf(1, 2, 3, 4, 5)))
        println(increasingTriplet(intArrayOf(5, 4, 3, 2, 1)))
        println(increasingTriplet(intArrayOf(2, 1, 5, 0, 4, 6)))
        println(increasingTriplet(intArrayOf(20, 100, 10, 12, 5, 13)))
        println(increasingTriplet(intArrayOf(1, 5, 0, 4, 1, 3)))

        val nums1 = intArrayOf(0, 1, 0, 3, 12)
        moveZeroes(nums1)
        println(nums1.toList())

        val nums2 = intArrayOf(0)
        moveZeroes(nums2)
        println(nums2.toList())

        println(maxOperations(intArrayOf(1, 2, 3, 4, 5), 5))
        println(maxOperations(intArrayOf(3, 1, 3, 4, 3), 6))
        println(maxOperations(intArrayOf(2, 5, 4, 4, 1, 3, 4, 4, 1, 4, 4, 1, 2, 1, 2, 2, 3, 2, 4, 2), 3))

        println(findMaxAverage(intArrayOf(1, 12, -5, -6, 50, 3), 4))
        println(findMaxAverage(intArrayOf(5), 1))
        println(findMaxAverage(intArrayOf(0, 1, 1, 3, 3), 4))
    }

    private fun findMaxAverage(nums: IntArray, k: Int): Double {
        var maxSum = 0.0

        for (i in 0 until k) {
            maxSum += nums[i]
        }
        var maxAvg = maxSum / k

        for (i in k until nums.size) {
            maxSum += nums[i]
            maxSum -= nums[i - k]

            maxAvg = maxOf(maxAvg, maxSum / k)
        }

        return maxAvg
    }

    private fun maxOperations(nums: IntArray, k: Int): Int {
        val pariMap = mutableMapOf<Int, MutableList<Int>>()
        var operation = 0
        for (n in nums) {
            if (pariMap.containsKey(n)) {
                if (pariMap[n]!!.isNotEmpty()) {
                    pariMap[n]!!.removeAt(0)
                    ++operation
                    if (pariMap[n]!!.isEmpty()) {
                        pariMap.remove(n)
                    }
                } else {
                    pariMap[n]!!.add(n)
                }
            } else {
                if (pariMap.containsKey(k - n)) {
                    pariMap[k - n]!!.add(n)
                } else {
                    pariMap[k - n] = mutableListOf(n)
                }
            }
        }
        return operation
    }

    private fun moveZeroes(nums: IntArray): Unit {
        val zeroIndices = mutableListOf<Int>()

        var i = 0
        while (i < nums.size) {
            if (nums[i] == 0) {
                zeroIndices.add(i)
            } else {
                if (zeroIndices.isNotEmpty()) {
                    val index = zeroIndices.removeAt(0)
                    val temp = nums[i]
                    nums[i] = nums[index]
                    nums[index] = temp
                    zeroIndices.add(i)
                }
            }
            ++i
        }
    }

    private fun increasingTriplet(nums: IntArray): Boolean {
        var firstSmallest = Int.MAX_VALUE
        var secondSmallest = Int.MAX_VALUE

        for (n in nums) {
            if (n <= firstSmallest) firstSmallest = n
            else if (n <= secondSmallest) secondSmallest = n
            else return true
        }

        return false
    }

    private fun productExceptSelf(nums: IntArray): IntArray {
        val ans = IntArray(nums.size)
        var n = 1
        for (i in nums.indices) {
            ans[i] = n
            n *= nums[i]
        }

        n = 1
        for (i in ans.size - 1 downTo 0) {
            ans[i] = n * ans[i]
            n *= nums[i]
        }

        return ans
    }

    private fun reverseVowels(s: String): String {
        val vowles = listOf('a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U')
        val tempS = StringBuilder(s)
        var i = 0
        var j = tempS.length - 1

        while (i < j) {
            if (vowles.contains(tempS[i]) && vowles.contains(tempS[j])) {
                val temp = tempS[i]
                tempS[i] = tempS[j]
                tempS[j] = temp
                ++i
                --j
            }
            if (!vowles.contains(tempS[i])) {
                ++i
            }
            if (!vowles.contains(tempS[j])) {
                --j
            }
        }

        return tempS.toString()
    }

    private fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var tempN = n
        for (i in flowerbed.indices) {
            if (tempN <= 0) return true
            if (flowerbed[i] == 1) continue

            val previous = i - 1
            val next = i + 1

            if (previous in flowerbed.indices && next in flowerbed.indices) {
                if (flowerbed[previous] == 0 && flowerbed[next] == 0) {
                    flowerbed[i] = 1
                    --tempN
                }
            } else if (previous in flowerbed.indices) {
                if (flowerbed[previous] == 0) {
                    flowerbed[i] = 1
                    --tempN
                }
            } else if (next in flowerbed.indices) {
                if (flowerbed[next] == 0) {
                    flowerbed[i] = 1
                    --tempN
                }
            } else {
                if (flowerbed[i] == 0) {
                    flowerbed[i] = 1
                    --tempN
                }
            }
        }

        return tempN == 0
    }

    private fun kidsWithCandies(candies: IntArray, extraCandies: Int): List<Boolean> {
        var maxValue = candies.first()

        for (i in 1 until candies.size) {
            if (maxValue < candies[i]) {
                maxValue = candies[i]
            }
        }

        val result = mutableListOf<Boolean>()
        for (candy in candies) {
            result.add(candy + extraCandies >= maxValue)
        }

        return result
    }

    private fun gcdOfStrings(str1: String, str2: String): String {
        return if (str1.length < str2.length) {
            gcdOfStrings(str2, str1)
        } else if (str1 == str2) {
            str1
        } else {
            if (str1.startsWith(str2)) {
                gcdOfStrings(str1.removePrefix(str2), str2)
            } else {
                ""
            }
        }
    }

    private fun mergeAlternately(word1: String, word2: String): String {
        val output = StringBuilder("")

        var i = 0

        while (i < word1.length || i < word2.length) {
            if (i < word1.length) {
                output.append(word1[i])
            }
            if (i < word2.length) {
                output.append(word2[i])
            }
            ++i
        }

        return output.toString()
    }

    private fun findKthLargest(nums: IntArray, k: Int): Int {
        val chunks = mutableListOf<List<Int>>()
        val temp = mutableListOf<Int>()
        for (i in nums.indices) {
            if (i > 0 && i % 5 == 0) {
                chunks.add(temp.toList())
                temp.clear()
            }
            temp.add(nums[i])
        }
        if (temp.isNotEmpty()) {
            chunks.add(temp)
        }

        val medians = mutableListOf<Int>()
        for (chunk in chunks) {
            medians.add(chunk.sorted()[chunk.size / 2])
        }

        val pivotElement = medians.sorted()[medians.size / 2]

        val leftArray = mutableListOf<Int>()
        val rightArray = mutableListOf<Int>()
        for (n in nums) {
            if (n > pivotElement) {
                leftArray.add(n)
            } else if (n < pivotElement) {
                rightArray.add(n)
            }
        }

        val pivotIndex = leftArray.size
        if (pivotIndex > k - 1) {
            return findKthLargest(leftArray.toIntArray(), k)
        } else if (pivotIndex < k - 1) {
            return findKthLargest(rightArray.toIntArray(), k - leftArray.size - 1)
        }
        return pivotElement
    }

    private fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()

        val output = mutableListOf("")
        val digitCharMap = mapOf(
                "2" to listOf("a", "b", "c"),
                "3" to listOf("d", "e", "f"),
                "4" to listOf("g", "h", "i"),
                "5" to listOf("j", "k", "l"),
                "6" to listOf("m", "n", "o"),
                "7" to listOf("p", "q", "r", "s"),
                "8" to listOf("t", "u", "v"),
                "9" to listOf("w", "x", "y", "z"),
        )

        for (d in digits.indices) {
            while (output.first().length == d) {
                val first = output.removeAt(0)
                for (c in digitCharMap[digits[d].toString()]!!) {
                    output.add("$first$c")
                }
            }
        }

        return output.toList()
    }

    private fun isSymmetric(root: TreeNode?): Boolean {
        return isMirror(root, root)
    }

    private fun isMirror(t1: TreeNode?, t2: TreeNode?): Boolean {
        if (t1 == null && t2 == null) return true
        if (t1 == null || t2 == null) return false
        return (t1.data == t2.data) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left)
    }

    private fun invertTree(root: TreeNode?): TreeNode? {
        val queueRoots = mutableListOf(root)

        while (queueRoots.isNotEmpty()) {
            val tempRoot = queueRoots.removeFirst()
            if (tempRoot != null) {
                val left = tempRoot.left
                val right = tempRoot.right

                tempRoot.left = right
                tempRoot.right = left

                if (left != null) queueRoots.add(left)
                if (right != null) queueRoots.add(right)
            }
        }

        return root
    }

    // https://www.youtube.com/watch?v=3joo9yAZVh8
    private fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()

        var l = 0
        var r = matrix.first().size - 1
        val totalSize = matrix.size * matrix.first().size

        while (l <= r) {
            val t = l
            val b = (matrix.size - 1) - t

            for (i in l..r) {
                result.add(matrix[t][i])
            }
            if (result.size == totalSize) break

            for (i in t + 1..b) {
                result.add(matrix[i][r])
            }
            if (result.size == totalSize) break

            for (i in r - 1 downTo l) {
                result.add(matrix[b][i])
            }
            if (result.size == totalSize) break

            for (i in b - 1 downTo t + 1) {
                result.add(matrix[i][l])
            }
            if (result.size == totalSize) break

            ++l
            --r
        }

        return result
    }

    private fun setZeroes(matrix: Array<IntArray>) {
        val rows = matrix.size
        val cols = matrix.first().size
        val foundZeros = mutableSetOf<String>()

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                if (matrix[row][col] == 0) {
                    foundZeros.add("$row,$col")
                }
            }
        }

        foundZeros.forEach { index ->
            val row = index.split(",").first().toInt()
            val col = index.split(",").last().toInt()

            for (i in 0 until cols) {
                matrix[row][i] = 0
            }

            for (i in 0 until rows) {
                matrix[i][col] = 0
            }
        }
    }

    private fun rotate(matrix: Array<IntArray>) {
        var l = 0
        var r = matrix.size - 1

        while (l < r) {
            for (i in 0 until r - l) {
                val t = l
                val b = r

                val temp = matrix[t][l + i]
                matrix[t][l + i] = matrix[b - i][l]
                matrix[b - i][l] = matrix[b][r - i]
                matrix[b][r - i] = matrix[t + i][r]
                matrix[t + i][r] = temp
            }

            ++l
            --r
        }
    }

    private fun reverseWords(s: String): String {
        val words = s.split(" ").reversed()

        val finalReverse = StringBuilder()
        words.forEach { word ->
            if (word.trim().isNotEmpty()) {
                finalReverse.append(word)
                finalReverse.append(" ")
            }
        }
        return finalReverse.toString().trim()
    }

    private fun evalRPN(tokens: Array<String>): Int {
        val stack = mutableListOf<Int>()
        tokens.forEach { t ->
            if (t == "*" || t == "-" || t == "+" || t == "/") {
                val secondNum = stack.removeAt(stack.size - 1)
                val firstNum = stack.removeAt(stack.size - 1)
                when (t) {
                    "*" -> {
                        stack.add(firstNum * secondNum)
                    }

                    "-" -> {
                        stack.add(firstNum - secondNum)
                    }

                    "+" -> {
                        stack.add(firstNum + secondNum)
                    }

                    else -> {
                        stack.add(firstNum / secondNum)
                    }
                }
            } else {
                stack.add(t.toInt())
            }
        }

        return stack.first()
    }

    private fun simplifyPath(path: String): String {
        val pathsList = path.split("/")
        val pathStack = mutableListOf<String>()

        pathsList.forEach { p ->
            if (p == "..") {
                if (pathStack.isNotEmpty()) {
                    pathStack.removeAt(pathStack.size - 1)
                }
            } else {
                if (p.isNotEmpty() && p != ".") {
                    pathStack.add(p)
                }
            }
        }

        val finalPath = StringBuilder()
        for (p in pathStack) {
            finalPath.append("/")
            finalPath.append(p)
        }

        return if (finalPath.isEmpty()) {
            "/"
        } else {
            finalPath.toString()
        }
    }

    // https://www.youtube.com/watch?v=auK3PSZoidc&t=2196s
    // can be made faster, using only n^2
    private fun isValidSudoku(board: Array<CharArray>): Boolean {
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                val rowItemSet = mutableSetOf<Char>()
                for (x in 0 until 9) {
                    if (board[i][x] == '.') continue
                    if (!rowItemSet.add(board[i][x])) {
                        return false
                    }
                }

                val columnItemSet = mutableSetOf<Char>()
                for (x in 0 until 9) {
                    if (board[x][j] == '.') continue
                    if (!columnItemSet.add(board[x][j])) {
                        return false
                    }
                }

                val boxX = 3 * (i / 3)
                val boxY = 3 * (j / 3)
                val boxItemSet = mutableSetOf<Char>()
                for (x in boxX until boxX + 3) {
                    for (y in boxY until boxY + 3) {
                        if (board[x][y] == '.') continue
                        if (!boxItemSet.add(board[x][y])) {
                            return false
                        }
                    }
                }
            }
        }

        return true
    }

    private fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()

        val result = mutableListOf<String>()

        var first = nums.first()
        var last = nums.first()
        for (i in 1 until nums.size) {
            if (nums[i] - 1 != last) {
                if (first == last) {
                    result.add("$first")
                } else {
                    result.add("$first->$last")
                }
                first = nums[i]
            }
            last = nums[i]
        }

        if (first == last) {
            result.add("$first")
        } else {
            result.add("$first->$last")
        }

        return result
    }

    private fun threeSum(nums: IntArray): List<List<Int>> {
        val result = mutableSetOf<List<Int>>()
        nums.sort()

        for (i in 0..nums.size - 2) {
            var left = i + 1
            var right = nums.size - 1

            while (left < right) {
                val sum = nums[i] + nums[left] + nums[right]
                if (sum == 0) {
                    result.add(listOf(nums[i], nums[left], nums[right]))
                    ++left
                    --right
                } else if (sum < 0) {
                    ++left
                } else {
                    --right
                }
            }
        }

        return result.toList()
    }

    private fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        var prevStart: ListNode? = null
        var start = head
        var end = start
        var nextEnd = end?.next
        var tempLeft = left - 1
        var tempRight = right - left

        while (tempLeft > 0 || tempRight > 0) {
            if (tempLeft > 0) {
                prevStart = start
                start = start?.next
                end = start
                nextEnd = end?.next
                --tempLeft
            } else {
                end = end?.next
                nextEnd = end?.next
                --tempRight
            }
        }

        var temp = start
        var next = temp?.next
        var prev = prevStart

        while (temp != nextEnd) {
            temp?.next = prev
            prev = temp
            temp = next
            next = next?.next
        }

        start?.next = nextEnd
        prevStart?.next = end

        if (start == head) return prev

        return head
    }

    private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        var head: ListNode? = null
        var last: ListNode? = null

        var temp1 = l1
        var temp2 = l2

        var carry = 0

        while (temp1 != null || temp2 != null) {
            val sum = (temp1?.data ?: 0) + (temp2?.data ?: 0) + carry
            carry = sum / 10
            val data = sum % 10
            if (head == null) {
                head = ListNode(data = data)
                last = head
            } else {
                last?.next = ListNode(data = data)
                last = last?.next
            }

            temp1 = temp1?.next
            temp2 = temp2?.next
        }

        if (carry != 0) {
            last?.next = ListNode(data = carry)
        }

        return head
    }

    private fun longestConsecutive(nums: IntArray): Int {
        var count = 0
        val numsSet = nums.toSet()

        for (n in nums) {
            if (!numsSet.contains(n - 1)) {
                var length = 0
                while (numsSet.contains(n + length)) {
                    ++length
                }
                count = max(count, length)
            }
        }

        return count
    }

    private fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val mapAnagrams = mutableMapOf<String, List<String>>()

        for (s in strs) {
            val sorted = s.toList().sorted().joinToString("")
            if (mapAnagrams.containsKey(sorted)) {
                mapAnagrams[sorted] = listOf(s) + mapAnagrams[sorted]!!
            } else {
                mapAnagrams[sorted] = listOf(s)
            }
        }

        return mapAnagrams.values.toList()
    }

    private fun wordPattern(pattern: String, s: String): Boolean {
        val sList = s.split(" ")
        if (pattern.length != sList.size) return false
        val charStringMap = mutableMapOf<Char, String>()

        var i = 0
        while (i < pattern.length) {
            if (charStringMap.containsKey(pattern[i]) || charStringMap.containsValue(sList[i])) {
                if (charStringMap[pattern[i]] != sList[i]) {
                    return false
                }
            } else {
                charStringMap[pattern[i]] = sList[i]
            }
            ++i
        }

        return true
    }

    private fun twoSum2(numbers: IntArray, target: Int): IntArray {
//        var start = 1
//        var end = numbers.size
//
//        while (true) {
//            val sum = numbers[start - 1] + numbers[end - 1]
//            if (sum == target) break
//            if (sum > target) --end
//            else ++start
//        }
//
//        return intArrayOf(start, end)

        val difference = mutableMapOf<Int, Int>()

        for (i in numbers.indices) {
            val num = numbers[i]
            if (difference.containsKey(target - num)) {
                return intArrayOf(difference[target - num]!! + 1, i + 1)
            } else {
                difference[num] = i
            }
        }

        return intArrayOf(-1, -1)
    }

    private fun mySqrt(x: Int): Int {
        var start = 1
        var end = x

        while (start <= end) {
            val mid = (start + end) / 2
            if (x / mid == mid) return mid
            else if (mid > x / mid) end = mid - 1
            else start = mid + 1
        }

        return end
    }

    private fun plusOne(digits: IntArray): IntArray {
        var carry = 1

        for (i in digits.size - 1 downTo 0) {
            digits[i] += carry
            carry = digits[i] / 10
            if (carry == 0) return digits
            digits[i] %= 10
        }

        return intArrayOf(carry, *digits)
    }

    // Too slow
    private fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null || q == null) {
            return p == null && q == null
        }

        val pqueue = arrayListOf(p)
        val qqueue = arrayListOf(q)

        while (pqueue.isNotEmpty()) {
            val ptop = pqueue.removeAt(0)
            val qtop = qqueue.removeAt(0)

            if (ptop.data != qtop.data) return false

            ptop.left?.let { pqueue.add(it) }
            qtop.left?.let { qqueue.add(it) }
            if (pqueue.size != qqueue.size) return false
            ptop.right?.let { pqueue.add(it) }
            qtop.right?.let { qqueue.add(it) }
            if (pqueue.size != qqueue.size) return false
        }

        return true
    }

    private fun maxDepth(root: TreeNode?): Int {
        if (root == null) return 0

        val leftDepth = maxDepth(root.left)
        val rightDepth = maxDepth(root.right)

        return if (leftDepth < rightDepth) {
            rightDepth + 1
        } else {
            leftDepth + 1
        }
    }

    private fun hasCycle(head: ListNode?): Boolean {
        var slow = head?.next
        var fast = head?.next?.next

        while (fast != null) {
            if (slow == fast) return true

            slow = slow?.next
            fast = fast.next?.next
        }

        return false
    }

    private fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val map = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]

            if (map.containsKey(num) && abs(i - map[num]!!) <= k) return true
            map[num] = i
        }

        return false
    }

    // Can be improved more
    private fun isHappy(n: Int): Boolean {
        var nstr = n.toString()
        val seenValues = mutableSetOf<String>()

        while (nstr != "1" && !seenValues.contains(nstr)) {
            var sum = 0
            for (c in nstr) {
                sum += c.toString().toInt() * c.toString().toInt()
            }

            seenValues.add(nstr)
            nstr = sum.toString()
        }

        return nstr == "1"
    }

    private fun twoSum(nums: IntArray, target: Int): IntArray {
        val difference = mutableMapOf<Int, Int>()

        for (i in nums.indices) {
            val num = nums[i]
            if (difference.containsKey(target - num)) {
                return intArrayOf(i, difference[target - num]!!)
            } else {
                difference[num] = i
            }
        }

        return intArrayOf(-1, -1)
    }

    private fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false
        return s.toList().sorted().joinToString("") == t.toList().sorted().joinToString("")
    }

    private fun isIsomorphic(s: String, t: String): Boolean {
        val characterMap = mutableMapOf<Char, Char>()

        var i = 0
        while (i < s.length) {
            if (characterMap.containsKey(s[i])) {
                if (characterMap[s[i]] != t[i]) {
                    return false
                }
            } else if (characterMap.values.contains(t[i])) {
                return false
            } else {
                characterMap[s[i]] = t[i]
            }
            ++i
        }

        return true
    }

    // Can be improved more
    private fun canConstruct(ransomNote: String, magazine: String): Boolean {
//        var tempMagazine = magazine
//        for (c in ransomNote) {
//            if (tempMagazine.contains(c)) {
//                tempMagazine = StringBuilder(tempMagazine).deleteCharAt(tempMagazine.indexOf(c)).toString()
//            } else {
//                return false
//            }
//        }
//
//        return true

        val charMap = mutableMapOf<Char, Int>()

        for (c in magazine) {
            charMap[c] = charMap.getOrDefault(c, 0) + 1
        }

        for (c in ransomNote) {
            if (!charMap.containsKey(c)) return false
            if (charMap.getOrDefault(c, 0) <= 0) return false
            charMap[c] = charMap.getOrDefault(c, 0) - 1
        }

        return true
    }

    private fun isSubsequence(s: String, t: String): Boolean {
        var index = 0

        for (c in t) {
            if (index == s.length) break

            if (c == s[index]) {
                ++index
            }
        }

        return index == s.length
    }

    private fun isPalindrome(s: String): Boolean {
//        val lowers = s.toLowerCase()
        val lowers = s.lowercase()

        var start = 0
        var end = lowers.length - 1

        while (start <= end) {
            if (lowers[start] == lowers[end]) {
                ++start
                --end
            } else {
                if (!lowers[start].isLetterOrDigit()) {
                    ++start
                } else if (!lowers[end].isLetterOrDigit()) {
                    --end
                } else if (lowers[start] != lowers[end]) {
                    return false
                }
            }
        }

        return true
    }

    private fun lengthOfLastWord(s: String): Int {
//        return s.trimEnd().split(" ").last().length
        var length = 0

        for (i in s.length - 1 downTo 0) {
            if (s[i] == ' ') {
                if (length != 0) break
                else continue
            } else ++length
        }

        return length
    }

    private fun maxProfit2(prices: IntArray): Int {
        var minVal = prices.first()
        var maxProfit = 0

        for (i in 1 until prices.size) {
            if (prices[i] < minVal) {
                minVal = prices[i]
            } else if (prices[i] - minVal > 0) {
                maxProfit += prices[i] - minVal
                minVal = prices[i]
            }
        }

        return maxProfit
    }

    private fun maxProfit(prices: IntArray): Int {
        var minVal = prices.first()
        var maxDiff = 0

        for (i in 1 until prices.size) {
            if (prices[i] < minVal) {
                minVal = prices[i]
            } else if (prices[i] - minVal > maxDiff) {
                maxDiff = prices[i] - minVal
            }
        }

        return maxDiff
    }

    private fun reverse(nums: IntArray, start: Int, end: Int) {
        var tempStart = start
        var tempEnd = end
        while (tempStart < tempEnd) {
            val temp = nums[tempStart]
            nums[tempStart++] = nums[tempEnd]
            nums[tempEnd--] = temp
        }
    }

    // https://betterprogramming.pub/3-ways-to-rotate-an-array-2a45b39f7bec
    private fun rotate(nums: IntArray, k: Int) {
        val tempK = k % nums.size
        reverse(nums, 0, nums.size - 1)
        reverse(nums, 0, tempK - 1)
        reverse(nums, tempK, nums.size - 1)
    }

    private fun majorityElement(nums: IntArray): Int {
        nums.sort()

        var element = nums[0]
        var tempElement = nums[0]
        var maxcount = 0
        var count = 0

        for (i in nums.indices) {
            if (nums[i] == tempElement) {
                ++count
            } else {
                tempElement = nums[i]
                count = 1
            }

            if (count > maxcount) {
                maxcount = count
                element = tempElement
            }
        }

        return element
    }

    private fun removeDuplicates2(nums: IntArray): Int {
        var counter = 1

        for (i in 2 until nums.size) {
            if (nums[i] != nums[counter] || nums[i] != nums[counter - 1]) {
                nums[++counter] = nums[i]
            }
        }

        return counter + 1
    }

    private fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var i = m - 1
        var j = n - 1
        var k = m + n - 1
        while (j >= 0) {
            nums1[k--] = if (i < 0 || nums1[i] < nums2[j]) nums2[j--] else nums1[i--]
        }
    }

    private fun searchInsert(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        var m: Int

        while (l <= r) {
            m = (l + r) / 2
            if (nums[m] == target) return m
            else if (nums[m] < target) {
                l = m + 1
            } else {
                r = m - 1
            }
        }

        m = (l + r) / 2
        return if (nums[m] == target) m
        else if (nums[m] < target) m + 1
        else {
            if (m - 1 < 0) 0
            else m - 1
        }
    }

    private fun searchRange(nums: IntArray, target: Int): IntArray {
        var l = 0
        var r = nums.size - 1

        while (l <= r) {
            if (nums[l] == target && nums[r] == target) {
                return intArrayOf(l, r)
            } else {
                if (nums[l] == target && nums[r] != target) {
                    --r
                } else if (nums[l] != target && nums[r] == target) {
                    ++l
                } else if (nums[l] != target && nums[r] != target) {
                    --r
                    ++l
                }
            }
        }

        return intArrayOf(-1, -1)
    }

    // Not passing - Time exceeds error
    private fun findSubstring(s: String, words: Array<String>): List<Int> {
        val indices = mutableListOf<Int>()
        val concatWords = words.toList().joinToString("").toCharArray().sorted().joinToString("")
        val maxLength = concatWords.length

        for (i in s.indices) {
            if (i + maxLength > s.length) break
            val partString = s.substring(i, i + maxLength)
            if (partString.toCharArray().sorted().joinToString("") == concatWords) {
                val partsSorted = partString.chunked(words.first().length).sorted()
                val wordsSorted = words.sorted()

                if (partsSorted == wordsSorted) {
                    indices.add(i)
                }
            }
        }

        return indices
    }

    private fun strStr(haystack: String, needle: String): Int {
        if (needle.length > haystack.length) return -1

        for (i in haystack.indices) {
            val endIndex = if (i + needle.length > haystack.length) haystack.length else i + needle.length
            if (haystack.substring(i, endIndex) == needle) {
                return i
            }
        }

        return -1
    }

    private fun removeElement(nums: IntArray, `val`: Int): Int {
        var counter = 0

        for (i in nums.indices) {
            if (nums[i] != `val`) {
                nums[counter++] = nums[i]
            }
        }

        return counter
    }

    private fun maxApples(target: Int, apples: List<Int>): Int {
        var maxcount = 0
        var tempcount = 0
        var j = 0

        apples.sorted()

        for (a in apples) {
            ++tempcount

            while (a - apples[j] > target) {
                ++j
                --tempcount
            }

            maxcount = max(maxcount, tempcount)
        }

        return maxcount
    }

    private fun removeDuplicates(nums: IntArray): Int {
        var counter = 0

        for (i in 1 until nums.size) {
            if (nums[counter] != nums[i]) {
                ++counter
                nums[counter] = nums[i]
            }
        }

        return counter + 1
    }

    private fun swapPairs(head: ListNode?): ListNode? {
        if (head == null) return null

        var newHead = head
        var t1 = head
        var t2 = head.next
        var prev: ListNode? = null

        var isFirst = true

        while (t1 != null && t2 != null) {
            t1.next = t2.next
            t2.next = t1
            prev = t1

            if (isFirst) {
                newHead = t2
                isFirst = false
            }

            t1 = t1.next
            t2 = t1?.next
            prev.next = t2
        }

        if (t1 != null) {
            prev?.next = t1
        }

        return newHead
    }

    private fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null && list2 == null) return null
        else if (list1 == null) return list2
        else if (list2 == null) return list1

        var head: ListNode? = null
        var last: ListNode? = null

        var temp1 = list1
        var temp2 = list2

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                if (head == null) {
                    head = ListNode(data = temp1.data)
                    last = head
                } else {
                    last?.next = ListNode(data = temp1.data)
                    last = last?.next
                }

                temp1 = temp1.next
            } else if (temp1.data == temp2.data) {
                if (head == null) {
                    head = ListNode(data = temp1.data)
                    last = head
                } else {
                    last?.next = ListNode(data = temp1.data)
                    last = last?.next
                }
                last?.next = ListNode(data = temp1.data)
                last = last?.next

                temp1 = temp1.next
                temp2 = temp2.next
            } else {
                if (head == null) {
                    head = ListNode(data = temp2.data)
                    last = head
                } else {
                    last?.next = ListNode(data = temp2.data)
                    last = last?.next
                }

                temp2 = temp2.next
            }
        }

        while (temp1 != null) {
            last?.next = ListNode(data = temp1.data)
            last = last?.next
            temp1 = temp1.next
        }

        while (temp2 != null) {
            last?.next = ListNode(data = temp2.data)
            last = last?.next
            temp2 = temp2.next
        }

        return head
    }

    private fun isValid(s: String): Boolean {
        val stacks = Stack<Char>()

        if (s.length % 2 != 0) return false

        for (c in s) {
            if (c == '(' || c == '{' || c == '[') {
                stacks.push(c)
            } else {
                if (stacks.isNotEmpty()) {
                    val last = stacks.pop()

                    if (last != '(' && c == ')') return false
                    else if (last != '[' && c == ']') return false
                    else if (last != '{' && c == '}') return false
                } else {
                    return false
                }
            }
        }

        return stacks.isEmpty()
    }

    private fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var totalSize = 0
        var temp = head

        while (temp != null) {
            ++totalSize
            temp = temp.next
        }

        temp = head
        var prev: ListNode? = null
        var index = totalSize - n

        while (index > 0) {
            --index
            prev = temp
            temp = temp?.next
        }

        if (prev == null && temp != null) {
            return if (temp.next == null) {
                null
            } else {
                temp.next
            }
        } else {
            prev?.next = temp?.next
        }

        return head
    }

    private fun longestCommonPrefix(strs: Array<String>): String {
        strs.sort()

        val first = strs.first()
        val last = strs.last()

        var index = 0
        while (index < first.length && index < last.length) {
            if (first[index] == last[index]) {
                ++index
            } else {
                break
            }
        }

        return first.substring(0, index)
    }

    private fun isMatch(s: String, p: String): Boolean {
        val regex = p.toRegex()
        return regex.matches(s)
    }

    private fun isPalindrome(x: Int): Boolean {
        val xStr = x.toString()

        for (i in xStr.indices) {
            if (xStr[i] != xStr[xStr.length - 1 - i]) {
                return false
            }
        }

        return true
    }

    private fun reverse(x: Int): Int {
        if (x == Int.MIN_VALUE) return 0
        if (abs(x) < 10) return x
        var s = abs(x).toString().reversed()
        while (s[0].toString() == "0")
            s = s.substringAfter("0")
        if (s.toLong() > Int.MAX_VALUE) return 0
        if (x < 0) return -s.toInt()
        return s.toInt()
    }

    private fun longestPalindrome(s: String): String {
        if (s.length == 1) return s

        var plaindromeString = ""
        for (i in s.indices) {
            for (j in i until s.length) {
                val sub = s.substring(i..j)
                if (isPalindrom(sub)) {
                    if (plaindromeString.length < sub.length) {
                        plaindromeString = sub
                    }
                }
            }
        }
        return plaindromeString
    }

    private fun isPalindrom(s: String): Boolean {
        for (i in 0..s.length / 2) {
            if (s[i] != s[s.length - 1 - i]) {
                return false
            }
        }

        return true
    }

    private fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val fullArray = mutableListOf<Int>()
        var nums1Index = 0
        var nums2Index = 0

        while (nums1Index < nums1.size || nums2Index < nums2.size) {
            if (nums1Index < nums1.size && nums2Index < nums2.size) {
                if (nums1[nums1Index] < nums2[nums2Index]) {
                    fullArray.add(nums1[nums1Index])
                    ++nums1Index
                } else {
                    fullArray.add(nums2[nums2Index])
                    ++nums2Index
                }
            } else {
                if (nums1Index < nums1.size) {
                    fullArray.add(nums1[nums1Index])
                    ++nums1Index
                }
                if (nums2Index < nums2.size) {
                    fullArray.add(nums2[nums2Index])
                    ++nums2Index
                }
            }
        }

        return if (fullArray.size % 2 != 0) {
            val medianIndex = fullArray.size / 2
            fullArray[medianIndex].toDouble()
        } else {
            val medianIndex = fullArray.size / 2
            (fullArray[medianIndex].toDouble() + fullArray[medianIndex - 1].toDouble()) / 2
        }
    }

    private fun lengthOfLongestSubstring(s: String): Int {
        var maxLength = 0
        val tempMap = mutableListOf<Char>()

        for (c in s) {
            if (tempMap.contains(c)) {
                if (maxLength < tempMap.size) {
                    maxLength = tempMap.size
                }

                val index = tempMap.indexOf(c)
                tempMap.subList(0, index + 1).clear()
            }
            tempMap.add(c)
        }

        if (tempMap.isNotEmpty() && maxLength < tempMap.size) {
            maxLength = tempMap.size
        }

        return maxLength
    }
}