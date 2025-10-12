package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.viewModel

import androidx.lifecycle.ViewModel
import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.Constants.RANDOM_NAMES
import com.pradyotprakash.futuresugoroku.Constants.START_PLAYER_POINTS
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Door
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameScreenContent
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Penalty
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import kotlin.random.Random

class GameViewModel : ViewModel(), RoomsLogic, PlayersLogic {
    init {
        setupGame()
    }

    private fun setupGame() {
        val startRoom = Random.Default.nextInt(0, Constants.NUMBER_OF_ROOMS)
        val exitRoom = getExitRoom(startRoom)

        val rooms = getRoomsDetails(startRoom, exitRoom)
        val players = getPlayers(
            startCoordinates = rooms.flatten().first { it.isStart }.coordinates
        )

        val gameContent = GameScreenContent(
            players = players,
            rooms = rooms,
            gameStatus = GameStatus.InProgress,
            currentTurn = 0,
        )
    }
}