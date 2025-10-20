package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class RoomCoordinate(
    val name: String,
    val number: Int,
) {
    val humanReadableName: String
        get() = "$name$number"
}
