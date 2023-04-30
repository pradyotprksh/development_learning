package data_structures.stacks_queues

import data_structures.linked_lists.SinglyLinkedList

class Queues {
    private val singlyLinkedList = SinglyLinkedList()
    val length: Int
        get() = singlyLinkedList.length
    val top: Int?
        get() = singlyLinkedList.tail?.data

    fun enqueue(data: Int): Int {
        singlyLinkedList.push(data)
        return singlyLinkedList.length
    }

    fun dequeue(): Int? {
        try {
            return singlyLinkedList.pop()
        } catch (e: Exception) {
            throw IllegalStateException("Queue is empty")
        }
    }

    fun print() {
        singlyLinkedList.print()
    }
}