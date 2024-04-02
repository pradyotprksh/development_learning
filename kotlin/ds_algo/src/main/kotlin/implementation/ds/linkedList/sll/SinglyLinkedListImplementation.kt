package implementation.ds.linkedList.sll

import java.lang.IllegalArgumentException

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