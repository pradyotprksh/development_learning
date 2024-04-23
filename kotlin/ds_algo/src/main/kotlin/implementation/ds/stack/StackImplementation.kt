package implementation.ds.stack

import implementation.ds.linkedList.sll.SLLNode

class StackImplementation<T>(
    data: T,
) {
    private var top: SLLNode<T>? = SLLNode(data = data, next = null)
    private var length = 1

    fun push(data: T) {
        val newNode = SLLNode(data = data, next = top)
        top = newNode
        ++length
    }

    fun pop(): SLLNode<T>? {
        if (top == null) {
            println("Stack is empty")
            return null
        }

        val temp = top
        top = top?.next
        temp?.next = null
        --length

        return temp
    }

    fun peak(): SLLNode<T>? {
        if (top == null) {
            println("Stack is empty")
            return null
        }

        return top
    }

    fun printSLL() {
        println("$top. Length: $length")
    }
}