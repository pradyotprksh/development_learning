package implementation.ds.linkedList.sll

object SinglyLinkedList {
    fun implementation() {
        val sll = SinglyLinkedListImplementation(data = 1)

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting starting node [1]->...
        sll.printSLL() // null. Length: 0

        sll.reverse()
        sll.printSLL() // null. Length: 0

        sll.append(data = 1)

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        sll.printSLL() // null. Length: 0

        sll.append(data = 1)

        sll.reverse()
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 2)

        sll.printSLL() // [1]->[2]->null. Length: 2

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 2)
        sll.append(data = 3)
        sll.append(data = 4)
        sll.append(data = 5)
        sll.append(data = 6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(data = 7)
        sll.printSLL() // [7]->[1]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        sll.insertAtPosition(pos = 2, data = 8)
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[6]->null. Length: 8

        sll.insertAtPosition(pos = 7, data = 9)
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 9

        sll.insertAtPosition(pos = 0, data = 10)
        sll.printSLL() // [10]->[7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 10

        sll.insertAtPosition(pos = sll.length(), data = 11)
        sll.printSLL() // [10]->[7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 11

        /*
        sll.insertAtPosition(pos = -1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be less than 0
	    at implementation.ds.linkedList.sll.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
	    at implementation.ds.linkedList.sll.SinglyLinkedList.implementation(SinglyLinkedList.kt:104)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
	    at MainKt.main(Main.kt:16)
	    at MainKt.main(Main.kt)
         */
        /*
        sll.insertAtPosition(pos = sll.length() + 1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be greater than length
	    at implementation.ds.linkedList.sll.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
        at implementation.ds.linkedList.sll.SinglyLinkedList.implementation(SinglyLinkedList.kt:117)
        at MainKt$main$3.invoke(Main.kt:16)
        at MainKt$main$3.invoke(Main.kt:16)
        at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
        at MainKt.main(Main.kt:16)
        at MainKt.main(Main.kt)
        */

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [10]->...
        sll.printSLL() // [7]->[1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 10

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->[11]->null. Length: 9

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [2]->...
        sll.printSLL() // [1]->[8]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 8

        println("Deleting at position 1 ${sll.deleteAtPosition(pos = 1)?.onlyNodeString()}") // Deleting node [8]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[9]->[6]->null. Length: 7

        println("Deleting at position 5 ${sll.deleteAtPosition(pos = 5)?.onlyNodeString()}") // Deleting node [9]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtStart(7)
        sll.printSLL() // [7]->[1]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        println("Deleting at position 0 ${sll.deleteAtPosition(pos = 0)?.onlyNodeString()}") // Deleting node [7]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Deleting at position ${sll.length()} ${sll.deleteAtPosition(pos = sll.length())?.onlyNodeString()}") // Deleting node [6]->...
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->null. Length: 5

        sll.append(6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Search 6. ${sll.search(data = 6)?.onlyNodeString()}") // Search 6. [6]->...
        println("Search 8. ${sll.search(data = 8)?.onlyNodeString()}") // Search 8. null

        sll.reverse()
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("Deleting starting node ${sll.deleteStart()?.onlyNodeString()}") // Deleting node [6]->...
        sll.printSLL() // [5]->[4]->[3]->[2]->[1]->null. Length: 5

        sll.insertAtStart(6)
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("Deleting last node ${sll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->null. Length: 5

        sll.append(1)
        sll.printSLL() // [6]->[5]->[4]->[3]->[2]->[1]->null. Length: 6

        println("2nd last node is ${sll.nodeAtPositionFromEnd(pos = 2)?.onlyNodeString()}") // 2nd last node is [2]->...
        println("6th last node is ${sll.nodeAtPositionFromEnd(pos = sll.length())?.onlyNodeString()}") // 6th last node is [6]->...

        sll.reverse()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertAtPosition(pos = 1, data = 2)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[5]->[6]->null. Length: 7

        sll.insertAtPosition(pos = 4, data = 4)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[4]->[5]->[6]->null. Length: 8

        sll.insertAtPosition(pos = 5, data = 4)
        sll.printSLL() // [1]->[2]->[2]->[3]->[4]->[4]->[4]->[5]->[6]->null. Length: 9

        sll.removeDuplicates()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteLast()

        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)

        sll.printSLL() // [1]->[1]->[1]->[1]->[1]->null. Length: 5

        sll.removeDuplicates()
        sll.printSLL() // [1]->null. Length: 1

        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 1)
        sll.append(data = 2)

        sll.printSLL() // [1]->[1]->[1]->[1]->[2]->null. Length: 5

        sll.removeDuplicates()
        sll.printSLL() // [1]->[2]->null. Length: 2

        sll.append(data = 3)
        sll.append(data = 4)
        sll.append(data = 5)
        sll.append(data = 6)
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        sll.insertDataInSorted(data = 0)
        sll.insertDataInSorted(data = 4)
        sll.insertDataInSorted(data = 5)
        sll.insertDataInSorted(data = 7)
        sll.insertDataInSorted(data = 10)

        sll.printSLL() // [0]->[1]->[2]->[3]->[4]->[4]->[5]->[5]->[6]->[7]->[10]->null. Length: 11

        sll.removeDuplicates()
        sll.deleteLast()
        sll.deleteLast()
        sll.deleteStart()
        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Removing 1. ${sll.removeGivenData(1)?.onlyNodeString()}") // Removing 1. [1]->...
        sll.printSLL() // [2]->[3]->[4]->[5]->[6]->null. Length: 5

        println("Removing 4. ${sll.removeGivenData(4)?.onlyNodeString()}") // Removing 4. [4]->...
        sll.printSLL() // [2]->[3]->[5]->[6]->null. Length: 4

        println("Removing 6. ${sll.removeGivenData(6)?.onlyNodeString()}") // Removing 6. [6]->...
        sll.printSLL() // [2]->[3]->[5]->null. Length: 3

        println("Removing 8. ${sll.removeGivenData(8)?.onlyNodeString()}") // Removing 8. null
        sll.printSLL() // [2]->[3]->[5]->null. Length: 3

        sll.insertAtStart(1)
        sll.insertDataInSorted(4)
        sll.append(6)

        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        println("Is ${sll.head?.toString()} looped? - ${sll.isLooped()}") // Is [1]->[2]->[3]->[4]->[5]->[6]->null looped? - false

        val loop1 = SLLNode(
            data = 1,
            next = null
        )
        val loop2 = SLLNode(
            data = 2,
            next = null
        )
        val loop3 = SLLNode(
            data = 3,
            next = null
        )
        loop1.next = loop2
        loop2.next = loop3
        loop3.next = loop1

        println("Is [1]->[2]->[3]->{1} Looped? ${sll.isLooped(node = loop1)}") // Is [1]->[2]->[3]->{1} Looped? true

        println("Remove loop from [1]->[2]->[3]->{1} ${sll.isLooped(node = loop1, removeLoop = true)}. $loop1") // Remove loop from [1]->[2]->[3]->{1} true. [1]->[2]->[3]->null

        sll.printSLL() // [1]->[2]->[3]->[4]->[5]->[6]->null. Length: 6

        val head1 = SLLNode(
            data = 1,
            next = SLLNode(
                data = 3,
                next = SLLNode(
                    data = 4,
                    next = SLLNode(
                        data = 6,
                        next = null
                    )
                )
            )
        )

        val head2 = SLLNode(
            data = 2,
            next = SLLNode(
                data = 3,
                next = SLLNode(
                    data = 4,
                    next = SLLNode(
                        data = 5,
                        next = SLLNode(
                            data = 5,
                            next = SLLNode(
                                data = 7,
                                next = SLLNode(
                                    data = 8,
                                    next = null
                                )
                            )
                        )
                    )
                )
            )
        )

        print("Merging $head1 + $head2") // Merging [1]->[3]->[4]->[6]->null + [2]->[3]->[4]->[5]->[5]->[7]->[8]->null
        val mergedSLL = SLLUtils.mergeTwoSortedList(head1 = head1, head2 = head2)
        println(" = $mergedSLL") // = [1]->[2]->[3]->[3]->[4]->[4]->[5]->[5]->[6]->[7]->[8]->null

        val sumHead1 = SLLNode(
            data = 3,
            next = SLLNode(
                data = 4,
                next = SLLNode(
                    data = 3,
                    next = null
                )
            )
        )

        val sumHead2 = SLLNode(
            data = 5,
            next = SLLNode(
                data = 6,
                next = SLLNode(
                    data = 4,
                    next = null
                )
            )
        )

        print("Sum of $sumHead1 + $sumHead2")
        val sumSLL = SLLUtils.sumOfTwoNumbers(head1 = sumHead1, head2 = sumHead2)
        println(" = $sumSLL")
    }
}