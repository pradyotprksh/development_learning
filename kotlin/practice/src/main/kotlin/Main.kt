import practice.maze.MazeProblem

fun main() {
    when (Utils.readInput(
        "Please select the practice question:" +
                "\n1. Maze"
    )) {
        1 -> {
            val mazeProblem = MazeProblem()
            mazeProblem.createTheMaze()
            mazeProblem.showMazeDetails()
            mazeProblem.findTheExitNode()
        }
        else -> println("Please enter a valid option.")
    }
}