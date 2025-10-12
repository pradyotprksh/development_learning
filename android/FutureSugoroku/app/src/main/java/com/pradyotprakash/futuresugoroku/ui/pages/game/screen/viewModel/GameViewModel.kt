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

class GameViewModel : ViewModel() {
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

    private fun getRoomsDetails(startRoom: Int, exitRoom: Int): List<List<Room>> {
        val rooms = mutableListOf<List<Room>>()

        var roomNumber = 0

        for (col in 0 until Constants.MAX_COL) {
            val columnRooms = mutableListOf<Room>()
            for (row in 0 until Constants.MAX_ROW) {
                val coordinates = Pair(
                    first = Constants.roomRowName[row],
                    second = Constants.roomColName[col]
                )
                val containsExitDoor = exitRoom == roomNumber
                val isStart = startRoom == roomNumber

                val room = Room(
                    position = roomNumber,
                    coordinates = coordinates,
                    isStart = isStart,
                    containsExitDoor = containsExitDoor,
                    penalty = getPenalty(
                        isStart = isStart,
                        containsExitDoor = containsExitDoor,
                    ),
                    dice = emptyList(),
                    doors = getDoors(
                        coordinates,
                        containsExitDoor,
                    ),
                )

                columnRooms.add(
                    room
                )
                ++roomNumber
            }

            rooms.add(columnRooms)
        }

        return rooms
    }

    private fun getPenalty(
        isStart: Boolean,
        containsExitDoor: Boolean,
    ) = Penalty(
        scoreDeduction = if (Random.Default.nextBoolean() && !isStart && !containsExitDoor) {
            Constants.RANDOM_PENALTY.random()
        } else {
            0
        },
    )

    private fun getPlayers(
        startCoordinates: Pair<String, Int>,
    ): List<Player> {
        val players = mutableListOf<Player>()

        repeat(
            RANDOM_NAMES.random(
                Random(10)
            ).length
        ) {
            players.add(
                Player(
                    name = RANDOM_NAMES[it],
                    roomPosition = startCoordinates,
                    status = PlayerStatus.Playing,
                    score = START_PLAYER_POINTS,
                )
            )
        }

        return players
    }

    private fun getDoors(
        coordinates: Pair<String, Int>,
        containsExitDoor: Boolean,
    ): List<Door> {
        val columns = Constants.roomRowName
        val minRow = 1
        val maxRow = 5

        if (containsExitDoor) {
            return listOf(Door(Constants.EXIT_DOOR to 0))
        }

        val doors = mutableListOf<Door>()
        val (col, row) = coordinates
        val colIndex = columns.indexOf(col)

        if (colIndex > 0) {
            doors.add(Door(columns[colIndex - 1] to row))
        }
        if (colIndex < columns.size - 1) {
            doors.add(Door(columns[colIndex + 1] to row))
        }
        if (row > minRow) {
            doors.add(Door(col to (row - 1)))
        }
        if (row < maxRow) {
            doors.add(Door(col to (row + 1)))
        }

        return doors
    }

    private fun getExitRoom(startRoom: Int): Int {
        var randomRoom = startRoom
        while (randomRoom == startRoom) {
            randomRoom = Constants.EXIT_ROOM_POSITION.random()
        }
        return randomRoom
    }
}