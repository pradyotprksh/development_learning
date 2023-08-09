package random

class RandomQuestions {
    fun solveRandomQuestions() {
        println("Solving random questions")

        println(youWillAllConform(listOf("F", "B", "B", "F", "B", "F", "F", "B", "B", "B", "F")))
    }

    private fun youWillAllConform(caps: List<String>): Int {
        val forwardCaps = mutableListOf<Pair<Int, Int>>()
        val backwardCaps = mutableListOf<Pair<Int, Int>>()

        var firstIndex = 0

        for (i in 1 until caps.size) {
            if (caps[i - 1] != caps[i]) {
                if (caps[i - 1] == "F") {
                    forwardCaps.add(Pair(firstIndex, i - 1))
                } else {
                    backwardCaps.add(Pair(firstIndex, i - 1))
                }
                firstIndex = i
            }
        }

        if (caps[firstIndex] == "F") {
            forwardCaps.add(Pair(firstIndex, caps.size - 1))
        } else {
            backwardCaps.add(Pair(firstIndex, caps.size - 1))
        }

        println("Forward: $forwardCaps Backward: $backwardCaps")

        return if (forwardCaps.size < backwardCaps.size) forwardCaps.size else backwardCaps.size
    }
}