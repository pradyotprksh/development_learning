package linked_list.doubly

import linked_list.DoublyNode

interface DoublyLinkedList {
    fun startLinkedList()

    fun createNode(data: Int = 0): DoublyNode

    fun addAtLast(node: DoublyNode)

    fun addAtFront(node: DoublyNode)

    fun addAtAGivenNode(prevNode: DoublyNode, node: DoublyNode)

    fun deleteFirst(): DoublyNode?

    fun deleteLast(): DoublyNode?

    fun deleteMiddle(toDelete: DoublyNode): DoublyNode?

    fun showLinkedListDetails()

    fun deleteLinkedList()
}