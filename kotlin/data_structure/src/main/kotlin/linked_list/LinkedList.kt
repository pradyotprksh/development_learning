package linked_list

interface LinkedList<NodeType> {
    fun startLinkedList()

    fun createNode(data: Int = 0): NodeType

    fun addAtLast(node: NodeType)

    fun addAtFront(node: NodeType)

    fun addAtAGivenNode(prevNode: NodeType, node: NodeType)

    fun deleteFirst(): NodeType?

    fun deleteLast(): NodeType?

    fun deleteMiddle(toDelete: NodeType): NodeType?

    fun showLinkedListDetails()

    fun deleteLinkedList()
}