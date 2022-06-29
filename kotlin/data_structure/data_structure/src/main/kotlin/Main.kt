import queue.Queue
import queue.QueueImpl
import stack.Stack
import stack.StackImpl

fun main() {
    when (Utils.readInput("Please select the DS you want to use.\n1.Stack\n2.Queue")) {
        1 -> {
            val stack: Stack = StackImpl()
            stack.startStack()
        }
        2 -> {
            val queue: Queue = QueueImpl()
            queue.startQueue()
        }
    }
}