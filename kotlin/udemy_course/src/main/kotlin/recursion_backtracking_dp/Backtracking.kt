package recursion_backtracking_dp

class Backtracking {
    fun startBacktracking() {
        println("Starting backtracking")

        // To find first solution place the queens on column
        Queens(4).solveNQueens()
        // To find all the solution place the queens on rows
        Queens(4).totalSolutions()

        HamiltonianCycle(listOf(listOf(0, 1, 0, 0, 0, 1), listOf(1, 0, 1, 0, 0, 0), listOf(0, 1, 0, 0, 1, 0), listOf(0, 0, 0, 0, 1, 1), listOf(0, 0, 1, 1, 0, 1), listOf(1, 0, 0, 1, 1, 0))).solveHamiltonianPath()
        HamiltonianCycle(listOf(listOf(0, 1, 0, 0), listOf(0, 0, 0, 1), listOf(1, 0, 0, 0), listOf(0, 0, 1, 0))).solveHamiltonianCycle()

        Coloring(listOf(listOf(0, 1, 0, 1), listOf(1, 0, 0, 1), listOf(1, 0, 0, 1), listOf(0, 1, 1, 0)), listOf("Red", "Green")).solveColoring()
        Coloring(listOf(listOf(0, 1, 0, 0, 0, 1), listOf(1, 0, 1, 0, 0, 0), listOf(0, 1, 0, 0, 1, 0), listOf(0, 0, 0, 0, 1, 1), listOf(0, 0, 1, 1, 0, 1), listOf(1, 0, 0, 1, 1, 0)), listOf("Red", "Green", "Blue")).solveColoring()
        Coloring(listOf(listOf(0, 1, 1, 1), listOf(1, 0, 1, 0), listOf(1, 1, 0, 1), listOf(1, 0, 1, 0)), listOf("Red", "Green", "Blue")).solveColoring()
    }

    class Coloring(private val graph: List<List<Int>>, private val colors: List<String>) {
        private val selectedColors = mutableListOf<String>()

        fun solveColoring() {
            if (solve(0)) {
                println("Found the solution")
                printGraphWithColors()
            } else {
                println("No solution found for the given graph and colors")
            }
        }

        private fun solve(row: Int): Boolean {
            if (row == graph.size) return true

            for (color in colors) {
                if (isValidCombination(color, row)) {
                    selectedColors.add(color)
                    if (solve(row + 1)) {
                        return true
                    }
                }
            }

            return false
        }

        private fun isValidCombination(color: String, row: Int): Boolean {
            for (i in graph[row].indices) {
                if (i >= selectedColors.size) break
                if (graph[row][i] == 1) {
                    if (selectedColors[i] != color) {
                        continue
                    }
                    return false
                }
            }

            return true
        }

        private fun printGraphWithColors() {
            println(selectedColors)
        }
    }

    class HamiltonianCycle(private val graph: List<List<Int>>) {
        private val alphabetMap = mapOf(
                0 to "A",
                1 to "B",
                2 to "C",
                3 to "D",
                4 to "E",
                5 to "F",
        )
        private val visitedNodes = mutableListOf(0)

        fun solveHamiltonianCycle() {
            if (solve(1, true)) {
                println("Hamiltonian cycle for the graph")
                printGraph()
                println("is")
                printHamiltonianPath()
            } else {
                println("Hamiltonian cycle is not possible in the given graph")
            }
        }

        fun solveHamiltonianPath() {
            if (solve(1)) {
                println("Hamiltonian path for the graph")
                printGraph()
                println("is")
                printHamiltonianPath()
            } else {
                println("Hamiltonian path is not possible in the given graph")
            }
        }

        private fun printHamiltonianPath() {
            for (i in visitedNodes) {
                print("${alphabetMap[i]}->")
            }
            println()
        }

        private fun solve(row: Int, isForCycle: Boolean = false): Boolean {
            if (row == graph.size) {
                return if (!isForCycle) true
                else {
                    val lastVertex = visitedNodes.last()
                    if (graph[lastVertex][0] == 1) {
                        visitedNodes.add(0)
                        return true
                    }
                    return false
                }
            }

            for (col in 1 until graph[row].size) {
                if (isConnected(row, col)) {
                    visitedNodes.add(col)
                    if (solve(row + 1, isForCycle)) {
                        return true
                    }
                    visitedNodes.removeAt(visitedNodes.size - 1)
                }
            }

            return false
        }

        private fun isConnected(row: Int, col: Int): Boolean {
            if (graph[visitedNodes[row - 1]][col] == 0) return false

            if (visitedNodes.contains(col)) return false
//            for (i in 0 until row) {
//                if (visitedNodes[i] == col) return false
//            }

            return true
        }

        private fun printGraph() {
            for (i in graph.indices) {
                for (j in graph[i].indices) {
                    print("${graph[i][j]} ")
                }
                println()
            }
        }
    }

    class Queens(private val queens: Int) {
        private var chessTable = List(queens) { _ -> MutableList<Int?>(queens) { _ -> null } }
        private var totalSolutions = 0

        fun totalSolutions(): Int {
            return if (solve(0)) {
                chessTable = List(queens) { _ -> MutableList(queens) { _ -> null } }
                solveRow(0)
                totalSolutions
            } else {
                println("$queens - Queens problem can't be solved.")
                0
            }
        }

        private fun solveRow(row: Int) {
            if (row == chessTable.size) {
                ++totalSolutions
                printChessBoard()
                println(totalSolutions)
                return
            }

            for (col in chessTable[row].indices) {
                if (isValidPosition(row, col)) {
                    chessTable[row][col] = 1
                    solveRow(row + 1)
                    chessTable[row][col] = null
                }
            }
        }

        fun solveNQueens() {
            if (solve(0)) {
                printChessBoard()
            } else {
                println("$queens - Queens problem can't be solved.")
            }
        }

        private fun solve(col: Int): Boolean {
            if (col == chessTable.size) return true

            for (row in chessTable.indices) {
                if (isValidPosition(row, col)) {
                    chessTable[row][col] = 1

                    if (solve(col + 1)) {
                        return true
                    }

                    chessTable[row][col] = null
                }
            }

            return false
        }

        private fun isValidPosition(row: Int, col: Int): Boolean {
            for (i in chessTable.indices) {
                if (chessTable[row][i] == 1) return false
            }

            for (i in chessTable.indices) {
                if (chessTable[i][col] == 1) return false
            }

            var tempC1 = col
            for (i in row downTo 0) {
                if (tempC1 < 0) break
                if (chessTable[i][tempC1--] == 1) return false
            }
            var tempC2 = col
            for (i in row downTo 0) {
                if (tempC2 == chessTable.size) break
                if (chessTable[i][tempC2++] == 1) return false
            }

            var tempR1 = row
            for (i in col downTo 0) {
                if (tempR1 == chessTable.size) break
                if (chessTable[tempR1++][i] == 1) return false
            }
            var tempR2 = row
            for (i in col until chessTable.size) {
                if (tempR2 == chessTable.size) break
                if (chessTable[tempR2++][i] == 1) return false
            }

            return true
        }

        private fun printChessBoard() {
            for (i in chessTable.indices) {
                for (j in chessTable[i].indices) {
                    val value = if (chessTable[i][j] == null) "---" else "-Q-"
                    print("$value ")
                }
                println()
            }
        }
    }
}