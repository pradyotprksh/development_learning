package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state

import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame

data class BridgeGameState(
    val bridgeGame: BridgeGame = BridgeGame(isOffline = true, isOfflineInfinite = false),
    val glasses: List<GlassState> = emptyList(),
    val players: List<PlayerState> = emptyList(),
    val isGameStarted: Boolean = false,
    val isGameFinished: Boolean = false,
    val gameTimeValue: Int = 0,
    val currentPlayer: Int = 0,
    val glassPageNumber: Int = 1,
    val showGameTime: Boolean = false,
    val showGameScore: Boolean = false,
    val isInfiniteGame: Boolean = false,
    val updateGameBlinker: Boolean = true,
    val gameScore: Int = 0,
) {
    val timeDetails: List<List<Int>>
        get() {
            val minutes = gameTimeValue / 60
            val remainingSeconds = gameTimeValue % 60

            val minutesString = minutes.toString().padStart(2, '0')
            val minuteFirst = minutesString[0].toString().toInt()
            val minuteSecond = minutesString[1].toString().toInt()

            val secondsString = remainingSeconds.toString().padStart(2, '0')
            val secondFirst = secondsString[0].toString().toInt()
            val secondSecond = secondsString[1].toString().toInt()

            return listOf(
                convertDigitToTimerList(minuteFirst),
                convertDigitToTimerList(minuteSecond),
                convertDigitToTimerList(secondFirst),
                convertDigitToTimerList(secondSecond),
            )
        }

    private fun convertDigitToTimerList(digit: Int): List<Int> = when (digit) {
        0 -> listOf(1, 1, 1, 1, 1, 1, 0)
        1 -> listOf(0, 1, 1, 0, 0, 0, 0)
        2 -> listOf(1, 1, 0, 1, 1, 0, 1)
        3 -> listOf(1, 1, 1, 1, 0, 0, 1)
        4 -> listOf(0, 1, 1, 0, 0, 1, 1)
        5 -> listOf(1, 0, 1, 1, 0, 1, 1)
        6 -> listOf(0, 0, 1, 1, 1, 1, 1)
        7 -> listOf(1, 1, 1, 0, 0, 0, 0)
        8 -> listOf(1, 1, 1, 1, 1, 1, 1)
        9 -> listOf(1, 1, 1, 0, 0, 1, 1)
        else -> listOf(0, 0, 0, 0, 0, 0, 0)
    }

    val winnerPlayers: List<PlayerState>
        get() = if (isInfiniteGame) emptyList() else players.filter { it.showInTheWinnerArena }

    val inArenaPlayers: List<PlayerState>
        get() = players.filter { it.showInTheArena }

    val currentPlayerDetails: PlayerState
        get() = playerDetailsByIndex(if (isInfiniteGame) 0 else currentPlayer)

    fun playerDetailsByIndex(index: Int) =
        players.first { it.playerNumber == if (isInfiniteGame) 0 else index }

    fun humanPlayerDetailsByIndex(index: Int) =
        players.first { it.playerNumber == (if (isInfiniteGame) 0 else index) && it.isThePlayer }

    fun glassDetailsByIndex(index: Int) = glasses.first { it.glassNumber == index }
}
