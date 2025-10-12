package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class Player(
    val name: String,
    val roomPosition: RoomCoordinate,
    val status: PlayerStatus,
)