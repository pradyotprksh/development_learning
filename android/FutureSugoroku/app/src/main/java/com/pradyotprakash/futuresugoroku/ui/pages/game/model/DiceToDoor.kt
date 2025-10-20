package com.pradyotprakash.futuresugoroku.ui.pages.game.model

data class DiceToDoor(
    val dice: Int,
    val toRoomCoordinate: RoomCoordinate,
) {
    val humanReadableName: String
        get() = "$dice - ${toRoomCoordinate.humanReadableName}"
}
