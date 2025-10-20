package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_TURNS
import com.pradyotprakash.futuresugoroku.DiceToDoor
import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class GameScreenContent(
    val players: List<Player> = emptyList(),
    val rooms: List<List<Room>> = emptyList(),
    val gameStatus: GameStatus = GameStatus.Start,
    val remainingRoomTurns: List<RoomCoordinate> = emptyList(),
    val selectedRoomCoordinate: RoomCoordinate? = null,
    val currentTurnDetails: CurrentTurnDetails = CurrentTurnDetails(),
) {
    val selectedRoomDices: List<DiceToDoor>?
        get() = currentTurnDetails.currentRollDice.filter { it.first == selectedRoomCoordinate }
            .takeIf {
                it.isNotEmpty()
            }?.map { it.second }

    val remainingTurns: Int
        get() = NUMBER_OF_TURNS - currentTurnDetails.currentTurn
}
