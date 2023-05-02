package data_structures

import data_structures.graphs.Graphs
import data_structures.linked_lists.LinkedLists
import data_structures.stacks_queues.StacksQueues
import data_structures.trees.Trees

class DataStructures {
    fun startDataStructures() {
        println("Starting Data Structures")
        LinkedLists().startLinkedLists()
        StacksQueues().startStacksQueues()
        Trees().startTrees()
        Graphs().startGraphs()
    }
}