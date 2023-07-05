package leet_code

import data_structures.linked_lists.ListNode
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
            val endIndex = if (i + (needle.length) > haystack.length) haystack.length else i + (needle.length)
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