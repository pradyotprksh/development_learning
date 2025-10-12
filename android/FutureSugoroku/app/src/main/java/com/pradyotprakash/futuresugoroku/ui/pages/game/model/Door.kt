package com.pradyotprakash.futuresugoroku.ui.pages.game.model

import com.pradyotprakash.futuresugoroku.RoomCoordinate

data class Door(
    val belongToRoom: RoomCoordinate,
    val nextRoom: RoomCoordinate,
)
