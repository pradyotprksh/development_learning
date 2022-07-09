package random

class Random {
    fun start() {
        val arr1 = listOf(6, 8, 5, 0)
        val arr2 = listOf(6, 8, 5, 0)
        val element = checkIfTwoArraysHaveCommonElements(arr1, arr2)
        println(if (element != null) "Found a common element which is $element" else "Didn't found an element")
    }

    private fun checkIfTwoArraysHaveCommonElements(arr1: List<Int>, arr2: List<Int>): Int? {
        if (arr1.isEmpty() || arr2.isEmpty()) return null
        val set = arr1.toHashSet()
        for (item in arr2) {
            if (set.contains(item)) return item
        }
        return null
    }
}