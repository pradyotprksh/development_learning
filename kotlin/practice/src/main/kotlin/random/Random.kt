package random

class Random {
    fun start() {
        val arr1 = listOf(6, 8, 5, 0)
        val arr2 = listOf(7, 9, 4, 1)
        val element = checkIfTwoArraysHaveCommonElements(arr1, arr2)
        println(if (element != null) "Found a common element which is $element" else "Didn't found an element")
        println("-".repeat(100))
        println(reverseAString("Pradyot"))
        println(reverseAString("Prakash"))
        println(reverseAString("P"))
        println("-".repeat(100))
    }

    private fun checkIfTwoArraysHaveCommonElements(arr1: List<Int>, arr2: List<Int>): Int? {
        if (arr1.isEmpty() || arr2.isEmpty()) return null
        val set = HashSet<Int>()
        for (item in arr1) {
            set.add(item)
        }
        for (item in arr2) {
            if (set.contains(item)) return item
        }
        return null
    }

    private fun reverseAString(input: String): String {
        if (input.length < 2) return input
        var newString = ""
        for (index in input.indices) {
            newString = "$newString${input[input.length - 1 - index]}"
        }
        return newString
    }
}