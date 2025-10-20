package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class CurrentTurnDetails(
    val playerToRoom: List<PlayerToRoom> = emptyList(),
    val currentRollDice: List<RoomToDice> = emptyList(),
    val currentTurn: Int = 0,
)
