package implementation.ds.linkedList.sll

object SLLUtils {
    fun sumOfTwoNumbers(head1: SLLNode<Int>, head2: SLLNode<Int>): SLLNode<Int> {
        val dummy = SLLNode(data = 0, next = null)
        var tail: SLLNode<Int>? = dummy

        var tempHead1: SLLNode<Int>? = head1
        var tempHead2: SLLNode<Int>? = head2
        var carry = 0

        while (tempHead1 != null && tempHead2 != null) {
            val sum = tempHead1.data + tempHead2.data + carry
            val data = sum % 10
            carry = sum / 10
            tail?.next = SLLNode(data = data, next = null)
            tail = tail?.next
            tempHead1 = tempHead1.next
            tempHead2 = tempHead2.next
        }

        if (tempHead1 != null) {
            tail?.next = SLLNode(data = tempHead1.data + carry, next = tempHead1.next)
        } else if (tempHead2 != null) {
            tail?.next = SLLNode(data = tempHead2.data + carry, next = tempHead2.next)
        }

        return dummy.next ?: dummy
    }

    fun <T> mergeTwoSortedList(head1: SLLNode<T>, head2: SLLNode<T>): SLLNode<T> where T: Comparable<T> {
        val dummy = SLLNode(data = head1.data, next = null)
        var tail = dummy

        var tempHead1: SLLNode<T>? = head1
        var tempHead2: SLLNode<T>? = head2

        while (tempHead1 != null && tempHead2 != null) {
            if (tempHead1.data > tempHead2.data) {
                tail.next = tempHead2
                tempHead2 = tempHead2.next
            } else {
                tail.next = tempHead1
                tempHead1 = tempHead1.next
            }
            tail = tail.next!!
        }

        if (tempHead1 != null) {
            tail.next = tempHead1
        } else {
            tail.next = tempHead2
        }

        return dummy.next ?: dummy
    }
}