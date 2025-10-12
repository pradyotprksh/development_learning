package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Door

interface DoorsLogic {
    fun getDoors(
        coordinates: Pair<String, Int>,
        containsExitDoor: Boolean,
    ): List<Door> {
        val columns = Constants.roomColName
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
}