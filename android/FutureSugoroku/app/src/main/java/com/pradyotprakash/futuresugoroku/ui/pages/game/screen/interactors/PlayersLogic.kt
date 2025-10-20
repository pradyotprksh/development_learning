package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants.PLAYER_NAMES
import com.pradyotprakash.futuresugoroku.Constants.START_PLAYER_POINTS
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.RoomCoordinate

interface PlayersLogic {
    fun getPlayers(
        startCoordinates: RoomCoordinate,
    ): List<Player> {
        val players = mutableListOf<Player>()

        repeat(
            PLAYER_NAMES.size
        ) {
            players.add(
                Player(
                    name = PLAYER_NAMES[it],
                    roomPosition = startCoordinates,
                    status = PlayerStatus.Playing,
                    score = START_PLAYER_POINTS,
                )
            )
        }

        return players
    }
}