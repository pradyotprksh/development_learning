package implementation.ds.linkedList

data class SLLNode<T>(
    val data: T,
    var next: SLLNode<T>?
) {
    override fun toString(): String {
        return "[$data]->$next"
    }
}

class SinglyLinkedListImplementation<T>(
    private var head: SLLNode<T>?
) {
    fun append(data: T) {
        val newNode = SLLNode(data = data, next = null)
        var current = head
        while (current?.next != null) {
            current = current.next
        }
        current?.next = newNode
    }

    fun insertNodeAtStart(data: T) {
        val newNode = SLLNode(data = data, next = head)
        head = newNode
    }

    fun printSLL() {
        println(head)
    }
}

object SinglyLinkedList {
    fun implementation() {
        val sll = SinglyLinkedListImplementation(head = SLLNode(data = 1, next = null))
        sll.append(2)
        sll.append(3)
        sll.append(4)
        sll.append(5)
        sll.append(6)

        sll.printSLL()

        sll.insertNodeAtStart(7)

        sll.printSLL()
    }
}