package com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.components

import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.GlassState
import com.pradyotprakash.glassbridgegame.app.pages.bridgeGame.viewModel.state.PlayerState

fun canJumpToTheSelectedGlass(
    playerDetails: PlayerState,
    currentGlassDetails: GlassState?,
    selectedGlassDetails: GlassState,
): Boolean {
    if (!playerDetails.isThePlayer) {
        return false
    }

    if (currentGlassDetails == null) {
        return selectedGlassDetails.glassNumber in 0..1
    }

    if (selectedGlassDetails.glassNumber <= currentGlassDetails.glassNumber) {
        return false
    }

    if (currentGlassDetails.glassNumber % 2 == 0) {
        if (selectedGlassDetails.glassNumber !in currentGlassDetails.glassNumber + 2..currentGlassDetails.glassNumber + 3) {
            return false
        }
    } else {
        if (selectedGlassDetails.glassNumber !in currentGlassDetails.glassNumber + 1..currentGlassDetails.glassNumber + 2) {
            return false
        }
    }

    if (selectedGlassDetails.playerNumber != -1) {
        return false
    }

    return true
}