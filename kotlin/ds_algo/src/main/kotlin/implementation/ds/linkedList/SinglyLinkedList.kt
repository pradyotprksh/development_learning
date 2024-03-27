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

/*
Sorting insert we can only do on comparable data, so that's why this extension
will help in calling the insert on sorted linked list only when it's possible
to compare the data
 */
fun <T> SinglyLinkedListImplementation<T>.insertDataInSorted(data: T) where T: Comparable<T> {
    if (head == null) {
        append(data = data)
    } else {
        var prev: SLLNode<T>? = null
        var current = head
        while (current != null) {
            if (current.data < data) {
                prev = current
                current = current.next
            } else {
                break
            }
        }

        if (prev == null) {
            insertAtStart(data = data)
        } else {
            val newNode = SLLNode(data = data, next = null)
            prev.next = newNode
            newNode.next = current
        }
    }
}

class SinglyLinkedListImplementation<T>(
    data: T
) {
    var head: SLLNode<T>? = SLLNode(data = data, next = null)
        private set

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

    fun deleteStart(): SLLNode<T>? {
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
                    deleteStart()
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

    fun search(data: T): SLLNode<T>? {
        var current = head

        while (current != null) {
            if (current.data == data) {
                return current
            }
            current = current.next
        }

        return null
    }

    fun reverse() {
        var prev: SLLNode<T>? = null
        var current = head
        var next = head?.next

        while (current != null) {
            current.next = prev
            prev = current
            current = next
            next = next?.next
        }

        head = prev
    }

    fun nodeAtPositionFromEnd(pos: Int): SLLNode<T>? {
        if (pos > length()) {
            throw IllegalArgumentException("Position can't be greater than length")
        }
        var fast = head
        var count = 0
        while (count < pos) {
            ++count
            fast = fast?.next
        }
        var current = head
        while (fast != null) {
            current = current?.next
            fast = fast.next
        }
        return current
    }

    fun removeDuplicates() {
        var current = head
        while (current?.next != null) {
            if (current.data == current.next?.data) {
                current.next = current.next?.next
            } else {
                current = current.next
            }
        }

        /*
        Another approach, but slow

        if (head == null) {
            return
        } else {
            var current = head
            while (current != null) {
                if (current.data == current.next?.data) {
                    var temp = current
                    while (temp != null) {
                        if (temp.data != current.data) {
                            break
                        }
                        temp = temp.next
                    }
                    current.next = temp
                }
                current = current.next
            }
        }
        */
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

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting starting node [1]->...
        sll.printSLL() // null. Length: 0

        sll.reverse()
        sll.printSLL() // null. Length: 0

        sll.append(data = 1)

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        sll.printSLL() // null. Length: 0

        sll.append(data = 1)

        sll.reverse()
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 2)

        sll.printSLL() // [1]->[2]->null. Length: 2

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 2)
        sll.append(data = 3)
        sll.append(data = 4)
        sll.append(data = 5)
        sll.append(data = 6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(data = 7)
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

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [10]->...
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 10

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 9

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 8

        println("Deleting at position 1 ${sll.deleteAtPosition(pos = 1)?.onlyNodeString()}") // Deleting node [8]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 7

        println("Deleting at position 5 ${sll.deleteAtPosition(pos = 5)?.onlyNodeString()}") // Deleting node [9]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(7)
        sll.printSLL() // [7]->[1]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        println("Deleting at position 0 ${sll.deleteAtPosition(pos = 0)?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Deleting at position ${sll.length()} ${sll.deleteAtPosition(pos = sll.length())?.onlyNodeString()}") // Deleting node [6]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->null. Length: 5

        sll.append(6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Search 6. ${sll.search(data = 6)?.onlyNodeString()}") // Search 6. [6]->...
        println("Search 8. ${sll.search(data = 8)?.onlyNodeString()}") // Search 8. null

        sll.reverse()
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [6]->...
        sll.printSLL() // [5]->[4]->[3]->[2]->[1]->null. Length: 5

        sll.insertAtStart(6)
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->null. Length: 5

        sll.append(1)
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("2nd last node is ${sll.nodeAtPositionFromEnd(pos = 2)?.onlyNodeString()}") // 2nd last node is [2]->...
        println("6th last node is ${sll.nodeAtPositionFromEnd(pos = sll.length())?.onlyNodeString()}") // 6th last node is [6]->...

        sll.reverse()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtPosition(pos = 1, data = 2)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        sll.insertAtPosition(pos = 4, data = 4)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[4]->[5]->[6]->null. Length: 8

        sll.insertAtPosition(pos = 5, data = 4)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[4]->[4]->[5]->[6]->null. Length: 9

        sll.removeDuplicates()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()

        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)

        sll.printSLL() // [1]->[1]->[1]->[1]->[1]->null. Length: 5

        sll.removeDuplicates()
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 2)

        sll.printSLL() // [1]->[1]->[1]->[1]->[2]->null. Length: 5

        sll.removeDuplicates()
        sll.printSLL() // [1]->[2]->null. Length: 2

        sll.append(data = 3)
        sll.append(data = 4)
        sll.append(data = 5)
        sll.append(data = 6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertDataInSorted(data = 0)
        sll.insertDataInSorted(data = 4)
        sll.insertDataInSorted(data = 5)
        sll.insertDataInSorted(data = 7)
        sll.insertDataInSorted(data = 10)

        sll.printSLL() // [0]->[1]->[2]->[3]->[4]->[4]->[5]->[5]->[6]->[7]->[10]->null. Length: 11

        sll.removeDuplicates()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteStart()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6
    }
}