package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_TURNS

data class GameScreenContent(
    val players: List<Player> = emptyList(),
    val rooms: List<List<Room>> = emptyList(),
    val gameStatus: GameStatus = GameStatus.Start,
    val selectedRoomCoordinate: RoomCoordinate? = null,
    val currentTurnDetails: CurrentTurnDetails = CurrentTurnDetails(),
) {
    val selectedRoomDices: List<DiceToDoor>?
        get() = currentTurnDetails.currentRollDice.filter { it.roomCoordinate == selectedRoomCoordinate }
            .takeIf {
                it.isNotEmpty()
            }?.map { it.diceToDoor }

    val remainingTurns: Int
        get() = NUMBER_OF_TURNS - currentTurnDetails.currentTurn
}
