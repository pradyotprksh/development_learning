package data_structures.linked_lists

data class Node(var data: Int, var next: Node? = null) {
    override fun toString(): String {
        return "$data->$next"
    }
}

data class DoublyNode(
    var prev: DoublyNode? = null,
    var data: Int,
    var next: DoublyNode? = null
) {
    override fun toString(): String {
        return "$data<->$next"
    }
}

class LinkedLists {
    fun startLinkedLists() {
        println("Starting Linked Lists")
        singlyLinkedList()
        doublyLinkedList()
    }

    private fun doublyLinkedList() {
        println("Starting Doubly Linked Lists")
        val doublyLinkedList = DoublyLinkedList()
        doublyLinkedList.unshift(7)
        doublyLinkedList.push(1)
        doublyLinkedList.push(2)
        doublyLinkedList.push(3)
        doublyLinkedList.print()
        doublyLinkedList.push(4)
        doublyLinkedList.print()
        doublyLinkedList.pop()
        doublyLinkedList.print()
        doublyLinkedList.unshift(5)
        doublyLinkedList.unshift(6)
        doublyLinkedList.print()
        doublyLinkedList.shift()
        doublyLinkedList.print()
        println(doublyLinkedList.get(0)?.data)
        println(doublyLinkedList.get(3)?.data)
        doublyLinkedList.set(0, 8)
        doublyLinkedList.print()
        doublyLinkedList.insert(2, 9)
        doublyLinkedList.print()
        doublyLinkedList.remove(2)
        doublyLinkedList.print()
        doublyLinkedList.reverse()
        doublyLinkedList.print()
    }

    private fun singlyLinkedList() {
        println("Starting Singly Linked Lists")
        val singlyLinkedList = SinglyLinkedList()
        singlyLinkedList.push(1)
        singlyLinkedList.push(2)
        singlyLinkedList.push(3)
        singlyLinkedList.print()
        singlyLinkedList.pop()
        singlyLinkedList.print()
        singlyLinkedList.shift()
        singlyLinkedList.print()
        singlyLinkedList.unshift(4)
        singlyLinkedList.unshift(5)
        singlyLinkedList.print()
        singlyLinkedList.push(6)
        singlyLinkedList.print()
        println(singlyLinkedList.get(0)?.data)
        println(singlyLinkedList.get(3)?.data)
        singlyLinkedList.set(0, 7)
        singlyLinkedList.set(3, 8)
        singlyLinkedList.print()
        singlyLinkedList.insert(0, 9)
        singlyLinkedList.print()
        singlyLinkedList.insert(4, 10)
        singlyLinkedList.print()
        singlyLinkedList.insert(3, 11)
        singlyLinkedList.print()
        singlyLinkedList.remove(0)
        singlyLinkedList.print()
        singlyLinkedList.remove(4)
        singlyLinkedList.print()
        singlyLinkedList.remove(2)
        singlyLinkedList.print()
        singlyLinkedList.reverse()
        singlyLinkedList.print()
    }
}