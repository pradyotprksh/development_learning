package implementation.ds.linkedList.sll.csll

import implementation.ds.linkedList.sll.SLLNode

class CircularSinglyLinkedListImplementation<T>(
    data: T,
) {
    private var last: SLLNode<T>? = SLLNode(
        data = data,
        next = null,
    )
    private var length = 1

    init {
        /*
        This will be required in CSLL because we have
        to map the last node to the initial node. So when the
        CSLL is created at that time the head next will be pointed
        to the head itself.
         */
        last?.next = last
    }

    fun append(data: T) {
        val newNode = SLLNode(data = data, next = null)
        if (last == null) {
            last = newNode
            last?.next = last
        } else {
            newNode.next = last?.next
            last?.next = newNode
            last = last?.next
        }
        ++length
    }

    fun insertAtStart(data: T) {
        val newNode = SLLNode(data = data, next = null)
        if (last == null) {
            last = newNode
        } else {
            newNode.next = last?.next
        }
        last?.next = newNode
        ++length
    }

    fun removeFirstNode(): SLLNode<T>? {
        if (last == null) {
            return null
        }

        val temp = last?.next

        if (last == temp) {
            last = null
        } else {
            last?.next = temp?.next
            temp?.next = null
        }

        --length

        return temp
    }

    private fun isEmpty() = length == 0

    fun printCsll() {
        if (!isEmpty()) {
            if (length != 1) {
                var head = last?.next
                while (head != null && head != last) {
                    print("[${head.data}]->")
                    head = head.next
                }
                print("[${head?.data}]->")
            } else {
                print("[${last?.data}]->")
            }
            println("[HEAD]. Length: $length")
        } else {
            println("CSLL is empty.")
        }
    }
}