package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Penalty
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomCoordinate
import kotlin.random.Random

interface RoomsLogic : DoorsLogic {
    fun getRoomsDetails(startRoom: Int, exitRoom: Int): List<List<Room>> {
        val rooms = mutableListOf<List<Room>>()

        var roomNumber = 0

        for (row in 0 until Constants.MAX_COL) {
            val columnRooms = mutableListOf<Room>()
            for (col in 0 until Constants.MAX_ROW) {
                val coordinates = RoomCoordinate(
                    name = Constants.roomColName[col],
                    number = Constants.roomRowName[row]
                )
                val containsExitDoor = exitRoom == roomNumber
                val isStart = startRoom == roomNumber
                val doors = getDoors(
                    coordinates,
                    containsExitDoor,
                )

                val room = Room(
                    position = roomNumber,
                    coordinates = coordinates,
                    isStart = isStart,
                    containsExitDoor = containsExitDoor,
                    penalty = getPenalty(
                        isStart = isStart,
                        containsExitDoor = containsExitDoor,
                    ),
                    numberOfDice = doors.size,
                    doors = doors,
                    isRedRoom = false,
                    cameFromRoom = null,
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

    fun getExitRoom(startRoom: Int): Int {
        var randomRoom = startRoom
        while (randomRoom == startRoom) {
            randomRoom = Constants.EXIT_ROOM_POSITION.random()
        }
        return randomRoom
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
}