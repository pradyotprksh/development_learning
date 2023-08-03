package recursion_backtracking_dp

class Recursion {
    fun startRecursion() {
        println("Starting with recursion")
        tail(5)
        head(10000)
    }

    private fun tail(n: Int) {
        if (n == 0) return
        println(n)
        tail(n - 1)
    }

    private val head = DeepRecursiveFunction<Int, Unit> { n ->
        if (n == 0) return@DeepRecursiveFunction
        callRecursive(n - 1)
        println(n)
    }
}