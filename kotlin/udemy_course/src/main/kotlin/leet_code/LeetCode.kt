package leet_code

import data_structures.linked_lists.ListNode
import data_structures.trees.TreeNode
import java.lang.Exception
import java.util.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow

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

        println(maxVowels("abciiidef", 3))
        println(maxVowels("aeiou", 2))
        println(maxVowels("leetcode", 3))

        println(findDifference(intArrayOf(1, 2, 3), intArrayOf(2, 4, 6)))
        println(findDifference(intArrayOf(1, 2, 3, 3), intArrayOf(1, 1, 2, 2)))

        println(uniqueOccurrences(intArrayOf(1, 2, 2, 1, 1, 3)))
        println(uniqueOccurrences(intArrayOf(1, 2)))
        println(uniqueOccurrences(intArrayOf(-3, 0, 1, -3, 1, 1, 1, -3, 10, 0)))

        println(equalPairs(arrayOf(intArrayOf(3, 2, 1), intArrayOf(1, 7, 6), intArrayOf(2, 7, 7))))
        println(equalPairs(arrayOf(intArrayOf(3, 1, 2, 2), intArrayOf(1, 4, 4, 5), intArrayOf(2, 4, 2, 2), intArrayOf(2, 4, 2, 2))))

        println(removeStars("leet**cod*e"))
        println(removeStars("erase*****"))

        println(decodeString("3[a]2[bc]"))
        println(decodeString("3[a2[c]]"))
        println(decodeString("2[abc]3[cd]ef"))

        println(pivotIndex(intArrayOf(1, 7, 3, 6, 5, 6)))
        println(pivotIndex(intArrayOf(1, 2, 3)))
        println(pivotIndex(intArrayOf(2, 1, -1)))

        println(deleteMiddle(ListNode(1, ListNode(3, ListNode(4, ListNode(7, ListNode(1, ListNode(2, ListNode(6)))))))))
        println(deleteMiddle(ListNode(1, ListNode(3, ListNode(4, ListNode(7))))))
        println(deleteMiddle(ListNode(1, ListNode(3))))

        println(pairSum(ListNode(5, ListNode(4, ListNode(2, ListNode(1))))))
        println(pairSum(ListNode(4, ListNode(2, ListNode(2, ListNode(3))))))
        println(pairSum(ListNode(4, ListNode(100000))))

        println(
                leafSimilar(
                        TreeNode(data = 3, left = TreeNode(data = 5, left = TreeNode(data = 6), right = TreeNode(data = 2, left = TreeNode(data = 7), right = TreeNode(data = 4))), right = TreeNode(data = 1, left = TreeNode(data = 9), right = TreeNode(data = 8))),
                        TreeNode(data = 3, left = TreeNode(data = 5, left = TreeNode(data = 6), right = TreeNode(data = 7)), right = TreeNode(data = 1, left = TreeNode(data = 4), right = TreeNode(data = 2, left = TreeNode(data = 9), right = TreeNode(data = 8))))
                )
        )

        println(
                goodNodes(TreeNode(data = 3, left = TreeNode(data = 1, left = TreeNode(data = 3)), right = TreeNode(data = 4, left = TreeNode(data = 1), right = TreeNode(data = 5))))
        )
        println(
                goodNodes(TreeNode(data = 3, left = TreeNode(data = 3, left = TreeNode(data = 4), right = TreeNode(data = 2))))
        )

        println(guessNumber(10))
        println(guessNumber(20))

        println(dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)).toList())
        println(dailyTemperatures(intArrayOf(89, 62, 70, 58, 47, 47, 46, 76, 100, 70)).toList())

        val stock = StockSpanner()
        println(stock.next(100))
        println(stock.next(80))
        println(stock.next(60))
        println(stock.next(70))
        println(stock.next(60))
        println(stock.next(75))

        println(combinationSum3(3, 7))
        println(combinationSum3(3, 9))
        println(combinationSum3(4, 1))

        val recentCounter = RecentCounter()
        println(recentCounter.ping(1))
        println(recentCounter.ping(100))
        println(recentCounter.ping(3001))
        println(recentCounter.ping(3002))

        println(countBits(2).toList())
        println(countBits(5).toList())

        println(singleNumber(intArrayOf(2, 2, 1)))
        println(singleNumber(intArrayOf(4, 1, 2, 1, 2)))
        println(singleNumber(intArrayOf(1)))

        println(addBinary("11", "1"))
        println(addBinary("1010", "1011"))

        println(deleteDuplicates(ListNode(1, ListNode(1, ListNode(2)))))

        println(generate(5))
        println(generate(1))

        println(getRow(1))
        println(getRow(3))

        val intersectionNode = ListNode(2, ListNode(4))
        println(getIntersectionNode(ListNode(1, ListNode(9, ListNode(1, intersectionNode))), ListNode(3, intersectionNode)))
        println(getIntersectionNode(ListNode(1, ListNode(9, ListNode(1))), ListNode(3)))

        println(convertToTitle(1))
        println(convertToTitle(28))
        println(convertToTitle(701))

        println(titleToNumber("A"))
        println(titleToNumber("AB"))
        println(titleToNumber("ZY"))

        println(removeElements(ListNode(7, ListNode(7, ListNode(7, ListNode(7)))), 7))
        println(removeElements(null, 7))
        println(removeElements(ListNode(1, ListNode(2, ListNode(6, ListNode(3, ListNode(4, ListNode(5, ListNode(6))))))), 6))

        println(countNodes(TreeNode(data = 1, left = TreeNode(data = 2, left = TreeNode(data = 4), right = TreeNode(data = 5)), right = TreeNode(data = 3, left = TreeNode(data = 6)))))

        val myStack = MyStack()
        myStack.push(1)
        myStack.push(2)
        println(myStack.top())
        println(myStack.pop())
        println(myStack.empty())

        println(isPowerOfTwo(1))
        println(isPowerOfTwo(2))
        println(isPowerOfTwo(6))
        println(isPowerOfTwo(256))

        val myQueue = MyQueue()
        myQueue.push(1)
        myQueue.push(2)
        println(myQueue.peek())
        println(myQueue.pop())
        println(myQueue.empty())

        println(isPalindrome(ListNode(1, ListNode(2, ListNode(2, ListNode(1))))))
        println(isPalindrome(ListNode(1, ListNode(2))))
        println(isPalindrome(ListNode(1, ListNode(2, ListNode(1)))))

        println(binaryTreePaths(TreeNode(data = 1, left = TreeNode(data = 2, right = TreeNode(data = 5)), right = TreeNode(data = 3))))
        println(binaryTreePaths(TreeNode(data = 1)))

        println(addDigits(38))
        println(addDigits(0))

        println(missingNumber(intArrayOf(3,0,1)))
        println(missingNumber(intArrayOf(0,1)))
        println(missingNumber(intArrayOf(9,6,4,2,3,5,7,0,1)))

        println(canWinNim(4))
        println(canWinNim(2))
        println(canWinNim(1))
        println(canWinNim(8))

        println(isPowerOfThree(3))
        println(isPowerOfThree(27))
        println(isPowerOfThree(0))
        println(isPowerOfThree(-1))

        println(intersection(intArrayOf(1,2,2,1), intArrayOf(2,2)).toList())

        println(firstUniqChar("leetcode"))
        println(firstUniqChar("loveleetcode"))
        println(firstUniqChar("aabb"))

        println(findTheDifference("ymbgaraibkfmvocpizdydugvalagaivdbfsfbepeyccqfepzvtpyxtbadkhmwmoswrcxnargtlswqemafandgkmydtimuzvjwxvlfwlhvkrgcsithaqlcvrihrwqkpjdhgfgreqoxzfvhjzojhghfwbvpfzectwwhexthbsndovxejsntmjihchaotbgcysfdaojkjldprwyrnischrgmtvjcorypvopfmegizfkvudubnejzfqffvgdoxohuinkyygbdzmshvyqyhsozwvlhevfepdvafgkqpkmcsikfyxczcovrmwqxxbnhfzcjjcpgzjjfateajnnvlbwhyppdleahgaypxidkpwmfqwqyofwdqgxhjaxvyrzupfwesmxbjszolgwqvfiozofncbohduqgiswuiyddmwlwubetyaummenkdfptjczxemryuotrrymrfdxtrebpbjtpnuhsbnovhectpjhfhahbqrfbyxggobsweefcwxpqsspyssrmdhuelkkvyjxswjwofngpwfxvknkjviiavorwyfzlnktmfwxkvwkrwdcxjfzikdyswsuxegmhtnxjraqrdchaauazfhtklxsksbhwgjphgbasfnlwqwukprgvihntsyymdrfovaszjywuqygpvjtvlsvvqbvzsmgweiayhlubnbsitvfxawhfmfiatxvqrcwjshvovxknnxnyyfexqycrlyksderlqarqhkxyaqwlwoqcribumrqjtelhwdvaiysgjlvksrfvjlcaiwrirtkkxbwgicyhvakxgdjwnwmubkiazdjkfmotglclqndqjxethoutvjchjbkoasnnfbgrnycucfpeovruguzumgmgddqwjgdvaujhyqsqtoexmnfuluaqbxoofvotvfoiexbnprrxptchmlctzgqtkivsilwgwgvpidpvasurraqfkcmxhdapjrlrnkbklwkrvoaziznlpor", "qhxepbshlrhoecdaodgpousbzfcqjxulatciapuftffahhlmxbufgjuxstfjvljybfxnenlacmjqoymvamphpxnolwijwcecgwbcjhgdybfffwoygikvoecdggplfohemfypxfsvdrseyhmvkoovxhdvoavsqqbrsqrkqhbtmgwaurgisloqjixfwfvwtszcxwktkwesaxsmhsvlitegrlzkvfqoiiwxbzskzoewbkxtphapavbyvhzvgrrfriddnsrftfowhdanvhjvurhljmpxvpddxmzfgwwpkjrfgqptrmumoemhfpojnxzwlrxkcafvbhlwrapubhveattfifsmiounhqusvhywnxhwrgamgnesxmzliyzisqrwvkiyderyotxhwspqrrkeczjysfujvovsfcfouykcqyjoobfdgnlswfzjmyucaxuaslzwfnetekymrwbvponiaojdqnbmboldvvitamntwnyaeppjaohwkrisrlrgwcjqqgxeqerjrbapfzurcwxhcwzugcgnirkkrxdthtbmdqgvqxilllrsbwjhwqszrjtzyetwubdrlyakzxcveufvhqugyawvkivwonvmrgnchkzdysngqdibhkyboyftxcvvjoggecjsajbuqkjjxfvynrjsnvtfvgpgveycxidhhfauvjovmnbqgoxsafknluyimkczykwdgvqwlvvgdmufxdypwnajkncoynqticfetcdafvtqszuwfmrdggifokwmkgzuxnhncmnsstffqpqbplypapctctfhqpihavligbrutxmmygiyaklqtakdidvnvrjfteazeqmbgklrgrorudayokxptswwkcircwuhcavhdparjfkjypkyxhbgwxbkvpvrtzjaetahmxevmkhdfyidhrdeejapfbafwmdqjqszwnwzgclitdhlnkaiyldwkwwzvhyorgbysyjbxsspnjdewjxbhpsvj"))
        println(findTheDifference("abcd", "abcde"))

        println(readBinaryWatch(1))
        println(readBinaryWatch(2))

        println(sumOfLeftLeaves(TreeNode(data = 3, left = TreeNode(data = 9), right = TreeNode(data = 20, left = TreeNode(data = 15), right = TreeNode(data = 7)))))

        println(fizzBuzz(3))
        println(fizzBuzz(5))
        println(fizzBuzz(15))

        println(addStrings("11", "123"))
        println(addStrings("456", "77"))
        println(addStrings("0", "0"))

        println(countSegments("Hello, my name is John"))
        println(countSegments("Hello"))

        println(arrangeCoins(5))
        println(arrangeCoins(8))

        println(findDisappearedNumbers(intArrayOf(4,3,2,7,8,2,3,1)))
        println(findDisappearedNumbers(intArrayOf(1,1)))

        println(findContentChildren(intArrayOf(1,2,3), intArrayOf(1,1)))
        println(findContentChildren(intArrayOf(1,2), intArrayOf(1,2,3)))
        println(findContentChildren(intArrayOf(10,9,8,7), intArrayOf(5,6,7,8)))

        println(findRestaurant(arrayOf("Shogun","Tapioca Express","Burger King","KFC"), arrayOf("Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun")).toList())
        println(findRestaurant(arrayOf("Shogun","Tapioca Express","Burger King","KFC"), arrayOf("KFC","Shogun","Burger King")).toList())
        println(findRestaurant(arrayOf("happy","sad","good"), arrayOf("sad","happy","good")).toList())

        println(repeatedSubstringPattern("aba"))

        println(hammingDistance(1,4))
        println(hammingDistance(3,1))
        println(hammingDistance(93,73))

        println(islandPerimeter(arrayOf(intArrayOf(0,1,0,0), intArrayOf(1,1,1,0), intArrayOf(0,1,0,0), intArrayOf(1,1,0,0))))

        println(licenseKeyFormatting("5F3Z-2e-9-w", 4))
        println(licenseKeyFormatting("2-5g-3-J", 2))

        println(findMaxConsecutiveOnes(intArrayOf(1,1,0,1,1,1)))
        println(findMaxConsecutiveOnes(intArrayOf(1,0,1,1,0,1)))

        println(findPoisonedDuration(intArrayOf(1,4), 2))
        println(findPoisonedDuration(intArrayOf(1,2), 2))

        println(findWords(arrayOf("Hello","Alaska","Dad","Peace")).toList())

        println(findRelativeRanks(intArrayOf(5,4,3,2,1)).toList())
        println(findRelativeRanks(intArrayOf(10,3,8,9,4)).toList())

        println(checkPerfectNumber(28))
        println(checkPerfectNumber(7))

        println(convert("PAYPALISHIRING", 3))
        println(convert("PAYPALISHIRING", 4))
        println(convert("A", 1))
        println(convert("ABC", 2))

        println(myAtoi("42"))
        println(myAtoi("   -42"))
        println(myAtoi("4193 with words"))
        println(myAtoi("-91283472332"))
    }

    private fun myAtoi(s: String): Int {
        val ans = StringBuilder()

        var found = false
        var charFound = false
        var digitsFound = false

        for (c in s) {
            if (c == '-' || c == '+' || c.code in 48 .. 57) {
                if (charFound) {
                    return 0
                }
                if (ans.isNotEmpty()) {
                    if (ans.last() == '-' || ans.last() == '+') {
                        if (c == '-' || c == '+') {
                            return 0
                        }
                    }
                    if (c == '-' || c == '+') {
                        break
                    }
                }
                if (c.code in 48 .. 57) {
                    digitsFound = true
                }

                found = true
                ans.append(c)
                continue
            }
            if (c != ' ') {
                charFound = true
            }
            if (found) {
                break
            }
        }

        if (ans.isEmpty()) return 0
        if (!digitsFound) return 0

        return try {
            ans.toString().toInt()
        } catch (_: Exception) {
            if (ans.first() == '-') {
                Int.MIN_VALUE
            } else {
                Int.MAX_VALUE
            }
        }
    }

    private fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s

        val ans = StringBuilder()

        for (r in 0 until numRows) {
            val increment = (numRows - 1) * 2
            for (i in r until s.length step increment) {
                ans.append(s[i])
                if (r != 0 && r != numRows - 1) {
                    val index = i + increment - 2 * r
                    if (index < s.length) {
                        ans.append(s[index])
                    }
                }
            }
        }

        return ans.toString()
    }

    private fun checkPerfectNumber(num: Int): Boolean {
        val divisors = mutableListOf<Int>()
        for (i in 1 until num) {
            if (num % i == 0) {
                divisors.add(i)
            }
        }

        return divisors.sum() == num
    }

    private fun findRelativeRanks(score: IntArray): Array<String> {
        val topThreeScores = score.toMutableList().sortedDescending()

        val ans = mutableListOf<String>()
        for (s in score) {
            when (val index = topThreeScores.indexOf(s)) {
                0 -> ans.add("Gold Medal")
                1 -> ans.add("Silver Medal")
                2 -> ans.add("Bronze Medal")
                else -> ans.add((index + 1).toString())
            }
        }

        return ans.toTypedArray()
    }

    private fun findMode(root: TreeNode?): IntArray {
        val nodesMap = mutableMapOf<Int, Int>()
        val queue = mutableListOf<TreeNode>()
        root?.let { queue.add(it) }
        var max = 0

        while (queue.isNotEmpty()) {
            val node = queue.removeAt(0)

            nodesMap[node.data] = nodesMap.getOrDefault(node.data, 0) + 1
            max = maxOf(nodesMap.getOrDefault(node.data, 0), max)

            node.left?.let { queue.add(it) }
            node.right?.let { queue.add(it) }
        }

        return nodesMap.filter { it.value == max }.keys.toIntArray()
    }

    private fun findWords(words: Array<String>): Array<String> {
        val firstRow = "qwertyuiop".toSet()
        val secondRow = "asdfghjkl".toSet()
        val thirdRow = "zxcvbnm".toSet()

        val ans = mutableListOf<String>()

        for (w in words) {
            val word = w.lowercase().toSet()

            if (firstRow.intersect(word).size == word.size || secondRow.intersect(word).size == word.size || thirdRow.intersect(word).size == word.size) {
                ans.add(w)
            }
        }

        return ans.toTypedArray()
    }

    private fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
        val seconds = mutableListOf<Int>()

        for (i in timeSeries.indices) {
            for (j in timeSeries[i] .. (timeSeries[i] + (duration - 1))) {
                if (i + 1 in timeSeries.indices) {
                    if (timeSeries[i + 1] == j) {
                        break
                    }
                }
                seconds.add(j)
            }
        }

        return seconds.size
    }

    private fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var ans = 0
        var temp = 0

        for (n in nums) {
            if (n == 1) {
                ++temp
                ans = maxOf(ans, temp)
            } else {
                temp = 0
            }
        }

        return ans
    }

    private fun licenseKeyFormatting(s: String, k: Int): String {
        val rS = s.reversed()

        val queue = mutableListOf<Char>()
        val ans = StringBuilder()

        for (c in rS) {
            if (c != '-') {
                queue.add(c.uppercaseChar())
            }

            if (queue.size == k) {
                ans.append('-')
                while (queue.isNotEmpty()) {
                    ans.append(queue.removeAt(0))
                }
            }
        }

        if (queue.isNotEmpty()) {
            ans.append('-')
            while (queue.isNotEmpty()) {
                ans.append(queue.removeAt(0))
            }
        }

        if (ans.isEmpty()) return ""

        val lastAns = ans.reverse().deleteCharAt(ans.length - 1)
        return lastAns.toString()
    }

    private fun islandPerimeter(grid: Array<IntArray>): Int {
        var ans = 0

        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (grid[i][j] == 1) {
                    ans += 4

                    print("$i,$j $ans")

                    if (i - 1 in grid.indices) {
                        if (grid[i - 1][j] == 1) {
                            ans -= 2
                            print(" | ${i - 1},$j $ans")
                        }
                    }

                    if (j - 1 in grid[i].indices) {
                        if (grid[i][j - 1] == 1) {
                            ans -= 2
                            print(" | $i,${j - 1} $ans")
                        }
                    }
                }
                println()
            }
        }

        return ans
    }

    private fun hammingDistance(x: Int, y: Int): Int {
        val xBits = Integer.toBinaryString(x)
        val yBits = Integer.toBinaryString(y)

        var xCount = xBits.length - 1
        var yCount = yBits.length - 1
        var ans = 0

        while (xCount >= 0 || yCount >= 0) {
            if (xCount >= 0 && yCount >= 0) {
                if (xBits[xCount] != yBits[yCount]) {
                    ++ans
                }
                --xCount
                --yCount
            } else if (xCount >= 0) {
                if (xBits[xCount] == '1') {
                    ++ans
                }
                --xCount
            } else {
                if (yBits[yCount] == '1') {
                    ++ans
                }
                --yCount
            }
        }

        return ans
    }

    private fun repeatedSubstringPattern(s: String): Boolean {
        val t = (s + s).substring(1 until (2 * s.length) - 1)
        return t.contains(s)
    }

    private fun findRestaurant(list1: Array<String>, list2: Array<String>): Array<String> {
        val mapRestaurant = mutableMapOf<String, MutableList<Int>>()
        var min = Int.MAX_VALUE
        // The 2 for loops can be combined into 1, but the complexity remains the same
        for (r in list1.indices) {
            val res = mapRestaurant.getOrDefault(list1[r], mutableListOf())
            res.add(r)
            mapRestaurant[list1[r]] = res
        }
        for (r in list2.indices) {
            if (mapRestaurant.containsKey(list2[r])) {
                mapRestaurant.getOrDefault(list2[r], mutableListOf()).add(r)
                // calculating the min value while mapping only
                val sum = mapRestaurant.getOrDefault(list2[r], mutableListOf()).sum()
                if (sum < min) {
                    min = sum
                }
            } else {
                val res = mapRestaurant.getOrDefault(list2[r], mutableListOf())
                res.add(r)
                mapRestaurant[list2[r]] = res
            }
        }

        // remove the unique elements from the map, if value size is one then it only occurred in one of the list
        // because both array contains unique elements, but one or more than is same in both
        val endMap = mapRestaurant.filter { it.value.size > 1 }
        val ans = mutableListOf<String>()
        for (res in endMap.entries) {
            if (res.value.sum() == min) {
                ans.add(res.key)
            }
        }
        return ans.toTypedArray()
    }

    private fun findContentChildren(g: IntArray, s: IntArray): Int {
        g.sort()
        s.sort()

        var gI = 0
        var sI = 0

        var count = 0
        while (gI < g.size && sI < s.size) {
            val greed = g[gI]
            val size = s[sI]
            if (size >= greed) {
                ++count
                ++gI
            }
            ++sI
        }

        return count
    }

    private fun findDisappearedNumbers(nums: IntArray): List<Int> {
        for (i in nums.indices) {
            val index = abs(nums[i]) - 1
            if (nums[index] > 0) {
                nums[index] = nums[index] * -1
            }
        }

        val output = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] > 0) {
                output.add(i + 1)
            }
        }
        return output
    }

    private fun arrangeCoins(n: Int): Int {
        val rows = mutableListOf<Int>()
        var tempN = n
        var row = 1

        while (true) {
            if (tempN >= row) {
                rows.add(row)
                tempN -= row
                ++row
            } else {
                break
            }
        }

        return rows.count()
    }

    private fun countSegments(s: String): Int {
        return s.split(" ").filter { it.isNotEmpty() }.size
    }

    private fun addStrings(num1: String, num2: String): String {
        val ans = StringBuilder()

        var a = num1.length - 1
        var b = num2.length - 1
        var carry = 0

        while (a >= 0 && b >= 0) {
            val first = num1[a--] - '0'
            val second = num2[b--] - '0'
            val sum = first + second + carry
            carry = sum / 10
            val data = sum % 10
            ans.append(data)
        }

        while (a >= 0) {
            val first = num1[a--] - '0'
            val sum = first + carry
            carry = sum / 10
            val data = sum % 10
            ans.append(data)
        }

        while (b >= 0) {
            val second = num2[b--] - '0'
            val sum = second + carry
            carry = sum / 10
            val data = sum % 10
            ans.append(data)
        }

        if (carry != 0) {
            ans.append(carry)
        }

        return ans.reverse().toString()
    }

    private fun fizzBuzz(n: Int): List<String> {
        val output = mutableListOf<String>()

        for (i in 0 until n) {
            val index = i + 1
            if (index % 3 == 0 && index % 5 == 0) {
                output.add("FizzBuzz")
            } else if (index % 3 == 0) {
                output.add("Fizz")
            } else if (index % 5 == 0) {
                output.add("Buzz")
            } else {
                output.add("$index")
            }
        }

        return output
    }

    private fun sumOfLeftLeaves(root: TreeNode?): Int {
        var sum = 0

        val queue = mutableListOf<TreeNode>()
        if (root != null) queue.add(root)

        while (queue.isNotEmpty()) {
            val node = queue.removeAt(0)
            node.left?.let { left ->
                if (left.left == null && left.right == null) {
                    sum += left.data
                } else {
                    queue.add(left)
                }
            }
            node.right?.let { queue.add(it) }
        }

        return sum
    }

    private fun readBinaryWatch(turnedOn: Int): List<String> {
        val output = mutableListOf<String>()

        for (hr in 0 until 12) {
            for (min in 0 until 60) {
                if (Integer.bitCount(hr) + Integer.bitCount(min) == turnedOn) {
                    if (min > 9) {
                        output.add("$hr:$min")
                    } else {
                        output.add("$hr:0$min")
                    }
                }
            }
        }

        return output
    }

    private fun findTheDifference(s: String, t: String): Char {
        val sMap = mutableMapOf<Char, Int>()
        for (c in s) {
            sMap[c] = sMap.getOrDefault(c, 0) + 1
        }

        val tMap = mutableMapOf<Char, Int>()
        for (c in t) {
            tMap[c] = tMap.getOrDefault(c, 0) + 1
        }

        for (entry in tMap.entries) {
            if (sMap.containsKey(entry.key)) {
                val sCount = sMap[entry.key]!!
                if (entry.value > sCount) return entry.key
            } else {
                return entry.key
            }
        }

        return t.first()
    }

    private fun firstUniqChar(s: String): Int {
        val charMap = mutableMapOf<Char, Int>()
        s.forEach { charMap[it] = charMap.getOrDefault(it, 0) + 1 }

        for (i in s.indices) {
            if (charMap[s[i]] == 1) {
                return i
            }
        }

        return -1
    }

    private fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val nums1Map = mutableSetOf<Int>()
        for (n in nums1) {
            nums1Map.add(n)
        }

        val ans = mutableSetOf<Int>()
        for (n in nums2) {
            if (nums1Map.contains(n)) {
                ans.add(n)
            }
        }

        return ans.toIntArray()
    }

    private fun isPowerOfThree(n: Int): Boolean {
        var tempN = n
        while (tempN > 0 && tempN % 3 == 0) {
            tempN /= 3
        }

        return tempN == 1
    }

    private fun canWinNim(n: Int, dpMap: MutableMap<Int, Boolean> = mutableMapOf()): Boolean {
        if (n <= 3) return true
        val result = mutableListOf<Boolean>()
        for (i in 1 .. 3) {
            if (!dpMap.containsKey(n - i)) {
                dpMap[n - i] = canWinNim(n - i, dpMap)
            }
            result.add(dpMap[n - i]!!)
        }
        return result.none { it }
    }

    private fun missingNumber(nums: IntArray): Int {
        val sum = nums.sum()
        val actualSum = ((nums.size + 1) * (nums.size)) / 2

        return actualSum - sum
    }

    private fun addDigits(num: Int): Int {
        return if (num == 0) 0
        else if (num % 9 == 0) 9
        else num % 9

        /*var ans = num.toString().toCharArray().map { it.toString().toInt() }

        while (ans.size != 1) {
            val sum = ans.sum()
            ans = sum .toString().toCharArray().map { it.toString().toInt() }
        }

        return ans.first()*/
    }

    private fun binaryTreePaths(root: TreeNode?): List<String> {
        return generatePath(root, null, mutableListOf(), "")
    }

    private fun generatePath(node: TreeNode?, prevNode: TreeNode?, ans: MutableList<String>, path: String): List<String> {
        if (node == null) {
            if (prevNode?.left == null && prevNode?.right == null) {
                val foundPath = path.removeSuffix("->")
                if (!ans.contains(foundPath)) {
                    ans.add(foundPath)
                }
            }
            return ans
        }
        generatePath(node.left, node, ans, path + "${node.data}->")
        generatePath(node.right, node, ans, path + "${node.data}->")
        return ans
    }

    private fun isPalindrome(head: ListNode?): Boolean {
        var prev: ListNode? = null
        var midSlow = head
        var fast = head
        while (fast != null) {
            prev = midSlow
            midSlow = midSlow?.next
            fast = fast.next?.next
        }

        prev?.next = reverseLLFromMiddle(midSlow)
        midSlow = prev?.next
        var temp = head

        while (temp != null && midSlow != null) {
            if (temp.data != midSlow.data) {
                return false
            }
            midSlow = midSlow.next
            temp = temp.next
        }

        return true
    }

    private fun reverseLLFromMiddle(node: ListNode?): ListNode? {
        var prev: ListNode? = null
        var mid = node
        var next = node?.next

        while (mid != null) {
            mid.next = prev
            prev = mid
            mid = next
            next = next?.next
        }

        return prev
    }

    private class MyQueue {
        private val stack = mutableListOf<Int>()

        fun push(x: Int) {
            stack.add(x)
        }

        fun pop(): Int {
            val tempStack = mutableListOf<Int>()
            while (size() != 1) {
                tempStack.add(stack.removeAt(size() - 1))
            }
            val item = stack.removeAt(size() - 1)
            while (tempStack.isNotEmpty()) {
                stack.add(tempStack.removeAt(tempStack.size - 1))
            }
            return item
        }

        fun peek(): Int {
            val tempStack = mutableListOf<Int>()
            while (size() != 1) {
                tempStack.add(stack.removeAt(size() - 1))
            }
            val item = stack.removeAt(size() - 1)
            tempStack.add(item)
            while (tempStack.isNotEmpty()) {
                stack.add(tempStack.removeAt(tempStack.size - 1))
            }
            return item
        }

        fun empty(): Boolean {
            return stack.isEmpty()
        }

        fun size(): Int {
            return stack.size
        }

    }

    // https://www.baeldung.com/cs/verify-if-power-of-two
    private fun isPowerOfTwo(n: Int): Boolean {
        var tempN = n
        while (tempN > 0 && tempN % 2 == 0) {
            tempN /= 2
        }

        return tempN == 1
    }

    private class MyStack {
        private val queue = mutableListOf<Int>()

        fun push(x: Int) {
            queue.add(x)
        }

        fun pop(): Int {
            val tempQueue = mutableListOf<Int>()
            while (size() != 1) {
                tempQueue.add(queue.removeAt(0))
            }
            val item = queue.removeAt(0)

            while (tempQueue.isNotEmpty()) {
                queue.add(tempQueue.removeAt(0))
            }
            return item
        }

        fun top(): Int {
            val tempQueue = mutableListOf<Int>()
            while (size() != 1) {
                tempQueue.add(queue.removeAt(0))
            }
            val top = queue.removeAt(0)
            tempQueue.add(top)

            while (tempQueue.isNotEmpty()) {
                queue.add(tempQueue.removeAt(0))
            }

            return top
        }

        fun empty(): Boolean {
            return queue.isEmpty()
        }

        fun size(): Int {
            return queue.size
        }

    }

    private fun countNodes(root: TreeNode?): Int {
        if (root == null) return 0

        val leftHeight = findLeftHeight(root)
        val rightHeight = findRightHeight(root)

        if (leftHeight == rightHeight) return 2.toDouble().pow(leftHeight).toInt() - 1

        return 1 + countNodes(root.left) + countNodes(root.right)
    }

    private fun findRightHeight(node: TreeNode?): Int {
        if (node == null) return 0
        return 1 + findRightHeight(node.right)
    }

    private fun findLeftHeight(node: TreeNode?): Int {
        if (node == null) return 0
        return 1 + findLeftHeight(node.left)
    }

    private fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        var tempHead = head
        var temp = tempHead
        var prev: ListNode? = null

        while (temp != null) {
            if (tempHead?.data == `val`) {
                while (tempHead?.data == `val`) {
                    tempHead = tempHead?.next
                }
                prev = tempHead
                temp = tempHead?.next
            } else {
                if (temp.data == `val`) {
                    while (temp != null && temp.data == `val`) {
                        temp = temp.next
                    }
                    prev?.next = temp
                    prev = temp
                    temp = temp?.next
                } else {
                    prev = temp
                    temp = temp.next
                }
            }
        }

        return tempHead
    }

    private fun titleToNumber(columnTitle: String): Int {
        var ans = 0

        for (i in columnTitle.length - 1 downTo 0) {
            val charCode = (columnTitle[i].code - 65) + 1
            val powerValue = 26.toDouble().pow(((columnTitle.length - 1) - i).toDouble()).toInt()
            ans += charCode * powerValue
        }

        return ans
    }

    private fun convertToTitle(columnNumber: Int): String {
        val ans = StringBuilder()
        var temp = columnNumber

        while (temp > 0) {
            ans.append(('A'.code + (temp - 1) % 26).toChar())
            temp = (temp - 1) / 26
        }

        return ans.reverse().toString()
    }

    // https://stackoverflow.com/a/32751102/8244668
    private fun getIntersectionNode(headA: ListNode?, headB: ListNode?): ListNode? {
        var tempA = headA
        var aCount = 0
        while (tempA != null) {
            ++aCount
            tempA = tempA.next
        }
        tempA = headA

        var tempB = headB
        var bCount = 0
        while (tempB != null) {
            ++bCount
            tempB = tempB.next
        }
        tempB = headB

        if (aCount > bCount) {
            while (aCount != bCount) {
                --aCount
                tempA = tempA?.next
            }
        } else if (bCount > aCount) {
            while (bCount != aCount) {
                --bCount
                tempB = tempB?.next
            }
        }

        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA
            }
            tempA = tempA.next
            tempB = tempB.next
        }

        return null
    }

    private fun getRow(rowIndex: Int): List<Int> {
        return generate(rowIndex + 1).last()
    }

    private fun generate(numRows: Int): List<List<Int>> {
        val ans = mutableListOf<List<Int>>()

        for (r in 0 until numRows) {
            val rowList = mutableListOf<Int>()
            for (i in 0..r) {
                if (i == 0 || i == r) {
                    rowList.add(1)
                } else {
                    rowList.add(ans[r - 1][i - 1] + ans[r - 1][i])
                }
            }
            ans.add(rowList)
        }

        return ans
    }

    private fun deleteDuplicates(head: ListNode?): ListNode? {
        var start = head
        var end = start?.next

        while (end != null) {
            if (end.data != start?.data) {
                start?.next = end
                start = end
            }
            end = end.next
        }

        start?.next = end

        return head
    }

    private fun addBinary(a: String, b: String): String {
        val ans = StringBuilder()

        var carry = '0'
        var aL = a.length - 1
        var bL = b.length - 1

        while (aL >= 0 || bL >= 0) {
            val bitA = if (aL >= 0) a[aL--] else '0'
            val bitB = if (bL >= 0) b[bL--] else '0'

            if (bitA == '1' && bitB == '1') {
                if (carry == '0') ans.append('0') else ans.append('1')
                carry = '1'
            } else if (bitA == '0' && bitB == '0') {
                if (carry == '0') ans.append('0') else ans.append('1')
                carry = '0'
            } else {
                if (carry == '0') ans.append('1') else ans.append('0')
            }
        }

        if (carry == '1') {
            ans.append(carry)
        }

        return ans.reverse().toString()
    }

    private fun singleNumber(nums: IntArray): Int {
        var ans = 0

        for (n in nums) {
            ans = ans xor n
        }

        return ans
    }

    private fun countBits(n: Int): IntArray {
        val ans = mutableListOf<Int>()

        for (i in 0..n) {
            var count = 0
            var temp = i
            while (temp != 0) {
                if (temp % 2 == 1) {
                    ++count
                }
                temp /= 2
            }
            ans.add(count)
        }

        return ans.toIntArray()
    }

    private class RecentCounter {
        val queue = mutableListOf<Int>()

        fun ping(t: Int): Int {
            while (queue.isNotEmpty() && queue.first() !in t - 3000..t) {
                queue.removeAt(0)
            }
            queue.add(t)
            return queue.size
        }

    }

    private fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        if (k > n) return emptyList()

        return CombinationSum(k, n).solveCombination()
    }

    private class CombinationSum(private val k: Int, private val n: Int) {
        private val answer = mutableListOf<List<Int>>()

        fun solveCombination(): List<List<Int>> {
            solve(mutableListOf())
            return answer
        }

        private fun solve(combination: MutableList<Int>) {
            if (isValidCombination(combination)) {
                val sortedCombination = combination.sorted()
                if (!answer.contains(sortedCombination)) {
                    answer.add(sortedCombination)
                }
                return
            }

            for (num in (combination.lastOrNull() ?: 0) + 1..9) {
                if (isValidEntry(num, combination)) {
                    combination.add(num)
                    solve(combination)
                    combination.removeAt(combination.size - 1)
                }
            }
        }

        private fun isValidEntry(newNum: Int, currentCombination: List<Int>): Boolean {
            if (currentCombination.contains(newNum)) return false
            if (currentCombination.sum() + newNum > n) return false

            return true
        }

        private fun isValidCombination(combination: List<Int>): Boolean {
            return combination.size == k && combination.sum() == n
        }
    }

    class StockSpanner {
        private val stack = mutableListOf<Pair<Int, Int>>()
        fun next(price: Int): Int {
            var ans = 1
            while (stack.isNotEmpty() && stack.last().first <= price) {
                ans += stack.removeAt(stack.size - 1).second
            }
            stack.add(Pair(price, ans))
            return ans
        }
    }

    // https://www.youtube.com/watch?v=ekFs9Nb2RNQ
    private fun dailyTemperatures(temperatures: IntArray): IntArray {
        val stack = mutableListOf<Int>()
        val output = mutableListOf<Int>()

        for (i in temperatures.indices.reversed()) {
            if (stack.isEmpty()) {
                stack.add(i)
                output.add(0, 0)
            } else {
                while (stack.isNotEmpty() && temperatures[stack.last()] <= temperatures[i]) {
                    stack.removeAt(stack.size - 1)
                }
                if (stack.isNotEmpty()) {
                    output.add(0, stack.last() - i)
                } else {
                    output.add(0, 0)
                }
                stack.add(i)
            }
        }

        return output.toIntArray()
    }

    private fun guessNumber(n: Int): Int {
        return playResult(1, n)
    }

    private fun playResult(start: Int, end: Int): Int {
        val middle = start + (end - start) / 2
        val result = guess(middle)
        return when (result) {
            -1 -> playResult(start, middle - 1)
            1 -> playResult(middle + 1, end)
            else -> middle
        }
    }

    // A method created just for calling purpose
    private fun guess(middle: Int): Int {
        return listOf(0, 1, -1).random()
    }

    private fun goodNodes(root: TreeNode?): Int {
        return findGoodNodes(root, root?.data ?: Int.MIN_VALUE)
    }

    private fun findGoodNodes(node: TreeNode?, maxValue: Int): Int {
        val leftAns = node?.left?.let { left ->
            findGoodNodes(left, maxOf(left.data, maxValue))
        } ?: 0
        val rightAns = node?.right?.let { right ->
            findGoodNodes(right, maxOf(right.data, maxValue))
        } ?: 0
        var ans = leftAns + rightAns
        if ((node?.data ?: Int.MAX_VALUE) >= maxValue) {
            ++ans
        }
        return ans
    }

    private fun leafSimilar(root1: TreeNode?, root2: TreeNode?): Boolean {
        val r1Leaves = getLeafNodes(root1)
        val r2Leaves = getLeafNodes(root2)

        return r1Leaves == r2Leaves
    }

    private fun getLeafNodes(root: TreeNode?, order: MutableList<Int> = mutableListOf()): List<Int> {
        root?.left?.let { left ->
            getLeafNodes(left, order)
        }
        root?.right?.let { right ->
            getLeafNodes(right, order)
        }
        if (root != null) {
            if (root.left == null && root.right == null) {
                order.add(root.data)
            }
        }
        return order
    }

    private fun pairSum(head: ListNode?): Int {
        val twin1 = mutableListOf<ListNode?>()
        val twin2 = mutableListOf<ListNode?>()
        var addedInTwin1 = false
        var temp = head

        while (temp != null) {
            if (addedInTwin1) {
                twin2.add(temp)
            } else {
                twin1.add(temp)
            }
            addedInTwin1 = !addedInTwin1
            temp = temp.next
        }
        twin2.reverse()

        var maxSum = 0
        while (twin1.isNotEmpty()) {
            maxSum = maxOf((twin1.removeAt(twin1.size - 1)?.data ?: 0) + (twin2.removeAt(twin2.size - 1)?.data
                    ?: 0), maxSum)
        }

        return maxSum
    }

    private fun deleteMiddle(head: ListNode?): ListNode? {
        var prev: ListNode? = null
        var slow = head
        var fast = head

        while (fast?.next != null) {
            prev = slow
            slow = slow?.next
            fast = fast.next?.next
        }
        if (prev == null) return prev
        prev.next = slow?.next
        return head
    }

    private fun pivotIndex(nums: IntArray): Int {
        var left = 0
        var right = nums.sum()

        for (i in nums.indices) {
            right -= nums[i]
            if (left == right) {
                return i
            }
            left += nums[i]
        }
        return -1
    }

    private fun decodeString(s: String): String {
        val stack = mutableListOf<Char>()
        var index = 0
        while (index < s.length) {
            if (s[index] != ']') {
                stack.add(s[index])
            } else {
                val string = StringBuilder()
                while (stack.isNotEmpty() && stack.elementAt(stack.size - 1) != '[') {
                    string.append(stack.removeAt(stack.size - 1))
                }
                string.reverse()
                if (stack.isNotEmpty()) {
                    stack.removeAt(stack.size - 1)
                }
                val count = StringBuilder()
                while (stack.isNotEmpty() && stack.elementAt(stack.size - 1).toString().toIntOrNull() != null) {
                    count.append(stack.removeAt(stack.size - 1))
                }
                count.reverse()
                string.toString().repeat(count.toString().toInt()).toCharArray().forEach { stack.add(it) }
            }
            ++index
        }

        return stack.joinToString("")
    }

    private fun removeStars(s: String): String {
        val stack = mutableListOf<Char>()

        for (c in s) {
            if (c == '*') {
                if (stack.isNotEmpty()) {
                    stack.removeAt(stack.size - 1)
                }
            } else {
                stack.add(c)
            }
        }

        return stack.joinToString("")
    }

    private fun equalPairs(grid: Array<IntArray>): Int {
        var count = 0

        val rowList = mutableMapOf<List<Int>, Int>()
        for (row in grid.indices) {
            rowList[grid[row].toList()] = rowList.getOrDefault(grid[row].toList(), 0) + 1
        }

        for (col in grid.indices) {
            val colList = mutableListOf<Int>()
            for (row in grid.indices) {
                colList.add(grid[row][col])
            }
            count += rowList.getOrDefault(colList, 0)
        }

        return count
    }

    private fun uniqueOccurrences(arr: IntArray): Boolean {
        val occuranceMap = mutableMapOf<Int, Int>()

        for (a in arr) {
            occuranceMap[a] = occuranceMap.getOrDefault(a, 0) + 1
        }

        return occuranceMap.values.toMutableSet().size == arr.toSet().size
    }

    private fun findDifference(nums1: IntArray, nums2: IntArray): List<List<Int>> {
        val mutableNums1 = nums1.toMutableSet()
        val mutableNums2 = nums2.toMutableSet()

        val distinct1 = mutableSetOf<Int>()

        for (n in mutableNums1) {
            if (mutableNums2.contains(n)) {
                mutableNums2.remove(n)
            } else {
                distinct1.add(n)
            }
        }

        return listOf(distinct1.toList(), mutableNums2.toList())
    }

    private fun maxVowels(s: String, k: Int): Int {
        var value = 0
        for (i in 0 until k) {
            value += isVowel(s[i])
        }
        var maxVowels = value

        for (i in k until s.length) {
            value = value - isVowel(s[i - k]) + isVowel(s[i])
            maxVowels = maxOf(maxVowels, value)
        }

        return maxVowels
    }

    private fun isVowel(c: Char): Int {
        return if (c in listOf('a', 'e', 'i', 'o', 'u')) 1 else 0
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