package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_TURNS
import com.pradyotprakash.futuresugoroku.toJson

data class GameScreenContent(
    val players: List<Player> = emptyList(),
    val rooms: List<List<Room>> = emptyList(),
    val selectedRoomCoordinate: RoomCoordinate? = null,
    val currentTurnDetails: CurrentTurnDetails = CurrentTurnDetails(),
    val exitRoomFound: Boolean = false,
) {
    val gameStatus: GameStatus
        get() = if (remainingTurns > 0 && players.any { it.status == PlayerStatus.Playing })
            GameStatus.InProgress
        else GameStatus.End

    val selectedRoomDices: List<DiceToDoor>?
        get() = currentTurnDetails.currentDiceRolls.filter { it.roomCoordinate == selectedRoomCoordinate }
            .takeIf {
                it.isNotEmpty()
            }?.map { it.diceToDoor }

    val selectedRoomPlayerSelection: List<PlayerToRoom>
        get() = currentTurnDetails.playersToRoom.filter { playersToRoom ->
            players.filter { player ->
                player.roomPosition == selectedRoomCoordinate
            }.map { it.name }.contains(playersToRoom.name)
        }

    val remainingTurns: Int
        get() = (NUMBER_OF_TURNS - currentTurnDetails.currentTurn).takeIf { it >= 0 } ?: 0

    override fun toString() = toJson()
}
