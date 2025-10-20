package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class CurrentTurnDetails(
    val playersToRoom: List<PlayerToRoom> = emptyList(),
    val currentDiceRolls: List<RoomToDice> = emptyList(),
    val currentTurn: Int = 0,
    val remainingRoomTurns: List<RoomCoordinate> = emptyList(),
)
