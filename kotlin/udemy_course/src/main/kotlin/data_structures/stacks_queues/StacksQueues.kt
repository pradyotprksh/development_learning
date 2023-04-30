package data_structures.stacks_queues

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