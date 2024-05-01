import examples.TimeComplexity
import implementation.ds.Arrays
import implementation.ds.linkedList.dll.DoublyLinkedList
import implementation.ds.linkedList.sll.SinglyLinkedList
import implementation.ds.linkedList.sll.csll.CircularSinglyLinkedList
import implementation.ds.queue.Queue
import implementation.ds.stack.Stack
import implementation.ds.tree.binaryTree.BinaryTree
import implementation.ds.tree.Tree
import notes.Algorithms
import notes.DataStructure

fun main() {
    println("Learning from = https://youtu.be/2ZLl8GAk1X4?si=ma0QRgdrVdQn-Wp6")

    Algorithms.algoIntro()
    Algorithms.algoAnalysis()
    Algorithms.timeComplexity { TimeComplexity.timeComplexityExample() }
    Algorithms.spaceComplexity()
    Algorithms.asymptoticAnalysisOfAlgorithm()

    DataStructure.dsIntro()
    println()
    println("=*=*=*= Linear Data Structures =*=*=*=")
    DataStructure.arrays { Arrays.implementation() }
    DataStructure.singlyLinkedList { SinglyLinkedList.implementation() }
    DataStructure.doublyLinkedList { DoublyLinkedList.implementation() }
    DataStructure.circularSinglyLinkedList { CircularSinglyLinkedList.implementation() }
    DataStructure.stack { Stack.implementation() }
    DataStructure.queue { Queue.implementation() }
    println()
    println("=*=*=*= Non-Linear Data Structures =*=*=*=")
    DataStructure.tree(
        implementation = { Tree.implementation() },
        binaryTreeImplementation = { BinaryTree.implementation() },
    )
}