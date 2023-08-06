package recursion_backtracking_dp

class BitManipulation {
    fun startBitManipulation() {
        println("Starting bit manipulation")

        print(Integer.toBinaryString(1) + " ")
        println(countBits(1))
        print(Integer.toBinaryString(16) + " ")
        println(countBits(16))

        println(isEven(14))
        println(isEven(123))

        println(multiplyIterative(5, 10))
        println(multiplyIterative(12, 12))
        println(multiplyIterative(10, 8))

        println(multiplyRecursion(5, 10))
        println(multiplyRecursion(12, 12))
        println(multiplyRecursion(10, 8))
    }

    private fun multiplyRecursion(a: Int, b: Int): Int {
        if (b <= 1) return a
        return if (b xor 1 == b - 1) a + multiplyRecursion(a shl 1, b shr 1)
        else multiplyRecursion(a shl 1, b shr 1)
    }

    private fun multiplyIterative(a: Int, b: Int): Int {
        // (2 * a) * b / 2 + a, if b is odd
        // (2 * a) * b / 2, if b is even
        var result = 0
        var tempa = a
        var tempb = b

        while (tempb > 0) {
            if (tempb xor 1 == tempb - 1) {
                result += tempa
            }

            // n shl 1 multiply the number by 2
            tempa = tempa shl 1
            tempb = tempb shr 1
        }

        return result
    }

    private fun isEven(n: Int): Boolean {
        // n xor 1 increment by 1 if n is even
        // n xor 1 decrement by 1 if n is odd
        return n xor 1 == n + 1
    }

    private fun countBits(n: Int, count: Int = 0): Int {
        if (n == 0) return count
        // n shr 1 divide the number by 2
        return countBits(n shr 1, count + 1)
    }
}