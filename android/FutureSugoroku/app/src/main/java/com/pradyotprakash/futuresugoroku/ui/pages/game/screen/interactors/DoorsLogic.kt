package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Door
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomCoordinate

interface DoorsLogic {
    fun getDoors(
        coordinates: RoomCoordinate,
        containsExitDoor: Boolean,
    ): List<Door> {
        val columns = Constants.roomColName
        val minRow = 1
        val maxRow = 5

        if (containsExitDoor) {
            return listOf(
                Door(
                    RoomCoordinate(
                        name = Constants.EXIT_DOOR,
                        number = 0,
                    )
                )
            )
        }

        val doors = mutableListOf<Door>()
        val (col, row) = coordinates
        val colIndex = columns.indexOf(col)

        if (colIndex > 0) {
            doors.add(
                Door(
                    RoomCoordinate(
                        name = columns[colIndex - 1],
                        number = row,
                    )
                )
            )
        }
        if (colIndex < columns.size - 1) {
            doors.add(
                Door(
                    RoomCoordinate(
                        name = columns[colIndex + 1],
                        number = row,
                    )
                )
            )
        }
        if (row > minRow) {
            doors.add(
                Door(
                    RoomCoordinate(
                        name = col,
                        number = (row - 1),
                    )
                )
            )
        }
        if (row < maxRow) {
            doors.add(
                Door(
                    RoomCoordinate(
                        name = col,
                        number = (row + 1),
                    )
                )
            )
        }

        return doors
    }
}