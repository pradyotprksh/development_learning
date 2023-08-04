package recursion_backtracking_dp

class Recursion {
    fun startRecursion() {
        println("Starting with recursion")

        tail(5)
        head(5)

        println(factorialHeadRecursion(4))
        println(factorialTailRecursion(4))

        println(fibonacciHeadRecursion(5))
        println(fibonacciTailRecursion(5))
        println(fibonacciHeadRecursion(6))
        println(fibonacciTailRecursion(6))

        println(reverseString("abc"))
        println(reverseString("Pradyot Prakash"))
    }

    private fun reverseString(s: String): String {
        if (s.isEmpty()) return s
        return s.last() + reverseString(s.substring(0, s.length - 1))
    }

    private fun fibonacciTailRecursion(n: Int, a: Int = 0, b: Int = 1): Int {
        if (n == 0) return a
        if (n == 1) return b
        return fibonacciTailRecursion(n - 1, b, a + b)
    }

    private fun fibonacciHeadRecursion(n: Int): Int {
        if (n == 0) return 0
        if (n == 1) return 1
        val fib1 = fibonacciHeadRecursion(n - 1)
        val fib2 = fibonacciHeadRecursion(n - 2)
        return fib1 + fib2
    }

    private fun factorialTailRecursion(n: Int, acc: Int = 1): Int {
        if (n <= 1) return acc
        return factorialTailRecursion(n - 1, n * acc)
    }

    private fun factorialHeadRecursion(n: Int): Int {
        if (n <= 1) return 1
        return factorialHeadRecursion(n - 1) * n
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