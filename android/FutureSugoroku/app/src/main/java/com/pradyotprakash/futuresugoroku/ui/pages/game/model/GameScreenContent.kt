package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_TURNS
import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class GameScreenContent(
    val players: List<Player> = emptyList(),
    val rooms: List<List<Room>> = emptyList(),
    val gameStatus: GameStatus = GameStatus.Start,
    val currentTurn: Int = 0,
    val remainingRoomTurns: List<RoomCoordinate> = emptyList(),
    val selectedRoomCoordinate: RoomCoordinate? = null,
    val rollDice: List<Int>? = null,
) {
    val remainingTurns: Int
        get() = NUMBER_OF_TURNS - currentTurn
}
