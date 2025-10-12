package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class Room(
    val position: Int,
    val coordinates: RoomCoordinate,
    val players: List<Player>,
    val isStart: Boolean,
    val containsExitDoor: Boolean,
    val penalty: Penalty,
    val dice: List<Dice>,
    val doors: List<Door>,
)
