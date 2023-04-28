package data_structures.linked_lists

import java.lang.IllegalArgumentException

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
        val node = Node(data = data)
        node.next = head
        head = node
        if (tail == null) {
            tail = head
        }
        ++length
    }

    fun get(pos: Int): Node? {
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
                val node = Node(data = data)

                var tempPos = pos
                var temp = head
                var prev: Node? = null

                while (tempPos > 0 && temp != null) {
                    prev = temp
                    temp = temp.next
                    --tempPos
                }

                node.next = temp
                prev?.next = node
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
                var prev: Node? = null

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
        var prev: Node? = null

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