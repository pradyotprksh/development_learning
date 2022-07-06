import hackerrank.HackerRank
import maze.MazeProblem

fun main() {
    when (Utils.readInput(
        "Please select the practice question:" +
                "\n1. Maze" +
                "\n2. HackerRank"
    )) {
        1 -> {
            val mazeProblem = MazeProblem()
            mazeProblem.createTheMaze()
            mazeProblem.showMazeDetails()
            mazeProblem.findTheExitNode()
        }
        2 -> {
            val hackerRank = HackerRank()
            hackerRank.solveAllProblems()
        }
        else -> println("Please enter a valid option.")
    }
}