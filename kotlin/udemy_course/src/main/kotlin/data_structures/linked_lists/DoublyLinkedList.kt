package data_structures.linked_lists

class DoublyLinkedList {
    var head: DoublyNode? = null
        private set
    var tail: DoublyNode? = null
        private set
    var length = 0
        private set

    fun push(data: Int) {
        val node = DoublyNode(data = data)

        if (head == null && tail == null) {
            head = node
            tail = node
        } else {
            tail?.next = node
            node.prev = tail
            tail = node
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
            tail = tail?.prev
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
        if (head == tail) {
            head = null
            tail = null
        } else {
            head = head?.next
            head?.prev = null
        }
        --length
        return data
    }

    fun unshift(data: Int) {
        val node = DoublyNode(data = data)
        if (length == 0) {
            push(data)
        } else {
            node.next = head
            head?.prev = node
            head = node
            ++length
        }
    }

    fun get(pos: Int): DoublyNode? {
        if (head == null) {
            throw IllegalStateException("Linked List is Empty")
        }
        if (pos < 0 || pos >= length) {
            throw IllegalArgumentException("Position should be >= 0 and <= $length")
        }
        var temp = head
        var tempPos = pos

        while (temp != null && tempPos > 0) {
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

        var temp = head
        var tempPos = pos

        while (temp != null && tempPos > 0) {
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
                unshift(data)
            }

            length - 1 -> {
                push(data)
            }

            else -> {
                val node = DoublyNode(data = data)
                var temp = head
                var tempPos = pos

                while (temp != null && tempPos > 0) {
                    temp = temp.next
                    --tempPos
                }

                temp?.prev?.next = node
                node.prev = temp?.prev
                node.next = temp
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
                var temp = head
                var tempPos = pos

                while (temp != null && tempPos > 0) {
                    temp = temp.next
                    --tempPos
                }

                temp?.prev?.next = temp?.next
                temp?.next?.prev = temp?.prev
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

        while (temp != null) {
            temp.next = temp.prev
            temp.prev = next

            temp = next
            next = next?.next
        }
    }

    fun print() {
        println("${head.toString()} | Length=$length")
    }
}