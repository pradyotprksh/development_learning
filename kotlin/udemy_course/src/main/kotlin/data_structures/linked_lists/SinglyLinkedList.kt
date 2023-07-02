package data_structures.linked_lists

class SinglyLinkedList {
    var head: ListNode? = null
        private set
    var tail: ListNode? = null
        private set
    var length = 0
        private set

    fun push(data: Int) {
        val listNode = ListNode(data = data)
        if (head == null && tail == null) {
            head = listNode
            tail = listNode
        } else {
            tail?.next = listNode
            tail = listNode
        }
        ++length
    }

    fun pop(): Int? {
        if (tail == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        val data = tail?.data
        if (head == tail) {
            head = null
            tail = null
        } else {
            var temp = head
            while (temp?.next != tail) {
                temp = temp?.next
            }
            tail = temp
            tail?.next = null
        }
        --length
        return data
    }

    fun shift(): Int? {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        val data = head?.data
        head = head?.next
        --length
        return data
    }

    fun unshift(data: Int) {
        val listNode = ListNode(data = data)
        listNode.next = head
        head = listNode
        if (tail == null) {
            tail = head
        }
        ++length
    }

    fun get(pos: Int): ListNode? {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        if (pos < 0 || pos >= length) {
            throw IllegalArgumentException("Position should be >= 0 and <= $length")
        }

        var tempPos = pos
        var temp = head
        while (tempPos > 0 && temp != null) {
            temp = temp.next
            --tempPos
        }

        return temp
    }

    fun set(pos: Int, data: Int) {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        if (pos < 0 || pos >= length) {
            throw IllegalArgumentException("Position should be >= 0 and <= $length")
        }

        var tempPos = pos
        var temp = head
        while (tempPos > 0 && temp != null) {
            temp = temp.next
            --tempPos
        }

        temp?.data = data
    }

    fun insert(pos: Int, data: Int) {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        if (pos < 0 || pos >= length) {
            throw IllegalArgumentException("Position should be >= 0 and <= $length")
        }

        when (pos) {
            0 -> {
                unshift(data = data)
            }

            length - 1 -> {
                push(data = data)
            }

            else -> {
                val listNode = ListNode(data = data)

                var tempPos = pos
                var temp = head
                var prev: ListNode? = null

                while (tempPos > 0 && temp != null) {
                    prev = temp
                    temp = temp.next
                    --tempPos
                }

                listNode.next = temp
                prev?.next = listNode
                ++length
            }
        }
    }

    fun remove(pos: Int): Int? {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        if (pos < 0 || pos >= length) {
            throw IllegalArgumentException("Position should be >= 0 and <= $length")
        }

        when (pos) {
            0 -> {
                return shift()
            }

            length - 1 -> {
                return pop()
            }

            else -> {
                var tempPos = pos
                var temp = head
                var prev: ListNode? = null

                while (tempPos > 0 && temp != null) {
                    prev = temp
                    temp = temp.next
                    --tempPos
                }

                prev?.next = temp?.next
                temp?.next = null

                --length

                return temp?.data
            }
        }
    }

    fun reverse() {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }

        var temp = head
        head = tail
        tail = temp

        var next = temp?.next
        var prev: ListNode? = null

        while (temp != null) {
            temp.next = prev
            prev = temp
            temp = next
            next = next?.next
        }
    }

    fun print() {
        println("${head.toString()} | Length=$length")
    }
}