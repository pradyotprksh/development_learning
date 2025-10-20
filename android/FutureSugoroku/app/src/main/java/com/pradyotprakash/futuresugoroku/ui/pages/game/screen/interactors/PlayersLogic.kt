package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants.NUMBER_OF_PLAYERS
import com.pradyotprakash.futuresugoroku.Constants.PLAYER_NAMES
import com.pradyotprakash.futuresugoroku.Constants.START_PLAYER_POINTS
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.Player
import com.pradyotprakash.futuresugoroku.ui.pages.game.model.PlayerStatus
import kotlin.random.Random

interface PlayersLogic {
    fun getPlayers(
        startCoordinates: Pair<String, Int>,
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