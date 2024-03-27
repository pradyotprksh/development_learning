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
        println()
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
}