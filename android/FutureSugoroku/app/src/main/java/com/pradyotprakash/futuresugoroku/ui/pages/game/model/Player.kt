package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class Player(
    val name: String,
    val roomPosition: RoomCoordinate,
    val fromRoomPosition: RoomCoordinate?,
    val status: PlayerStatus,
    val score: Int,
)