package com.pradyotprakash.futuresugoroku.ui.pages.game.screen.interactors

import com.pradyotprakash.futuresugoroku.Constants.RANDOM_NAMES
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
            RANDOM_NAMES.random(
                Random(10)
            ).length
        ) {
            players.add(
                Player(
                    name = RANDOM_NAMES[it],
                    roomPosition = startCoordinates,
                    status = PlayerStatus.Playing,
                    score = START_PLAYER_POINTS,
                )
            )
        }

        return players
    }
}