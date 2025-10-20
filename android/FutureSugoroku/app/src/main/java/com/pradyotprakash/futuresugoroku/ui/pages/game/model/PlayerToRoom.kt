package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class PlayerToRoom(
    val fromRoomCoordinate: RoomCoordinate,
    val name: String,
    val toRoomCoordinate: RoomCoordinate,
)
