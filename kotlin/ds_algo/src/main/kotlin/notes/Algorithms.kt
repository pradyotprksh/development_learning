package notes

object Algorithms {
    fun algoIntro() {
        println()
        println("=*=*=*= ALGORITHMS =*=*=*=")
        println("- Set of instructions to perform a task or solve a problem.")
    }

    fun algoAnalysis() {
        println()
        println("Algorithm analysis helps in finding the best algorithm which runs fast and takes less memory.")
        println("Time and space complexity are the two factors which determine the performance of an algorithm.")
    }

    fun timeComplexity(example: () -> Unit) {
        println()
        println(" Time Complexity")
        println(" - The amount of time taken by an algorithm to run.")
        println(" Let's see an example below for finding the sum of n numbers using 2 approaches")
        example()
        println(" This is not the correct way of finding the time complexity. Because depending on the machine the time calculation might change and can't give the correct result.")
    }

    fun spaceComplexity() {
        println()
        println(" Space Complexity")
        println(" - The amount of space or memory taken by an algorithm to run.")
    }

    fun asymptoticAnalysisOfAlgorithm() {
        println()
        println("We usually don't go by the exact number of time and space. So we use a model to show how much time and space the algorithm is taking.")
        println("Asymptotic analysis is one of the ways to show how much time and space the algorithm is taking. Also based on the input size as well. As we increase the size the time and space will get affected.")
        println()
        println("Asymptotic notations help in determining the running time of an algorithm based on input size in -")
        println("1. Best case")
        println("2. Average case")
        println("3. Worst case")
        println("scenarios")
        println()
        println("Different notation for runtime analysis")
        println("Omega notation - best amount of time an algorithm to complete the task. Mainly the lower bound, best case for an algorithm.")
        println("Big O notation - Upper bound of an algorithm. Longest amount of time an algorithm will take, worst case for an algorithm.")
        println("Theta notation - middle bound of an algorithm, average case mainly.")
        println()
        println("Big O notation is the most used notation. Since it shows the longest amount of time for an algorithm for a given input.")
        bigONotation()
    }

    private fun bigONotation() {
        println()
        println("Rules for Big O Notation")
        println("- Single processor")
        println("- Sequential execution of statements")
        println("- Assignment operation takes 1 unit of time")
        println("- Return statement takes 1 unit of time")
        println("- Arithmetic operation takes 1 unit of time")
        println("- Logical operation takes 1 unit of time")
        println("- Other small/single operation takes 1 unit of time")
        println("- Drop lower order terms")
        println(" - Eg. T = n^2 + 3n + 1 ==> O(n^2)")
        println("- Drop constant multipliers")
        println(" - Eg. T = 3n^2 + 6n + 1 ==> O(n^2)")
    }
}