@file:Suppress("FunctionName")

package interview

/**
 * https://leetcode.com/studyplan/top-interview-150/
 */
object TopInterview150 {
    fun `Merge Sorted Array`() {
        /**
         * https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
         */
        fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
            val tempNums1 = nums1.clone()

            /* CODE */
            var i = m - 1
            var j = n - 1
            var k = m + n - 1
            while (j >= 0) {
                nums1[k--] = if (i < 0 || nums1[i] < nums2[j]) nums2[j--] else nums1[i--]
            }
            /* CODE */

            println("Merge Sorted Array ${tempNums1.toList()} + ${nums2.toList()} = ${nums1.toList()}")
        }

        merge(intArrayOf(1, 2, 3, 0, 0, 0), 3, intArrayOf(2, 5, 6), 3)
        merge(intArrayOf(1), 1, intArrayOf(), 0)
        merge(intArrayOf(0), 0, intArrayOf(1), 1)
    }

    /**
     * https://leetcode.com/problems/remove-element/description/?envType=study-plan-v2&envId=top-interview-150
     */
    fun `Remove Element`() {
        fun removeElement(nums: IntArray, `val`: Int): Int {
            val tempNums = nums.clone()

            var k = 0

            var start = 0
            var end = nums.lastIndex

            while (start <= end) {
                if (nums[end] == `val`) {
                    --end
                } else {
                    ++k
                    if (nums[start] != `val`) {
                        ++start
                    } else {
                        val temp = nums[start]
                        nums[start] = nums[end]
                        nums[end] = temp
                        ++start
                        --end
                    }
                }
            }

            println("Remove Element ${tempNums.toList()} $`val` = ${nums.toList()} $k")

            return k
        }

        removeElement(intArrayOf(3, 2, 2, 3), 3)
        removeElement(intArrayOf(0, 1, 2, 2, 3, 0, 4, 2), 2)
    }

    /**
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
     */
    fun `Remove Duplicates from Sorted Array`() {}
}