import examples.TimeComplexity
import implementation.ds.Arrays
import notes.Algorithms
import notes.DataStructure

fun main() {
    DataStructure.dsIntro()

    Algorithms.algoIntro()

    Algorithms.algoAnalysis()

    Algorithms.timeComplexity { TimeComplexity.timeComplexityExample() }
    Algorithms.spaceComplexity()

    Algorithms.asymptoticAnalysisOfAlgorithm()

    DataStructure.arrays { Arrays.implementation() }

    DataStructure.singlyLinkedList {  }
}