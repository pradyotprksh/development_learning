import examples.TimeComplexity
import implementation.ds.Arrays
import implementation.ds.linkedList.dll.DoublyLinkedList
import implementation.ds.linkedList.sll.SinglyLinkedList
import implementation.ds.linkedList.sll.csll.CircularSinglyLinkedList
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
    DataStructure.arrays { Arrays.implementation() }
    DataStructure.singlyLinkedList { SinglyLinkedList.implementation() }
    DataStructure.doublyLinkedList { DoublyLinkedList.implementation() }
    DataStructure.circularSinglyLinkedList { CircularSinglyLinkedList.implementation() }
}