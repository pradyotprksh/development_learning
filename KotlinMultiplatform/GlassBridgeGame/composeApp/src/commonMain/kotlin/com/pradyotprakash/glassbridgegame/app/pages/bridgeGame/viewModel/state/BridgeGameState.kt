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

    val winnerPlayer: List<PlayerState>
        get() = players.filter { it.showInTheWinnerArena }

    val inArenaPlayer: List<PlayerState>
        get() = players.filter { it.showInTheArena }
}
