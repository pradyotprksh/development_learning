package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.DiceToDoor
import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class CurrentTurnDetails(
    val playerToRoom: List<Pair<String, RoomCoordinate>> = emptyList(),
    val currentRollDice: List<Pair<RoomCoordinate, DiceToDoor>> = emptyList(),
    val currentTurn: Int = 0,
)
