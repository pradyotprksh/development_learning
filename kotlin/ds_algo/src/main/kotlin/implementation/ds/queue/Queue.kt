package implementation.ds.queue

object Queue {
    fun implementation() {
        println("Queue implementation using Singly Linked List")
        val queueLinkedList = QueueLinkedListImplementation(data = 1)

        println("Top ${queueLinkedList.peak()?.onlyNodeString()}") // Top [1]->...

        println("Dequeue ${queueLinkedList.dequeue()?.onlyNodeString()}") // Dequeue [1]->...

        println("Top ${queueLinkedList.peak()?.onlyNodeString()}") /* Queue is empty
                                                                      Top null */

        queueLinkedList.enqueue(1)
        queueLinkedList.enqueue(2)
        queueLinkedList.enqueue(3)

        queueLinkedList.printSLLQueue() // [1]->[2]->[3]->null. Length: 3

        println("Dequeue ${queueLinkedList.dequeue()?.onlyNodeString()}") // Dequeue [1]->...
        queueLinkedList.printSLLQueue() // [2]->[3]->null. Length: 2
        println("Dequeue ${queueLinkedList.dequeue()?.onlyNodeString()}") // Dequeue [2]->...
        queueLinkedList.printSLLQueue() // [3]->null. Length: 1
        println("Dequeue ${queueLinkedList.dequeue()?.onlyNodeString()}") // Dequeue [3]->...
        queueLinkedList.printSLLQueue() // null. Length: 0
        println("Dequeue ${queueLinkedList.dequeue()?.onlyNodeString()}") /* Queue is empty
                                                                             Dequeue null */

        queueLinkedList.enqueue(1)
        queueLinkedList.enqueue(2)
        queueLinkedList.enqueue(3)
        queueLinkedList.printSLLQueue() // [1]->[2]->[3]->null. Length: 3

        println("Top ${queueLinkedList.peak()?.onlyNodeString()}") // Top [1]->...
    }
}