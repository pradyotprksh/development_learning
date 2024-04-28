package implementation.ds.queue

import implementation.ds.linkedList.sll.SLLNode

class QueueLinkedListImplementation<T>(
    data: T
) {
    var front: SLLNode<T>? = SLLNode(data = data, next = null)
        private set
    var rear: SLLNode<T>? = front

    fun enqueue(data: T) {
        val newNode = SLLNode(data = data, next = null)

        if (front == null) {
            front = newNode
            rear = newNode
        } else {
            rear?.next = newNode
            rear = rear?.next
        }
    }

    fun dequeue(): SLLNode<T>? {
        if (front == null) {
            println("Queue is empty")
            return null
        }
        val temp = front
        front = front?.next
        if (front == null) {
            rear = null
        }
        return temp
    }

    fun peak(): SLLNode<T>? {
        if (front == null) {
            println("Queue is empty")
            return null
        }

        return front
    }

    fun length(): Int {
        var count = 0
        var current = front
        while (current != null) {
            ++count
            current = current.next
        }
        return count
    }

    fun printSLLQueue() {
        println("$front. Length: ${length()}")
    }
}