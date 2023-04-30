package data_structures.stacks_queues

import data_structures.linked_lists.SinglyLinkedList

class Stacks {
    private val singlyLinkedList = SinglyLinkedList()
    val length: Int
        get() = singlyLinkedList.length
    val top: Int?
        get() = singlyLinkedList.head?.data

    fun push(data: Int): Int {
        singlyLinkedList.unshift(data)
        return singlyLinkedList.length
    }

    fun pop(): Int? {
        try {
            return singlyLinkedList.shift()
        } catch (e: Exception) {
            throw IllegalStateException("Stack is empty")
        }
    }

    fun print() {
        singlyLinkedList.print()
    }
}