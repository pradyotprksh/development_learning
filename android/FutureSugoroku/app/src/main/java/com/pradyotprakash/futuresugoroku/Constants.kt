package com.pradyotprakash.futuresugoroku

object Constants {
    const val MAX_ROW = 5
    const val MAX_COL = MAX_ROW
    const val NUMBER_OF_ROOMS = MAX_ROW * MAX_COL
    const val NUMBER_OF_PLAYERS = 10
    const val NUMBER_OF_TURNS = 15
    const val START_PLAYER_POINTS = 15
    const val EXIT_DOOR = "EXIT_DOOR"
    val roomRowName = listOf(1, 2, 3, 4, 5)
    val roomColName = listOf("A", "B", "C", "D", "E")
    val RANDOM_PENALTY = listOf(
        1,
        2,
        4,
    )
    val EXIT_ROOM_POSITION = listOf(
        0,
        1,
        2,
        3,
        4,
        5,
        9,
        10,
        14,
        15,
        19,
        20,
        24,
    )
    val PLAYER_NAMES = listOf(
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "K",
        "L"
    )
}