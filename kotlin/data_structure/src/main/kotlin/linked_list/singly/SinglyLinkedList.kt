package linked_list.singly

import linked_list.Node

interface SinglyLinkedList {
    fun startLinkedList()

    fun createNode(data: Int = 0): Node

    fun addAtLast(node: Node)

    fun addAtFront(node: Node)

    fun addAtAGivenNode(prevNode: Node, node: Node)

    fun deleteFirst(): Node?

    fun deleteLast(): Node?

    fun deleteMiddle(toDelete: Node): Node?

    fun showLinkedListDetails()

    fun deleteLinkedList()
}