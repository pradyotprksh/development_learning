package implementation.ds.linkedList.dll

/*
Sorting insert we can only do on comparable data, so that's why this extension
will help in calling the insert on sorted linked list only when it's possible
to compare the data
 */
fun <T> DoublyLinkedListImplementation<T>.insertDataInSorted(data: T) where T: Comparable<T> {
    if (head == null) {
        append(data = data)
    } else {
        var prev: DLLNode<T>? = null
        var current = head
        while (current != null) {
            if (current.data < data) {
                prev = current
                current = current.next
            } else {
                break
            }
        }

        if (prev == null) {
            insertAtStart(data = data)
        } else {
            val newNode = DLLNode(prev = null, data = data, next = null)
            prev.next = newNode
            newNode.prev = prev
            newNode.next = current
            current?.prev = newNode

            if (newNode.next == null) {
                tail = newNode
            }
        }
    }
}