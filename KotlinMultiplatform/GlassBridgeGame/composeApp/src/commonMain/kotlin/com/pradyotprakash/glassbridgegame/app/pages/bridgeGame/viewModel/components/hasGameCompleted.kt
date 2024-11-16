package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.BridgeGameState

fun hasGameCompleted(
    newBridgeState: BridgeGameState?,
    currentState: BridgeGameState,
): Boolean {
    val toCheckBridgeState = newBridgeState ?: currentState

    return if (toCheckBridgeState.isInfiniteGame) {
        toCheckBridgeState.players.none {
            !it.isDead
        }
    } else {
        toCheckBridgeState.players.none {
            (it.showInTheArena || !it.showInTheWinnerArena) && !it.isDead
        }
    }
}