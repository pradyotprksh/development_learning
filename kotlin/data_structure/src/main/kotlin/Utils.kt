object Utils {
    fun readInput(message: String = ""): Int {
        println(message)
        return readLine()?.toIntOrNull() ?: -1
    }
}