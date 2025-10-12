package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class GameScreenContent(
    val players: List<Player>,
    val rooms: List<Room>,
    val gameStatus: GameStatus,
    val currentTurn: Int,
)
