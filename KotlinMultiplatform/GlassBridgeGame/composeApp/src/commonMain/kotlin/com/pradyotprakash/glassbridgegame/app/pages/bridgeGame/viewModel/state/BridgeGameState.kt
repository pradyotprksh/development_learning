package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state

import com.pradyotprakash.glassbridgegame.app.navigation.BridgeGame

data class BridgeGameState(
    val bridgeGame: BridgeGame = BridgeGame(isOffline = true),
    val glasses: List<GlassState> = emptyList(),
    val players: List<PlayerState> = emptyList(),
    val isGameStarted: Boolean = false,
    val isGameFinished: Boolean = false,
    val gameTimeValue: Int = 0,
    val currentPlayer: Int = 0,
) {
    val gameTimeString: String
        get() {
            val minutes = gameTimeValue / 60
            val remainingSeconds = gameTimeValue % 60
            return "${minutes.toString().padStart(2, '0')}:${
                remainingSeconds.toString().padStart(2, '0')
            }"
        }

    val winnerPlayers: List<PlayerState>
        get() = players.filter { it.showInTheWinnerArena }

    val inArenaPlayers: List<PlayerState>
        get() = players.filter { it.showInTheArena }

    val currentPlayerDetails: PlayerState
        get() = playerDetailsByIndex(currentPlayer)

    fun playerDetailsByIndex(index: Int) = players.first { it.playerNumber == index }

    fun humanPlayerDetailsByIndex(index: Int) = players.first { it.playerNumber == index && it.isThePlayer }

    fun glassDetailsByIndex(index: Int) = glasses.first { it.glassNumber == index }
}
