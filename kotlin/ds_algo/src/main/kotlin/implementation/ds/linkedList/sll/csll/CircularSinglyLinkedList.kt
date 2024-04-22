package implementation.ds.linkedList.sll.csll

object CircularSinglyLinkedList {
    fun implementation() {
        val csll = CircularSinglyLinkedListImplementation(data = 3)

        csll.printCsll() // [3]->[HEAD]. Length: 1

        csll.append(4)
        csll.append(5)
        csll.append(6)

        csll.printCsll() // [3]->[4]->[5]->[6]->[HEAD]. Length: 4

        csll.insertAtStart(2)
        csll.insertAtStart(1)

        csll.printCsll() // [1]->[2]->[3]->[4]->[5]->[6]->[HEAD]. Length: 6

        csll.insertAtStart(7)
        csll.insertAtStart(8)

        csll.printCsll() // [8]->[7]->[1]->[2]->[3]->[4]->[5]->[6]->[HEAD]. Length: 8

        csll.removeFirstNode()
        csll.removeFirstNode()

        csll.printCsll() // [1]->[2]->[3]->[4]->[5]->[6]->[HEAD]. Length: 6

        csll.removeFirstNode()
        csll.removeFirstNode()
        csll.removeFirstNode()
        csll.removeFirstNode()
        csll.removeFirstNode()

        csll.printCsll() // [6]->[HEAD]. Length: 1

        csll.removeFirstNode()
        csll.printCsll() // CSLL is empty.

        csll.append(1)
        csll.append(2)
        csll.append(3)
        csll.append(4)
        csll.append(5)
        csll.append(6)

        csll.printCsll() // [1]->[2]->[3]->[4]->[5]->[6]->[HEAD]. Length: 6
    }
}