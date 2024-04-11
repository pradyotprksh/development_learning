package implementation.ds.linkedList.dll

import java.lang.IllegalArgumentException

class DoublyLinkedListImplementation<T>(
    data: T,
) {
    var head: DLLNode<T>? = DLLNode(prev = null, data = data, next = null)
        private set
    var tail = head

    fun append(data: T) {
        val newNode = DLLNode(prev = null, data = data, next = null)
        if (head == null) {
            head = newNode
            tail = head
        } else {
            tail?.next = newNode
            newNode.prev = tail
            tail = tail?.next
        }
    }

    fun insertAtStart(data: T) {
        val newNode = DLLNode(prev = null, data = data, next = head)
        head?.prev = newNode
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
                    val newNode = DLLNode(prev = null, data = data, next = null)
                    var prevNode = head
                    var position = 0
                    while (position < pos - 1) {
                        prevNode = prevNode?.next
                        ++position
                    }
                    newNode.next = prevNode?.next
                    newNode.prev = prevNode
                    prevNode?.next?.prev = newNode
                    prevNode?.next = newNode
                }
            }
        }
    }

    fun deleteStart(): DLLNode<T>? {
        val temp = head
        head = head?.next
        head?.prev = null

        if (head == null) {
            tail = null
        }

        return temp
    }

    fun deleteLast(): DLLNode<T>? {
        if (head == null) {
            return null
        }
        val temp = tail

        tail = tail?.prev
        temp?.prev = null
        tail?.next = null

        if (tail == null) {
            head = null
        }

        return temp
    }

    fun deleteAtPosition(pos: Int): DLLNode<T>? {
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

                    current?.next = temp?.next
                    temp?.next?.prev = current

                    temp
                }
            }
        }
    }

    fun search(data: T): DLLNode<T>? {
        var start = head
        var end = tail

        while (start != end) {
            if (start?.data == data) {
                return start
            } else if (end?.data == data) {
                return end
            } else {
                start = start?.next
                end = end?.prev
            }
        }

        return null
    }

    fun reverse() {
        var prev: DLLNode<T>? = null
        var current = head
        var next = head?.next
        tail = head

        while (current != null) {
            current.next = prev
            current.prev = next
            next?.prev = next?.next
            prev = current
            current = next
            next = next?.next
        }
        head = prev
    }

    fun nodeAtPositionFromEnd(pos: Int): DLLNode<T>? {
        if (pos > length()) {
            throw IllegalArgumentException("Position can't be greater than length")
        }
        var current = tail
        var count = 0

        /*Not sure why the current is jumping to the previous node even if the while loop is
        done. Because of that putting - 1 on pos check. Will check on this latter.*/
        while (count < pos - 1) {
            count++
            current = current?.prev
        }

        return current
    }

    fun removeDuplicates() {
        var current = head
        while (current != null) {
            if (current.next == null) {
                tail = current
            }
            if (current.data == current.next?.data) {
                current.next = current.next?.next
                current.next?.prev = current
            } else {
                current = current.next
            }
        }
    }

    fun removeGivenData(data: T): DLLNode<T>? {
        return null
    }

    fun isLooped(node: DLLNode<T>? = null, removeLoop: Boolean = false): Boolean {
        return false
    }

    private fun removeLoop(head: DLLNode<T>?, slow: DLLNode<T>?) {

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

    fun printDLL() {
        println("$head. Length: ${length()}")
    }

    fun printReverseDLL() {
        var temp = tail
        while (temp != null) {
            print("[${temp.data}]<->")
            temp = temp.prev
        }
        print("null")
        println()
    }
}