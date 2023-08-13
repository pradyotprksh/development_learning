package random

class RandomQuestions {
    fun solveRandomQuestions() {
        println("Solving random questions")

        println(youWillAllConform(listOf("F", "B", "B", "F", "B", "F", "F", "B", "B", "B", "F")))

        println(theBestTimeToParty(listOf(6, 7, 10, 10, 8, 9, 6), listOf(7, 9, 11, 12, 10, 11, 8)))
        println(theBestTimeToPartyFast(listOf(Pair(6, 7), Pair(7, 9), Pair(10, 11), Pair(10, 12), Pair(8, 10), Pair(9, 11), Pair(6, 8))))

        println(minimumWaiterRequired(listOf(Pair(8.0, 9.10), Pair(8.40, 12.0), Pair(8.50, 11.20), Pair(10.0, 11.30), Pair(16.0, 19.0), Pair(19.0, 20.0))))
    }

    // https://www.youtube.com/watch?v=zDHhHPZm2rc

    private fun minimumWaiterRequired(intervals: List<Pair<Double, Double>>): Int {
        val times = mutableListOf<Pair<Double, String>>()

        for (i in intervals) {
            times.add(Pair(i.first, "ARRIVAL"))
            times.add(Pair(i.second, "DEPARTURE"))
        }

        times.sortBy { it.first }

        var cCount = 0
        var waiterCount = 0

        for (t in times) {
            if (t.second == "ARRIVAL") {
                ++cCount
            } else {
                --cCount
            }
            if (cCount > waiterCount) {
                waiterCount = cCount
            }
        }

        return waiterCount
    }

    private fun theBestTimeToPartyFast(intervals: List<Pair<Int, Int>>): Int {
        val times = mutableListOf<Pair<Int, String>>()

        for (i in intervals) {
            times.add(Pair(i.first, "ARRIVAL"))
            times.add(Pair(i.second, "DEPARTURE"))
        }

        times.sortBy { it.first }

        var cCount = 0
        var maxCount = 0
        var time = times.first().first

        for (t in times) {
            if (t.second == "ARRIVAL") {
                ++cCount
            } else {
                --cCount
            }
            if (cCount > maxCount) {
                maxCount = cCount
                time = t.first
            }
        }
        return time
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

        return maxList.first().first
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