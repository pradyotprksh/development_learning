package queue

class QueueImpl: Queue {
    private var queue: Array<Int>? = null
    private var rear: Int = -1
    private var front: Int = -1
    private var queueSize: Int = -1

    override fun startQueue() {
        println("Hello User, let's start with queue")
        val size = Utils.readInput("Please choose the size of the queue")
        if (size < 1) {
            println("Please enter a valid queue size")
        } else {
            println("Queue Size is $size")
            createQueue(size)

            var input = -1
            while (input != 5) {
                println("Please choose any one of the option")
                println("1 > Enqueue")
                println("2 > Dequeue")
                println("3 > Show queue details")
                println("4 > Done")
                input = Utils.readInput()
                if (input < 1) {
                    println("Please enter a valid input")
                } else {
                    when (input) {
                        1 -> {
                            val item = Utils.readInput("Enter the number you want enqueue")
                            enqueue(item)
                        }
                        2 -> dequeue()
                        3 -> showQueueDetails()
                        4 -> {
                            println("Queue details at the end is:")
                            showQueueDetails()
                            closeQueue()
                            input = 5
                        }
                    }
                }
            }
        }
    }

    override fun createQueue(size: Int) {
        queue = Array(size){ 0 }
        queueSize = size
    }

    override fun showQueueDetails() {
        var items = ""
        queue?.forEach {
            items = "$items $it"
        }
        println("$items {rear=$rear, front=$front}")
    }

    override fun isQueueFull(): Boolean = rear == queueSize - 1

    override fun isQueueEmpty(): Boolean = front == -1 && rear == -1

    override fun enqueue(element: Int) {
        println("Adding element $element")
        queue?.let {
            if (!isQueueFull()) {
                if (front == -1) {
                    front = 0
                }
                it[++rear] = element
                showQueueDetails()
            } else {
                println("OOPS!! Queue is full. Can't add new elements. Please dequeue items.")
            }
        }
    }

    override fun dequeue(): Int {
        if (isQueueEmpty()) {
            println("OOPS!! Queue is empty. Can't pop any elements now. Please enqueue items.")
            return -1
        }
        val item = queue?.let {
            it[front]
        } ?: -1
        queue?.let {
            it[front] = 0
        }
        ++front
        if (front > rear) {
            front = -1
            rear = -1
        }
        println("Removing element $item {rear=$rear, front=$front}")
        return item
    }

    override fun closeQueue() {
        println("Closing queue, and clearing the data")
        queue = null
    }
}