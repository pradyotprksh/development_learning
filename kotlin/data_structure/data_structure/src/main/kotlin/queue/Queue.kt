package queue

interface Queue {
    fun startQueue()

    fun createQueue(size: Int = 5)

    fun showQueueDetails()

    fun isQueueFull(): Boolean

    fun isQueueEmpty(): Boolean

    fun enqueue(element: Int = 0)

    fun dequeue(): Int

    fun closeQueue()
}