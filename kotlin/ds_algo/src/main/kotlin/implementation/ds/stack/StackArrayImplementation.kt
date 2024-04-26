package implementation.ds.stack

class StackArrayImplementation(
    private val size: Int,
) {
    private val stack: Array<Int?>
    private var top = -1

    init {
        stack = Array(size = size, init = { null })
    }

    fun push(data: Int) {
        if (top == size - 1) {
            println("Stack is full")
            return
        }

        stack[++top] = data
    }

    fun pop(): Int? {
        if (top == -1) {
            println("Stack is empty")
            return null
        }

        val temp = stack[top]
        stack[top] = null
        --top

        return temp
    }

    fun peak(): Int? {
        if (top == -1) {
            println("Stack is empty")
            return null
        }

        return stack[top]
    }

    fun isEmpty(): Boolean {
        return top == -1
    }

    fun printStack() {
        println("${stack.map { it.toString() }}. Length: ${top + 1}")
    }
}