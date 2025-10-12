package com.pradyotprakash.futuresugoroku.ui.pages.game.screen

import androidx.lifecycle.ViewModel
import com.pradyotprakash.futuresugoroku.Constants.EXIT_ROOM_POSITION
import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_ROOMS
import com.pradyotprakash.futuresugoroku.Constants.RANDOM_PENALTY
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Penalty
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import kotlin.random.Random

class GameViewModel : ViewModel() {
    init {
        setupGame()
    }

    private fun setupGame() {
        val startRoom = Random.nextInt(0, NUMBER_OF_ROOMS)
        val exitRoom = getExitRoom(startRoom)

        val rooms = getRoomsDetails(startRoom, exitRoom)

        print(rooms)
    }

    private fun getRoomsDetails(startRoom: Int, exitRoom: Int): List<Room> {
        val rooms = mutableListOf<Room>()

        val rowName = listOf("A", "B", "C", "D", "E")
        val colName = listOf(1, 2, 3, 4, 5)
        var roomNumber = 0

        for (col in 0 until 5) {
            for (row in 0 until 5) {
                rooms.add(
                    Room(
                        position = roomNumber,
                        coordinates = Pair(
                            first = rowName[row],
                            second = colName[col]
                        ),
                        players = emptyList(),
                        isStart = startRoom == roomNumber,
                        containsExitDoor = exitRoom == roomNumber,
                        penalty = Penalty(
                            scoreDeduction = if (Random.nextBoolean()) {
                                RANDOM_PENALTY.random()
                            } else {
                                0
                            },
                        ),
                        dice = emptyList(),
                        doors = emptyList(),
                    )
                )
                ++roomNumber
            }
        }

        return rooms
    }

    private fun getExitRoom(startRoom: Int): Int {
        var randomRoom = startRoom
        while (randomRoom == startRoom) {
            randomRoom = EXIT_ROOM_POSITION.random()
        }
        return randomRoom
    }
}