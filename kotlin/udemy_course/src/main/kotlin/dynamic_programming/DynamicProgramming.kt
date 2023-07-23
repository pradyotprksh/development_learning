package dynamic_programming

class DynamicProgramming {
    fun startDynamicProgramming() {
        println("Starting problems on Dynamic Programming")

        // how many ways we can compute 6 using the numbers [1,3,5]
        println(solve(6))
    }

    private fun solve(n: Int): Int {
        if (n < 0) return 0
        if (n == 0) return 1
        return solve(n - 1) + solve(n - 3) + solve(n - 5)
    }
}