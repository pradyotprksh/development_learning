package implementation.ds.stack

object Stack {
    fun implementation() {
        println("Stack implementation using Singly Linked List")
        val stackLinkedList = StackLinkedListImplementation(data = 1)

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") // Top [1]->...

        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [1]->...

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") /* Stack is empty
                                                                      Top null */

        stackLinkedList.push(1)
        stackLinkedList.push(2)
        stackLinkedList.push(3)

        stackLinkedList.printSLLStack() // [3]->[2]->[1]->null. Length: 3

        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [3]->...
        stackLinkedList.printSLLStack() // [2]->[1]->null. Length: 2
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [2]->...
        stackLinkedList.printSLLStack() // [1]->null. Length: 1
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") // Pop [1]->...
        stackLinkedList.printSLLStack() // null. Length: 0
        println("Pop ${stackLinkedList.pop()?.onlyNodeString()}") /* Stack is empty
                                                                     Pop null */

        stackLinkedList.push(1)
        stackLinkedList.push(2)
        stackLinkedList.push(3)
        stackLinkedList.printSLLStack() // [3]->[2]->[1]->null. Length: 3

        println("Top ${stackLinkedList.peak()?.onlyNodeString()}") // Top [3]->...

        println()

        println("Stack implementation using Array")
        val stackArray = StackArrayImplementation(size = 3)

        println("Top ${stackArray.peak()}") /* Stack is empty
                                               Top null */

        println("Pop ${stackArray.pop()}") /* Stack is empty
                                              Pop null */

        println("Top ${stackArray.peak()}") /* Stack is empty
                                               Top null */

        stackArray.push(1)
        stackArray.push(2)
        stackArray.push(3)

        stackArray.printStack() // [1, 2, 3]. Length: 3

        println("Pop ${stackArray.pop()}") // Pop 3
        stackArray.printStack() // [1, 2, null]. Length: 2
        println("Pop ${stackArray.pop()}") // Pop 2
        stackArray.printStack() // [1, null, null]. Length: 1
        println("Pop ${stackArray.pop()}") // Pop 1
        stackArray.printStack() // [null, null, null]. Length: 0
        println("Pop ${stackArray.pop()}") /* Stack is empty
                                              Pop null */

        stackArray.push(1)
        stackArray.push(2)
        stackArray.push(3)
        stackArray.printStack() // [1, 2, 3]. Length: 3

        println("Top ${stackArray.peak()}") // Top 3

        fun reverseString(value: String): String {
            val charStack = StackLinkedListImplementation(data = value[0])
            for (index in 1 until value.length) {
                charStack.push(value[index])
            }

            val charArray = arrayListOf<Char>()
            while (charStack.peak() != null) {
                val top = charStack.pop()?.data
                if (top != null) {
                    charArray.add(top)
                }
            }

            return charArray.joinToString("")
        }

        println("Reverse abcd: ${reverseString(value = "abcd")}")
        println("Reverse Pradyot Prakash: ${reverseString(value = "Pradyot Prakash")}")
    }
}