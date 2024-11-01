package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state

data class PlayerState(
    val isBot: Boolean,
    val glassNumber: Int,
    val isDead: Boolean,
    val name: String,
    val isThePlayer: Boolean,
    val playerNumber: Int,
)
