package examples

import java.time.Instant

object TimeComplexity {
    fun timeComplexityExample() {
        val num: Long = getCurrentTimestamp()

        val startTime = getCurrentTimestamp()
        findSumByFormula(num)
        val endTime = getCurrentTimestamp()
        println(" Find sum of $num number by formula. Took ${endTime - startTime} milliseconds")

        val startTime2 = getCurrentTimestamp()
        findSumByIteration(num)
        val endTime2 = getCurrentTimestamp()
        println(" Find sum of $num number by iteration. Took ${endTime2 - startTime2} milliseconds")
    }

    private fun findSumByFormula(n: Long): Long {
        return n * (n + 1) / 2
    }

    private fun findSumByIteration(n: Long): Long {
        var sum: Long = 0
        for (num in 1..n) {
            sum += num
        }
        return sum
    }

    private fun getCurrentTimestamp(): Long {
        return Instant.now().epochSecond
    }
}