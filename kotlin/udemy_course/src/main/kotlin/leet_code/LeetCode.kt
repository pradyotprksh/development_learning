package leet_code

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
        for (i in 0 .. s.length / 2) {
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