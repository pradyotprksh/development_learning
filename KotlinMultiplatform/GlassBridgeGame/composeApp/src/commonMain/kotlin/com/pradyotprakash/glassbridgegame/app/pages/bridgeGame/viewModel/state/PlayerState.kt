package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state

import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_GLASSES

data class PlayerState(
    val isBot: Boolean,
    val glassNumber: Int,
    val isDead: Boolean,
    val name: String,
    val isThePlayer: Boolean,
    val playerNumber: Int,
) {
    val showInTheArena: Boolean
        get() = !isDead && glassNumber == -1


    val showInTheWinnerArena: Boolean
        get() = !isDead && glassNumber >= NUMBER_OF_GLASSES
}
