package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState
import com.pradyotprakash.glassbridgegame.utils.NUMBER_OF_PLAYERS
import kotlin.random.Random

fun getOfflinePlayers(): List<PlayerState> {
    val players = mutableListOf<PlayerState>()
    val thePlayerNumber = Random.nextInt(1, NUMBER_OF_PLAYERS)
    players.add(
        PlayerState(
            isBot = false,
            glassNumber = -1,
            isDead = false,
            name = "Pradyot",
            isThePlayer = true,
            playerNumber = thePlayerNumber,
        )
    )
    for (i in 0..<NUMBER_OF_PLAYERS) {
        if (i == thePlayerNumber) {
            continue
        }

        players.add(
            PlayerState(
                isBot = true,
                glassNumber = -1,
                isDead = false,
                name = "Bot $i",
                isThePlayer = false,
                playerNumber = i,
            )
        )
    }
    players.sortBy { it.playerNumber }

    return players
}