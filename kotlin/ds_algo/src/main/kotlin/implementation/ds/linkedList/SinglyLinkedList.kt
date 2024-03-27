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
    data: T
) {
    private var head: SLLNode<T>? = SLLNode(data = data, next = null)

    fun append(data: T) {
        val newNode = SLLNode(data = data, next = null)
        var current = head
        while (current?.next != null) {
            current = current.next
        }
        current?.next = newNode
    }

    fun insert(data: T) {
        val newNode = SLLNode(data = data, next = head)
        head = newNode
    }

    private fun length(): Int {
        var count = 0
        var current = head
        while (current != null) {
            ++count
            current = current.next
        }
        return count
    }

    fun printSLL() {
        println("$head. Length: ${length()}")
    }
}

object SinglyLinkedList {
    fun implementation() {
        val sll = SinglyLinkedListImplementation(data = 1)
        sll.append(2)
        sll.append(3)
        sll.append(4)
        sll.append(5)
        sll.append(6)

        sll.printSLL()

        sll.insert(7)

        sll.printSLL()
    }
}