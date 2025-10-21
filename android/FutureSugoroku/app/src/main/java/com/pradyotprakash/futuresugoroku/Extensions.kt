package com.pradyotprakash.futuresugoroku

import com.pradyotprakash.futuresugoroku.ui.pages.game.model.CurrentTurnDetails
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.DiceToDoor
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Door
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.GameScreenContent
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Penalty
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerToRoom
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Room
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomCoordinate
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomToDice

fun GameScreenContent.toJson(indent: String = ""): String {
    val nextIndent = indent + "  "

    fun Any?.toJsonValue(subIndent: String = nextIndent): String = when (this) {
        null -> "null"
        is String -> "\"$this\""
        is Number, is Boolean -> this.toString()
        is PlayerStatus -> "\"${this.name}\""
        is RoomCoordinate -> "{${subIndent}\"name\": \"${this.name}\",${subIndent}\"number\": ${this.number}$indent}"
        is Penalty -> "{${subIndent}\"scoreDeduction\": ${this.scoreDeduction}$indent}"
        is Door -> "{${subIndent}\"nextRoom\": ${this.nextRoom.toJsonValue(subIndent + "  ")}$indent}"
        is Room -> "{" +
                listOf(
                    "${subIndent}\"position\": ${this.position}",
                    "${subIndent}\"coordinates\": ${this.coordinates.toJsonValue(subIndent + "  ")}",
                    "${subIndent}\"isStart\": ${this.isStart}",
                    "${subIndent}\"containsExitDoor\": ${this.containsExitDoor}",
                    "${subIndent}\"penalty\": ${this.penalty.toJsonValue(subIndent + "  ")}",
                    "${subIndent}\"numberOfDice\": ${this.numberOfDice}",
                    "${subIndent}\"doors\": [" + this.doors.joinToString(",") {
                        it.toJsonValue(
                            subIndent + "    "
                        )
                    } + "${subIndent}]",
                    "${subIndent}\"isRedRoom\": ${this.isRedRoom}",
                    "${subIndent}\"cameFromRoom\": ${this.cameFromRoom.toJsonValue(subIndent + "  ")}"
                ).joinToString(",") +
                "$indent}"

        is Player -> "{" +
                listOf(
                    "${subIndent}\"name\": \"${this.name}\"",
                    "${subIndent}\"roomPosition\": ${this.roomPosition.toJsonValue(subIndent + "  ")}",
                    "${subIndent}\"status\": \"${this.status}\"",
                    "${subIndent}\"score\": ${this.score}"
                ).joinToString(",") +
                "$indent}"

        is PlayerToRoom -> "{" +
                listOf(
                    "${subIndent}\"fromRoomCoordinate\": ${
                        this.fromRoomCoordinate.toJsonValue(
                            subIndent + "  "
                        )
                    }",
                    "${subIndent}\"name\": \"${this.name}\"",
                    "${subIndent}\"toRoomCoordinate\": ${this.toRoomCoordinate.toJsonValue(subIndent + "  ")}"
                ).joinToString(",") +
                "$indent}"

        is DiceToDoor -> "{${subIndent}\"diceValue\": ${this.dice},${subIndent}\"door\": ${
            this.toRoomCoordinate.toJsonValue(
                subIndent + "  "
            )
        }$indent}"

        is RoomToDice -> "{${subIndent}\"roomCoordinate\": ${
            this.roomCoordinate.toJsonValue(
                subIndent + "  "
            )
        },${subIndent}\"diceToDoor\": ${this.diceToDoor.toJsonValue(subIndent + "  ")}$indent}"

        is CurrentTurnDetails -> "{" +
                listOf(
                    "${subIndent}\"playersToRoom\": [" + this.playersToRoom.joinToString(",") {
                        it.toJsonValue(
                            subIndent + "    "
                        )
                    } + "${subIndent}]",
                    "${subIndent}\"currentDiceRolls\": [" + this.currentDiceRolls.joinToString(",") {
                        it.toJsonValue(
                            subIndent + "    "
                        )
                    } + "${subIndent}]",
                    "${subIndent}\"currentTurn\": ${this.currentTurn}",
                    "${subIndent}\"roomsTurns\": [" + this.roomsTurns.joinToString(",") {
                        it.toJsonValue(
                            subIndent + "    "
                        )
                    } + "${subIndent}]"
                ).joinToString(",") +
                "$indent}"

        is List<*> -> "[" + this.joinToString(",") { it.toJsonValue(subIndent + "  ") } + "$indent]"
        else -> "\"$this\""
    }

    return "{" + listOf(
        "${nextIndent}\"players\": ${players.toJsonValue(nextIndent + "  ")}",
        "${nextIndent}\"rooms\": ${rooms.toJsonValue(nextIndent + "  ")}",
        "${nextIndent}\"selectedRoomCoordinate\": ${selectedRoomCoordinate.toJsonValue(nextIndent + "  ")}",
        "${nextIndent}\"currentTurnDetails\": ${currentTurnDetails.toJsonValue(nextIndent + "  ")}",
        "${nextIndent}\"exitRoomFound\": ${exitRoomFound}"
    ).joinToString(",") + "}"
}
