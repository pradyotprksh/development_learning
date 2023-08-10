package random

class RandomQuestions {
    fun solveRandomQuestions() {
        println("Solving random questions")

        println(youWillAllConform(listOf("F", "B", "B", "F", "B", "F", "F", "B", "B", "B", "F")))

        println(theBestTimeToParty(listOf(6, 7, 10, 10, 8, 9, 6), listOf(7, 9, 11, 12, 10, 11, 8)))
    }

    private fun theBestTimeToParty(arr: List<Int>, dep: List<Int>): Int {
        var maxCovered = 0
        var maxList = listOf<Pair<Int, Int>>()

        for (a in arr) {
            var tempMax = 0
            val tempList = mutableListOf<Pair<Int, Int>>()
            for (i in arr.indices) {
                if (a in arr[i] until dep[i]) {
                    ++tempMax
                    tempList.add(Pair(arr[i], dep[i]))
                }
            }
            if (tempMax > maxCovered) {
                maxCovered = tempMax
                maxList = tempList
            }
        }

        println(maxList)

        return maxCovered
    }

    private fun youWillAllConform(caps: List<String>): Int {
        val tempCaps = caps + listOf("END")

        val forwardCaps = mutableListOf<Pair<Int, Int>>()
        val backwardCaps = mutableListOf<Pair<Int, Int>>()

        var firstIndex = 0

        for (i in 1 until tempCaps.size) {
            if (tempCaps[i - 1] != tempCaps[i]) {
                if (tempCaps[i - 1] == "F") {
                    forwardCaps.add(Pair(firstIndex, i - 1))
                } else {
                    backwardCaps.add(Pair(firstIndex, i - 1))
                }
                firstIndex = i
            }
        }

        println("Forward: $forwardCaps Backward: $backwardCaps")

        return if (forwardCaps.size < backwardCaps.size) forwardCaps.size else backwardCaps.size
    }
}