package com.pradyotprakash.futuresugoroku.ui.pages.game.screen

import androidx.lifecycle.ViewModel
import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.RoomCoordinate
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameScreenContent
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors.PlayersLogic
import com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors.RoomsLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class GameViewModel : ViewModel(), RoomsLogic, PlayersLogic {
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
            currentTurn = 0,
            remainingRoomTurns = listOf(
                startRoomCoordinate,
            ),
        )
    }

    fun numberOfPlayerIn(roomCoordinate: RoomCoordinate) = _gameState.value.players.filter {
        it.roomPosition == roomCoordinate
    }.size

    fun toggleRoomGameSheet(roomCoordinate: RoomCoordinate?) {
        _gameState.update { state ->
            state.copy(
                selectedRoomCoordinate = roomCoordinate,
                rollDice = null,
            )
        }
    }

    fun getSelectedRoomDetails() = _gameState.value.rooms.flatten().first {
        it.coordinates == _gameState.value.selectedRoomCoordinate
    }

    fun getDiceRoll() {
        val selectedRoom = getSelectedRoomDetails()

        val numberOfRoll = selectedRoom.numberOfDice - if (selectedRoom.cameFromRoom != null) {
            1
        } else {
            0
        }

        val diceRolls = mutableListOf<Int>()
        repeat(numberOfRoll) {
            diceRolls.add(
                Random.nextInt(1, 7)
            )
        }
        _gameState.update {
            it.copy(
                rollDice = diceRolls,
            )
        }
    }
}