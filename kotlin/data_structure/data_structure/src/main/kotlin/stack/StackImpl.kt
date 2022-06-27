package stack

class StackImpl: Stack {
    private var stack: Array<Int>? = null
    private var top: Int = -1
    private var stackSize: Int = -1

    private fun readInput(message: String = ""): Int {
        println(message)
        return  readLine()?.toInt() ?: -1
    }

    override fun startStack() {
        println("Hello User, let's start with stack")
        stackSize = readInput("Please choose the size of the stack")
        if (stackSize < 1) {
            println("Please enter a valid stack size")
        } else {
            println("Stack Size is $stackSize")
            createStack(stackSize)

            var input = -1
            while (input != 5) {
                println("Please choose any one of the option")
                println("1 > Push")
                println("2 > Pop")
                println("3 > Show stack details")
                println("4 > Done")
                input = readInput()
                if (input < 1) {
                    println("Please enter a valid input")
                } else {
                    when (input) {
                        1 -> {
                            val item = readInput("Enter the number you want push")
                            push(item)
                        }
                        2 -> pop()
                        3 -> showStackDetails()
                        4 -> {
                            closeStack()
                            input = 5
                        }
                    }
                }
            }
        }
    }

    override fun createStack(size: Int) {
        stack = Array(size){ 0 }
    }

    override fun showStackDetails() {
        var items = ""
        stack?.forEach {
            items = "$it $items"
        }
        println("$items {top=$top}")
    }

    override fun isStackFull(): Boolean {
        return top == stackSize - 1
    }

    override fun isStackEmpty(): Boolean {
        return top == -1
    }

    override fun push(element: Int) {
        println("Adding element $element")
        stack?.let {
            if (!isStackFull()) {
                it[++top] = element
                showStackDetails()
            } else {
                println("Stack is full")
            }
        }
    }

    override fun pop(): Int {
        if (isStackEmpty()) {
            println("Stack is empty")
            return -1
        }
        val item = stack?.let {
            it[top]
        } ?: -1
        stack?.let {
            it[top] = 0
        }
        top -= 1
        println("Popping element $item {top=$top}")
        return item
    }

    override fun closeStack() {
        stack = null
    }
}