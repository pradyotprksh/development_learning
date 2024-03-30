package implementation.ds.linkedList

import java.lang.Exception
import java.lang.IllegalArgumentException

data class SLLNode<T>(
    val data: T,
    var next: SLLNode<T>?
) {
    override fun toString(): String {
        return try {
            "[$data]->$next"
        } catch (e: Exception) {
            "LOOP DETECTED"
        }
    }

    fun onlyNodeString(): String {
        return "[$data]->..."
    }
}

fun sumOfTwoNumbers(head1: SLLNode<Int>, head2: SLLNode<Int>): SLLNode<Int> {
    val dummy = SLLNode(data = 0, next = null)
    var tail: SLLNode<Int>? = dummy

    var tempHead1: SLLNode<Int>? = head1
    var tempHead2: SLLNode<Int>? = head2
    var carry = 0

    while (tempHead1 != null && tempHead2 != null) {
        val sum = tempHead1.data + tempHead2.data + carry
        val data = sum % 10
        carry = sum / 10
        tail?.next = SLLNode(data = data, next = null)
        tail = tail?.next
        tempHead1 = tempHead1.next
        tempHead2 = tempHead2.next
    }

    if (tempHead1 != null) {
        tail?.next = SLLNode(data = tempHead1.data + carry, next = tempHead1.next)
    } else if (tempHead2 != null) {
        tail?.next = SLLNode(data = tempHead2.data + carry, next = tempHead2.next)
    }

    return dummy.next ?: dummy
}

fun <T> mergeTwoSortedList(head1: SLLNode<T>, head2: SLLNode<T>): SLLNode<T> where T: Comparable<T> {
    val dummy = SLLNode(data = head1.data, next = null)
    var tail = dummy

    var tempHead1: SLLNode<T>? = head1
    var tempHead2: SLLNode<T>? = head2

    while (tempHead1 != null && tempHead2 != null) {
        if (tempHead1.data > tempHead2.data) {
            tail.next = tempHead2
            tempHead2 = tempHead2.next
        } else {
            tail.next = tempHead1
            tempHead1 = tempHead1.next
        }
        tail = tail.next!!
    }

    if (tempHead1 != null) {
        tail.next = tempHead1
    } else {
        tail.next = tempHead2
    }

    return dummy.next ?: dummy
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

    fun removeGivenData(data: T): SLLNode<T>? {
        if (head != null) {
            var prev: SLLNode<T>? = null
            var current = head

            while (current != null) {
                if (current.data != data) {
                    prev = current
                    current = current.next
                } else {
                    break
                }
            }

            if (current != null) {
                return if (prev == null) {
                    deleteStart()
                } else {
                    prev.next = current.next
                    current
                }
            }
        }
        return null
    }

    fun isLooped(node: SLLNode<T>? = null, removeLoop: Boolean = false): Boolean {
        val takeHead = node ?: head

        if (takeHead != null) {
            var slow = takeHead
            var fast = takeHead.next

            while (fast != null && slow != null) {
                if (slow == fast) {
                    if (removeLoop) {
                        removeLoop(head = node, slow = slow)
                    }
                    return true
                } else {
                    slow = slow.next
                    fast = fast.next?.next
                }
            }
        }
        return false
    }

    private fun removeLoop(head: SLLNode<T>?, slow: SLLNode<T>?) {
        var temp = head
        var tempSlow = slow
        while (tempSlow?.next != temp?.next) {
            temp = temp?.next
            tempSlow = slow?.next
        }
        slow?.next = null
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

        println("Removing 1. ${sll.removeGivenData(1)?.onlyNodeString()}") // Removing 1. [1]->...
        sll.printSLL() // [2]->[3]->[4]->[5]->[6]->null. Length: 5

        println("Removing 4. ${sll.removeGivenData(4)?.onlyNodeString()}") // Removing 4. [4]->...
        sll.printSLL() // [2]->[3]->[5]->[6]->null. Length: 4

        println("Removing 6. ${sll.removeGivenData(6)?.onlyNodeString()}") // Removing 6. [6]->...
        sll.printSLL() // [2]->[3]->[5]->null. Length: 3

        println("Removing 8. ${sll.removeGivenData(8)?.onlyNodeString()}") // Removing 8. null
        sll.printSLL() // [2]->[3]->[5]->null. Length: 3

        sll.insertAtStart(1)
        sll.insertDataInSorted(4)
        sll.append(6)

        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Is ${sll.head?.toString()} looped? - ${sll.isLooped()}") // Is [1]->[2]->[3]->[4]->[5]->[6]->null looped? - false

        val loop1 = SLLNode(
            data = 1,
            next = null
        )
        val loop2 = SLLNode(
            data = 2,
            next = null
        )
        val loop3 = SLLNode(
            data = 3,
            next = null
        )
        loop1.next = loop2
        loop2.next = loop3
        loop3.next = loop1

        println("Is [1]->[2]->[3]->{1} Looped? ${sll.isLooped(node = loop1)}") // Is [1]->[2]->[3]->{1} Looped? true

        println("Remove loop from [1]->[2]->[3]->{1} ${sll.isLooped(node = loop1, removeLoop = true)}. $loop1") // Remove loop from [1]->[2]->[3]->{1} true. [1]->[2]->[3]->null

        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        val head1 = SLLNode(
            data = 1,
            next = SLLNode(
                data = 3,
                next = SLLNode(
                    data = 4,
                    next = SLLNode(
                        data = 6,
                        next = null
                    )
                )
            )
        )

        val head2 = SLLNode(
            data = 2,
            next = SLLNode(
                data = 3,
                next = SLLNode(
                    data = 4,
                    next = SLLNode(
                        data = 5,
                        next = SLLNode(
                            data = 5,
                            next = SLLNode(
                                data = 7,
                                next = SLLNode(
                                    data = 8,
                                    next = null
                                )
                            )
                        )
                    )
                )
            )
        )

        print("Merging $head1 + $head2") // Merging [1]->[3]->[4]->[6]->null + [2]->[3]->[4]->[5]->[5]->[7]->[8]->null
        val mergedSLL = mergeTwoSortedList(head1 = head1, head2 = head2)
        println(" = $mergedSLL") // = [1]->[2]->[3]->[3]->[4]->[4]->[5]->[5]->[6]->[7]->[8]->null

        val sumHead1 = SLLNode(
            data = 3,
            next = SLLNode(
                data = 4,
                next = SLLNode(
                    data = 3,
                    next = null
                )
            )
        )

        val sumHead2 = SLLNode(
            data = 5,
            next = SLLNode(
                data = 6,
                next = SLLNode(
                    data = 4,
                    next = null
                )
            )
        )

        print("Sum of $sumHead1 + $sumHead2")
        val sumSLL = sumOfTwoNumbers(head1 = sumHead1, head2 = sumHead2)
        println(" = $sumSLL")
    }
}