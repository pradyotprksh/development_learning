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
    }
}