package implementation.ds.stack

object Stack {
    fun implementation() {
        val stack = StackImplementation(data = 1)

        println("Top ${stack.peak()?.onlyNodeString()}") // Top [1]->...

        println("Pop ${stack.pop()?.onlyNodeString()}") // Pop [1]->...

        println("Top ${stack.peak()?.onlyNodeString()}") /* Stack is empty
                                                            Top null */

        stack.push(1)
        stack.push(2)
        stack.push(3)

        stack.printSLL() // [3]->[2]->[1]->null. Length: 3

        println("Pop ${stack.pop()?.onlyNodeString()}") // Pop [3]->...
        stack.printSLL() // [2]->[1]->null. Length: 2
        println("Pop ${stack.pop()?.onlyNodeString()}") // Pop [2]->...
        stack.printSLL() // [1]->null. Length: 1
        println("Pop ${stack.pop()?.onlyNodeString()}") // Pop [1]->...
        stack.printSLL() // null. Length: 0
        println("Pop ${stack.pop()?.onlyNodeString()}") /* Stack is empty
                                                           Pop null */

        stack.push(1)
        stack.push(2)
        stack.push(3)
        stack.printSLL() // [3]->[2]->[1]->null. Length: 3

        println("Top ${stack.peak()?.onlyNodeString()}") // Top [3]->...
    }
}