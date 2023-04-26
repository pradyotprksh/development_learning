package data_structures.linked_lists

data class Node(val data: Int, var next: Node? = null) {
    override fun toString(): String {
        return "$data->$next"
    }
}

class LinkedLists {
    fun startLinkedLists() {
        println("Starting Linked Lists")
        singlyLinkedList()
    }

    private fun singlyLinkedList() {
        println("Starting Singly Linked Lists")
        val singlyLinkedList = SinglyLinkedList()
        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)
        singlyLinkedList.print()
        singlyLinkedList.pop()
        singlyLinkedList.pop()
        singlyLinkedList.print()
    }
}

class SinglyLinkedList {
    var head: Node? = null
        private set
    var tail: Node? = null
        private set
    var length = 0
        private set

    fun push(data: Int) {
        val node = Node(data = data)
        if (head == null && tail == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            tail = node
        }
        ++length
    }

    fun pop(): Int? {
        if (tail == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        val data = tail?.data;
        if (head == tail) {
            head = null
            tail = null
        } else {
            var temp = head
            while (temp?.next != tail) {
                temp = temp?.next
            }
            temp?.next = null
            tail = temp
        }
        --length
        return data
    }

    fun print() {
        println("${head.toString()} | Length=$length")
    }
}