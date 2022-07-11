import linked_list.DoublyNode
import linked_list.LinkedList
import linked_list.Node
import linked_list.doubly.DoubleLinkedListImpl
import linked_list.singly.SinglyLinkedListImpl
import queue.Queue
import queue.QueueImpl
import stack.Stack
import stack.StackImpl

/**
 * Please keep in mind that these are not the best solution out there for the questions,
 * these are the solutions made by me and there can be n number of better solutions.
 *
 * You are welcome to raise the PR for making the solutions better and help others and me
 * for the same.
 */
fun main() {
    when (Utils.readInput(
        "Please select the DS you want to use." +
                "\n1. Stack" +
                "\n2. Queue" +
                "\n3. LinkedList"
    )) {
        1 -> {
            val stack: Stack = StackImpl()
            stack.startStack()
        }
        2 -> {
            val queue: Queue = QueueImpl()
            queue.startQueue()
        }
        3 -> {
            when (Utils.readInput(
                "Please select the type of linked list:" +
                        "\n1. Singly Linked List" +
                        "\n2. Double Linked List" +
                        "\n3. Circular Linked List" +
                        "\n4. Doubly Circular Linked List"
            )) {
                1 -> {
                    val singlyLinkedList: LinkedList<Node> = SinglyLinkedListImpl()
                    singlyLinkedList.startLinkedList()
                }
                2 -> {
                    val doublyLinkedList: LinkedList<DoublyNode> = DoubleLinkedListImpl()
                    doublyLinkedList.startLinkedList()
                }
                3 -> {}
                4 -> {}
                else -> println("Please enter a valid option.")
            }
        }
        else -> println("Please enter a valid option.")
    }
}