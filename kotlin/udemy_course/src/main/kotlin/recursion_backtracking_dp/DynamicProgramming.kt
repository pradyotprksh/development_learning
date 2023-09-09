package recursion_backtracking_dp

class DynamicProgramming {
    fun startDynamicProgramming() {
        println("Starting dynamic programming")

        println(fibonacciNumbers(5))
    }

    private fun fibonacciNumbers(n: Int, memorization: MutableMap<Int, Int> = mutableMapOf(0 to 1, 1 to 1)): Int {
        if (!memorization.containsKey(n)) {
            memorization[n] = fibonacciNumbers(n - 1, memorization) + fibonacciNumbers(n - 2, memorization)
        }
        return memorization[n]!!
    }
}