package implementation.ds.linkedList

import java.lang.IllegalArgumentException

data class SLLNode<T>(
    val data: T,
    var next: SLLNode<T>?
) {
    override fun toString(): String {
        return "[$data]->$next"
    }

    fun onlyNodeString(): String {
        return "[$data]->..."
    }
}

class SinglyLinkedListImplementation<T>(
    data: T
) {
    private var head: SLLNode<T>? = SLLNode(data = data, next = null)

    fun append(data: T) {
        val newNode = SLLNode(data = data, next = null)

        if (head == null) {
            head = newNode
        } else {
            var current = head
            while (current?.next != null) {
                current = current.next
            }
            current?.next = newNode
        }
    }

    fun insertAtStart(data: T) {
        val newNode = SLLNode(data = data, next = head)
        head = newNode
    }

    fun insertAtPosition(pos: Int, data: T) {
        if (pos > length()) {
            throw IllegalArgumentException("Position can't be greater than length")
        } else if (pos < 0) {
            throw IllegalArgumentException("Position can't be less than 0")
        } else {
            when (pos) {
                0 -> {
                    insertAtStart(data = data)
                }

                length() -> {
                    append(data = data)
                }

                else -> {
                    val newNode = SLLNode(data = data, next = null)
                    var prevNode = head
                    var position = 0
                    while (position < pos - 1) {
                        prevNode = prevNode?.next
                        ++position
                    }
                    newNode.next = prevNode?.next
                    prevNode?.next = newNode
                }
            }
        }
    }

    fun deleteAtStart(): SLLNode<T>? {
        val temp = head
        head = head?.next
        return temp
    }

    fun deleteLast(): SLLNode<T>? {
        if (head == null) {
            return null
        }
        if (head?.next == null) {
            val temp = head
            head = null
            return temp
        }

        var current = head
        while (current?.next?.next != null) {
            current = current.next
        }

        val temp = current?.next
        current?.next = null

        return temp
    }

    fun deleteAtPosition(pos: Int): SLLNode<T>? {
        return if (pos > length()) {
            throw IllegalArgumentException("Position can't be greater than length")
        } else if (pos < 0) {
            throw IllegalArgumentException("Position can't be less than 0")
        } else {
            when (pos) {
                0 -> {
                    deleteAtStart()
                }
                length() -> {
                    deleteLast()
                }
                else -> {
                    var position = 0
                    var current = head

                    while (position < pos - 1) {
                        ++position
                        current = current?.next
                    }

                    val temp = current?.next
                    current?.next = current?.next?.next
                    temp
                }
            }
        }
    }

    fun length(): Int {
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

        println("Deleting starting node ${sll.deleteAtStart()?.onlyNodeString()}") // Deleting starting node [1]->...
        sll.printSLL() // null. Length: 0

        sll.append(1)

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        sll.printSLL() // null. Length: 0

        sll.append(1)
        sll.append(2)

        sll.printSLL() // [1]->[2]->null. Length: 2

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->null. Length: 1

        sll.append(2)

        sll.append(3)
        sll.append(4)
        sll.append(5)
        sll.append(6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(7)
        sll.printSLL() // [7]->[1]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        sll.insertAtPosition(pos = 2, data = 8)
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[6]->null. Length: 8

        sll.insertAtPosition(pos = 7, data = 9)
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 9

        sll.insertAtPosition(pos = 0, data = 10)
        sll.printSLL() // [10]->[7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 10

        sll.insertAtPosition(pos = sll.length(), data = 11)
        sll.printSLL() // [10]->[7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 11

        /*
        sll.insertAtPosition(pos = -1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be less than 0
	    at implementation.ds.linkedList.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
	    at implementation.ds.linkedList.SinglyLinkedList.implementation(SinglyLinkedList.kt:104)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
	    at MainKt.main(Main.kt:16)
	    at MainKt.main(Main.kt)
         */

        /*
        sll.insertAtPosition(pos = sll.length() + 1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be greater than length
	    at implementation.ds.linkedList.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
        at implementation.ds.linkedList.SinglyLinkedList.implementation(SinglyLinkedList.kt:117)
        at MainKt$main$3.invoke(Main.kt:16)
        at MainKt$main$3.invoke(Main.kt:16)
        at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
        at MainKt.main(Main.kt:16)
        at MainKt.main(Main.kt)
        */

        println("Deleting starting node ${sll.deleteAtStart()?.onlyNodeString()}") // Deleting node [10]->...
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 10

        println("Deleting starting node ${sll.deleteAtStart()?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 9

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 8

        println("Deleting at position 1 ${sll.deleteAtPosition(1)?.onlyNodeString()}") // Deleting node [8]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 7

        println("Deleting at position 5 ${sll.deleteAtPosition(5)?.onlyNodeString()}") // Deleting node [9]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(7)
        sll.printSLL() // [7]->[1]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        println("Deleting at position 0 ${sll.deleteAtPosition(0)?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Deleting at position ${sll.length()} ${sll.deleteAtPosition(sll.length())?.onlyNodeString()}") // Deleting node [6]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->null. Length: 5

        sll.append(6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6
    }
}