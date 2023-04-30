package data_structures.stacks_queues

import data_structures.linked_lists.SinglyLinkedList
import java.lang.Exception

class StacksQueues {
    fun startStacksQueues() {
        println("Starting Stacks & Queues")
        stacks()
        queues()
    }

    private fun queues() {
        println("Starting Queues")
        val queue = Queues()
        queue.enqueue(1)
        queue.enqueue(2)
        queue.enqueue(3)
        println(queue.top)
        queue.dequeue()
        println(queue.top)
        println(queue.length)
        queue.print()
    }

    private fun stacks() {
        println("Starting Stacks")
        val stacks = Stacks()
        stacks.push(1)
        stacks.push(2)
        stacks.push(3)
        println(stacks.top)
        stacks.pop()
        println(stacks.top)
        println(stacks.length)
        stacks.print()
    }
}

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