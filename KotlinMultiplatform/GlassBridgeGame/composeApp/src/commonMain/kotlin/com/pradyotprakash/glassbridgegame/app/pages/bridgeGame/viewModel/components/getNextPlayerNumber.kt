package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.BridgeGameState

fun getNextPlayerNumber(updatedState: BridgeGameState, isPlayerDead: Boolean): Int {
    val nextPlayerNumber = updatedState.currentPlayer
    updatedState.players.filter { it.showInTheArena }.forEach { player ->
        val firstGlass = updatedState.glassDetailsByIndex(0)
        val secondGlass = updatedState.glassDetailsByIndex(1)

        if (firstGlass.playerNumber == -1 && secondGlass.playerNumber == -1) {
            return player.playerNumber
        } else if (firstGlass.isBroken && secondGlass.playerNumber == -1) {
            return player.playerNumber
        } else if (secondGlass.isBroken && firstGlass.playerNumber == -1) {
            return player.playerNumber
        }
    }

    updatedState.players.filter { !it.isDead && !it.showInTheArena && !it.showInTheWinnerArena }
        .sortedByDescending { it.playerNumber }.forEach { player ->
            val currentGlass =
                updatedState.glassDetailsByIndex(player.glassNumber)

            val nextFirstGlass = if (currentGlass.glassNumber % 2 == 0) {
                updatedState.glasses.firstOrNull { it.glassNumber == currentGlass.glassNumber + 2 }
            } else {
                updatedState.glasses.firstOrNull { it.glassNumber == currentGlass.glassNumber + 1 }
            }

            if (nextFirstGlass == null) {
                return player.playerNumber
            }

            val nextSecondGlass =
                updatedState.glassDetailsByIndex(nextFirstGlass.glassNumber + 1)

            if (nextFirstGlass.playerNumber == -1 && nextSecondGlass.playerNumber == -1) {
                return player.playerNumber
            } else if (nextFirstGlass.isBroken && nextSecondGlass.playerNumber == -1) {
                return player.playerNumber
            } else if (nextSecondGlass.isBroken && nextFirstGlass.playerNumber == -1) {
                return player.playerNumber
            }
        }

    updatedState.players.filter { !it.isDead && !it.showInTheArena && !it.showInTheWinnerArena }
        .sortedBy { it.playerNumber }.forEach { player ->
            return player.playerNumber
        }

    return if (isPlayerDead) nextPlayerNumber + 1 else nextPlayerNumber
}