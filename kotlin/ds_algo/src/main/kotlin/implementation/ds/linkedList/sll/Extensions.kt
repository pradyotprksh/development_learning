package implementation.ds.linkedList.sll

/*
Sorting insert we can only do on comparable data, so that's why this extension
will help in calling the insert on sorted linked list only when it's possible
to compare the data
 */
fun <T> SinglyLinkedListImplementation<T>.insertDataInSorted(data: T) where T: Comparable<T> {
    if (head == null) {
        append(data = data)
    } else {
        var prev: SLLNode<T>? = null
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
            val newNode = SLLNode(data = data, next = null)
            prev.next = newNode
            newNode.next = current
        }
    }
}