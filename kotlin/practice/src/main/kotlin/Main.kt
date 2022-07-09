import hackerrank.HackerRank
import maze.MazeProblem
import random.Random

/**
 * Please keep in mind that these are not the best solution out there for the questions,
 * these are the solutions made by me and there can be n number of better solutions.
 *
 * You are welcome to raise the PR for making the solutions better and help others and me
 * for the same.
 */
fun main() {
    when (Utils.readInput(
        "Please select the practice question:" +
                "\n1. Maze" +
                "\n2. HackerRank" +
                "\n3. Random"
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
        3 -> {
            val random = Random()
            random.start()
        }
        else -> println("Please enter a valid option.")
    }
}