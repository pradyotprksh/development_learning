package linked_list.singly

import Utils

class SinglyLinkedListImpl: SinglyLinkedList {
    private var head: Node? = null
    private var linkedListSize: Int = 0

    override fun startLinkedList() {
        println("Hello User, let's start with singly linked list")
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

    override fun createNode(data: Int): Node {
        println("Creating node with data $data")
        return Node(data = data, next = null)
    }

    override fun addAtLast(node: Node) {
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

    override fun addAtFront(node: Node) {
        ++linkedListSize
        println("Adding $node at the front of the linked list")
        if (head == null) {
            head = node
        } else {
            node.next = head
            head = node
        }
        showLinkedListDetails()
    }

    override fun addAtAGivenNode(prevNode: Node, node: Node) {
        ++linkedListSize
        println("Adding $node after $prevNode in the linked list")
        if (head == null) {
            println("OOPS!! SinglyLinkedList is empty.")
        } else {
            var temp = head
            while (temp?.data != prevNode.data && temp != null) {
                temp = temp.next
            }
            if (temp != null) {
                node.next = temp.next
                temp.next = node
                showLinkedListDetails()
            } else {
                println("OOPS!! No node with $prevNode details was found")
            }
        }
    }

    override fun deleteFirst(): Node? {
        var temp = head
        if (head == null) {
            println("OOPS!! Linked list is empty. Please add few elements before deleting.")
        } else {
            --linkedListSize
            if (head?.next == null) {
                println("Only one element is available in the SinglyLinkedList, deleting the SinglyLinkedList data")
                deleteLinkedList()
            } else {
                println("Deleting $temp node at first from linked list")
                temp = head
                head = head?.next
                showLinkedListDetails()
            }
        }
        return temp
    }

    override fun deleteLast(): Node? {
        var temp = head
        if (head == null) {
            println("OOPS!! Linked list is empty. Please add few elements before deleting.")
        } else {
            --linkedListSize
            if (head?.next == null) {
                println("Only one element is available in the SinglyLinkedList, deleting the linked list data")
                deleteLinkedList()
            } else {
                while (temp?.next?.next != null) {
                    temp = temp.next
                }
                println("Deleting ${temp?.next} node at end from linked list")
                temp?.next = null
                showLinkedListDetails()
            }
        }
        return temp
    }

    override fun deleteMiddle(toDelete: Node): Node? {
        var temp = head
        if (head == null) {
            println("OOPS!! Linked list is empty. Please add few elements before deleting.")
        } else {
            if (head?.next == null) {
                println("Only one element is available in the SinglyLinkedList, deleting the linked list data")
                deleteLinkedList()
            } else {
                while (temp?.next?.data != toDelete.data && temp != null) {
                    temp = temp.next
                }
                if (temp != null) {
                    println("Deleting ${temp.next} node from linked list")
                    --linkedListSize
                    temp.next = temp.next?.next
                    showLinkedListDetails()
                } else {
                    println("OOPS!! No node with $toDelete details was found")
                }
            }
        }
        return temp
    }

    override fun showLinkedListDetails() {
        if (head != null) {
            var temp = head
            var items = ""
            while (temp != null) {
                items = if (items.isEmpty()) {
                    "${temp.data}"
                } else {
                    "$items --> ${temp.data}"
                }
                temp = temp.next
            }
            println(items.plus(" --> null"))
        } else {
            println("OOPS!! Linked list is empty. Please add few elements.")
        }
    }

    override fun deleteLinkedList() {
        println("Deleting linked list, and clearing the data")
        head = null
    }
}