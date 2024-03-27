import examples.TimeComplexity
import implementation.ds.Arrays
import implementation.ds.linkedList.SinglyLinkedList
import notes.Algorithms
import notes.DataStructure

fun main() {
    Algorithms.algoIntro()
    Algorithms.algoAnalysis()
    Algorithms.timeComplexity { TimeComplexity.timeComplexityExample() }
    Algorithms.spaceComplexity()
    Algorithms.asymptoticAnalysisOfAlgorithm()

    DataStructure.dsIntro()
    DataStructure.arrays { Arrays.implementation() }
    DataStructure.singlyLinkedList { SinglyLinkedList.implementation() }
}