package implementation.ds.linkedList.dll

object DoublyLinkedList {
    fun implementation() {
        val dll = DoublyLinkedListImplementation(data = 1)

        println("Deleting starting node ${dll.deleteStart()?.onlyNodeString()}") // Deleting starting node ...<-[1]->...
        dll.printDLL() // null. Length: 0

        dll.reverse()
        dll.printDLL() // null. Length: 0

        dll.append(data = 1)

        println("Deleting last node ${dll.deleteLast()?.onlyNodeString()}") // Deleting last node ...<-[1]->...
        dll.printDLL() // null. Length: 0

        dll.append(data = 1)

        dll.reverse()
        dll.printDLL() // [1]<->null. Length: 1

        dll.append(data = 2)

        dll.printDLL() // [1]<->[2]<->null. Length: 2

        println("Deleting last node ${dll.deleteLast()?.onlyNodeString()}") // Deleting last node ...<-[2]->...
        dll.printDLL() // [1]<->null. Length: 1

        dll.append(data = 2)
        dll.append(data = 3)
        dll.append(data = 4)
        dll.append(data = 5)
        dll.append(data = 6)
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        dll.insertAtStart(data = 7)
        dll.printDLL() // [7]<->[1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 7

        dll.insertAtPosition(pos = 2, data = 8)
        dll.printDLL() // [7]<->[1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 8

        dll.insertAtPosition(pos = 7, data = 9)
        dll.printDLL() // [7]<->[1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->null. Length: 9

        dll.insertAtPosition(pos = 0, data = 10)
        dll.printDLL() // [10]<->[7]<->[1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->null. Length: 10

        dll.insertAtPosition(pos = dll.length(), data = 11)
        dll.printDLL() // [10]<->[7]<->[1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->[11]<->null. Length: 11

        /*
        dll.insertAtPosition(pos = -1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be less than 0
	    at implementation.ds.linkedList.dll.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
	    at implementation.ds.linkedList.dll.SinglyLinkedList.implementation(SinglyLinkedList.kt:104)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at MainKt$main$3.invoke(Main.kt:16)
	    at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
	    at MainKt.main(Main.kt:16)
	    at MainKt.main(Main.kt)
         */
        /*
        dll.insertAtPosition(pos = dll.length() + 1, data = 12)

        Exception in thread "main" java.lang.IllegalArgumentException: Position can't be greater than length
	    at implementation.ds.linkedList.dll.SinglyLinkedListImplementation.insertAtPosition(SinglyLinkedList.kt:35)
        at implementation.ds.linkedList.dll.SinglyLinkedList.implementation(SinglyLinkedList.kt:117)
        at MainKt$main$3.invoke(Main.kt:16)
        at MainKt$main$3.invoke(Main.kt:16)
        at notes.DataStructure.singlyLinkedList(DataStructure.kt:36)
        at MainKt.main(Main.kt:16)
        at MainKt.main(Main.kt)
        */

        println("Deleting starting node ${dll.deleteStart()?.onlyNodeString()}") // Deleting starting node ...<-[10]->...
        dll.printDLL() // [7]<->[1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->[11]<->null. Length: 10

        println("Deleting starting node ${dll.deleteStart()?.onlyNodeString()}") // Deleting starting node ...<-[7]->...
        dll.printDLL() // [1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->[11]<->null. Length: 9

        println("Deleting last node ${dll.deleteLast()?.onlyNodeString()}") // Deleting last node ...<-[11]->...
        dll.printDLL() // [1]<->[8]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->null. Length: 8

        println("Deleting at position 1 ${dll.deleteAtPosition(pos = 1)?.onlyNodeString()}") // Deleting at position 1 ...<-[8]->...
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[9]<->[6]<->null. Length: 7

        println("Deleting at position 5 ${dll.deleteAtPosition(pos = 5)?.onlyNodeString()}") // Deleting at position 5 ...<-[9]->...
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        dll.insertAtStart(7)
        dll.printDLL() // [7]<->[1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 7

        println("Deleting at position 0 ${dll.deleteAtPosition(pos = 0)?.onlyNodeString()}") // Deleting at position 0 ...<-[7]->...
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        println("Deleting at position ${dll.length()} ${dll.deleteAtPosition(pos = dll.length())?.onlyNodeString()}") // Deleting at position 6 ...<-[6]->...
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->null. Length: 5

        dll.append(6)
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        println("Search 6. ${dll.search(data = 6)?.onlyNodeString()}") // Search 6. ...<-[6]->...
        println("Search 8. ${dll.search(data = 8)?.onlyNodeString()}") // Search 8. null

        dll.reverse()
        dll.printDLL() // [6]<->[5]<->[4]<->[3]<->[2]<->[1]<->null. Length: 6
        dll.printReverseDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null

        println("Deleting starting node ${dll.deleteStart()?.onlyNodeString()}") // Deleting node [6]->...
        dll.printDLL() // [5]<->[4]<->[3]<->[2]<->[1]<->null. Length: 5

        dll.insertAtStart(6)
        dll.printDLL() // [6]<->[5]<->[4]<->[3]<->[2]<->[1]<->null. Length: 6

        println("Deleting last node ${dll.deleteLast()?.onlyNodeString()}") // Deleting last node [1]->...
        dll.printDLL() // [6]<->[5]<->[4]<->[3]<->[2]<->null. Length: 5

        dll.append(1)
        dll.printDLL() // [6]<->[5]<->[4]<->[3]<->[2]<->[1]<->null. Length: 6

        println("2nd last node is ${dll.nodeAtPositionFromEnd(pos = 2)?.onlyNodeString()}") // 2nd last node is ...<-[2]->...
        println("6th last node is ${dll.nodeAtPositionFromEnd(pos = dll.length())?.onlyNodeString()}") // 6th last node is ...<-[6]->...

        dll.reverse()
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        dll.insertAtPosition(pos = 1, data = 2)
        dll.printDLL() // [1]<->[2]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 7

        dll.insertAtPosition(pos = 4, data = 4)
        dll.printDLL() // [1]<->[2]<->[2]<->[3]<->[4]<->[4]<->[5]<->[6]<->null. Length: 8

        dll.insertAtPosition(pos = 5, data = 4)
        dll.printDLL() // [1]<->[2]<->[2]<->[3]<->[4]<->[4]<->[4]<->[5]<->[6]<->null. Length: 9

        dll.removeDuplicates()
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        dll.deleteLast()
        dll.deleteLast()
        dll.deleteLast()
        dll.deleteLast()
        dll.deleteLast()

        dll.printDLL() // [1]<->null. Length: 1

        dll.append(data = 1)
        dll.append(data = 1)
        dll.append(data = 1)
        dll.append(data = 1)

        dll.printDLL() // [1]<->[1]<->[1]<->[1]<->[1]<->null. Length: 5

        dll.removeDuplicates()
        dll.printDLL() // [1]<->null. Length: 1

        dll.append(data = 1)
        dll.append(data = 1)
        dll.append(data = 1)
        dll.append(data = 2)

        dll.printDLL() // [1]<->[1]<->[1]<->[1]<->[2]<->null. Length: 5

        dll.removeDuplicates()
        dll.printDLL() // [1]<->[2]<->null. Length: 2

        dll.append(data = 3)
        dll.append(data = 4)
        dll.append(data = 5)
        dll.append(data = 6)
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        dll.insertDataInSorted(data = 0)
        dll.insertDataInSorted(data = 4)
        dll.insertDataInSorted(data = 5)
        dll.insertDataInSorted(data = 7)
        dll.insertDataInSorted(data = 10)
        dll.printDLL() // [0]<->[1]<->[2]<->[3]<->[4]<->[4]<->[5]<->[5]<->[6]<->[7]<->[10]<->null. Length: 11

        dll.removeDuplicates()
        dll.deleteLast()
        dll.deleteLast()
        dll.deleteStart()
        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        println("Removing 1. ${dll.removeGivenData(1)?.onlyNodeString()}") // Removing 1. ...<-[1]->...
        dll.printDLL() // [2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 5

        println("Removing 4. ${dll.removeGivenData(4)?.onlyNodeString()}") // Removing 4. ...<-[4]->...
        dll.printDLL() // [2]<->[3]<->[5]<->[6]<->null. Length: 4

        println("Removing 6. ${dll.removeGivenData(6)?.onlyNodeString()}") // Removing 6. ...<-[6]->...
        dll.printDLL() // [2]<->[3]<->[5]<->null. Length: 3

        println("Removing 8. ${dll.removeGivenData(8)?.onlyNodeString()}") // Removing 8. null
        dll.printDLL() // [2]<->[3]<->[5]<->null. Length: 3

        dll.insertAtStart(1)
        dll.insertDataInSorted(4)
        dll.append(6)

        dll.printDLL() // [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null. Length: 6

        println("Is ${dll.head?.toString()} looped? - ${dll.isLooped()}") // Is [1]<->[2]<->[3]<->[4]<->[5]<->[6]<->null looped? - false
    }
}