package org.example

object Arrays {
    fun implementArrays() {
        println(twoSum(listOf(2, 7, 11, 15), 9).toList())
        println(twoSum(listOf(3, 2, 4), 6).toList())
        println(twoSum(listOf(3, 3), 6).toList())
        println(twoSum(listOf(-3, 4, 3, 90), 0).toList())

        println(removeDuplicates(intArrayOf(1, 1, 2)))
        println(removeDuplicates(intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)))
    }

    private fun twoSum(nums: List<Int>, target: Int): IntArray {
        val solution = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, num ->
            val difference = (target - num)

            val result = solution[difference]
            if (result != null) {
                return intArrayOf(result, index)
            }

            solution[num] = index
        }

        return intArrayOf(-1, -1)
    }

    private fun removeDuplicates(nums: IntArray): Int {
        var k = 0
        var i = 0
        var j = 0

        while (j < nums.size) {
            if (nums[i] == nums[j]) {
                ++j
            } else {
                ++i
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
                ++j
                ++k
            }
        }

        return k + 1
    }
}