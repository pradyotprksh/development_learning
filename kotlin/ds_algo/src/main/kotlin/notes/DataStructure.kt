package notes

object DataStructure {
    fun dsIntro() {
        println()
        println("=*=*=*= DATA STRUCTURES =*=*=*=")
        println("- Way to organize data.")
        println("-- Helps in processing the data.")
        println()
        println("Two kinds of data structures:")
        println("- Linear")
        println("-- Data elements arranged in a sequential manner. Each member is connected to the previous and next element. Makes it easier to traverse them. Eg. Array, Linked List, Stack, Queue, etc.")
        println("- Non-Linear")
        println("-- Data elements arranged in a non-sequential manner. Each member is connected to the previous and next element through paths. Traversal is a bit difficult and not easy to implement. Eg. Tree, Graph, etc.")
    }

    fun arrays(implementation: () -> Unit) {
        println("=*=*=*= ARRAYS =*=*=*=")
        println("- Collection of data, of specified type")
        println("- Contiguous memory allocations")
        println("- Size is fixed and once created it can't be modified")
        println("- Index starts from 0 and ends at n - 1, where n is the size - for 1D array")
        println("Let's see some examples: ")
        implementation()
    }

    fun singlyLinkedList(implementation: () -> Unit) {
        println()
        println("=*=*=*= SINGLY LINKED LIST =*=*=*=")
        println("- Stores collection of data")
        println("- Contains sequence of nodes")
        println("- Have a head, start of the linked list")
        println("- Contains data and the reference to the next node, if end then the value is null.")
        println("Let's see the implementation: ")
        implementation()
    }

    fun doublyLinkedList(implementation: () -> Unit) {
        println()
        println("=*=*=*= DOUBLY LINKED LIST =*=*=*=")
        println("- Two way linked list")
        println("- Stores collection of data")
        println("- Contains sequence of nodes")
        println("- Have a head, start of the linked list")
        println("- Contains data and the reference to the previous and next node, if end then the next value is null or if start then the previous value is null.")
        println("Let's see the implementation: ")
        implementation()
    }

    fun circularSinglyLinkedList(implementation: () -> Unit) {
        println()
        println("=*=*=*= CIRCULAR SINGLY LINKED LIST =*=*=*=")
        println("- Similar to SLL")
        println("- Circular Singly Linked List the last node points to the first node and not null")
        println("- Instead of head, we keep track of last node in Circular Singly Linked List, because it helps in insertion and deletion at constant time")
        println("Let's see the implementation: ")
        implementation()
    }

    fun stack(implementation: () -> Unit) {
        println()
        println("=*=*=*= STACK =*=*=*=")
        println("- Linear data structure used for storing data")
        println("- Insertion and Deletion are done at one end, called the top")
        println("- LIFO, last in first out")
        println("Let's see the implementation: ")
        implementation()
    }

    fun queue(implementation: () -> Unit) {
        println()
        println("=*=*=*= QUEUE =*=*=*=")
        println("- Linear data structure used for storing data")
        println("- Insertion is done at one end, called rear and deletion is done at one end, called front")
        println("- FIFO, first in first out")
        println("Let's see the implementation: ")
        implementation()
    }

    fun tree(
        implementation: () -> Unit,
        binaryTreeImplementation: () -> Unit,
    ) {
        println("=*=*=*= TREE =*=*=*=")
        println("- Non-Linear data structure")
        println("- Made of nodes and edges without any cycle")
        println("- Each node in the tree can point to n number of nodes")
        println("- Shows hierarchical structure with parent node as root and many levels of additional nodes")
        println("Let's see the implementation: ")
        implementation()
        binaryTree { binaryTreeImplementation() }
    }

    private fun binaryTree(implementation: () -> Unit) {
        println()
        println("=*=*=*= BINARY TREE =*=*=*=")
        println("- A tree is a binary tree when each node has only 0/1/2 children.")
        println("Let's see the implementation: ")
        implementation()
    }
}