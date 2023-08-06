package recursion_backtracking_dp

class Backtracking {
    fun startBacktracking() {
        println("Starting backtracking")

        // To find first solution place the queens on column
        Queens(4).solveNQueens()
        // To find all the solution place the queens on rows
        Queens(4).totalSolutions()
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