package implementation.ds.stack

object Stack {
    fun implementation() {
        val stackLinkedList = StackLinkedListImplementation(data = 1)

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") // Top [1]->...

        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [1]->...

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") /* Stack is empty
                                                            Top null */

        stackLinkedList.push(1)
        stackLinkedList.push(2)
        stackLinkedList.push(3)

        stackLinkedList.printSLL() // [3]->[2]->[1]->null. Length: 3

        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [3]->...
        stackLinkedList.printSLL() // [2]->[1]->null. Length: 2
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [2]->...
        stackLinkedList.printSLL() // [1]->null. Length: 1
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [1]->...
        stackLinkedList.printSLL() // null. Length: 0
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") /* Stack is empty
                                                           Pop null */

        stackLinkedList.push(1)
        stackLinkedList.push(2)
        stackLinkedList.push(3)
        stackLinkedList.printSLL() // [3]->[2]->[1]->null. Length: 3

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") // Top [3]->...
    }
}