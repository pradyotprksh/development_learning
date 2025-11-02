package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.ui.pages.game.model.DiceToDoor
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomToDice
import kotlin.random.Random

interface DiceLogic {
    fun performDiceRoll(
        selectedRoom: Room,
        currentDiceRolls: List<RoomToDice>,
    ): List<RoomToDice> {
        val diceRolls = currentDiceRolls.toMutableList()
        for (door in selectedRoom.doors) {
            if (door.nextRoom == selectedRoom.cameFromRoom) {
                continue
            }
            val roll = Random.nextInt(
                listOf(0, 1).random(),
                listOf(2, 3, 4, 5, 6).random().plus(1),
            )
            diceRolls.add(
                RoomToDice(
                    roomCoordinate = selectedRoom.coordinates,
                    diceToDoor = DiceToDoor(
                        dice = roll,
                        toRoomCoordinate = door.nextRoom,
                    )
                )
            )
        }
        return diceRolls
    }
}