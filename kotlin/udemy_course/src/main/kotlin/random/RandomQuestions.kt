package random

class RandomQuestions {
    fun solveRandomQuestions() {
        println("Solving random questions")

        println(youWillAllConform(listOf("F", "B", "B", "F", "B", "F", "F", "B", "B", "B", "F")))
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