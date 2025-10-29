package com.pradyotprakash.futuresugoroku.ui.pages.game.screen

import androidx.lifecycle.ViewModel
import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.Constants.EXIT_DOOR
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.CurrentTurnDetails
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.DiceToDoor
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameScreenContent
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomCoordinate
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors.DiceLogic
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors.PlayersLogic
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors.RoomsLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GameViewModel : ViewModel(), RoomsLogic, PlayersLogic, DiceLogic {
    private val _gameState = MutableStateFlow(GameScreenContent())
    val gameState = _gameState.asStateFlow()

    init {
        setupGame()
    }

    private fun setupGame() {
        val startRoom = Random.Default.nextInt(0, Constants.NUMBER_OF_ROOMS)
        val exitRoom = getExitRoom(startRoom)

        val rooms = getRoomsDetails(startRoom, exitRoom)
        val startRoomCoordinate = rooms.flatten().first { it.isStart }.coordinates
        val players = getPlayers(
            startCoordinates = rooms.flatten().first { it.isStart }.coordinates
        )

        _gameState.value = GameScreenContent(
            players = players,
            rooms = rooms,
            currentTurnDetails = CurrentTurnDetails(
                roomsTurns = listOf(
                    startRoomCoordinate,
                ),
            ),
        )
    }

    fun getPlayersIn(roomCoordinate: RoomCoordinate) = _gameState.value.players.filter {
        it.roomPosition == roomCoordinate
    }

    fun numberOfPlayerIn(roomCoordinate: RoomCoordinate) = getPlayersIn(roomCoordinate).size

    fun toggleRoomGameSheet(roomCoordinate: RoomCoordinate?) {
        _gameState.update { state ->
            state.copy(
                selectedRoomCoordinate = roomCoordinate,
            )
        }
    }

    fun getRoomDetails(roomCoordinate: RoomCoordinate) = _gameState.value.rooms.flatten().first {
        it.coordinates == roomCoordinate
    }

    fun getSelectedRoomDetails(): Room {
        val details = _gameState.value.selectedRoomCoordinate?.let {
            getRoomDetails(it)
        }
        require(details != null)
        return details
    }

    fun getSelectedRoomPlayers() = _gameState.value.selectedRoomCoordinate?.let {
        getPlayersIn(it)
    } ?: emptyList()

    fun performDiceRoll() {
        val selectedRoom = getSelectedRoomDetails()
        _gameState.update {
            it.copy(
                currentTurnDetails = it.currentTurnDetails.copy(
                    currentDiceRolls = performDiceRoll(
                        selectedRoom = selectedRoom,
                        currentDiceRolls = _gameState.value.currentTurnDetails.currentDiceRolls,
                    ),
                )
            )
        }
    }

    fun performPlayerDoorSelection(player: Player, door: DiceToDoor) {
        with(_gameState.value.currentTurnDetails) {
            _gameState.update {
                it.copy(
                    currentTurnDetails = it.currentTurnDetails.copy(
                        playersToRoom = performPlayerDoorSelection(
                            currentTurnDetails = this,
                            selectedRoomCoordinate = _gameState.value.selectedRoomCoordinate,
                            player = player,
                            door = door,
                            selectedRoomDices = _gameState.value.selectedRoomDices,
                        ),
                    )
                )
            }
        }
    }

    private fun roomHasExitDoor(room: Room) =
        room.doors.first().nextRoom.name == EXIT_DOOR

    fun checkAndCompleteCurrentTurn() {
        if (isSelectionProcessStarted()) {
            val playerNewRooms = _gameState.value.players.toMutableList().map { player ->
                val selection =
                    _gameState.value.currentTurnDetails.playersToRoom.firstOrNull { playerToRoom ->
                        playerToRoom.name == player.name
                    }?.toRoomCoordinate
                if (selection != null) {
                    val selectionRoomDetails = getRoomDetails(selection)
                    val selectionContainsExitDoor = roomHasExitDoor(selectionRoomDetails)
                    val roomPenalty = selectionRoomDetails.penalty
                    val newScore = player.score.minus(roomPenalty.scoreDeduction)
                    val playerStatus = if (selectionContainsExitDoor) {
                        PlayerStatus.Won
                    } else if (newScore <= 0) {
                        PlayerStatus.Dead
                    } else {
                        PlayerStatus.Playing
                    }

                    player.copy(
                        roomPosition = selection,
                        score = player.score.minus(roomPenalty.scoreDeduction),
                        status = playerStatus,
                    )
                } else {
                    player
                }
            }

            _gameState.update {
                it.copy(
                    currentTurnDetails = it.currentTurnDetails.copy(
                        playersToRoom = emptyList(),
                        currentDiceRolls = emptyList(),
                        currentTurn = it.currentTurnDetails.currentTurn.plus(1),
                        roomsTurns = it.currentTurnDetails.playersToRoom.map { player ->
                            player.toRoomCoordinate
                        }.toSet().toList().filter { roomCoordinate ->
                            !roomHasExitDoor(
                                getRoomDetails(roomCoordinate)
                            )
                        },
                    ),
                    players = playerNewRooms,
                    exitRoomFound = playerNewRooms.any { player -> player.status == PlayerStatus.Won },
                )
            }
        }
    }

    fun isSelectionProcessStarted(): Boolean {
        with(_gameState.value.currentTurnDetails) {
            if (roomsTurns.isEmpty()) {
                return false
            } else {
                roomsTurns.forEach { roomTurns ->
                    val players = playersToRoom.filter {
                        it.fromRoomCoordinate == roomTurns
                    }
                    val dices = currentDiceRolls.filter {
                        it.roomCoordinate == roomTurns
                    }

                    if (players.isEmpty() || dices.isEmpty()) {
                        return false
                    }
                }
            }
        }
        return true
    }
}