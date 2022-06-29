package stack

class StackImpl: Stack {
    private var stack: Array<Int>? = null
    private var top: Int = -1
    private var stackSize: Int = -1

    override fun startStack() {
        println("Hello User, let's start with stack")
        val size = Utils.readInput("Please choose the size of the stack")
        if (size < 1) {
            println("Please enter a valid stack size")
        } else {
            println("Stack Size is $size")
            createStack(size)

            var input = -1
            while (input != 5) {
                println("Please choose any one of the option")
                println("1 > Push")
                println("2 > Pop")
                println("3 > Show stack details")
                println("4 > Done")
                input = Utils.readInput()
                if (input < 1) {
                    println("Please enter a valid input")
                } else {
                    when (input) {
                        1 -> {
                            val item = Utils.readInput("Enter the number you want push")
                            push(item)
                        }
                        2 -> pop()
                        3 -> showStackDetails()
                        4 -> {
                            println("Stack details at the end is:")
                            showStackDetails()
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
        stackSize = size
    }

    override fun showStackDetails() {
        var items = ""
        stack?.forEach {
            items = "$it $items"
        }
        println("$items {top=$top}")
    }

    override fun isStackFull(): Boolean = top == stackSize - 1

    override fun isStackEmpty(): Boolean = top == -1

    override fun push(element: Int) {
        println("Pushing element $element")
        stack?.let {
            if (!isStackFull()) {
                it[++top] = element
                showStackDetails()
            } else {
                println("OOPS!! Stack is full. Can't add new elements. Please pop items.")
            }
        }
    }

    override fun pop(): Int {
        if (isStackEmpty()) {
            println("OOPS!! Stack is empty. Can't pop any elements now. Please push items.")
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
        println("Closing stack, and clearing the data")
        stack = null
    }
}