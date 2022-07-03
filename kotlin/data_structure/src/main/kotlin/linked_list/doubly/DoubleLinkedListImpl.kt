package linked_list.doubly

import linked_list.DoublyNode

class DoubleLinkedListImpl: DoublyLinkedList {
    private var head: DoublyNode? = null
    private var linkedListSize: Int = 0

    override fun startLinkedList() {
        println("Hello User, let's start with doubly linked list")
        var input = -1
        while (input != 9) {
            input = Utils.readInput(
                "Please choose any one of the option" +
                        "\n1 > Add at last" +
                        "\n2 > Add at front" +
                        "\n3 > Add after a given node" +
                        "\n4 > Delete first" +
                        "\n5 > Delete last" +
                        "\n6 > Delete the middle" +
                        "\n7 > Show linked list details" +
                        "\n8 > Done"
            )
            when (input) {
                1 -> {
                    val item = Utils.readInput("Enter the new data")
                    addAtLast(createNode(item))
                }
                2 -> {
                    val item = Utils.readInput("Enter the new data")
                    addAtFront(createNode(item))
                }
                3 -> {
                    val prevItem = Utils.readInput("Enter the node after which you want to add")
                    val item = Utils.readInput("Enter the new data")
                    addAtAGivenNode(createNode(prevItem), createNode(item))
                }
                4 -> deleteFirst()
                5 -> deleteLast()
                6 -> {
                    val item = Utils.readInput("Enter the node you want to delete")
                    deleteMiddle(createNode(item))
                }
                7 -> showLinkedListDetails()
                8 -> {
                    println("Linked list details at the end is:")
                    showLinkedListDetails()
                    deleteLinkedList()
                    input = 9
                }
                else -> println("Please enter a valid option.")
            }
        }
    }

    override fun createNode(data: Int): DoublyNode {
        println("Creating node with data $data")
        return DoublyNode(prev = null, data = data, next = null)
    }

    override fun addAtLast(node: DoublyNode) {
        ++linkedListSize
        println("Adding $node at the end of the linked list")
        if (head == null) {
            head = node
        } else {
            var temp = head
            while (temp?.next != null) {
                temp = temp.next
            }
            temp?.next = node
        }
        showLinkedListDetails()
    }

    override fun addAtFront(node: DoublyNode) {
        ++linkedListSize
        println("Adding $node at the front of the linked list")
        if (head == null) {
            head = node
        } else {
            head?.prev = node
            node.next = head
            head = node
        }
        showLinkedListDetails()
    }

    override fun addAtAGivenNode(prevNode: DoublyNode, node: DoublyNode) {
        println("Adding $node after $prevNode in the linked list")
        if (head == null) {
            println("OOPS!! SinglyLinkedList is empty.")
        } else {
            var temp = head
            while (temp?.data != prevNode.data && temp != null) {
                temp = temp.next
            }
            if (temp != null) {
                ++linkedListSize
                val nextNode = temp.next
                nextNode?.prev = node
                node.next = nextNode
                temp.next = node
                node.prev = temp
                showLinkedListDetails()
            } else {
                println("OOPS!! No node with $prevNode details was found")
            }
        }
    }

    override fun deleteFirst(): DoublyNode? {
        TODO("Not yet implemented")
    }

    override fun deleteLast(): DoublyNode? {
        TODO("Not yet implemented")
    }

    override fun deleteMiddle(toDelete: DoublyNode): DoublyNode? {
        TODO("Not yet implemented")
    }

    override fun showLinkedListDetails() {
        if (head != null) {
            var temp = head
            var items = ""
            while (temp != null) {
                items = if (items.isEmpty()) {
                    "${temp.data}"
                } else {
                    "$items <==> ${temp.data}"
                }
                temp = temp.next
            }
            println(items.plus(" <==> null"))
        } else {
            println("OOPS!! Linked list is empty. Please add few elements.")
        }
    }

    override fun deleteLinkedList() {
        println("Deleting linked list, and clearing the data")
        head = null
        linkedListSize = 0
    }
}