package com.pradyotprakash.futuresugoroku.ui.pages.game.screen

import androidx.lifecycle.ViewModel
import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.CurrentTurnDetails
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.DiceToDoor
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameScreenContent
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
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
            gameStatus = GameStatus.InProgress,
            currentTurnDetails = CurrentTurnDetails(
                remainingRoomTurns = listOf(
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

    fun getSelectedRoomDetails() = _gameState.value.rooms.flatten().first {
        it.coordinates == _gameState.value.selectedRoomCoordinate
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
}