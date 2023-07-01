package leet_code

import data_structures.linked_lists.Node
import java.util.Stack
import kotlin.math.abs

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
                Node(
                    data = 1,
                    next = Node(data = 2, next = Node(3, next = Node(4, next = Node(data = 5))))
                ), 2
            )
        )
        println(removeNthFromEnd(Node(data = 1, next = Node(data = 2)), 1))
        println(removeNthFromEnd(Node(data = 1), 1))
        println(removeNthFromEnd(Node(data = 1, next = Node(data = 2)), 2))

        println(isValid("()"))
        println(isValid("()[]{}"))
        println(isValid("(]"))
        println(isValid("("))
        println(isValid("){"))

        println(
            mergeTwoLists(
                Node(data = 1, next = Node(data = 2, Node(data = 4))),
                Node(data = 1, next = Node(data = 3, Node(data = 4))),
            )
        )
        println(mergeTwoLists(null, null,))
        println(
            mergeTwoLists(
                null,
                Node(data = 0),
            )
        )
    }

    private fun mergeTwoLists(list1: Node?, list2: Node?): Node? {
        if (list1 == null && list2 == null) return null
        else if (list1 == null) return list2
        else if (list2 == null) return list1

        var head: Node? = null
        var last: Node? = null

        var temp1 = list1
        var temp2 = list2

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                if (head == null) {
                    head = Node(data = temp1.data)
                    last = head
                } else {
                    last?.next = Node(data = temp1.data)
                    last = last?.next
                }

                temp1 = temp1.next
            } else if (temp1.data == temp2.data) {
                if (head == null) {
                    head = Node(data = temp1.data)
                    last = head
                } else {
                    last?.next = Node(data = temp1.data)
                    last = last?.next
                }
                last?.next = Node(data = temp1.data)
                last = last?.next

                temp1 = temp1.next
                temp2 = temp2.next
            } else {
                if (head == null) {
                    head = Node(data = temp2.data)
                    last = head
                } else {
                    last?.next = Node(data = temp2.data)
                    last = last?.next
                }

                temp2 = temp2.next
            }
        }

        while (temp1 != null) {
            last?.next = Node(data = temp1.data)
            last = last?.next
            temp1 = temp1.next
        }

        while (temp2 != null) {
            last?.next = Node(data = temp2.data)
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

    private fun removeNthFromEnd(head: Node?, n: Int): Node? {
        var totalSize = 0
        var temp = head

        while (temp != null) {
            ++totalSize
            temp = temp.next
        }

        temp = head
        var prev: Node? = null
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