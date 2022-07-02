package stack

interface Stack {
    fun startStack()

    fun createStack(size: Int = 5)

    fun showStackDetails()

    fun isStackFull(): Boolean

    fun isStackEmpty(): Boolean

    fun push(element: Int = 0)

    fun pop(): Int

    fun closeStack()
}