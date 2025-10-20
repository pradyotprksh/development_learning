package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class Room(
    val position: Int,
    val coordinates: RoomCoordinate,
    val isStart: Boolean,
    val containsExitDoor: Boolean,
    val penalty: Penalty,
    val numberOfDice: Int,
    val doors: List<Door>,
    val isRedRoom: Boolean,
    val cameFromRoom: Room?,
)
