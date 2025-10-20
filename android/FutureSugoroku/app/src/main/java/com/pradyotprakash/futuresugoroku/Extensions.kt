package com.pradyotprakash.futuresugoroku

val RoomCoordinate.roomHumanReadable: String
    get() = "$first$second"

val DiceToDoor.diceHumanReadable: String
    get() = "$first - ${second.roomHumanReadable}"